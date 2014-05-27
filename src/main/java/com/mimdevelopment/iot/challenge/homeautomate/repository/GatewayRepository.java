package com.mimdevelopment.iot.challenge.homeautomate.repository;

import com.mimdevelopment.iot.challenge.homeautomate.dto.BarometricPayloadDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.GatewayDTO;
import com.mimdevelopment.iot.challenge.homeautomate.exception.ExceptionReasonCode;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomateException;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.model.BarometricPayload;
import com.mimdevelopment.iot.challenge.homeautomate.model.Gateway;
import com.mimdevelopment.iot.challenge.homeautomate.model.Location;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 11:46 AM
 */

public class GatewayRepository {

    public Gateway fetchByIdentifier(String gatewayIdentifier) throws HomeAutomateException {

        Gateway result;
        EntityManager entityManager = null;

        try {
            entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Gateway> cq = cb.createQuery(Gateway.class);
            Root<Gateway> gatewayRoot = cq.from(Gateway.class);
            Predicate condition = cb.and(
                    cb.equal(gatewayRoot.get("identifier").as(String.class), gatewayIdentifier),
                    cb.equal(gatewayRoot.get("active").as(Boolean.class), true)
            );
            cq.where(condition);
            TypedQuery<Gateway> query = entityManager.createQuery(cq);
            result = query.getSingleResult();
            entityManager.detach(result);
        } catch (NonUniqueResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.NON_UNIQUE_GATEWAY_USER);
        } catch (NoResultException ex) {
            result = null;
        } catch (Exception ex) {
            throw new HomeAutomateException(ex.getMessage(), ExceptionReasonCode.UNKNOWN);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    public void processBarometricPayloadSubmission
            (List<BarometricPayloadDTO> barometricPayloadTemplates)
            throws HomeAutomateException, HomeAutomatePropertyValidationException {

        if (barometricPayloadTemplates == null) {
            throw new IllegalArgumentException("The barometricPayloadTemplates argument can not be null.");
        }

        EntityManager entityManager = null;

        try {
            entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();
            entityManager.getTransaction().begin();

            for (BarometricPayloadDTO barometricPayloadTemplate : barometricPayloadTemplates) {
                try {
                    barometricPayloadTemplate.validate();

                    Gateway gateway = entityManager.find(Gateway.class,
                            barometricPayloadTemplate.getGateway().getIdentity());

                    if (gateway == null) {
                        throw new HomeAutomateException(ExceptionReasonCode.UNKNOWN_GATEWAY);
                    }

                    BarometricPayload barometricPayload = new BarometricPayload();
                    barometricPayload.setGateway(gateway);
                    barometricPayload.setDate(barometricPayloadTemplate.getDate());
                    barometricPayload.setPressure(barometricPayloadTemplate.getPressure());
                    barometricPayload.setTemperature(barometricPayloadTemplate.getTemperature());
                    barometricPayload.setReportedLatitude(barometricPayloadTemplate.getReportedLatitude());
                    barometricPayload.setReportedLongitude(barometricPayloadTemplate.getReportedLongitude());
                    barometricPayload.setSensorManufacturer(barometricPayloadTemplate.getSensorManufacturer());
                    barometricPayload.setSensorIdentifier(barometricPayloadTemplate.getSensorIdentifier());

                    entityManager.persist(barometricPayload);

                } catch (NoResultException ex) {
                    entityManager.getTransaction().rollback();
                    throw new HomeAutomateException(ExceptionReasonCode.UNKNOWN_GATEWAY);
                } catch(HomeAutomatePropertyValidationException ex){
                    entityManager.getTransaction().rollback();
                    throw ex;
                }
                catch (HomeAutomateException ex) {
                    entityManager.getTransaction().rollback();
                    throw ex;
                } catch (Exception ex) {
                    entityManager.getTransaction().rollback();
                    throw new HomeAutomateException(ex.getMessage(), ExceptionReasonCode.UNKNOWN);
                }
            }
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }

    public Gateway save(GatewayDTO gatewayTemplate) throws HomeAutomateException, HomeAutomatePropertyValidationException {

        Gateway result;
        EntityManager entityManager = null;

        gatewayTemplate.validate();

        try {
            entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();

            if (gatewayTemplate.getIdentity() == null) {

                entityManager.getTransaction().begin();

                // adding a new entity
                result = new Gateway();
                result.setIdentifier(gatewayTemplate.getIdentifier());
                result.setCurrentLocation(gatewayTemplate.getCurrentLocation().toLocation());
                result.setFirstSeen(gatewayTemplate.getFirstSeen());
                result.setLastSeen(gatewayTemplate.getLastSeen());
                result.setActive(gatewayTemplate.isActive());

                entityManager.persist(result);
                entityManager.getTransaction().commit();
            } else {

                // updating an existing entity
                entityManager.getTransaction().begin();
                result = entityManager.find(Gateway.class, gatewayTemplate.getIdentity());

                // marshall the singleton properties from the template to the entity for persistence
                result.setFirstSeen(gatewayTemplate.getFirstSeen());
                result.setLastSeen(gatewayTemplate.getLastSeen());
                result.setActive(gatewayTemplate.isActive());
                result.setIdentifier(gatewayTemplate.getIdentifier());

                // evaluate the current location to determine if there has been an update
                Double newLatitude = gatewayTemplate.getCurrentLocation().getLatitude();
                Double newLongitude = gatewayTemplate.getCurrentLocation().getLongitude();
                Double currentLatitude;
                Double currentLongitude;

                if (result.getCurrentLocation() == null) {
                    currentLatitude = currentLongitude = null;
                } else {
                    currentLatitude = result.getCurrentLocation().getLatitude();
                    currentLongitude = result.getCurrentLocation().getLongitude();
                }

                if (!newLatitude.equals(currentLatitude) || !newLongitude.equals(currentLongitude)) {
                    if (result.getCurrentLocation() == null) {
                        result.setCurrentLocation(new Location(newLatitude, newLongitude));
                    } else {
                        result.getLocationHistory().add(new Location(currentLatitude, currentLongitude));
                        result.getCurrentLocation().setLatitude(newLatitude);
                        result.getCurrentLocation().setLongitude(newLongitude);
                    }
                }

                entityManager.persist(result);
                entityManager.getTransaction().commit();
            }

        } catch (NonUniqueResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.NON_UNIQUE_GATEWAY_USER);
        } catch (NoResultException ex) {
            result = null;
        } catch (Exception ex) {
            throw new HomeAutomateException(ex.getMessage(), ExceptionReasonCode.UNKNOWN);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;

    }

}
