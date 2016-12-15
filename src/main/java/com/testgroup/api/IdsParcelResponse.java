package com.testgroup.api;

import java.time.LocalDateTime;

/**
 * @author beata.ilowiecka@impaqgroup.com on 15.12.16.
 */
public class IdsParcelResponse {

    private Long senderId;
    private Long receiverId;
    private Long connectedPersonId;
    private Long committedById;
    private LocalDateTime commitTimestamp;
    private String parcelType;

    public IdsParcelResponse(Long senderId, Long receiverId, Long connectedPersonId, Long committedById, LocalDateTime commitTimestamp, String parcelType) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.connectedPersonId = connectedPersonId;
        this.committedById = committedById;
        this.commitTimestamp = commitTimestamp;
        this.parcelType = parcelType;
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

    public LocalDateTime getCommitTimestamp() {
        return commitTimestamp;
    }

    public void setCommitTimestamp(LocalDateTime commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }
}
