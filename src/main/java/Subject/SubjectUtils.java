package Subject;

import java.util.*;

import Marks.MarksDAO;

import java.sql.*;

import Student.Student;
import Student.StudentDAO;

public class SubjectUtils {

    private final StudentDAO studentDAO;
    private final SubjectDAO subjectDAO;
    private final MarksDAO marksDAO;

    public SubjectUtils(Connection conn) {
        // Connecting to all the DAOs only once - better for performance
        this.studentDAO = new StudentDAO(conn);
        this.subjectDAO = new SubjectDAO(conn);
        this.marksDAO = new MarksDAO(conn);
    }

    // Adding subject to DB
    public void storeSubjectData(Integer subjectCount, Scanner scanner) {

        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Subject Name : ");
            String name = scanner.nextLine();

            System.out.print("Total Marks : ");
            Integer totalMarks = scanner.nextInt();
            scanner.nextLine(); // consume the '\n'

            Subject subject = new Subject(name, totalMarks);

            subjectDAO.addSubject(subject);
        }

    }

    public void displayAllSubjects() {
        // Fetch this from the table
        List<Subject> subjects = subjectDAO.getAllSubjects();
        if (subjects.size() == 0) {
            // Handling error
            System.out.println("No subjects have been added to the DB yet.");
        } else {
            // Print the subjects in tabular fashion
            System.out.println("+-------+-------------+");
            System.out.printf("| %-5s | %-11s |\n", "ID", "Name");
            System.out.println("+-------+-------------+");
            for (Subject subject : subjects) {
                System.out.printf("| %-5d | %-11s |\n", subject.getId(), subject.getName());
                System.out.println("+-------+-------------+");
            }
        }
    }

    public void displaySubjectIdByName(String name) {
        // Fetch this from the table
        List<Subject> subjects = new ArrayList<>();
        Subject subject = subjects.get(0);
        if (subject != null) {
            System.out.println("+-------+-------------+");
            System.out.printf("| %-5s | %-11s |\n", "ID", "Name");
            System.out.println("+-------+-------------+");
            System.out.printf("| %-5d | %-11s |\n", subject.getId(), subject.getName());
            System.out.println("+-------+-------------+");
        } else
            System.err.println("error: Please enter a valid ID !!");
    }

    // public int getSubjectMark(Student student, Integer subjectId) {
    // Subject selectedSubject = student.getMarks().stream()
    // .filter(s -> s.getId() == subjectId).findFirst().orElse(null);
    // return selectedSubject.getMarks();
    // }

    // public char getSubjectGrade(Student student, Integer subjectId) {
    // Subject selectedSubject = student.getMarks().stream()
    // .filter(s -> s.getId() == subjectId).findFirst().orElse(null);
    // return selectedSubject.getGrade();
    // }
}
