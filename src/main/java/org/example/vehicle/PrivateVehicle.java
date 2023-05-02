package org.example.vehicle;

import org.example.owner.Owner;

import java.util.Map;

public class PrivateVehicle extends Vehicle {

    public PrivateVehicle(String numberPlate, Owner owner, VehicleType type) {
        super(numberPlate, owner, type);
    }

    public PrivateVehicle(String numberPlate, Map<String,Owner> owners, VehicleType type) {
        super(numberPlate, owners, type);
    }
}
