package com.testgroup.api;

/**
 * Created by sbod on 06.12.16.
 */
public class ShipmentCriteria {

    private String createdFrom;
    private String createdUntil;
    private Long senderId;
    private Long recipientId;
    private String parcelType;

    public boolean isCreatedDatesDefined() {
        return createdFrom != null || createdUntil != null;
    }

    public boolean isCreatedFromDefined() {
        return createdFrom != null;
    }

    public boolean isCreatedUntilDefined() {
        return createdUntil != null;
    }

    public boolean isSenderIdDefined() {
        return senderId != null;
    }

    public boolean isRecipientIdDefined() {
        return recipientId != null;
    }

    public boolean isParcelTypeDefined() {
        return parcelType != null;
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    public String getCreatedUntil() {
        return createdUntil;
    }

    public void setCreatedUntil(String createdUntil) {
        this.createdUntil = createdUntil;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }
}
