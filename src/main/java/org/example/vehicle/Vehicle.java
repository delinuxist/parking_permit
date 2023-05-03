package org.example.vehicle;

import org.example.owner.Owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Vehicle {
    private final String numberPlate;

    private Map<String,Owner> owners = new HashMap<>();

    private final VehicleType type;

    protected double baseCharge;

    // single owner
    Vehicle(String numberPlate,Owner owner,VehicleType type) {
        this.numberPlate = numberPlate;
        this.type = type;
        owners.put(owner.getDriversLicense(),owner);
    }

    Vehicle(String numberPlate,Map<String,Owner> owners,VehicleType type) {
        this.numberPlate = numberPlate;
        this.type = type;
        this.owners.putAll(owners);
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public Map<String,Owner> getOwners() {
        return owners;
    }

    public VehicleType getType() {
        return type;
    }

    public double getBaseCharge() {
        return baseCharge;
    }
}
