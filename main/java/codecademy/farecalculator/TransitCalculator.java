/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecademy.farecalculator;

/**
 *
 * @author Stephen
 * A Project through codecademy to calculate the best fare price for different tickets
 */
public class TransitCalculator {
    // The number of days in a period of time 
    int numberOfDays;
    // The number of rides during the period of days
    int numberOfIndividualRides;
    // The age of a person
    int personAge;
    // If the person has a disability
    boolean personDisability;
    // Current possible Fare Types
    String[] fareTypes = {"Pay-per-ride", "7-day Unlimited Rides", "30-day Unlimited Rides"};
    // The standard prices for the different Fare Types
    double[] farePrices = {2.75, 33.00, 127.00};
    // The reduced prices for the different Fare Types
    double[] fareReducedPrices = {1.35, 16.50, 63.50};
    
    public TransitCalculator(int nod, int nor, int age, boolean disability) {
        numberOfDays = nod;
        numberOfIndividualRides = nor;
        personAge = age;
        personDisability = disability;
    }
    
    // Calculating the price ratio for 7-day Unlimited Rides
    public double unlimited7Price() {
        int numberOfTickets;
        double totalPrice;
        double pricePerRide;
        
        if (numberOfDays % 7 > 0) {
            numberOfTickets = (numberOfDays / 7) + 1; 
        } else {
            numberOfTickets = numberOfDays / 7;
        }
        
        if (personAge >= 65 || personDisability) {
            totalPrice = numberOfTickets * fareReducedPrices[1];
        } else {
            totalPrice = numberOfTickets * farePrices[1];
        }
        
        pricePerRide = totalPrice / numberOfIndividualRides;
        
        return Math.round(pricePerRide * 100.0) / 100.0;
    }
    
    // Calculating the price ratio for 30-day Unlimited Rides
    public double unlimited30Price() {
        int numberOfTickets;
        double totalPrice;
        double pricePerRide;
        
        if (numberOfDays % 30 > 0) {
            numberOfTickets = (numberOfDays / 30) + 1; 
        } else {
            numberOfTickets = numberOfDays / 30;
        }
        
        if (personAge >= 65 || personDisability) {
            totalPrice = numberOfTickets * fareReducedPrices[2];
        } else {
            totalPrice = numberOfTickets * farePrices[2];
        }
        
        pricePerRide = totalPrice / numberOfIndividualRides;
        
        return Math.round(pricePerRide * 100.0) / 100.0;
    }
    
    // Retrieving the price per ride for standard or reduced types
    public double payPerRidePrice() {
        if (personAge >= 65 || personDisability) {
            return fareReducedPrices[0];
        }
        
        return farePrices[0];
    }
    
    public double[] getRidePrices() {
        double[] ridePrices = {
            payPerRidePrice(),
            unlimited7Price(),
            unlimited30Price()
        };
        
        return ridePrices;
    }
    
    // Retrieving the cheapest price ratio of the different Fair Types
    public String getBestFare() {
        double[] ridePrices = getRidePrices();
        double cheapestPrice = 0;
        int indexOfCheapest = 0;
        String result;
        
        for (int i = 0; i < fareTypes.length; i++) {
            if (ridePrices[i] < cheapestPrice || cheapestPrice == 0) {
                cheapestPrice = ridePrices[i];
                indexOfCheapest = i;
            }
        }
        
        // Print cheapest Fare Type ticket to the screen
        result = "You should get the " 
                    + fareTypes[indexOfCheapest] 
                    + " option at $" + ridePrices[indexOfCheapest];
        
        return result;
    }
    
    public static void main(String[] args) {
        TransitCalculator transitCalculator = new TransitCalculator(26, 54, 64, true);
        
        System.out.println(transitCalculator.getBestFare());
    }
}