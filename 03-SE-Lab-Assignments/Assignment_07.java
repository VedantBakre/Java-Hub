import java.util.Scanner;

class NumberPrinter {

    private int start;
    private int end;

    NumberPrinter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    synchronized void printEven() {
        System.out.println("Even Numbers:");

        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
  
    synchronized void printOdd() {
        System.out.println("Odd Numbers:");

        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }
    }
}

class EvenThread extends Thread {

    private NumberPrinter printer;

    EvenThread(NumberPrinter printer) {
        this.printer = printer;
    }

    public void run() {
        printer.printEven();
    }
}

class OddThread extends Thread {

    private NumberPrinter printer;

    OddThread(NumberPrinter printer) {
        this.printer = printer;
    }

    public void run() {
        printer.printOdd();
    }
}

public class Assignment_07 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter starting number: ");
            int start = sc.nextInt();

            System.out.print("Enter ending number: ");
            int end = sc.nextInt();

            if (start > end) {
                throw new IllegalArgumentException(
                    "Invalid range! Starting number must be less than or equal to ending number."
                );
            }

            NumberPrinter printer = new NumberPrinter(start, end);

            EvenThread evenThread = new EvenThread(printer);
            OddThread oddThread = new OddThread(printer);

            evenThread.start();
            oddThread.start();
          
            evenThread.join();
            oddThread.join();

            System.out.println("Both threads completed successfully.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter valid integers.");

        } finally {
            sc.close();
        }
    }
}
