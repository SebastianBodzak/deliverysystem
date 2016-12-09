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
        printInfo("BEST BLOCK After creating GENESIS");
        this.contract = blockchain.submitNewContract(solidityContract);
        printInfo("BEST BLOCK After creating CONTRACT");
    }

    private void printInfo(String about) {
        System.out.println("#########################################################  " + about +" : \n\n" +
                blockchain.getBlockchain().getBestBlock() + "\n" +
                "\n######################################################### ");
    }

    public void createTransaction(String parcel) {
        printContract();
        contract.callFunction("createParcel", parcel);
        printInfo("BEST BLOCK AFTER CALLING FUNCTION CREATE PARCEL");
    }

    private void printContract() {
        System.out.println("\n\n######################################################### CALLING CONTRACT FUNCTION: \n\n" +
                contract + "\n" +
                "\n######################################################### ");
    }
}
