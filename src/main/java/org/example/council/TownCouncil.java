package org.example.council;

import org.example.authService.PermitIssuerService;
import org.example.authService.VerificationService;
import org.example.exception.OwnerNotRegisteredException;
import org.example.exception.PermitIssueFailedException;
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

    private final VerificationService verificationService;

    private final PermitIssuerService permitIssuerService;

    public TownCouncil(VerificationService verificationService,PermitIssuerService permitIssuerService){
        this.verificationService = verificationService;
        this.permitIssuerService = permitIssuerService;
    }


    public Permit issuePermit(Vehicle vehicle,Owner requester) throws OwnerNotRegisteredException, PermitIssueFailedException {
        if(!isOwnerRegistered(vehicle.getOwners(),requester,vehicle)){
            throw new OwnerNotRegisteredException();
        }
        // create permit
        Permit newPermit;
        if(vehicle.getType() == VehicleType.PRIVATE || vehicle.getType() == VehicleType.MOTOR){
            if(permitIssuerService.issuePermit(vehicle).equals("")){
                throw new PermitIssueFailedException();
            }
        }

        newPermit = new Permit(vehicle,calculateCharge(vehicle));
        permitsIssued.put(newPermit.getPermitNumber(),newPermit);

        updateVehiclesList(vehicle);

        return newPermit;
    }

    private boolean isOwnerRegistered(Map<String,Owner> owners,Owner requester,Vehicle vehicle){
        return owners.containsKey(requester.getDriversLicense()) && verificationService.verifyPerson(requester,vehicle);
    }

    private Double calculateCharge(Vehicle vehicle){
        double charge;
        charge = switch (vehicle.getType()) {
            case PRIVATE -> vehicle.getBaseCharge();
            case TRACK -> calculateTrackCharge((SiteVehicle) vehicle);
            case MOTOR -> calculateMotorCharge((MotorBike) vehicle);
        };
        return charge;
    }

    private Double calculateTrackCharge(SiteVehicle siteVehicle) {
        double totalCharge = siteVehicle.getBaseCharge();
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
            return motorBike.getBaseCharge();
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

    public List<Vehicle> getVehiclesByType(VehicleType type) {
        return this.vehicles.get(type);
    }

    public Permit getPermit(Integer permitNumber) {
        return permitsIssued.get(permitNumber);
    }
}
