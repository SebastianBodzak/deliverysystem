package com.testgroup.controller;

import com.testgroup.api.CreateParcelRequest;
import com.testgroup.api.SendingService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sbod on 06.12.16.
 */
@RestController
@RequestMapping("/parcels")
public class ParcelsController {

    private SendingService sendingService;

    public ParcelsController(SendingService sendingService) {
        this.sendingService = sendingService;
    }

    @PutMapping("/add")
    public Long add(@RequestBody CreateParcelRequest request) {
        return sendingService.addParcel(request);
    }
}
