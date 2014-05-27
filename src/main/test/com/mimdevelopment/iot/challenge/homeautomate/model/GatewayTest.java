package com.mimdevelopment.iot.challenge.homeautomate.model;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 1:06 PM
 */
public class GatewayTest {

    @Test
    public void testNewGateway() throws Exception {

        EntityManager entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();
        entityManager.getTransaction().begin();

        Gateway gateway = new Gateway();
        gateway.setIdentifier("test_gateway");
        gateway.setFirstSeen(new Date());
        gateway.setLastSeen(new Date());

        Location location =  new Location(33.393931, -84.097061);
        gateway.setCurrentLocation(location);

        gateway.setActive(true);

        // entityManager.persist(location);
        entityManager.persist(gateway);
        entityManager.getTransaction().commit();

        System.out.println("gateway=" + gateway + ", identifier=[" + gateway.getIdentifier() + "]");

        Gateway foundGateway = entityManager.find(Gateway.class, gateway.getIdentity());
        System.out.println("foundGateway=" + foundGateway);

        entityManager.close();
    }

}
