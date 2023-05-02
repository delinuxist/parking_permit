package org.example.vehicle;

import org.example.owner.Owner;

import java.util.Map;

public class MotorBike extends Vehicle{
    private final Integer engineCapacity;

    public MotorBike(String numberPlate, Owner owner, VehicleType type, Integer engineCapacity) {
        super(numberPlate, owner, type);
        this.engineCapacity = engineCapacity;
    }

    public MotorBike(String numberPlate, Map<String,Owner> owners, VehicleType type, Integer engineCapacity) {
        super(numberPlate, owners, type);
        this.engineCapacity = engineCapacity;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }
}
