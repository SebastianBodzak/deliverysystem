package com.testgroup.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sbod on 06.12.16.
 */
@AllArgsConstructor
public class ParcelDto {

    private Long sender; //beacuse lack of sessions
    private Long recipient;
    private String parcelType;
    private String content;
    private String overwriteRecipientData;

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
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
