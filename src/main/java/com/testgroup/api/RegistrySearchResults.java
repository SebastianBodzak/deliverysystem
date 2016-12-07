package com.testgroup.api;

/**
 * Created by sbod on 06.12.16.
 */
public class RegistrySearchResults {

    private Iterable<ShipmentDto> shipments;

    public RegistrySearchResults() {}

    public RegistrySearchResults(Iterable<ShipmentDto> shipments) {
        this.shipments = shipments;
    }

    public Iterable<ShipmentDto> getShipments() {
        return shipments;
    }

    public void setShipments(Iterable<ShipmentDto> shipments) {
        this.shipments = shipments;
    }
}
