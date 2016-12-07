package com.testgroup.api;

import com.testgroup.domain.ParcelType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sbod on 06.12.16.
 */
public class ShipmentDto {

    private Long shipmentId;
    private Long parcelId;
    private String date;
    private Long senderId;
    private Long recipientId;
    private ParcelType parcelType;

    public ShipmentDto(Long shipmentId, Long parcelId, Date date, Long senderId, Long recipientId, ParcelType parcelType) {
        this.shipmentId = shipmentId;
        this.parcelId = parcelId;
        this.date = new SimpleDateFormat("yyyy/MM/dd").format(date);
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.parcelType = parcelType;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
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

    public ParcelType getParcelType() {
        return parcelType;
    }

    public void setParcelType(ParcelType parcelType) {
        this.parcelType = parcelType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
