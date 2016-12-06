package com.testgroup.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sbod on 06.12.16.
 */
@Setter
@Getter
@AllArgsConstructor
public class ParcelDto {

    private Long sender; //beacuse lack of sessions
    private Long recipient;
    private String parcelType;
    private String content;
    private String overwriteRecipientData;
}
