/*********************************************************
 * Author: Ayush Poudal                                  *
 * Date: 9 Sep 2025                                      *
 * Purpose: Student database to enter and manage records *
 *********************************************************/

import java.io.*;
import java.util.*;

public class StudentData // Main class
{
    static final int maxStudent = 100;          // Maximum students allowed
    static Student[] students = new Student[maxStudent]; // Array to store students
    static int studentCount = 0;                // Current number of students

    // Student class representing a student record
    static class Student
    {
        private String firstName;
        private String lastName;
        private String studentID;
        private Details details; // Inner class storing detailed info

        // Inner class for detailed student info
        static class Details
        {
            private int year;
            private double cwa;
            private String course;
            private String status;
            private int earnedCredits;

            // Constructor for details
            Details(String course, int year, double cwa, String status, int earnedCredits)
            {
                this.course = course;
                this.year = year;
                this.cwa = cwa;
                this.status = status;
                this.earnedCredits = earnedCredits;
            }

            @Override
            public String toString()
            {
                return "Course: " + course +
                       ", Year: " + year +
                       ", CWA: " + cwa +
                       ", Status: " + status +
                       ", Credits: " + earnedCredits;
            }
        }

        // Constructor for student
        Student(String studentID, String firstName, String lastName, String course,
                int year, double cwa, String status, int earnedCredits)
        {
            this.studentID = studentID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.details = new Details(course, year, cwa, status, earnedCredits);
        }

        @Override
        public String toString()
        {
            return "ID: " + studentID + ", Name: " + firstName + " " + lastName + "," + details.toString();
        }
    }

    // Main method
    public static void main(String[] args)
    {
        String fileName = "data.csv";  // CSV file storing student data
        loadFile(fileName);            // Load existing data
        Choice(fileName);              // Start menu
    }

    /************** Menu Choice Method ****************/
    public static void Choice(String fileName)
    {
        Scanner sc = new Scanner(System.in); // Single scanner for all input
        int choose;

        do {
            // Display menu
            System.out.println("============================================= \n\t"
                    + "Welcome to Student Central \n"
                    + "=============================================\n"
                    + "Your options for this system are listed below\n\t"
                    + "1> Add new student.\n\t"
                    + "2> Edit student.\n\t"
                    + "3> View all students.\n\t"
                    + "4> Filter by course.\n\t"
                    + "5> Filter by status.\n\t"
                    + "6> Highest CWA.\n\t"
                    + "7> Average CWA for each course.\n\t"
                    + "8> Credit / Graduation Eligibility. \n\t"
                    + "9> Exit");
            System.out.println("Enter a option");
            choose = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Switch to call corresponding method
            switch (choose) {
                case 1: addStudent(fileName, sc); break;
                case 2: editStudent(fileName, sc); break;
                case 3: viewStudent(fileName); break;
                case 4: filterByCourse(sc); break;
                case 5: filterByStatus(sc); break;
                case 6: highestCWA(); break;
                case 7: averageCWAByCourse(); break;
                case 8: graduationEligibility(); break;
                default: break;
            }

        } while (choose != 9);
        sc.close();
    }

    /************** Load Data from File ****************/
    static void loadFile(String fileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null && studentCount < maxStudent) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    students[studentCount++] = new Student(parts[0], parts[1], parts[2],
                            parts[3], Integer.parseInt(parts[4]), Double.parseDouble(parts[5]),
                            parts[6], Integer.parseInt(parts[7]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error while loading file");
        }
    }

    /************** Add New Student ****************/
    static void addStudent(String fileName, Scanner sc)
    {
        if (studentCount >= maxStudent) {
            System.out.println("The database is full ");
            return;
        }

        // Input student details
        System.out.println("Enter ID");
        String ID = sc.nextLine();
        System.out.print("Enter First Name: ");
        String first = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String last = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();
       int year = 0;
        while (true)
         {
            System.out.print("Enter Year: ");
            String yearInput = sc.nextLine();
         try 
        {
            year = Integer.parseInt(yearInput); // try converting input to integer
            if (year <= 0|| year > 4) { // basic check
            System.out.println("Year must be between 1 and 4");
            continue;
        }
        break; // valid input, exit loop
        } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a numeric value for Year.");
            }
        }
        double cwa = 0;
while (true) {
    System.out.print("Enter CWA: ");
    String cwaInput = sc.nextLine();
    try {
        cwa = Double.parseDouble(cwaInput); 
        break; // valid input, exit loop
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number for CWA.");
    }
            }
        System.out.println("Enter Status: ");
        String status = sc.nextLine();
        int earnedCredits = 0;
while (true) {
    System.out.print("Enter Earned Credits: ");
    String creditsInput = sc.nextLine();
    try {
             earnedCredits = Integer.parseInt(creditsInput);
            if (earnedCredits < 0) {
            System.out.println("Credits cannot be negative.");
            continue;
        }
        break;
        } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a numeric value for Credits.");
             }
        }


        // Add to array
        students[studentCount++] = new Student(ID, first, last, course, year, cwa, status, earnedCredits);
        System.out.println("Student added successfully.");

        // Append to CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(ID + "," + first + "," + last + "," + course + "," + year + "," + cwa + "," + status + "," + earnedCredits);
            bw.newLine();
            System.out.println("Student added and written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /************** Edit Student ****************/
    static void editStudent(String fileName, Scanner sc)
    {
        if (studentCount == 0) {
            System.out.println("No students in the database to edit.");
            return;
        }

        System.out.print("Enter the Student ID to edit: ");
        String id = sc.nextLine();

        Student studentToEdit = null;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].studentID.equals(id)) {
                studentToEdit = students[i];
                break;
            }
        }

        if (studentToEdit == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Current details: " + studentToEdit);

        // Update fields (Enter to skip)
        System.out.print("Enter new First Name (" + studentToEdit.firstName + "): ");
        String first = sc.nextLine();
        if (!first.isEmpty()) studentToEdit.firstName = first;

        System.out.print("Enter new Last Name (" + studentToEdit.lastName + "): ");
        String last = sc.nextLine();
        if (!last.isEmpty()) studentToEdit.lastName = last;

        System.out.print("Enter new Course (" + studentToEdit.details.course + "): ");
        String course = sc.nextLine();
        if (!course.isEmpty()) studentToEdit.details.course = course;

        System.out.print("Enter new Year (" + studentToEdit.details.year + "): ");
        String yearInput = sc.nextLine();
        if (!yearInput.isEmpty()) studentToEdit.details.year = Integer.parseInt(yearInput);

        System.out.print("Enter new CWA (" + studentToEdit.details.cwa + "): ");
        String cwaInput = sc.nextLine();
        if (!cwaInput.isEmpty()) studentToEdit.details.cwa = Double.parseDouble(cwaInput);

        System.out.print("Enter new Status (" + studentToEdit.details.status + "): ");
        String status = sc.nextLine();
        if (!status.isEmpty()) studentToEdit.details.status = status;

        System.out.print("Enter new Earned Credits (" + studentToEdit.details.earnedCredits + "): ");
        String creditsInput = sc.nextLine();
        if (!creditsInput.isEmpty()) studentToEdit.details.earnedCredits = Integer.parseInt(creditsInput);

        System.out.println("Student updated successfully.");

        // Save all changes to file
        saveAllStudentsToFile(fileName);
    }

    /************** Save All Students to File ****************/
    static void saveAllStudentsToFile(String fileName)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < studentCount; i++) {
                Student s = students[i];
                bw.write(s.studentID + "," + s.firstName + "," + s.lastName + "," +
                        s.details.course + "," + s.details.year + "," + s.details.cwa + "," +
                        s.details.status + "," + s.details.earnedCredits);
                bw.newLine();
            }
            System.out.println("All changes saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /************** View All Students ****************/
    static void viewStudent(String fileName)
    {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        System.out.println("===== All Students =====");
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i]); // calls toString()
        }
        System.out.println("========================");
    }

    /************** Filter by Course ****************/
    static void filterByCourse(Scanner sc)
    {
        System.out.print("Enter course to filter by: ");
        String courseFilter = sc.nextLine().trim();

        boolean found = false;
        System.out.println("===== Students in course: " + courseFilter + " =====");
        for (int i = 0; i < studentCount; i++) {
            if (students[i].details.course.trim().equalsIgnoreCase(courseFilter)) {
                System.out.println(students[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No students found in this course.");
        System.out.println("===============================================");
    }

    /************** Filter by Status ****************/
    static void filterByStatus(Scanner sc)
    {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        System.out.print("Enter status to filter by: ");
        String statusFilter = sc.nextLine().trim();

        boolean found = false;
        System.out.println("===== Students with status: " + statusFilter + " =====");
        for (int i = 0; i < studentCount; i++) {
            if (students[i].details.status.trim().equalsIgnoreCase(statusFilter)) {
                System.out.println(students[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No students found with this status.");
        System.out.println("===============================================");
    }

    /************** Highest CWA ****************/
    static void highestCWA()
    {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        double maxCWA = students[0].details.cwa;

        // Find the maximum CWA
        for (int i = 1; i < studentCount; i++) {
            if (students[i].details.cwa > maxCWA) maxCWA = students[i].details.cwa;
        }

        // Display students with highest CWA
        System.out.println("===== Student(s) with Highest CWA: " + maxCWA + " =====");
        for (int i = 0; i < studentCount; i++) {
            if (students[i].details.cwa == maxCWA) System.out.println(students[i]);
        }
        System.out.println("======================================================");
    }

    /************** Average CWA by Course ****************/
    static void averageCWAByCourse()
    {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        // Collect unique courses
        String[] courses = new String[studentCount];
        int courseCount = 0;

        for (int i = 0; i < studentCount; i++) {
            String course = students[i].details.course.trim();
            boolean exists = false;
            for (int j = 0; j < courseCount; j++) {
                if (courses[j].equalsIgnoreCase(course)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) courses[courseCount++] = course;
        }

        // Calculate average for each course
        System.out.println("===== Average CWA per Course =====");
        for (int i = 0; i < courseCount; i++) {
            String course = courses[i];
            double totalCWA = 0;
            int count = 0;
            for (int j = 0; j < studentCount; j++) {
                if (students[j].details.course.equalsIgnoreCase(course)) {
                    totalCWA += students[j].details.cwa;
                    count++;
                }
            }
            double average = totalCWA / count;
            System.out.printf("%s: %.2f\n", course, average);
        }
        System.out.println("=================================");
    }

    /************** Graduation Eligibility (Credits >= 400) ****************/
    static void graduationEligibility()
    {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        boolean found = false;
        System.out.println("===== Students Eligible for Graduation (400+ credits) =====");
        for (int i = 0; i < studentCount; i++) {
            Student s = students[i];
            if (s.details.earnedCredits >= 400) {
                System.out.println(s.firstName + " " + s.lastName + " (" + s.details.course + "): " + s.details.earnedCredits + " credits");
                found = true;
            }
        }
        if (!found) System.out.println("No students are eligible for graduation yet.");
        System.out.println("==========================================================");
    }
}
