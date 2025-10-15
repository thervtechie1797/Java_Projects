package Java_projects.Student_Project_Management_System;

import java.util.Scanner;

public class SGC {
    // Method to calculate total marks
    public static int calculateTotal(int marks[]) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Method to calculate average marks
    public static double calculateAverageMarks(int total, int numberOfSubjects) {
        return (double) total / numberOfSubjects;
    }

    // Method to determine grade based on average

    public static String determineGrade(double average) {
        if (average >= 80) {
            return "A";
        } else if (average >= 60) {
            return "B";
        } else if (average >= 40) {
            return "C";
        } else {
            return "Fail";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number of Subjects: ");
        int n = sc.nextInt();

        int marks[] = new int[n];

        System.out.print("Enter marks");

        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }

        int total = calculateTotal(marks);
        double average = calculateAverageMarks(total, n);
        String grade = determineGrade(average);

        // Display Results:
        System.out.println("\n--- Result ---");
        System.out.println("Total = " + total);
        System.out.println("Average = " + average);
        System.out.println("Grade = " + grade);

        sc.close();
    }
}
