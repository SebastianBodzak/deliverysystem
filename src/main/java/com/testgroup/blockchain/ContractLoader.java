package com.testgroup.blockchain;

import lombok.extern.slf4j.Slf4j;
import org.ethereum.core.Transaction;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.crypto.ECKey;
import org.ethereum.facade.Ethereum;
import org.ethereum.solidity.compiler.CompilationResult;
import org.ethereum.solidity.compiler.SolidityCompiler;
import org.ethereum.util.ByteUtil;
import org.spongycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.math.BigInteger;

@Slf4j
public class ContractLoader {
    private Ethereum ethereum;

    public final static String SENDER_PRIVATE_KEY_STR = "f67c4032a7ff79bbfa7a780331b235c4eb681d51a0704cb1562064fb6c4bced5";
    protected final byte[] senderPrivateKey = Hex.decode(SENDER_PRIVATE_KEY_STR);
    protected final byte[] senderAddress = ECKey.fromPrivate(senderPrivateKey).getAddress();

    public ContractLoader(Ethereum ethereum) {
        this.ethereum = ethereum;
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
    }

    protected TransactionReceipt sendTxAndWait(byte[] receiveAddress, byte[] data) throws InterruptedException {

        BigInteger nonce = ethereum.getRepository().getNonce(senderAddress);
        Transaction tx = new Transaction(
                ByteUtil.bigIntegerToBytes(nonce),
//                ByteUtil.longToBytesNoLeadZeroes(ethereum.getGasPrice()),
                ByteUtil.longToBytesNoLeadZeroes(0L),
                ByteUtil.longToBytesNoLeadZeroes(3_000_000_000L),
                receiveAddress,
                ByteUtil.longToBytesNoLeadZeroes(1),
                data,
                ethereum.getChainIdForNextBlock());
        tx.sign(ECKey.fromPrivate(senderPrivateKey));
        log.info("<=== Sending transaction: " + tx);
        ethereum.submitTransaction(tx);

        return null;
//                waitForTx(tx.getHash());
    }
}