package com.testgroup.ethereum;

import com.testgroup.blockchain.BlockchainRepository;
import com.testgroup.blockchain.ContractLoader;
import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;

import static com.testgroup.blockchain.BlockchainRepository.*;

@Component
public class EthereumBean {

    private Ethereum ethereum;
    private ContractLoader contractLoader;
    private boolean contractInstalled = false;

    public Ethereum getEthereum() {
        return ethereum;
    }

    public void start(){
        this.ethereum = EthereumFactory.createEthereum();
        contractLoader = new ContractLoader(ethereum);
        this.ethereum.addListener(new EthereumListener(ethereum, this));

        ethereum.getBlockMiner().addListener(new EMinerListener(ethereum.getBlockMiner()));
        ethereum.getBlockMiner().setMinGasPrice(BigInteger.ZERO);
//        ethereum.getBlockMiner().setCpuThreads(2);
        System.out.println("### Total dificulty: "+ethereum.getBlockchain().getTotalDifficulty());
        System.out.println(ethereum.getBlockchain().getBestBlock());
        ethereum.getBlockMiner().startMining();
        if(! contractInstalled){
            try {
                System.out.println("### loading first contract");
                contractLoader.loadContractIntoEthereum(USERS_CONTRACT);
                contractInstalled = true;
            } catch (Exception e) {

            }
        }
    }

    public String getBestBlock() {
        return "" + ethereum.getBlockchain().getBestBlock().getNumber();
    }

    public void loadFirstContract(){
        System.out.println("### loading contract from other place");
        if(! contractInstalled){
            try {
                System.out.println("### loading first contract");
                contractLoader.loadContractIntoEthereum(USERS_CONTRACT);
                contractInstalled = true;
            } catch (Exception e) {

            }
        }
    }
}
