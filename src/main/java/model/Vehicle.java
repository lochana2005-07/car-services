package model;

public class Vehicle {
    private String vehicleNumber;
    private String brand;
    private String model;
    private int year;

    public Vehicle(String vehicleNumber, String brand, String model, int year) {
        this.vehicleNumber = vehicleNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getVehicleNumber() { 
        return vehicleNumber; 
    }
    public String getBrand() { 
        return brand; 
    }
    public String getModel() { 
        return model; 
    }
    public int getYear() { 
        return year; 
    }
}
