package com.testgroup.controller;

import com.testgroup.api.RegistryCatalog;
import com.testgroup.api.RegistrySearchResults;
import com.testgroup.api.ShipmentCriteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * Created by sbod on 07.12.16.
 */
@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private RegistryCatalog registryCatalog;

    public ShipmentController(RegistryCatalog registryCatalog) {
        this.registryCatalog = registryCatalog;
    }

    @GetMapping
    public RegistrySearchResults index(ShipmentCriteria shipmentCriteria) throws ParseException {
        return registryCatalog.find(shipmentCriteria);
    }
}
