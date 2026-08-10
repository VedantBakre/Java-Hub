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

import java.util.Scanner;

abstract class Transport {
    protected double distance;
    protected String type;
    protected int numPassengers;

    Transport(double distance, String type, int numPassengers) {
        this.distance = distance;
        this.type = type;
        this.numPassengers = numPassengers;
    }

    public abstract double calculateFarePerPassenger();

    public void showDetails(int index) {
        double perPassenger = calculateFarePerPassenger();
        double total = perPassenger * numPassengers;
        System.out.println("Booking #" + (index + 1) + " -> " + this.getClass().getSimpleName());
        System.out.printf("  Type/Class         : %s%n", type);
        System.out.printf("  Distance (km)      : %.2f%n", distance);
        System.out.printf("  No. of Passengers  : %d%n", numPassengers);
        System.out.printf("  Fare per Passenger : %.2f%n", perPassenger);
        System.out.printf("  Total Fare         : %.2f%n", total);
        System.out.println("---------------------------------------------");
    }
}

class Bus extends Transport {
    private static final double NON_AC_RATE = 5.0;
    private static final double AC_RATE = 8.0;

    Bus(double distance, String type, int numPassengers) {
        super(distance, type, numPassengers);
    }

    @Override
    public double calculateFarePerPassenger() {
        return distance * (type.equalsIgnoreCase("AC") ? AC_RATE : NON_AC_RATE);
    }
}

class Train extends Transport {
    private static final double GENERAL_RATE = 3.0;
    private static final double SLEEPER_RATE = 5.0;
    private static final double AC_RATE = 7.0;

    Train(double distance, String type, int numPassengers) {
        super(distance, type, numPassengers);
    }

    @Override
    public double calculateFarePerPassenger() {
        String t = type.toLowerCase();
        if (t.contains("ac")) return distance * AC_RATE;
        if (t.contains("sleeper")) return distance * SLEEPER_RATE;
        return distance * GENERAL_RATE;
    }
}

class Flight extends Transport {
    private static final double ECONOMY_RATE = 40.0;
    private static final double BUSINESS_RATE = 80.0;
    private static final double FIRST_RATE = 120.0;

    Flight(double distance, String type, int numPassengers) {
        super(distance, type, numPassengers);
    }

    @Override
    public double calculateFarePerPassenger() {
        String t = type.toLowerCase();
        if (t.contains("business")) return distance * BUSINESS_RATE;
        if (t.contains("first") || t.contains("1st")) return distance * FIRST_RATE;
        return distance * ECONOMY_RATE;
    }
}

public class Assignment_06 {
    private static final Scanner sc = new Scanner(System.in);

    private static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v > 0) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a positive integer.");
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                double v = Double.parseDouble(s);
                if (v > 0) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a positive number.");
        }
    }

    private static String chooseOption(String prompt, String... options) {
        String joined = String.join(" / ", options);
        while (true) {
            System.out.print(prompt + " (" + joined + "): ");
            String ans = sc.nextLine().trim();
            if (ans.isEmpty()) continue;
            for (String opt : options) {
                if (ans.equalsIgnoreCase(opt) || ans.toLowerCase().contains(opt.toLowerCase())) {
                    return opt;
                }
            }
            System.out.println("Invalid option. Try one of: " + joined);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Transport Booking System (Simple) ===");
        int n = readPositiveInt("How many bookings would you like to enter? ");

        Transport[] bookings = new Transport[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for booking #" + (i + 1));
            int choice = readPositiveInt("Select Transport Type: 1) Bus  2) Train  3) Flight  (enter 1-3): ");
            double distance = readPositiveDouble("Enter distance in kilometers: ");
            String type;
            if (choice == 1) {
                type = chooseOption("Enter Bus Type", "AC", "Non-AC");
                bookings[i] = new Bus(distance, type, readPositiveInt("Enter number of passengers: "));
            } else if (choice == 2) {
                type = chooseOption("Enter Train Class", "General", "Sleeper", "AC");
                bookings[i] = new Train(distance, type, readPositiveInt("Enter number of passengers: "));
            } else {
                type = chooseOption("Enter Flight Class", "Economy", "Business", "First");
                bookings[i] = new Flight(distance, type, readPositiveInt("Enter number of passengers: "));
            }
            System.out.println("Booking recorded.");
        }

        System.out.println("\n=== Booking Summary ===");
        double grandTotal = 0.0;
        for (int i = 0; i < bookings.length; i++) {
            bookings[i].showDetails(i);
            grandTotal += bookings[i].calculateFarePerPassenger() * bookings[i].numPassengers;
        }
        System.out.printf("Grand Total for all bookings: %.2f%n", grandTotal);
        sc.close();
    }
}
}
