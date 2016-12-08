package com.testgroup;

import com.testgroup.blockchain.BlockchainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by sbod on 06.12.16.
 */
@SpringBootApplication
public class DeliverysystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverysystemApplication.class, args);
    }

}