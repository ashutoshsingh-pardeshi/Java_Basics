package Student;

import java.sql.*;
import java.util.*;

import Subject.SubjectUtils;

public class Student {
    private final String firstName;
    private final String lastName;
    private final Integer MIS;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public Integer getMIS() {
        return MIS;
    }

    // constructor
    public Student(Integer MIS, String firstName, String lastName) {
        this.MIS = MIS;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static void run(Connection conn) {
        StudentUtils studentUtils = new StudentUtils(conn);
        SubjectUtils subjectUtils = new SubjectUtils(conn);

        Scanner scanner = new Scanner(System.in);

        // Making the application user interactive (39)
        System.out.println("|---------------------------------------|");
        System.out.println("|  Welcome to Student Database Portal   |");
        // System.out.println("|---------------------------------------|");

        int userInput = 0;
        List<Student> students = new ArrayList<>();

        while (userInput != 10) {
            studentUtils.printMenu();
            System.out.print("Please eneter the command: ");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 1) { // Add a subject

                System.out.print("How many subject's data needs to be added : ");
                int subjectCount = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                subjectUtils.storeSubjectData(subjectCount, scanner);

            } else if (userInput == 2) { // Add a student (with their marks)

                System.out.print("How many student's data needs to be added : ");
                int studentCount = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                studentUtils.storeStudentData(studentCount, scanner);

            } else if (userInput == 3) { // View all subjects

                subjectUtils.displayAllSubjects();

            } else if (userInput == 4) { // View all students

                studentUtils.displayAllStudents();

            } else if (userInput == 5) { // Print student report

                System.out.print("Enter student MIS : ");
                Integer studentMIS = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                studentUtils.printStudentReport(studentMIS);

            } else if (userInput == 6) {
                // View subject toppper

                System.out.println("Functionality in development ... Please try later");
                // System.out.print("Enter subject ID : ");
                // int subjectID = scanner.nextInt();
                // scanner.nextLine(); // consume the '\n'

                // Subject subject = subjects.stream().filter(s -> s.getId() ==
                // subjectID).findFirst().orElse(null);
                // if (subject != null)
                // studentUtils.printSubjectTopper(students, subjects, subjectID);
                // else
                // System.out.println("Invalid subject ID enetered !");
            } else if (userInput == 7) {
                // View overall topper
                studentUtils.printTopper(students);
            } else if (userInput == 8) {
                // Search a student
                System.out.println("Functionality in development ... Please try later");
            } else if (userInput == 9) {
                // Search a subject
                System.out.println("Functionality in development ... Please try later");
            } else if (userInput == 10) {
                System.out.println(" Bye !");
            } else {
                System.out.println("Invalid input");
            }
        }

        scanner.close();
    }

}
