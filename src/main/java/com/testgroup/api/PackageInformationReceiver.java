package com.testgroup.api;

import com.testgroup.blockchain.BlockchainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * Created by sbod on 15.12.16.
 */
@Service
public class PackageInformationReceiver {

    private BlockchainRepository blockChainRepository;

    public PackageInformationReceiver(BlockchainRepository blockChainRepository) {
        this.blockChainRepository = blockChainRepository;
    }

    @Transactional
    public BigInteger getParcelsCount() {
        return blockChainRepository.getParcelsCount();
    }
}
