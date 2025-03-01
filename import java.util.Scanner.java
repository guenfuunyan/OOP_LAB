import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input
        System.out.print("Enter first number: ");
        double num1 = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter second number: ");
        double num2 = Double.parseDouble(scanner.nextLine());

        // Calculating results
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        
        // Checking divisor for division
        Double quotient = null;
        if (num2 != 0) {
            quotient = num1 / num2;
        }

        // Displaying results
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        if (quotient != null) {
            System.out.println("Quotient: " + quotient);
        } else {
            System.out.println("Division by zero is not allowed.");
        }

        scanner.close();
    }
}