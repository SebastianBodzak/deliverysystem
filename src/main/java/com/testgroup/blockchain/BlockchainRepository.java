package com.testgroup.blockchain;

import org.ethereum.config.SystemProperties;
import org.ethereum.config.blockchain.FrontierConfig;
import org.ethereum.util.blockchain.SolidityContract;
import org.ethereum.util.blockchain.StandaloneBlockchain;

import java.math.BigInteger;

/**
 * @author beata.ilowiecka@impaqgroup.com on 07.12.16.
 */
public class BlockchainRepository {

    private final SolidityContract contract;
    private StandaloneBlockchain blockchain;

    private static final String solidityContract =
            "contract parcelContract {" +
                    "  string public data;" +
                    // public field can be accessed by calling 'data' function
                    "  function createParcel(string parcelData) {" +
                    "    data = parcelData;" +
                    "  }" +
                    "  function getParcel() returns (string abc){" +
                    "    return data;" +
                    "  }" +
                    "}";

    public BlockchainRepository(StandaloneBlockchain blockchain) {
        SystemProperties.getDefault().setBlockchainConfig(new FrontierConfig(new FrontierConfig.FrontierConstants() {
            @Override
            public BigInteger getMINIMUM_DIFFICULTY() {
                return BigInteger.ONE;
            }
        }));
        this.blockchain = blockchain;
        this.contract = blockchain.submitNewContract(solidityContract);

        printInfoBeforeCreatingBlock();

        blockchain.createBlock();

        printInfoAfterCreatingBlock();
    }


    private void printInfoAfterCreatingBlock() {
        System.out.println("######################################################### BLOCKCHAIN \n\n" + blockchain.getBlockchain() + "\n" +
                "\n######################################################### ");
        System.out.println("######################################################### BEST BLOCK: \n\n" + blockchain.getBlockchain().getBestBlock() + "\n" +
                "\n######################################################### ");
    }

    private void printInfoBeforeCreatingBlock() {
        System.out.println("######################################################### BEST BLOCK BEFORE: \n\n" + blockchain.getBlockchain().getBestBlock() + "\n" +
                "\n######################################################### ");
    }

    public void createTransaction(String parcel) {
        printInfoBeforeCreatingBlock();
        blockchain.createBlock();
        printInfoAfterCreatingBlock();

        printContract();
        contract.callFunction("createParcel", parcel);
        printContract();
    }

    private void printContract() {
        System.out.println("\n\n######################################################### CONTRACT: \n\n" + contract + "\n" +
                "\n######################################################### ");
    }
}
