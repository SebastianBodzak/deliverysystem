package com.testgroup.infrastructure;

import com.testgroup.api.RegistryCatalog;
import com.testgroup.api.RegistrySearchResults;
import com.testgroup.api.ShipmentCriteria;
import com.testgroup.api.ShipmentDto;
import com.testgroup.domain.ParcelType;
import com.testgroup.domain.Shipment;
import com.testgroup.domain.Shipment_;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by sbod on 07.12.16.
 */
@Service
public class JpaRegistryCatalog implements RegistryCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RegistrySearchResults find(ShipmentCriteria shipmentCriteria) throws ParseException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ShipmentDto> query = builder.createQuery(ShipmentDto.class);
        Root<Shipment> root = query.from(Shipment.class);
        selectShipmentDto(builder, query, root);
        applyCriteria(shipmentCriteria, builder, query, root);

        Query jpaQuery = entityManager.createQuery(query);

        return new RegistrySearchResults(jpaQuery.getResultList());
    }

    private void selectShipmentDto(CriteriaBuilder builder, CriteriaQuery<ShipmentDto> query, Root<Shipment> root) {
        query.select(builder.construct(ShipmentDto.class,
                root.get(Shipment_.id),
                root.get(Shipment_.parcelId),
                root.get(Shipment_.date),
                root.get(Shipment_.senderId),
                root.get(Shipment_.recipientId),
                root.get(Shipment_.parcelType)
                ));
    }

    private void applyCriteria(ShipmentCriteria shipmentCriteria, CriteriaBuilder builder, CriteriaQuery<ShipmentDto> query, Root<Shipment> root) throws ParseException {
        Collection<Predicate> predicates = new HashSet<>();
        applyParcelType(shipmentCriteria, builder, root, predicates);
        applyCreatedDates(shipmentCriteria, builder, root, predicates);
        applySender(shipmentCriteria, builder, root, predicates);
        applyRecipient(shipmentCriteria, builder, root, predicates);
        query.where(predicates.toArray(new Predicate[] {}));
    }

    private void applySender(ShipmentCriteria shipmentCriteria, CriteriaBuilder builder, Root<Shipment> root, Collection<Predicate> predicates) {
        if (shipmentCriteria.isSenderIdDefined())
            predicates.add(builder.equal(root.get(Shipment_.senderId), shipmentCriteria.getSenderId()));
    }

    private void applyRecipient(ShipmentCriteria shipmentCriteria, CriteriaBuilder builder, Root<Shipment> root, Collection<Predicate> predicates) {
        if (shipmentCriteria.isRecipientIdDefined())
            predicates.add(builder.equal(root.get(Shipment_.recipientId), shipmentCriteria.getRecipientId()));
    }

    private void applyParcelType(ShipmentCriteria shipmentCriteria, CriteriaBuilder builder, Root<Shipment> root, Collection<Predicate> predicates) {
        if (shipmentCriteria.isParcelTypeDefined())
            predicates.add(builder.equal(root.get(Shipment_.parcelType), ParcelType.valueOf(shipmentCriteria.getParcelType())));
    }

    private void applyCreatedDates(ShipmentCriteria shipmentCriteria, CriteriaBuilder builder, Root<Shipment> root, Collection<Predicate> predicates) throws ParseException {
        if (shipmentCriteria.isCreatedDatesDefined()) {
            if (shipmentCriteria.isCreatedFromDefined())
                predicates.add(builder.greaterThan(root.get(Shipment_.date), createDateFromString(shipmentCriteria.getCreatedFrom())));
            if (shipmentCriteria.isCreatedUntilDefined())
                predicates.add(builder.lessThanOrEqualTo(root.get(Shipment_.date), createDateFromString(shipmentCriteria.getCreatedUntil())));
        }
    }

    private Date createDateFromString(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/DD");
            return (Date)formatter.parse(date);
    }
}
