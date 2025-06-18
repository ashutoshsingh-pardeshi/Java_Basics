package Java.Student;

import java.util.*;

// Each student has a name, roll number, and 3 subject marks

// Calculate average and assign grade(A/B/C)

// Find highest average

public class Student {
    private String name;
    private Integer MIS;
    private List<Subject> marks;

    public String getName() {
        return name;
    }

    public Float getAllMarks() {
        float total = 0;
        for (int i = 0; i < marks.size(); i++) {
            total += marks.get(i).getMarks();
        }
        return total;
    }

    public float getSubjectMark(Integer subjectId) {
        Subject selectedSubject = marks.stream()
                .filter(s -> s.getId() == subjectId).toList().get(0);
        return selectedSubject.getMarks();
    }

    public char getSubjectGrade(Integer subjectId) {
        Subject selectedSubject = marks.stream()
                .filter(s -> s.getId() == subjectId).toList().get(0);
        return selectedSubject.getGrade();
    }

    public Integer getMIS() {
        return MIS;
    }

    public void printMarks() {

    }

    // constructor
    public Student(Integer MIS, String name, List<Subject> marks) {
        this.name = name;
        this.MIS = MIS;
        this.marks = marks;
    }

    public static void run() {
        StudentUtils studentUtils = new StudentUtils();

        Scanner scanner = new Scanner(System.in);

        System.out.print("How many student's data needs to be added : ");
        int studentCount = scanner.nextInt();
        scanner.nextLine(); // consume the '\n'

        System.out.print("How many subject's data needs to be added : ");
        int subjectCount = scanner.nextInt();
        scanner.nextLine(); // consume the '\n'

        List<Subject> subjects = studentUtils.storeSubjectData(subjectCount);

        List<Student> students = studentUtils.storeStudentData(studentCount, subjects);
        // studentUtils.printStudents(students);

        for (int i = 0; i < subjectCount; i++) {
            studentUtils.printSubjectTopper(students, subjects, subjects.get(i).getId());
        }

        studentUtils.printTopper(students);

        scanner.close();
    }

}