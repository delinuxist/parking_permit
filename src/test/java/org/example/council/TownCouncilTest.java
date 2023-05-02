package org.example.council;

import org.example.exception.OwnerNotRegisteredException;
import org.example.owner.Owner;
import org.example.vehicle.MotorBike;
import org.example.vehicle.PrivateVehicle;
import org.example.vehicle.SiteVehicle;
import org.example.vehicle.VehicleType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TownCouncilTest {
TownCouncil underTest;
Owner owner1;
Owner owner2;

@BeforeEach
    void setup() {
    underTest = new TownCouncil();
    owner1 = new Owner("Jake","234sdfasdfss");
    owner2 = new Owner("Mike","sdfd8777989");

}

    @Test
    void testThrowOwnerNotRegisteredException() {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);

        // then
        assertThrows(OwnerNotRegisteredException.class,()-> underTest.issuePermit(pv1,owner2));
    }

    @Test
    void testDoesNotThrowException() {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);

        // then
        assertDoesNotThrow(()->underTest.issuePermit(pv1,owner1));
    }

    @Test
    void testPermitsIssuedIncreases() throws OwnerNotRegisteredException {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);

        // when
        underTest.issuePermit(pv1,owner1);
        // then
        assertEquals(1,underTest.getPermitsIssued().size());
    }

    @Test
    void testVehicleListUpdated() throws OwnerNotRegisteredException {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);

        // when
        underTest.issuePermit(pv1,owner1);
        // then
        assertEquals(1,underTest.getVehicles().size());
    }

    @Test
    void testVehicleTypeListIncreaseToTwo() throws OwnerNotRegisteredException {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);
        PrivateVehicle pv2 = new PrivateVehicle("GH-2281-2",owner2, VehicleType.PRIVATE);

        // when
        underTest.issuePermit(pv1,owner1);
        underTest.issuePermit(pv2,owner2);
        var privateTypeList = underTest.getVehicles().get(VehicleType.PRIVATE);
        // then
        assertEquals(2,privateTypeList.size());
    }

    @Test
    void testIfVehicleListContainsTwoTypesOfVehicle() throws OwnerNotRegisteredException {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);
        MotorBike mv1 = new MotorBike("GH-8080-3",owner2,VehicleType.MOTOR,850);

        // when
        underTest.issuePermit(pv1,owner1);
        underTest.issuePermit(mv1,owner2);
        // then
        assertEquals(2,underTest.getVehicles().size());
    }

    @Test
    void testPrivateVehicleReturnsDefaultCharge() throws OwnerNotRegisteredException {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);

        // when
        var permit = underTest.issuePermit(pv1,owner1);
        // then
        assertEquals(20.0,permit.getCharge());
    }

    @Test
    void testMotorBikeReturnsDefaultCharge() throws OwnerNotRegisteredException {
        // given
        MotorBike mv1 = new MotorBike("GH-8080-3",owner2,VehicleType.MOTOR,350);

        // when
        var permit = underTest.issuePermit(mv1,owner2);
        // then
        assertEquals(7.0,permit.getCharge());
    }

    @Test
    void testMotorBikeReturnsExtraCharge() throws OwnerNotRegisteredException {
        // given
        MotorBike mv1 = new MotorBike("GH-8080-3",owner2,VehicleType.MOTOR,900);

        // when
        var permit = underTest.issuePermit(mv1,owner2);
        // then
        assertEquals(10.0,permit.getCharge());
    }

    @Test
    void testSiteVehicleReturnsDefaultCharge() throws OwnerNotRegisteredException {
        // given
        SiteVehicle sv1 = new SiteVehicle("GH-8080-3",owner2,VehicleType.TRACK,150);

        // when
        var permit = underTest.issuePermit(sv1,owner2);
        // then
        assertEquals(30.0,permit.getCharge());
    }

    @Test
    void testSiteVehicleReturnsChargePlusExtraCharge() throws OwnerNotRegisteredException {
        // given
        SiteVehicle sv1 = new SiteVehicle("GH-8080-3",owner2,VehicleType.TRACK,200);

        // when
        var permit = underTest.issuePermit(sv1,owner2);
        // then
        assertEquals(45.0,permit.getCharge());
    }

    @Test
    void testShouldReturnListOfVehiclesOfSameType() throws OwnerNotRegisteredException {
        // given
        PrivateVehicle pv1 = new PrivateVehicle("GH-481-2",owner1, VehicleType.PRIVATE);
        PrivateVehicle pv2 = new PrivateVehicle("GH-2281-2",owner2, VehicleType.PRIVATE);

        // when
        underTest.issuePermit(pv1,owner1);
        underTest.issuePermit(pv2,owner2);

        var vehicleList = underTest.getVehiclesByType(VehicleType.PRIVATE);
        // then
        assertEquals(2,vehicleList.size());
    }

}