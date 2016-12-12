package com.testgroup.ethereum;

import com.testgroup.blockchain.BlockchainRepository;
import com.testgroup.blockchain.ContractLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.Executors;

@Configuration
public class EthereumConfig {

    @Bean
    EthereumBean ethereumBean() throws Exception {
        EthereumBean ethereumBean = new EthereumBean();
        Executors.newSingleThreadExecutor().
                submit(ethereumBean::start);
        return ethereumBean;
    }

//    @Bean
//    ContractLoader contractLoader(EthereumBean ethereumBean)throws IOException, InterruptedException{
//        ContractLoader contractLoader = new ContractLoader(ethereumBean.getEthereum());
//        contractLoader.loadContractIntoEthereum(BlockchainRepository.USERS_CONTRACT);
//        return contractLoader;
//    }
}
