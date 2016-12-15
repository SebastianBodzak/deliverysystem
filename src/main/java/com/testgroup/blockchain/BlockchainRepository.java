package com.testgroup.blockchain;

import com.testgroup.domain.ParcelType;
import com.testgroup.domain.User;
import org.ethereum.config.SystemProperties;
import org.ethereum.config.blockchain.FrontierConfig;
import org.ethereum.util.blockchain.SolidityCallResult;
import org.ethereum.util.blockchain.SolidityContract;
import org.ethereum.util.blockchain.StandaloneBlockchain;

import java.math.BigInteger;

/**
 * @author beata.ilowiecka@impaqgroup.com on 07.12.16.
 */
public class BlockchainRepository {

    private final SolidityContract contract;
    private StandaloneBlockchain blockchain;

    public static final String CONTRACT =
            "contract UsernameStorage{\n" +
                    "    mapping (uint64 => string) userNames;\n" +
                    "    //potencjalne rozszerzenie - przerobić typ zwracany mappingu na tablice uint\n" +
                    "    mapping (bytes32 => uint64) hash_to_id_index;\n" +
                    "    uint64 lastUserId = 0;\n" +
                    "    //if username not exists in our storage - will be inserted\n" +
                    "    function getUserId(string username)  returns (uint64 id){\n" +
                    "        bytes32 hash = sha3(username);\n" +
                    "        //check if this hash have id\n" +
                    "        uint64 existingId = hash_to_id_index[hash];\n" +
                    "        if(existingId == 0){ //no id in system for this hash\n" +
                    "            lastUserId++;\n" +
                    "            hash_to_id_index[hash] = lastUserId;\n" +
                    "            userNames[lastUserId] = username;\n" +
                    "            return lastUserId;\n" +
                    "        }else{\n" +
                    "            return existingId;\n" +
                    "        }\n" +
                    "    }\n" +
                    "    function getUsername(uint64 id) returns (string username){\n" +
                    "        return userNames[id];\n" +
                    "    }\n" +
                    "    function getUsersCount() returns (uint64 count){\n" +
                    "        return lastUserId;\n" +
                    "    }\n" +
                    "    \n" +
                    "    function findUserId(string username) internal returns (uint64 id){\n" +
                    "        bytes32 hash = sha3(username);\n" +
                    "        return hash_to_id_index[hash];\n" +
                    "    }\n" +
                    "}\n" +
                    "contract ParcelStorage is UsernameStorage{\n" +
                    "       \n" +
                    "    UsernameStorage usernameStorage;\n" +
                    "    \n" +
                    "    enum ParcelType{\n" +
                    "        LETTER,\n" +
                    "        PACK,\n" +
                    "        INVOICE,\n" +
                    "        EMAIL\n" +
                    "    } \n" +
                    "    mapping (uint8 => ParcelType) parcelTypes;\n" +
                    "    \n" +
                    "    struct Parcel{\n" +
                    "        uint64 senderId;\n" +
                    "        uint64 receiverId;\n" +
                    "        uint64 connectedPersonId;\n" +
                    "        uint64 commitedById;\n" +
                    "        uint64 commitTimestamp;\n" +
                    "        ParcelType parcelType;\n" +
                    "    }\n" +
                    "    \n" +
                    "    uint64 lastParcelId = 0;\n" +
                    "    \n" +
                    "    mapping (uint => Parcel) parcels;\n" +
                    "        \n" +
                    "    //for search purposes, maps senderId into array of his parcels    \n" +
                    "    //enables faster search for parcel having senderId\n" +
                    "    mapping (uint64 => uint64[]) senderParcelIds;\n" +
                    "    mapping (uint64 => uint64[]) receiverParcelIds;\n" +
                    "    mapping (uint8 => uint64[]) typeParcelIds;\n" +
                    "    mapping (uint64 => uint64[]) commiterParcelIds;\n" +
                    "    \n" +
                    "    //konstruktor \n" +
                    "    function ParcelStorage(){\n" +
                    "        parcelTypes[0] = ParcelType.LETTER;\n" +
                    "        parcelTypes[1] = ParcelType.PACK;\n" +
                    "        parcelTypes[2] = ParcelType.INVOICE;\n" +
                    "        parcelTypes[3] = ParcelType.EMAIL;\n" +
                    "    }\n" +
                    "    \n" +
                    "    function addParcel(string sender, string receiver, string commitedBy, \n" +
                    "                        uint8 parcelTypeId) returns (uint64 id){\n" +
                    "        lastParcelId++;\n" +
                    "        Parcel p = parcels[lastParcelId];\n" +
                    "        p.senderId = getUserId(sender);\n" +
                    "        p.receiverId = getUserId(receiver);\n" +
                    "        p.commitedById = getUserId(commitedBy);\n" +
                    "        p.parcelType = parcelTypes[parcelTypeId];   \n" +
                    "        p.commitTimestamp = uint64(block.timestamp);\n" +
                    "        p.connectedPersonId = p.receiverId;\n" +
                    "        addParcelIndexing(p.senderId, p.receiverId, \n" +
                    "                        p.commitedById, parcelTypeId, lastParcelId);\n" +
                    "        return lastParcelId;\n" +
                    "    }\n" +
                    "    \n" +
                    "    function getParcelsCount() returns (uint64 count){\n" +
                    "        return lastParcelId;\n" +
                    "    }\n" +
                    "    \n" +
                    "    function getParcelAsIds(uint64 parcelId) returns (bytes parcelBytes){\n" +
                    "        Parcel p = parcels[parcelId];\n" +
                    "        if( p.senderId == 0){\n" +
                    "            return \"\";\n" +
                    "        }\n" +
                    "        parcelBytes = toBytes(p);\n" +
                    "    }\n" +
                    "    \n" +
                    "    function getParcelAsString(uint64 parcelId) returns (string parcelDetails){\n" +
                    "        Parcel p = parcels[parcelId];\n" +
                    "        if( p.senderId == 0){\n" +
                    "            return \"\";\n" +
                    "        }\n" +
                    "        parcelDetails = string(toString(p));\n" +
                    "    }\n" +
                    "    \n" +
                    "    function findParcelIdsBySender(string sender) returns (uint64[] parcelIds){\n" +
                    "        uint64 senderId = findUserId(sender);\n" +
                    "        if(senderId == 0){\n" +
                    "            return new uint64[](0);\n" +
                    "        }\n" +
                    "        return senderParcelIds[senderId];\n" +
                    "    }\n" +
                    "    \n" +
                    "    function addParcelIndexing(uint64 senderId, uint64 receiverId, uint64 commitedId, \n" +
                    "                uint8 parcelTypeId, uint64 parcelId) internal {\n" +
                    "        senderParcelIds[senderId].push(parcelId);\n" +
                    "        receiverParcelIds[receiverId].push(parcelId);\n" +
                    "        commiterParcelIds[commitedId].push(parcelId);\n" +
                    "        typeParcelIds[parcelTypeId].push(parcelId);\n" +
                    "    }\n" +
                    "    \n" +
                    "    function toBytes(Parcel p) internal returns (bytes b) {\n" +
                    "        b = new bytes(41);\n" +
                    "        for (uint i = 0; i < 8; i++) {\n" +
                    "             //divisor formula:\n" +
                    "             //http://ethereum.stackexchange.com/questions/4170/how-to-convert-a-uint-to-bytes-in-solidity\n" +
                    "             uint divisor = 2**(8*(7 - i));  \n" +
                    "             b[i] = byte(uint8( p.senderId / divisor)); \n" +
                    "             b[8+i] = byte(uint8( p.receiverId / divisor)); \n" +
                    "             b[16+i] = byte(uint8( p.connectedPersonId / divisor)); \n" +
                    "             b[24+i] = byte(uint8( p.commitedById / divisor)); \n" +
                    "             b[32+i] = byte(uint8( p.commitTimestamp / divisor)); \n" +
                    "        }\n" +
                    "        b[40] = byte(parcelTypeId( p.parcelType));\n" +
                    "    }\n" +
                    "    \n" +
                    "    function toString(Parcel p) internal returns (bytes b){\n" +
                    "        bytes sender = bytes(userNames[p.senderId]);\n" +
                    "        bytes receiver = bytes(userNames[p.receiverId]);\n" +
                    "        bytes connected = bytes(userNames[p.connectedPersonId]);\n" +
                    "        bytes commiter = bytes(userNames[p.commitedById]);\n" +
                    "        uint totalLength = sender.length + receiver.length \n" +
                    "                + commiter.length + connected.length\n" +
                    "                + 9  //8 for timestamp, 1 for typeId\n" +
                    "                + 5; //5 newline characters\n" +
                    "        b = new bytes(totalLength);\n" +
                    "        for(uint i = 0; i < sender.length; i++){\n" +
                    "            b[i] = sender[i]; \n" +
                    "        }\n" +
                    "        b[i] = \"\\n\";\n" +
                    "        for(uint j = 0; j < receiver.length; j++){\n" +
                    "            b[++i] = receiver[j];\n" +
                    "        }\n" +
                    "        b[++i] = \"\\n\";\n" +
                    "        for(j = 0; j < connected.length; j++){\n" +
                    "            b[++i] = connected[j];\n" +
                    "        }\n" +
                    "        b[++i] = \"\\n\";\n" +
                    "        for(j = 0; j < commiter.length; j++){\n" +
                    "            b[++i] = commiter[j];\n" +
                    "        }\n" +
                    "        b[++i] = \"\\n\";\n" +
                    "        for (uint k = 0; k < 8; k++) {\n" +
                    "            b[++i] = byte(uint8( p.commitTimestamp / (2**(8*(7 - k))))); \n" +
                    "        }\n" +
                    "        b[++i] = \"\\n\";\n" +
                    "        b[++i] = byte(parcelTypeId( p.parcelType));\n" +
                    "    }\n" +
                    "    function parcelTypeId(ParcelType pt) internal returns (uint8 typeId){\n" +
                    "        if(pt == ParcelType.LETTER) return 0;\n" +
                    "        if(pt == ParcelType.PACK) return 1;\n" +
                    "        if(pt == ParcelType.INVOICE) return 2;\n" +
                    "        if(pt == ParcelType.EMAIL) return 3;\n" +
                    "    }\n" +
                    "}";

    public BlockchainRepository() {
        StandaloneBlockchain blockchain = new StandaloneBlockchain().withAutoblock(true);
        this.blockchain = blockchain;
        SystemProperties.getDefault().setBlockchainConfig(new FrontierConfig(new FrontierConfig.FrontierConstants() {
            @Override
            public BigInteger getMINIMUM_DIFFICULTY() {
                return BigInteger.ONE;
            }
        }));
        printInfo("BEST BLOCK AFTER CREATING GENESIS");
        this.contract = blockchain.submitNewContract(CONTRACT, "ParcelStorage");
    }

    private void printInfo(String about) {
        System.out.println("#########################################################  " + about + " : \n\n" +
                blockchain.getBlockchain().getBestBlock() + "\n" +
                "\n######################################################### ");
    }

    public Long addParcel(String sender, String recipient, String committedBy, int parcelTypeId) {
//        System.out.println("wanna send: "+sender +", "+recipient+", "+committedBy+", "+parcelTypeId);
        SolidityCallResult result = contract.callFunction("addParcel", sender, recipient, committedBy, parcelTypeId);
        printInfo("BEST BLOCK AFTER CALLING FUNCTION CREATE PARCEL");
        BigInteger parcelId = (BigInteger) result.getReturnValue();
        return parcelId.longValue();
    }

    private void printContract() {
        System.out.println("\n\n######################################################### CALLING CONTRACT FUNCTION: \n\n" +
                contract + "\n" +
                "\n######################################################### ");
    }
//
//    public Long addUser(User user) {
//        SolidityCallResult result = usersContract.callFunction("getUserId", user.getName());
//        BigInteger returnedValue = (BigInteger) result.getReturnValue();
//        return returnedValue.longValue();
//    }
//
    public String getUserById(Long id) {
        return (String) contract.callFunction("getUsername", id).getReturnValue();
    }

    public BigInteger getParcelsCount() {
        return (BigInteger) contract.callFunction("getParcelsCount").getReturnValue();
    }
}