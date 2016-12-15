package com.testgroup.controller;

import com.testgroup.api.*;
import com.testgroup.api.dtos.CreateParcelRequest;
import com.testgroup.api.dtos.IdsParcelResponse;
import com.testgroup.api.dtos.StringParcelResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * Created by sbod on 06.12.16.
 */
@RestController
@RequestMapping("/parcels")
public class ParcelsController {

    private SendingService sendingService;
    private PackageInformationReceiver packageInformationReceiver;

    public ParcelsController(SendingService sendingService, PackageInformationReceiver packageInformationReceiver) {
        this.sendingService = sendingService;
        this.packageInformationReceiver = packageInformationReceiver;
    }

    @PutMapping("/add")
    public Long add(@RequestBody CreateParcelRequest request) {
        return sendingService.addParcel(request);
    }

    @GetMapping("/count")
    public BigInteger getParcelsCount() {
        return packageInformationReceiver.getParcelsCount();
    }

    @GetMapping("/string/{id}")
    public StringParcelResponse geParcelAsString(@PathVariable Long id) {
        return sendingService.getStringParcel(id);
    }

    @GetMapping("/asIds/{id}")
    public IdsParcelResponse geParcelAsIds(@PathVariable Long id) {
        return sendingService.getParcelDataIds(id);
    }

    @GetMapping("/{name}")
    public Object[] getParcelIdsBySenderName(@PathVariable String name) {
        return sendingService.getParcelIdsBySender(name);
    }
}