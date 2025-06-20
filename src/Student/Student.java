package Student;

import java.util.*;

import Subject.Subject;
import Subject.SubjectUtils;

public class Student {
    private String name;
    private Integer MIS;
    private List<Subject> marks;

    public String getName() {
        return name;
    }

    public List<Subject> getMarks() {
        return marks;
    }

    public Float getAllMarks() {
        float total = 0;
        for (int i = 0; i < marks.size(); i++) {
            total += marks.get(i).getMarks();
        }
        return total;
    }

    public Integer getMIS() {
        return MIS;
    }

    // constructor
    public Student(Integer MIS, String name, List<Subject> marks) {
        this.name = name;
        this.MIS = MIS;
        this.marks = marks;
    }

    public static void run() {
        StudentUtils studentUtils = new StudentUtils();
        SubjectUtils subjectUtils = new SubjectUtils();

        Scanner scanner = new Scanner(System.in);

        // Making the application user interactive (39)
        System.out.println("|---------------------------------------|");
        System.out.println("|  Welcome to Student Database Portal   |");
        // System.out.println("|---------------------------------------|");

        int userInput = 0;
        boolean studentExists = false, subjectExists = false;
        List<Subject> subjects = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        while (userInput != 10) {
            studentUtils.printMenu();
            System.out.print("Please eneter the command: ");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 1) {
                // Add a subject
                System.out.print("How many subject's data needs to be added : ");
                int subjectCount = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                studentUtils.storeSubjectData(subjects, subjectCount, scanner);
                subjectExists = true;
            } else if (userInput == 2) {
                // Add a student
                System.out.print("How many student's data needs to be added : ");
                int studentCount = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                studentUtils.storeStudentData(students, studentCount, subjects, scanner);
                studentExists = true;
            } else if (userInput == 3 && subjectExists) {
                // View all subjects
                subjectUtils.displayAllSubjects(subjects);
            } else if (userInput == 4 && studentExists) {
                // View all students
                studentUtils.displayAllStudents(students);
            } else if (userInput == 5 && subjectExists && studentExists) {
                // Print student report
                System.out.print("Enter student MIS : ");
                int studentMIS = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                Student student = students.stream().filter(s -> s.getMIS() == studentMIS).findFirst().orElse(null);

                if (student != null)
                    studentUtils.printStudentReport(student);
                else
                    System.out.println("Invalid MIS enetered !");

            } else if (userInput == 6 && subjectExists && studentExists) { 
                // View subject toppper

                System.out.print("Enter subject ID : ");
                int subjectID = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                Subject subject = subjects.stream().filter(s -> s.getId() == subjectID).findFirst().orElse(null);
                if (subject != null)
                    studentUtils.printSubjectTopper(students, subjects, subjectID);
                else
                    System.out.println("Invalid subject ID enetered !");
            } else if (userInput == 7 && subjectExists && studentExists) {
                // View overall topper
                studentUtils.printTopper(students);
            } else if (userInput == 8) {
                // Search a student
                System.out.println("Functionality in development ... Please try later");
            } else if (userInput == 9) {
                // Search a subject
                System.out.println("Functionality in development ... Please try later");
            } else if(userInput == 10){
                System.out.println(" Bye !");
            }else if(!subjectExists || !studentExists){
                if(!subjectExists) System.out.println("Subject DB missing !");
                if(!studentExists) System.out.println("Student DB missing !");
            }
        }

        scanner.close();
    }

}
