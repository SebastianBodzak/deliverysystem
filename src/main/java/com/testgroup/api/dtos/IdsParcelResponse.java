package com.testgroup.api.dtos;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static com.testgroup.api.dtos.ByteUtils.bytesToInt;
import static com.testgroup.domain.ParcelType.toParcelType;

/**
 * @author beata.ilowiecka@impaqgroup.com on 15.12.16.
 */
public class IdsParcelResponse {

    private Long senderId;
    private Long receiverId;
    private Long connectedPersonId;
    private Long committedById;
    private Long commitTimestamp;



    private String parcelType;
    private String commitDate;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public IdsParcelResponse(Long senderId, Long receiverId, Long connectedPersonId, Long committedById, Long commitTimestamp, String parcelType) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.connectedPersonId = connectedPersonId;
        this.committedById = committedById;
        this.commitTimestamp = commitTimestamp;
        this.parcelType = parcelType;
    }

    public IdsParcelResponse(byte[] parcelAsIds){
        if(parcelAsIds.length >= 41) {
            senderId = ByteUtils.bytesToLong(Arrays.copyOfRange(parcelAsIds, 0, 8));
            receiverId = ByteUtils.bytesToLong(Arrays.copyOfRange(parcelAsIds, 8, 16));
            connectedPersonId = ByteUtils.bytesToLong(Arrays.copyOfRange(parcelAsIds, 16, 24));
            committedById = ByteUtils.bytesToLong(Arrays.copyOfRange(parcelAsIds, 24, 32));
            commitTimestamp = ByteUtils.bytesToLong(Arrays.copyOfRange(parcelAsIds, 32, 40));
            commitDate = dateFormat.format(new Date(commitTimestamp * 1000));
            parcelType = toParcelType(bytesToInt(Arrays.copyOfRange(parcelAsIds, 40, 41)));
        }
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getConnectedPersonId() {
        return connectedPersonId;
    }

    public void setConnectedPersonId(Long connectedPersonId) {
        this.connectedPersonId = connectedPersonId;
    }

    public Long getCommittedById() {
        return committedById;
    }

    public void setCommittedById(Long committedById) {
        this.committedById = committedById;
    }

    public Long getCommitTimestamp() {
        return commitTimestamp;
    }

    public void setCommitTimestamp(Long commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }
}
