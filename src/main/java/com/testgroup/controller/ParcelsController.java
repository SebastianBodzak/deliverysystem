package com.testgroup.controller;

import com.testgroup.api.CreateParcelRequest;
import com.testgroup.api.SendingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sbod on 06.12.16.
 */
@RestController
@RequestMapping("/parcels")
@AllArgsConstructor
public class ParcelsController {

    private SendingService sendingService;

    @PutMapping("/add")
    public Long add(@RequestBody CreateParcelRequest request) {
        return sendingService.addParcel(request);
    }
}
