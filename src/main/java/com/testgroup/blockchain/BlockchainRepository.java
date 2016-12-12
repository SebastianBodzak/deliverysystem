package com.testgroup.blockchain;

import com.testgroup.domain.User;
import org.ethereum.config.SystemProperties;
import org.ethereum.config.blockchain.FrontierConfig;
import org.ethereum.util.blockchain.SolidityCallResult;
import org.ethereum.util.blockchain.SolidityContract;
import org.ethereum.util.blockchain.StandaloneBlockchain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @author beata.ilowiecka@impaqgroup.com on 07.12.16.
 */
@Component
public class BlockchainRepository {

    private final SolidityContract parcelContract;
    private final SolidityContract usersContract;
    private StandaloneBlockchain blockchain;

    private static final String PARCEL_CONTRACT =
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
    private static final String USERS_CONTRACT =
            "contract usernameStorage{\n\n" +

                    "    mapping (uint => string) userNames;\n\n" +

                    "    //potencjalne rozszerzenie - przerobić typ zwracany mappingu na tablice uint\n" +
                    "    mapping (bytes32 => uint) hash_to_id_index;\n\n" +
                    "    uint lastUserId = 0;\n\n" +

                    "    //if username not exists in our storage - will be inserted\n" +
                    "    function getUserId(string username)  returns (uint id){\n" +
                    "        bytes32 hash = sha3(username);\n" +
                    "        //check if this hash have id\n" +
                    "        uint existingId = hash_to_id_index[hash];\n" +
                    "        if(existingId == 0){ //no id in system for this hash\n" +
                    "            lastUserId++;\n" +
                    "            hash_to_id_index[hash] = lastUserId;\n" +
                    "            userNames[lastUserId] = username;\n" +
                    "            return lastUserId;\n" +
                    "        }else{\n" +
                    "            return existingId;\n" +
                    "        }\n" +
                    "    }\n" +
                    "function getUsername(uint id) returns (string username){\n" +
                    "        return userNames[id];\n" +
                    "}" +
                    "}";

    public BlockchainRepository(StandaloneBlockchain blockchain) {
        SystemProperties.getDefault().setBlockchainConfig(new FrontierConfig(new FrontierConfig.FrontierConstants() {
            @Override
            public BigInteger getMINIMUM_DIFFICULTY() {
                return BigInteger.ONE;
            }
        }));
        this.blockchain = blockchain;
        printInfo("BEST BLOCK AFTER CREATING GENESIS");
        this.parcelContract = blockchain.submitNewContract(PARCEL_CONTRACT);
        printInfo("BEST BLOCK AFTER CREATING PARCEL CONTRACT");
        this.usersContract = blockchain.submitNewContract(USERS_CONTRACT);
        printInfo("BEST BLOCK AFTER CREATING USERS CONTRACT");
    }

//    @Autowired
    public BlockchainRepository(){
        parcelContract = null;
        usersContract = null;
    }

    private void printInfo(String about) {
        System.out.println("#########################################################  " + about + " : \n\n" +
                blockchain.getBlockchain().getBestBlock() + "\n" +
                "\n######################################################### ");
    }

    public void addParcel(String parcel) {
        printContract();
        parcelContract.callFunction("createParcel", parcel);
        printInfo("BEST BLOCK AFTER CALLING FUNCTION CREATE PARCEL");
    }

    private void printContract() {
        System.out.println("\n\n######################################################### CALLING CONTRACT FUNCTION: \n\n" +
                parcelContract + "\n" +
                "\n######################################################### ");
    }

    public Long addUser(User user) {
        SolidityCallResult result = usersContract.callFunction("getUserId", user.getName());
        BigInteger returnedValue = (BigInteger) result.getReturnValue();
        return returnedValue.longValue();
    }

    public String getUser(Long id) {
        SolidityCallResult userData = usersContract.callFunction("getUsername", id);
        return (String) userData.getReturnValue();
    }
}

