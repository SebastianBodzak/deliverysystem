package com.testgroup.api.dtos;

import com.testgroup.domain.ParcelType;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.testgroup.api.dtos.ByteUtils.bytesToInt;
import static com.testgroup.domain.ParcelType.toParcelType;

/**
 * @author beata.ilowiecka@impaqgroup.com on 15.12.16.
 */
public class StringParcelResponse {

    private String sender;
    private String recipient;
    private String committedBy;
    private String connectedPerson;
    private String parcelType;
    private String commitTimestamp;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");


    public StringParcelResponse(String unparsedResponse) {
        String[] responseLines = unparsedResponse.split("\n");
        if(responseLines.length == 6) {
            sender = responseLines[0];
            recipient = responseLines[1];
            connectedPerson = responseLines[2];
            committedBy = responseLines[3];
            parcelType = toParcelType(bytesToInt(responseLines[5].getBytes()));
        }
    }



    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getCommittedBy() {
        return committedBy;
    }

    public void setCommittedBy(String committedBy) {
        this.committedBy = committedBy;
    }

    public String getConnectedPerson() {
        return connectedPerson;
    }

    public void setConnectedPerson(String connectedPerson) {
        this.connectedPerson = connectedPerson;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }



    private long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        if(bytes.length > Long.BYTES) {
            System.out.println( bytes.length - Long.BYTES);
            buffer.put(bytes, bytes.length - Long.BYTES, Long.BYTES);
        }else{
            int bytesLeft = Long.BYTES - bytes.length;
            if( bytesLeft > 0){
                buffer.put(new byte[bytesLeft]);
            }
            buffer.put(bytes);
        }
        buffer.flip();//need flip
        System.out.println( "timestamp from buffer: "+buffer.getLong());
        System.out.println( "timestamp now: "+ (new Date().getTime()));
        return buffer.getLong();
    }


    public String getCommitTimestamp() {
        return commitTimestamp;
    }

    public void setCommitTimestamp(String commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }
}
