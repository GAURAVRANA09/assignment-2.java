import java.util.*;

// Custom Exception
class InvalidMarksException extends Exception {
    public InvalidMarksException(String msg) { super(msg); }
}

// Student Class
class Student {
    int roll;
    String name;
    int[] marks = new int[3];

    Student(int roll, String name, int[] marks) throws InvalidMarksException {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
        validateMarks();
    }

    void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100)
                throw new InvalidMarksException("Invalid marks for subject " + (i+1));
        }
    }

    double avg() {
        return (marks[0] + marks[1] + marks[2]) / 3.0;
    }

    void display() {
        System.out.println("Roll: " + roll + "\nName: " + name);
        System.out.println("Marks: " + marks[0] + " " + marks[1] + " " + marks[2]);
        System.out.println("Average: " + avg());
        System.out.println("Result: " + (avg() >= 40 ? "Pass" : "Fail"));
    }
}

// Result Manager Class
public class ResultManager {
    Student[] students = new Student[100];
    int count = 0;
    Scanner sc = new Scanner(System.in);

    void addStudent() {
        try {
            System.out.print("Roll: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            int[] m = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Marks " + (i+1) + ": ");
                m[i] = sc.nextInt();
            }

            students[count++] = new Student(roll, name, m);
            System.out.println("Added Successfully.");

        } catch (InvalidMarksException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        } finally {
            System.out.println("Returning to Menu...");
        }
    }

    void showStudent() {
        try {
            System.out.print("Enter Roll: ");
            int r = sc.nextInt();

            for (int i = 0; i < count; i++) {
                if (students[i].roll == r) {
                    students[i].display();
                    return;
                }
            }
            System.out.println("Student Not Found.");

        } finally {
            System.out.println("Search Completed.");
        }
    }

    void menu() {
        while (true) {
            System.out.println("\n1. Add Student\n2. Show Student\n3. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            switch (ch) {
                case 1: addStudent(); break;
                case 2: showStudent(); break;
                case 3: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid Choice");
            }
        }
    }

    public static void main(String[] args) {
        new ResultManager().menu();
    }
}
