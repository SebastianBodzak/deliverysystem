package com.testgroup.api.dtos;

import com.testgroup.domain.ParcelType;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        sender = responseLines[0];
        recipient = responseLines[1];
        connectedPerson = responseLines[2];
        committedBy = responseLines[3];
        parcelType = toParcelType(bytesToInt(responseLines[5].getBytes()));
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    private String toParcelType(int parcelTypeNr) {
        ParcelType type;
        switch (parcelTypeNr) {
            case 0:
                type = ParcelType.LETTER;
                break;
            case 1:
                type = ParcelType.PACK;
                break;
            case 2:
                type = ParcelType.INVOICE;
                break;
            case 3:
                type = ParcelType.EMAIL;
                break;
            default:
                throw new IllegalArgumentException("undefined parcel type");
        }
        return type.toString();
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
        if (bytes.length > Long.BYTES) {
            System.out.println(bytes.length - Long.BYTES);
            buffer.put(bytes, bytes.length - Long.BYTES, Long.BYTES);
        } else {
            int bytesLeft = Long.BYTES - bytes.length;
            if (bytesLeft > 0) {
                buffer.put(new byte[bytesLeft]);
            }
            buffer.put(bytes);
        }
        buffer.flip();//need flip
        System.out.println("timestamp from buffer: " + buffer.getLong());
        System.out.println("timestamp now: " + (new Date().getTime()));
        return buffer.getLong();
    }

    private int bytesToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        int bytesLeft = Integer.BYTES - bytes.length;
        buffer.put(new byte[bytesLeft]);
        buffer.put(bytes);

        buffer.flip();//need flip
        return buffer.getInt();
    }

    public String getCommitTimestamp() {
        return commitTimestamp;
    }

    public void setCommitTimestamp(String commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }
}
