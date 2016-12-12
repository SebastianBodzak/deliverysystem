package com.testgroup.ethereum;

import lombok.extern.slf4j.Slf4j;
import org.ethereum.core.Block;
import org.ethereum.facade.Ethereum;
import org.ethereum.mine.BlockMiner;
import org.ethereum.mine.MinerListener;

@Slf4j
public class EMinerListener implements MinerListener {

    private BlockMiner miner;

    public EMinerListener(BlockMiner miner) {
        this.miner = miner;
    }

    @Override
    public void miningStarted() {
        log.info("Miner started");
//        System.out.println("Miner started");
    }

    @Override
    public void miningStopped() {
        log.info("Miner stopped");
//        System.out.println("Miner stopped");
    }

    @Override
    public void blockMiningStarted(Block block) {

        log.info("Start mining block: " + block.getShortDescr());
//        System.out.println("Start mining block: " + block.getShortDescr());
    }

    @Override
    public void blockMined(Block block) {
        log.info("Block mined! : \n" + block);
//        System.out.println("Block mined! : \n" + block);
        if(block.getNumber() >= 5){
            miner.stopMining();
        }
    }

    @Override
    public void blockMiningCanceled(Block block) {
        log.info("Cancel mining block: " + block.getShortDescr());
        System.out.println("Cancel mining block: " + block.getShortDescr());
    }
}
