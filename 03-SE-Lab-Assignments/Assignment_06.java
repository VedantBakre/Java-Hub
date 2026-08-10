/*
Problem Statement:
Create a Java program that demonstrates an Array of Objects and Hierarchical Inheritance
using a base abstract class Transport and three subclasses: Bus, Train, and Flight.
The base class should declare an abstract method calculateFare() which each subclass
overrides to compute the fare according to its type and distance. Each booking stores
distance, a category/type (e.g., Bus: AC/NON-AC, Train: General/Sleeper/AC, Flight: Economy/Business/First),
and the number of passengers booked. The program should accept multiple bookings from the user,
store them in an array of Transport references (showing polymorphism), compute fares per passenger,
and display per-booking and overall totals.
*/

// Base class 
abstract class Transport { 
    double distance; 

    Transport(double distance) { 
        this.distance = distance; 
    } 

    abstract double calculateFare(); 
} 

// Bus class 
class Bus extends Transport { 
    Bus(double distance) { 
        super(distance); 
    } 

    @Override 
    double calculateFare() { 
        return distance * 5; // Rs.5 per km 
    } 
} 

// Train class 
class Train extends Transport { 
    String travelClass; 

    Train(double distance, String travelClass) { 
        super(distance); 
        this.travelClass = travelClass; 
    } 

    @Override 
    double calculateFare() { 
        if (travelClass.equalsIgnoreCase("Sleeper")) 
            return distance * 3; 
        else 
            return distance * 6; 
    } 
} 

// Flight class 
class Flight extends Transport { 
    String travelClass; 

    Flight(double distance, String travelClass) { 
        super(distance); 
        this.travelClass = travelClass; 
    } 

    @Override 
    double calculateFare() { 
        if (travelClass.equalsIgnoreCase("Economy")) 
            return distance * 10; 
        else 
            return distance * 20; 
    } 
} 

// Main class 
public class Assignment_05 { 
    public static void main(String[] args) { 
        Transport[] bookings = new Transport[3]; 

        bookings[0] = new Bus(100); 
        bookings[1] = new Train(200, "Sleeper"); 
        bookings[2] = new Flight(500, "Business"); 

        for (Transport t : bookings) { 
            System.out.println("Fare: Rs. " + t.calculateFare()); 
        } 
    } 
}

}
