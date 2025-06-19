package Java.Student;

import java.util.*;

import Java.Subject.Subject;
import Java.Subject.SubjectUtils;

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

        System.out.print("How many student's data needs to be added : ");
        int studentCount = scanner.nextInt();
        scanner.nextLine(); // consume the '\n'

        System.out.print("How many subject's data needs to be added : ");
        int subjectCount = scanner.nextInt();
        scanner.nextLine(); // consume the '\n'

        List<Subject> subjects = studentUtils.storeSubjectData(subjectCount, scanner);

        List<Student> students = studentUtils.storeStudentData(studentCount, subjects, scanner);
        // studentUtils.printStudents(students);

        for (int i = 0; i < subjectCount; i++) {
            studentUtils.printSubjectTopper(students, subjects, subjects.get(i).getId());
        }

        subjectUtils.displayAllSubjects(subjects);

        for (Student student : students) {
            studentUtils.printStudentReport(student);
        }

        studentUtils.printTopper(students);

        scanner.close();
    }

}