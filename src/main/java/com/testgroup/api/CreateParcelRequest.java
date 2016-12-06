package com.testgroup.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * Created by sbod on 06.12.16.
 */
@Setter
@Getter
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
        return parcel.getSenderId();
    }

    public Long getRecipientId() {
        return parcel.getRecipientId();
    }

    public String getOverwriteRecipient() {
        return parcel.getOverwriteRecipientData();
    }
}
