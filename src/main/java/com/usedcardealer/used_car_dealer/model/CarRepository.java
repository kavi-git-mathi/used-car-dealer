package com.usedcardealer.used_car_dealer.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;  // Add this import
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    
    // Find cars by make
    List<Car> findByMake(String make);
    
    // Find cars by year range
    List<Car> findByYearBetween(Integer startYear, Integer endYear);
    
    // Find cars by price less than
    List<Car> findByPriceLessThanEqual(BigDecimal maxPrice);  // This line needs BigDecimal
    
    // Find available (not sold) cars
    List<Car> findByIsSoldFalse();
    
    // Find cars by make and model
    List<Car> findByMakeAndModel(String make, String model);
}