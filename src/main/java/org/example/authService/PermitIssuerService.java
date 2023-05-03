package org.example.authService;

import org.example.vehicle.Vehicle;

public interface PermitIssuerService {
    /**
     * issue a  permit
     * @param vehicle vehicle to issue the permit for
     * @return String - empty string if the permit was not issued
     */
    String issuePermit(Vehicle vehicle);


}
