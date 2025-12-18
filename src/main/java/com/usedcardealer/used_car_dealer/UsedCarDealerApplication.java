package com.usedcardealer.used_car_dealer;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.usedcardealer.used_car_dealer.model.Car;
import com.usedcardealer.used_car_dealer.model.CarRepository;

@SpringBootApplication
public class UsedCarDealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsedCarDealerApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(CarRepository carRepository) {
        return (args) -> {
            // Clear existing data
            carRepository.deleteAll();
            
            // Add sample cars
            Car car1 = new Car("Toyota", "Camry", 2020, 
                new BigDecimal("18500.00"), 45000, "Silver", 
                "Gasoline", "Automatic", "Well-maintained sedan with leather seats and sunroof.");
            
            Car car2 = new Car("Honda", "Civic", 2019, 
                new BigDecimal("16500.00"), 38000, "Blue", 
                "Gasoline", "Automatic", "Excellent condition, one owner, all service records available.");
            
            Car car3 = new Car("Ford", "F-150", 2021, 
                new BigDecimal("32000.00"), 22000, "Black", 
                "Gasoline", "Automatic", "4x4 XLT trim with tow package and bed liner.");
            
            Car car4 = new Car("BMW", "3 Series", 2018, 
                new BigDecimal("28500.00"), 35000, "White", 
                "Gasoline", "Automatic", "Luxury sedan with premium package and navigation.");
            
            carRepository.save(car1);
            carRepository.save(car2);
            carRepository.save(car3);
            carRepository.save(car4);
            
            System.out.println("=== Sample data loaded successfully ===");
            System.out.println("Total cars in database: " + carRepository.count());
            
            // Print all cars for verification
            carRepository.findAll().forEach(car -> 
                System.out.println(" - " + car.getYear() + " " + car.getMake() + " " + car.getModel())
            );
        };
    }
}