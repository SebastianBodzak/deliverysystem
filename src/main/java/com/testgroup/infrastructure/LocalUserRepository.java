package com.testgroup.infrastructure;

import com.testgroup.domain.User;
import com.testgroup.domain.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by sbod on 06.12.16.
 */
@Repository
public class LocalUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User load(Long senderId) {
        return entityManager.find(User.class, senderId);
    }

    @Override
    public Long save(User user) {
        entityManager.persist(user);
        return user.getId();
    }
}
