import java.util.Scanner;

// Calculator class demonstrating method overloading
class Calculator {

    // Overloaded methods for addition
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Subtraction method
    int subtract(int a, int b) {
        return a - b;
    }

    // Multiplication method
    double multiply(double a, double b) {
        return a * b;
    }

    // Division method with simple exception handling
    double divide(int a, int b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return 0;
        }
        return (double) a / b;
    }
}

// Main class for user interaction
public class CalculatorApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();
        int choice;

        do {
            System.out.println("\n--- Calculator Application ---");
            System.out.println("1. Add Numbers");
            System.out.println("2. Subtract Numbers");
            System.out.println("3. Multiply Numbers");
            System.out.println("4. Divide Numbers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter first number: ");
                    int a1 = sc.nextInt();
                    System.out.print("Enter second number: ");
                    int b1 = sc.nextInt();
                    System.out.println("Result: " + calc.add(a1, b1));
                    break;

                case 2:
                    System.out.print("Enter first number: ");
                    int a2 = sc.nextInt();
                    System.out.print("Enter second number: ");
                    int b2 = sc.nextInt();
                    System.out.println("Result: " + calc.subtract(a2, b2));
                    break;

                case 3:
                    System.out.print("Enter first number: ");
                    double a3 = sc.nextDouble();
                    System.out.print("Enter second number: ");
                    double b3 = sc.nextDouble();
                    System.out.println("Result: " + calc.multiply(a3, b3));
                    break;

                case 4:
                    System.out.print("Enter first number: ");
                    int a4 = sc.nextInt();
                    System.out.print("Enter second number: ");
                    int b4 = sc.nextInt();
                    System.out.println("Result: " + calc.divide(a4, b4));
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}