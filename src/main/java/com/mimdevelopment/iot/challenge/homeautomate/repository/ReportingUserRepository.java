package com.mimdevelopment.iot.challenge.homeautomate.repository;

import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserMoodRatingDTO;
import com.mimdevelopment.iot.challenge.homeautomate.dto.ReportingUserPhysicalRatingDTO;
import com.mimdevelopment.iot.challenge.homeautomate.exception.ExceptionReasonCode;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomateException;
import com.mimdevelopment.iot.challenge.homeautomate.exception.HomeAutomatePropertyValidationException;
import com.mimdevelopment.iot.challenge.homeautomate.model.ReportingUser;
import com.mimdevelopment.iot.challenge.homeautomate.model.ReportingUserMoodRating;
import com.mimdevelopment.iot.challenge.homeautomate.model.ReportingUserPhysicalRating;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * User: luther stanton
 * Date: 4/27/14
 * Time: 10:08 AM
 */

public class ReportingUserRepository {

    public ReportingUser fetchByUserName(String userName)
            throws HomeAutomateException {

        ReportingUser result;
        EntityManager entityManager = null;

        try {
            entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ReportingUser> cq = cb.createQuery(ReportingUser.class);
            Root<ReportingUser> reportingUserRoot = cq.from(ReportingUser.class);
            Predicate condition = cb.and(
                    cb.equal(reportingUserRoot.get("userName").as(String.class), userName),
                    cb.equal(reportingUserRoot.get("active").as(Boolean.class), true)
            );
            cq.where(condition);
            TypedQuery<ReportingUser> query = entityManager.createQuery(cq);
            result = query.getSingleResult();
            entityManager.detach(result);
        } catch (NonUniqueResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.NON_UNIQUE_GATEWAY_USER);
        } catch (NoResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.UNKNOWN_GATEWAY_USER);
        } catch (Exception ex) {
            throw new HomeAutomateException(ex.getMessage(), ExceptionReasonCode.UNKNOWN);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return result;
    }

    public void addMoodRating(ReportingUserMoodRatingDTO moodRatingTemplate)
            throws HomeAutomateException , HomeAutomatePropertyValidationException {

        moodRatingTemplate.validate();

        if(moodRatingTemplate.getReportingUser().getIdentity() == null){
            throw new HomeAutomatePropertyValidationException(
                    "The moodRatingTemplate.ReportingUser.Identity property cannot be null or empty.");
        }

        EntityManager entityManager = null;

        try {
            entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();
            entityManager.getTransaction().begin();

            ReportingUser reportingUser = entityManager.find(ReportingUser.class,
                    moodRatingTemplate.getReportingUser().getIdentity());

            ReportingUserMoodRating moodRating = new ReportingUserMoodRating();
            moodRating.setReportingUser(reportingUser);
            moodRating.setDate(moodRatingTemplate.getDate());
            moodRating.setRating(moodRatingTemplate.getRating());
            moodRating.setReportedLatitude(moodRatingTemplate.getReportedLatitude());
            moodRating.setReportedLongitude(moodRatingTemplate.getReportedLongitude());

            entityManager.persist(moodRating);
            entityManager.getTransaction().commit();
        } catch (NonUniqueResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.NON_UNIQUE_GATEWAY_USER);
        } catch (NoResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.UNKNOWN_GATEWAY_USER);
        } catch (Exception ex) {
            throw new HomeAutomateException(ex.getMessage(), ExceptionReasonCode.UNKNOWN);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void addPhysicalRating(ReportingUserPhysicalRatingDTO physicalRatingTemplate)
            throws HomeAutomateException , HomeAutomatePropertyValidationException {

        physicalRatingTemplate.validate();

        if(physicalRatingTemplate.getReportingUser().getIdentity() == null){
            throw new HomeAutomatePropertyValidationException(
                    "The physicalRatingTemplate.ReportingUser.Identity property cannot be null or empty.");
        }

        EntityManager entityManager = null;

        try {
            entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();
            entityManager.getTransaction().begin();

            ReportingUser reportingUser = entityManager.find(ReportingUser.class,
                    physicalRatingTemplate.getReportingUser().getIdentity());

            ReportingUserPhysicalRating physicalRating = new ReportingUserPhysicalRating();
            physicalRating.setReportingUser(reportingUser);
            physicalRating.setDate(physicalRatingTemplate.getDate());
            physicalRating.setRating(physicalRatingTemplate.getRating());
            physicalRating.setReportedLatitude(physicalRatingTemplate.getReportedLatitude());
            physicalRating.setReportedLongitude(physicalRatingTemplate.getReportedLongitude());

            entityManager.persist(physicalRating);
            entityManager.getTransaction().commit();
        } catch (NonUniqueResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.NON_UNIQUE_GATEWAY_USER);
        } catch (NoResultException ex) {
            throw new HomeAutomateException(ExceptionReasonCode.UNKNOWN_GATEWAY_USER);
        } catch (Exception ex) {
            throw new HomeAutomateException(ex.getMessage(), ExceptionReasonCode.UNKNOWN);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
