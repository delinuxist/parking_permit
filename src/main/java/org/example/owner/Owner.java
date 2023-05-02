package org.example.owner;

public class Owner {
    private final String name;
    private String driversLicense;
    private boolean registered;

    public Owner(String name, String driversLicense){
        this.name = name;
        this.driversLicense = driversLicense;
    }

    public String getName() {
        return name;
    }

    public String getDriversLicense() {
        return driversLicense;
    }

    public boolean isRegistered() {
        return registered;
    }
}
