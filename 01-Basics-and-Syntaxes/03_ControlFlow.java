public class ControlFlow {
    public static void main(String[] args) {
        int number = 15;

        if (number % 2 == 0) {
            System.out.println(number + " is Even.");
        } else {
            System.out.println(number + " is Odd.");
        }

        System.out.println("Counting from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Step " + i);
        }
    }
}
