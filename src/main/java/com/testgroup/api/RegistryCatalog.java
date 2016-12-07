package com.testgroup.api;

import java.text.ParseException;

/**
 * Created by sbod on 06.12.16.
 */
public interface RegistryCatalog {

    RegistrySearchResults find(ShipmentCriteria shipmentCriteria) throws ParseException;
}
