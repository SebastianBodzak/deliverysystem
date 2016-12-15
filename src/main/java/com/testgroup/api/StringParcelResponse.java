package com.testgroup.api;

import java.time.LocalDateTime;

/**
 * @author beata.ilowiecka@impaqgroup.com on 15.12.16.
 */
public class StringParcelResponse {

    private String sender;
    private String recipient;
    private String committedBy;
    private String connectedPersonId;
    private String parcelType;
    private LocalDateTime commitTimestamp;

    public StringParcelResponse(String sender, String recipient, String committedBy, String connectedPersonId, String parcelType, LocalDateTime commitTimestamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.committedBy = committedBy;
        this.connectedPersonId = connectedPersonId;
        this.parcelType = parcelType;
        this.commitTimestamp = commitTimestamp;
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

    public String getConnectedPersonId() {
        return connectedPersonId;
    }

    public void setConnectedPersonId(String connectedPersonId) {
        this.connectedPersonId = connectedPersonId;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public LocalDateTime getCommitTimestamp() {
        return commitTimestamp;
    }

    public void setCommitTimestamp(LocalDateTime commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }
}
