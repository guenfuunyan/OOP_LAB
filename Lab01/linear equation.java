import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose the type of equation to solve:");
            System.out.println("1. First-degree equation (ax + b = 0)");
            System.out.println("2. System of first-degree equations with two variables");
            System.out.println("3. Second-degree equation (ax^2 + bx + c = 0)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    solveLinearEquation(scanner);
                    break;
                case 2:
                    solveLinearSystem(scanner);
                    break;
                case 3:
                    solveQuadraticEquation(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Solve ax + b = 0
    public static void solveLinearEquation(Scanner scanner) {
        System.out.print("Enter coefficient a: ");
        double a = scanner.nextDouble();
        if (a == 0) {
            System.out.println("Coefficient 'a' must not be zero.");
            return;
        }
        System.out.print("Enter coefficient b: ");
        double b = scanner.nextDouble();
        
        double x = -b / a;
        System.out.println("Solution: x = " + x);
    }

    // Solve system of equations
    public static void solveLinearSystem(Scanner scanner) {
        System.out.println("Enter coefficients for the system:");
        System.out.print("a11: ");
        double a11 = scanner.nextDouble();
        System.out.print("a12: ");
        double a12 = scanner.nextDouble();
        System.out.print("b1: ");
        double b1 = scanner.nextDouble();
        System.out.print("a21: ");
        double a21 = scanner.nextDouble();
        System.out.print("a22: ");
        double a22 = scanner.nextDouble();
        System.out.print("b2: ");
        double b2 = scanner.nextDouble();

        double D = a11 * a22 - a12 * a21;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D == 0) {
            if (D1 == 0 && D2 == 0) {
                System.out.println("The system has infinitely many solutions.");
            } else {
                System.out.println("The system has no solution.");
            }
        } else {
            double x1 = D1 / D;
            double x2 = D2 / D;
            System.out.println("Solution: x1 = " + x1 + ", x2 = " + x2);
        }
    }

    // Solve ax^2 + bx + c = 0
    public static void solveQuadraticEquation(Scanner scanner) {
        System.out.print("Enter coefficient a: ");
        double a = scanner.nextDouble();
        if (a == 0) {
            System.out.println("Coefficient 'a' must not be zero.");
            return;
        }
        System.out.print("Enter coefficient b: ");
        double b = scanner.nextDouble();
        System.out.print("Enter coefficient c: ");
        double c = scanner.nextDouble();

        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("Two distinct roots: x1 = " + x1 + ", x2 = " + x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            System.out.println("One double root: x = " + x);
        } else {
            System.out.println("No real solution.");
        }
    }
}
