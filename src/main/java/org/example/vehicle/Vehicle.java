package org.example.vehicle;

import org.example.owner.Owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vehicle {
    private final String numberPlate;

    private Map<String,Owner> owners = new HashMap<>();

    private final VehicleType type;

    // single owner
    Vehicle(String numberPlate,Owner owner,VehicleType type) {
        this.numberPlate = numberPlate;
        this.type = type;
        owners.put(owner.getDriversLicense(),owner);
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
}
