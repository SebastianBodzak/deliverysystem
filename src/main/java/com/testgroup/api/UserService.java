package com.testgroup.api;

import com.testgroup.blockchain.BlockchainRepository;
import com.testgroup.domain.User;
import com.testgroup.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author beata.ilowiecka@impaqgroup.com on 09.12.16.
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private BlockchainRepository blockchainRepository;

    public UserService(UserRepository userRepository, BlockchainRepository blockchainRepository) {
        this.userRepository = userRepository;
        this.blockchainRepository = blockchainRepository;
    }

    @Transactional
    public Long addUser(CreateUserRequest request) {
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getAddress());
        Long userId = userRepository.save(user);
        User loadedUser = userRepository.load(userId);

//        return blockchainRepository.addUser(loadedUser);
        return 0L;
    }

    @Transactional
    public String getById(Long id) {
        return blockchainRepository.getUserById(id);
    }
}
