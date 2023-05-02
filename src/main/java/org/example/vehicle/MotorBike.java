package org.example.vehicle;

import org.example.owner.Owner;

public class MotorBike extends Vehicle{
    private final Integer engineCapacity;

    public MotorBike(String numberPlate, Owner owner, VehicleType type, Integer engineCapacity) {
        super(numberPlate, owner, type);
        this.engineCapacity = engineCapacity;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }
}
