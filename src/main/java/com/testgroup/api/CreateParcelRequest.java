package com.testgroup.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * Created by sbod on 06.12.16.
 */
@AllArgsConstructor
public class CreateParcelRequest {

    private ParcelDto parcel;
    private File attachment;

    public void validate() {}

    public String getConent() {
        return parcel.getContent();
    }

    public String getParcelType() {
        return parcel.getParcelType();
    }

    public Long getSenderId() {
        return parcel.getSender();
    }

    public Long getRecipientId() {
        return parcel.getRecipient();
    }

    public String getOverwriteRecipient() {
        return parcel.getOverwriteRecipientData();
    }

    public ParcelDto getParcel() {
        return parcel;
    }

    public void setParcel(ParcelDto parcel) {
        this.parcel = parcel;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }
}
