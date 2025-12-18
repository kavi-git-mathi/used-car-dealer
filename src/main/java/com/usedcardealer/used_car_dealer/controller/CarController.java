package com.usedcardealer.used_car_dealer.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usedcardealer.used_car_dealer.model.Car;
import com.usedcardealer.used_car_dealer.model.CarRepository;

@Controller
public class CarController {
    
    private final CarRepository carRepository;
    
    // Use constructor injection (recommended)
    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
        System.out.println("=== DEBUG: CarController created with repository ===");
    }
    
    // Display all cars
    @GetMapping("/cars")
    public String getAllCars(Model model) {
        System.out.println("=== DEBUG: getAllCars() called ===");
        List<Car> cars = carRepository.findAll();
        System.out.println("Found " + cars.size() + " cars");
        model.addAttribute("cars", cars);
        model.addAttribute("title", "Our Inventory");
        return "cars";
    }
    
    // Display available cars (not sold)
    @GetMapping("/cars/available")
    public String getAvailableCars(Model model) {
        List<Car> cars = carRepository.findByIsSoldFalse();
        model.addAttribute("cars", cars);
        model.addAttribute("title", "Available Cars");
        return "cars";
    }
    
    // Display car details by ID
    @GetMapping("/cars/{id}")
    public String getCarDetails(@PathVariable Long id, Model model) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            model.addAttribute("car", carOptional.get());
            model.addAttribute("title", "Car Details");
            return "car-details";
        } else {
            return "redirect:/cars";
        }
    }
    
    // Search cars by make
    @GetMapping("/cars/search")
    public String searchCars(@RequestParam(required = false) String make, 
                           @RequestParam(required = false) Integer maxPrice,
                           Model model) {
        List<Car> cars;
        
        if (make != null && !make.isEmpty()) {
            cars = carRepository.findByMake(make);
            model.addAttribute("searchMake", make);
        } else if (maxPrice != null) {
            cars = carRepository.findByPriceLessThanEqual(BigDecimal.valueOf(maxPrice));
            model.addAttribute("searchMaxPrice", maxPrice);
        } else {
            cars = carRepository.findAll();
        }
        
        model.addAttribute("cars", cars);
        model.addAttribute("title", "Search Results");
        return "cars";
    }
    
    // Test endpoint for debugging
    @GetMapping("/cars/test-simple")
    @ResponseBody
    public String testSimple() {
        long count = carRepository.count();
        return "Cars in DB: " + count + ". Go to <a href='/cars'>/cars</a>";
    }
    
    // Debug endpoint
    @GetMapping("/debug")
    @ResponseBody
    public String debug() {
        long count = carRepository.count();
        StringBuilder result = new StringBuilder();
        result.append("<h1>Debug Info</h1>");
        result.append("<p>Total cars: ").append(count).append("</p>");
        
        if (count > 0) {
            result.append("<ul>");
            carRepository.findAll().forEach(car -> {
                result.append("<li>")
                      .append(car.getYear()).append(" ")
                      .append(car.getMake()).append(" ")
                      .append(car.getModel()).append(" - $")
                      .append(car.getPrice())
                      .append("</li>");
            });
            result.append("</ul>");
        } else {
            result.append("<p>No cars found in database!</p>");
        }
        
        return result.toString();
    }
}