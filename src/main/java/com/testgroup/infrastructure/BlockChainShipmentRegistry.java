package com.testgroup.infrastructure;

import com.testgroup.domain.Shipment;
import com.testgroup.domain.ShipmentRegistry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
@Repository
public class BlockChainShipmentRegistry implements ShipmentRegistry {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(Shipment shipment) {
        entityManager.persist(shipment);
        entityManager.flush();
        return shipment.getId();
    }
}
