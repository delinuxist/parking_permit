package org.example.permit;

import org.example.vehicle.Vehicle;

public class Permit {
    private final Vehicle vehicle;
    private final Integer permitNumber;
    private Double charge;

    private static int counter;


    public Permit(Vehicle vehicle, Double charge) {
        counter++;
        this.vehicle = vehicle;
        this.permitNumber = counter;
        this.charge = charge;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Integer getPermitNumber() {
        return permitNumber;
    }

    public Double getCharge() {
        return charge;
    }

}
