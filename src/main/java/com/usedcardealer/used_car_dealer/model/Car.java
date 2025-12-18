package com.usedcardealer.used_car_dealer.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String make;
    
    @Column(nullable = false)
    private String model;
    
    @Column(name = "model_year", nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    private Integer mileage;
    
    @Column(length = 20)
    private String color;
    
    @Column(name = "fuel_type", length = 20)
    private String fuelType;
    
    @Column(name = "transmission", length = 20)
    private String transmission;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "date_added")
    private LocalDate dateAdded;
    
    @Column(name = "is_sold")
    private Boolean isSold = false;
    
    // Default constructor
    public Car() {
        this.dateAdded = LocalDate.now();
    }
    
    // Parameterized constructor
    public Car(String make, String model, Integer year, BigDecimal price, 
               Integer mileage, String color, String fuelType, 
               String transmission, String description) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.color = color;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.description = description;
        this.dateAdded = LocalDate.now();
        this.isSold = false;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getMileage() {
        return mileage;
    }
    
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public String getTransmission() {
        return transmission;
    }
    
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDate getDateAdded() {
        return dateAdded;
    }
    
    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public Boolean getIsSold() {
        return isSold;
    }
    
    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", mileage=" + mileage +
                ", color='" + color + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", transmission='" + transmission + '\'' +
                ", isSold=" + isSold +
                '}';
    }
}