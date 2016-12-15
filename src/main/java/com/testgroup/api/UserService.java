package com.testgroup.api;

import com.testgroup.blockchain.BlockchainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author beata.ilowiecka@impaqgroup.com on 09.12.16.
 */
@Service
public class UserService {

    private BlockchainRepository blockchainRepository;

    public UserService(BlockchainRepository blockchainRepository) {
        this.blockchainRepository = blockchainRepository;
    }

    @Transactional
    public String getById(Long id) {
        return blockchainRepository.getUserById(id);
    }

    @Transactional
    public BigInteger getByName(String fullName) {
        return blockchainRepository.getUserByName(fullName);
    }

    @Transactional
    public BigInteger count() {
        return blockchainRepository.count();
    }
}
