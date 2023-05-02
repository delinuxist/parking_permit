package org.example.council;

import org.example.exception.OwnerNotRegisteredException;
import org.example.owner.Owner;
import org.example.permit.Permit;
import org.example.vehicle.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TownCouncil {
    private final Map<Integer, Permit> permitsIssued = new HashMap<>();
    private final Map<VehicleType, List<Vehicle>> vehicles = new HashMap<>();


    public Permit issuePermit(Vehicle vehicle,Owner requester) throws OwnerNotRegisteredException {
        if(!isOwnerRegistered(vehicle.getOwners(),requester)){
            throw new OwnerNotRegisteredException();
        }
        // create permit
        Permit newPermit = new Permit(vehicle,calculateCharge(vehicle));

        permitsIssued.put(newPermit.getPermitNumber(),newPermit);

        updateVehiclesList(vehicle);

        return newPermit;
    }

    private boolean isOwnerRegistered(Map<String,Owner> owners,Owner requester){
        return owners.containsKey(requester.getDriversLicense());
    }

    private Double calculateCharge(Vehicle vehicle){
        double charge;
        charge = switch (vehicle.getType()) {
            case PRIVATE -> calculatePrivateCharge();
            case TRACK -> calculateTrackCharge((SiteVehicle) vehicle);
            case MOTOR -> calculateMotorCharge((MotorBike) vehicle);
        };
        return charge;
    }


    private Double calculatePrivateCharge(){
        return 20.0;
    }

    private Double calculateTrackCharge(SiteVehicle siteVehicle) {
        double totalCharge = 30.0;
        if(siteVehicle.getCapacity() <= 150){
            return totalCharge;
        }

        int capacityRemaining = siteVehicle.getCapacity() - 150;
        double extraCharge = Math.ceil((double) capacityRemaining/20);
        totalCharge += extraCharge*5;
        return totalCharge;
    }

    private Double calculateMotorCharge(MotorBike motorBike) {
        if(motorBike.getEngineCapacity() <= 850) {
            return 7.0;
        }
        return 10.0;
    }

    private void updateVehiclesList(Vehicle vehicle) {
        if(vehicles.containsKey(vehicle.getType())){
            List<Vehicle>  listData = vehicles.get(vehicle.getType());
            listData.add(vehicle);
        }else {
            List<Vehicle>  listData = new ArrayList<>();
            listData.add(vehicle);
            vehicles.put(vehicle.getType(),listData);
        }
    }

    public Map<Integer, Permit> getPermitsIssued() {
        return permitsIssued;
    }

    public Map<VehicleType, List<Vehicle>> getVehicles() {
        return vehicles;
    }
}
