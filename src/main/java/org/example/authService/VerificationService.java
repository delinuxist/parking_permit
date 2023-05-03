package org.example.authService;

import org.example.owner.Owner;
import org.example.vehicle.Vehicle;

public interface VerificationService {
    boolean verifyPerson(Owner owner, Vehicle vehicle);
}
