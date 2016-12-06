package com.testgroup.infrastructure;

import com.testgroup.domain.Parcel;
import com.testgroup.domain.ParcelRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by sbod on 06.12.16.
 */
@Repository
public class LocalParcelRepository implements ParcelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(Parcel parcel) {
        entityManager.persist(parcel);
        entityManager.flush();
        return parcel.getId();
    }
}
