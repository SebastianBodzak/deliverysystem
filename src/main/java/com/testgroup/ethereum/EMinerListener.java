package com.testgroup.ethereum;

import lombok.extern.slf4j.Slf4j;
import org.ethereum.core.Block;
import org.ethereum.mine.BlockMiner;
import org.ethereum.mine.MinerListener;
import org.spongycastle.util.encoders.Hex;

@Slf4j
public class EMinerListener implements MinerListener {

    private BlockMiner miner;

    public EMinerListener(BlockMiner miner) {
        this.miner = miner;
    }

    @Override
    public void miningStarted() {
        log.info("Mining started by miner: " + miner);
    }

    @Override
    public void miningStopped() {
        log.info("Miner stopped");
    }

    @Override
    public void blockMiningStarted(Block block) {
        log.info("Start mining block: " + block.getShortDescr());
    }

    @Override
    public void blockMined(Block block) {
        log.info("Block mined! : \n" + block);
        if (!block.getTransactionsList().isEmpty()) {
            byte[] contractAddress = block.getTransactionsList().get(0).getContractAddress();
            log.info("##### THIS IS contract address: " + Hex.toHexString(contractAddress));
        }
        if(block.getNumber() >= 5){
            miner.stopMining();
        }
    }

    @Override
    public void blockMiningCanceled(Block block) {
        log.info("Cancel mining block: " + block.getShortDescr());
    }
}
