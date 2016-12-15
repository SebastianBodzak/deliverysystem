package com.testgroup.api;

import lombok.AllArgsConstructor;

/**
 * Created by sbod on 06.12.16.
 */
@AllArgsConstructor
public class ParcelDto {

    private String sender;
    private String recipient;
    private String committedBy;
    private String parcelType;
    private String content;
    private String overwriteRecipientData;

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

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOverwriteRecipientData() {
        return overwriteRecipientData;
    }

    public void setOverwriteRecipientData(String overwriteRecipientData) {
        this.overwriteRecipientData = overwriteRecipientData;
    }
}
