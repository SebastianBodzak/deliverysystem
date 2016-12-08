package com.testgroup.blockchain;

import org.ethereum.util.blockchain.StandaloneBlockchain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author beata.ilowiecka@impaqgroup.com on 08.12.16.
 */
@Configuration
public class BlockchainConfig {

    @Bean
    public BlockchainRepository standaloneBlockChain() {
        StandaloneBlockchain bc = new StandaloneBlockchain().withAutoblock(true);

        return new BlockchainRepository(bc);
    }
}
