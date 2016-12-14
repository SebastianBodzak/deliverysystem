package com.testgroup.ethereum;

import com.testgroup.blockchain.ContractLoader;
import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class EthereumBean {

    private Ethereum ethereum;
    private ContractLoader contractLoader;

    public Ethereum getEthereum() {
        return ethereum;
    }

    public void start() {
        this.ethereum = EthereumFactory.createEthereum();
        contractLoader = new ContractLoader(ethereum);
        this.ethereum.addListener(new EthereumListener(ethereum, this));
        setupMiner();
        contractLoader.loadUsersContract();
    }

    private void setupMiner() {
        ethereum.getBlockMiner().addListener(new EMinerListener(ethereum.getBlockMiner()));
        ethereum.getBlockMiner().setMinGasPrice(BigInteger.ZERO);
//        ethereum.getBlockMiner().setCpuThreads(2);
        printBlockchainInfo();
        ethereum.getBlockMiner().startMining();
    }

    private void printBlockchainInfo() {
        System.out.println("### Total difficulty: " + ethereum.getBlockchain().getTotalDifficulty());
        System.out.println(ethereum.getBlockchain().getBestBlock());
    }

    public void loadFirstContract() {
        System.out.println("### loading contract from other place");
        contractLoader.loadUsersContract();
    }

    public String getBestBlock() {
        return "" + ethereum.getBlockchain().getBestBlock().getNumber();
    }
}
