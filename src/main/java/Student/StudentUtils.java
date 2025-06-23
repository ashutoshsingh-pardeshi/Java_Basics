package Student;

import java.util.*;

import Marks.Marks;
import Marks.MarksDAO;
import Marks.MarksDAO.StudentReport;

import java.sql.*;

import Subject.Subject;
import Subject.SubjectDAO;
import Subject.SubjectUtils;

public class StudentUtils {
    private final StudentDAO studentDAO;
    private final SubjectDAO subjectDAO;
    private final MarksDAO marksDAO;

    public StudentUtils(Connection conn) {
        // Connecting to all the DAOs only once - better for performance
        this.studentDAO = new StudentDAO(conn);
        this.subjectDAO = new SubjectDAO(conn);
        this.marksDAO = new MarksDAO(conn);
    }

    // Print Menu
    public void printMenu() {
        System.out.println("|---------------------------------------|");
        System.out.println("| Choose one of the options             |");
        System.out.println("|---------------------------------------|");
        System.out.println("| Id  |  Command                        |");
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  1. |  Add a subject                  |");
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  2. |  Add a student                  |");
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  3. |  View all subjects              |"); // displayAllSubjects(subjects);
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  4. |  View all students              |"); // displayAllStudents(students);
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  5. |  Print student report           |"); // printStudentReport(student);
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  6. |  View subject topper           |"); // printSubjectTopper(students, subjects, subjectID)
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  7. |  View overall topper            |"); // printTopper(students)
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  8. |  Search a student               |");
        System.out.println("|-----+---------------------------------|");
        System.out.println("|  9. |  Search a subject               |");
        System.out.println("|-----+---------------------------------|");
        System.out.println("| 10. |  Exit                           |");
        System.out.println("|---------------------------------------|");
    }

    // Adding student to DB
    public void storeStudentData(Integer studentCount, Scanner scanner) {

        // Fetch all subjects from subjects table
        List<Subject> studentSubjects = subjectDAO.getAllSubjects();

        for (int i = 0; i < studentCount; i++) {
            System.out.print("Student MIS : ");
            Integer MIS = scanner.nextInt();
            scanner.nextLine(); // consume the '\n'

            System.out.print("Enter First Name : ");
            String firstName = scanner.nextLine();

            System.out.print("Enter Last Name : ");
            String lastName = scanner.nextLine();

            // store the student in the DB
            Student newStudent = new Student(MIS, firstName, lastName);
            studentDAO.addStudent(newStudent);

            // Adding marks
            System.out.println("Add subject marks below");
            for (Subject subject : studentSubjects) {
                System.out.print("Add marks for " + subject.getName() + " : ");
                Integer marks = scanner.nextInt();
                scanner.nextLine(); // consume the '\n'

                // Creating a new mark instance
                Marks studentMarks = new Marks(MIS, subject.getId(), marks);
                marksDAO.addMarks(studentMarks);
            }
            System.out.println("-------------------------------------------------");
        }

    }

    // Printing subject topper
    // public void printSubjectTopper(List<Student> students, List<Subject>
    // subjects, Integer subjectId) {
    // SubjectUtils subjectUtils = new SubjectUtils(null);
    // Student topper = students.get(0);

    // Subject subject = subjects.stream().filter(s -> Objects.equals(s.getId(),
    // subjectId)).findFirst().orElse(null);

    // if (subject != null) {
    // for (Student student : students) {
    // if (subjectUtils.getSubjectMark(topper, subjectId) <
    // subjectUtils.getSubjectMark(student, subjectId)) {
    // topper = student;
    // }
    // }

    // System.out.println("The topper in " + subject.getName() + " is " +
    // topper.getName());
    // System.out.println("-------------------------------------------------");
    // } else {
    // System.out.println("Invalid subject ID !");
    // }

    // }

    // Print overall topper
    public void printTopper(List<Student> students) {
        Student topper = students.get(0);

        System.out.println("The Overall topper is " + topper.getName());
        System.out.println("-------------------------------------------------");

    }

    // Print student marks - DONE
    public void printStudentReport(Integer studentMIS) {
        StudentReport studentReport = marksDAO.getStudentMarks(studentMIS);
        System.out.println("|---------------------------------|");
        System.out.printf("| Report Sheet of %-15s |\n", studentReport.firstName() + " " + studentReport.lastName());
        System.out.printf("| MIS: %-26d |\n", studentMIS);
        System.out.println("|---------------------------------|");
        System.out.printf("| %-13s |  %5s | %6s |\n", "Subject", "Marks", "Grade");
        studentReport.subjectMarks().forEach((subject, details) -> {
            System.out.println("|---------------+--------+--------|");
            System.out.printf("| %-13s | %-6d | %-6c |\n", subject, details.marks(), details.grade());
        });
        System.out.println("|---------------------------------|");
        System.out.println();
        // System.out.println("Invalid MIS enetered !");
    }

    // Print list of students
    public void displayAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        System.out.println("+-------+---------------------------+");
        System.out.printf("| %-5s | %-25s |\n", "ID", "Name");
        System.out.println("+-------+---------------------------+");
        for (Student student : students) {
            System.out.printf("| %-5d | %-25s |\n", student.getMIS(), student.getName());
            System.out.println("+-------+---------------------------+");
        }
    }
}
