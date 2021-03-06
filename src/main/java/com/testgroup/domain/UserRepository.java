package com.testgroup.domain;

/**
 * @author beata.ilowiecka@impaqgroup.com on 06.12.16.
 */
public interface UserRepository {
    User load(Long userId);
    Long save(User user);
}
