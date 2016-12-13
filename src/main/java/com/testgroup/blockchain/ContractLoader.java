package com.testgroup.blockchain;

import lombok.extern.slf4j.Slf4j;
import org.ethereum.core.Transaction;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.crypto.ECKey;
import org.ethereum.db.ByteArrayWrapper;
import org.ethereum.facade.Ethereum;
import org.ethereum.solidity.compiler.CompilationResult;
import org.ethereum.solidity.compiler.SolidityCompiler;
import org.ethereum.util.ByteUtil;
import org.spongycastle.util.encoders.Hex;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.testgroup.blockchain.BlockchainRepository.USERS_CONTRACT;

@Slf4j
public class ContractLoader {

    private Ethereum ethereum;
    private boolean contractInstalled = false;

    public final static String SENDER_PRIVATE_KEY_STR = "f67c4032a7ff79bbfa7a780331b235c4eb681d51a0704cb1562064fb6c4bced5";
    protected final byte[] senderPrivateKey = Hex.decode(SENDER_PRIVATE_KEY_STR);
    protected final byte[] senderAddress = ECKey.fromPrivate(senderPrivateKey).getAddress();

    private Map<ByteArrayWrapper, TransactionReceipt> txWaiters =
            Collections.synchronizedMap(new HashMap<ByteArrayWrapper, TransactionReceipt>());

    public ContractLoader(Ethereum ethereum) {
        this.ethereum = ethereum;
    }

    public void loadUsersContract() {
        if (!contractInstalled) {
            try {
                System.out.println("### loading first contract");
                loadContractIntoEthereum(USERS_CONTRACT);
                contractInstalled = true;
            } catch (Exception e) {

            }
        }
    }

    public void loadContractIntoEthereum(String contractDefinition) throws IOException, InterruptedException {
        log.info("Compiling contract...");
        SolidityCompiler.Result result = SolidityCompiler.compile(contractDefinition.getBytes(), true,
                SolidityCompiler.Options.ABI, SolidityCompiler.Options.BIN);
        if (result.isFailed()) {
            throw new RuntimeException("Contract compilation failed:\n" + result.errors);
        }
        CompilationResult res = CompilationResult.parse(result.output);
        if (res.contracts.isEmpty()) {
            throw new RuntimeException("Compilation failed, no contracts returned:\n" + result.errors);
        }
        CompilationResult.ContractMetadata metadata = res.contracts.values().iterator().next();
        if (metadata.bin == null || metadata.bin.isEmpty()) {
            throw new RuntimeException("Compilation failed, no binary returned:\n" + result.errors);
        }
        log.info("compilation successful");
        log.info("Sending contract to net and waiting for inclusion");
        TransactionReceipt receipt = sendTxAndWait(new byte[0], Hex.decode(metadata.bin));
        byte[] contractAddress = receipt.getTransaction().getContractAddress();
        log.info("Contract created: " + Hex.toHexString(contractAddress));
    }

    protected TransactionReceipt sendTxAndWait(byte[] receiveAddress, byte[] data) throws InterruptedException {
        BigInteger nonce = ethereum.getRepository().getNonce(senderAddress);
        Transaction tx = new Transaction(
                ByteUtil.bigIntegerToBytes(nonce),
                ByteUtil.longToBytesNoLeadZeroes(ethereum.getGasPrice()),
                ByteUtil.longToBytesNoLeadZeroes(3_000_000_000L),
                receiveAddress,
                ByteUtil.longToBytesNoLeadZeroes(1),
                data,
                ethereum.getChainIdForNextBlock());
        tx.sign(ECKey.fromPrivate(senderPrivateKey));
        log.info("<=== Sending transaction: " + tx);
        ethereum.submitTransaction(tx);

        return waitForTx(tx.getHash());
    }

    private TransactionReceipt waitForTx(byte[] txHash) throws InterruptedException {
        ByteArrayWrapper txHashW = new ByteArrayWrapper(txHash);
        txWaiters.put(txHashW, null);
        long startBlock = ethereum.getBlockchain().getBestBlock().getNumber();
        while(true) {
            TransactionReceipt receipt = txWaiters.get(txHashW);
            if (receipt != null) {
                return receipt;
            } else {
                long curBlock = ethereum.getBlockchain().getBestBlock().getNumber();
                if (curBlock > startBlock + 16) {
                    throw new RuntimeException("The transaction was not included during last 16 blocks: " + txHashW.toString().substring(0,8));
                } else {
                    log.info("Waiting for block with transaction 0x" + txHashW.toString().substring(0,8) +
                            " included (" + (curBlock - startBlock) + " blocks received so far) ...");
                }
            }
            synchronized (this) {
                wait(20000);
            }
        }
    }
}
