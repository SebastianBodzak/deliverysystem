package com.testgroup.api;

import com.testgroup.domain.User;
import com.testgroup.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sbod on 06.12.16.
 */
@Component
public class DataLoaderInitialization implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoaderInitialization(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments applicationArguments) throws Exception {
        User firstUser = new User("FirstUserName", "FirstUserSurname", "FirstUses@mail.com", "FirstUserAddress");
        User secondUser = new User("SecondUserName", "SecondUserSurname", "SecondUser@mail.com", "SecondUserAddress");
        User thirdUser = new User("ThirdUserName", "ThirdUserSurname", "ThirdUser@mail.com", "ThirdUser");
        Long firstUserId = userRepository.save(firstUser);
        Long secondUserId = userRepository.save(secondUser);
        Long thirdUserId = userRepository.save(thirdUser);

        System.out.println("FIRST USER ID: " + firstUserId);
        System.out.println("SECOND USER ID: " + secondUserId);
        System.out.println("THIRD USER ID: " + thirdUserId);
    }
}
