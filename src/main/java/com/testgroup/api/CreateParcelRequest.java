package com.testgroup.api;

import lombok.AllArgsConstructor;

import java.io.File;

/**
 * Created by sbod on 06.12.16.
 */
@AllArgsConstructor
public class CreateParcelRequest {

    private ParcelDto parcel;
    private File attachment;

    public void validate() {}

    public String getSender() {
        return parcel.getSender();
    }

    public String getRecipient() {
        return parcel.getRecipient();
    }

    public String getCommitedBy() {
        return parcel.getCommittedBy();
    }

    public String getConent() {
        return parcel.getContent();
    }

    public String getParcelType() {
        return parcel.getParcelType();
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
