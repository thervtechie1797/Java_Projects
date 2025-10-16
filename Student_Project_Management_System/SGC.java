package Java_projects.Student_Project_Management_System;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

//Create a student class to represent a student
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int marks[];
    int total;
    double average;
    String grade;

    // Constructor to initialize name & marks
    Student(String name, int marks[]) {
        this.name = name;
        this.marks = marks;
        calculateTotal();
        calculateAverage();
        determineGrade();

    }

    // Method to calculate total marks
    void calculateTotal() {
        total = 0;
        for (int mark : marks) {
            total += mark;
        }
    }

    // Method to calculate average marks
    void calculateAverage() {
        average = (double) total / marks.length;
    }

    // Method to determine grade based on average
    void determineGrade() {
        if (average >= 80) {
            grade = "A";
        } else if (average >= 60) {
            grade = "B";
        } else if (average >= 40) {
            grade = "C";
        } else {
            grade = "Fail";
        }
    }

    void displayDetails() {
        System.out.println("\n--- Student Details ---");
        System.out.println("Name: " + name);
        System.out.println("Marks: " + Arrays.toString(marks));
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println("\nTotal = " + total);
        System.out.println("Average = " + average);
        System.out.println("Grade = " + grade);
    }

}

// Management System Class

public class SGC {
    private static final String FILE_NAME = "students.dat";
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Save & Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5: {
                    saveData();
                    System.out.println("Date saved. Exiting Program....");
                    return;

                }

                default:
                    System.out.println("invalid choice! Please try again.");
            }
        }

    }

    // ----------ADD STUDENT-------------
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int numSubjects = getIntInput();

        int marks[] = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = getIntInput();
        }

        students.add(new Student(name, marks));
        System.out.println("✅ Student added successfully");

    }

    // ----------DISPLAY ALL STUDENTS--------------
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No Student Found!");
            return;
        }

        System.out.println("\n===== Student List =====");
        for (Student s : students) {
            s.displayDetails();
        }
    }

    // ---------Search Student Name------------
    private static void searchStudent() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
        boolean found = false;

        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                s.displayDetails();
                found = true;
            }
        }
        if (!found)
            System.out.println("Student not found!");
    }

    // -----------DELETE STUDENT ---------------
    private static void deleteStudent() {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();

        Iterator<Student> iterator = students.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.name.equalsIgnoreCase(name)) {
                iterator.remove();
                removed = true;
                System.out.println("✅ Student removed successfully!");

            }
        }
        if (!removed)
            System.out.println("Student not found!");
    }

    // ----------SAVE DATA TO FILE-------------
    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // ----------LOAD DATA FROM FILE------------
    @SuppressWarnings("unchecked")
    private static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
            System.out.println("✅ Data loaded successfully! (\" + students.size() + \" students)");
        } catch (Exception e) {
            System.out.println("Error Loading Data: " + e.getMessage());
        }
    }

    // -----------SAFE INTEGER INPUT
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a number: ");
            }
        }
    }
}