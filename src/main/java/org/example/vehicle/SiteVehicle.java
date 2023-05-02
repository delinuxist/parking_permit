package org.example.vehicle;

import org.example.owner.Owner;

import java.util.Map;

public class SiteVehicle extends Vehicle{
    private final Integer capacity;
    public SiteVehicle(String numberPlate, Owner owner, VehicleType type, Integer capacity) {
        super(numberPlate, owner, type);
        this.capacity = capacity;
    }

    public SiteVehicle(String numberPlate, Map<String,Owner> owners, VehicleType type, Integer capacity) {
        super(numberPlate, owners, type);
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
