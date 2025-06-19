package Java.Student;

import java.util.*;

import Java.Subject.Subject;
import Java.Subject.SubjectUtils;

public class StudentUtils {

    public List<Student> storeStudentData(Integer studentCount, List<Subject> subjects, Scanner scanner) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < studentCount; i++) {
            System.out.print("Student MIS : ");
            Integer MIS = scanner.nextInt();
            scanner.nextLine(); // consume the '\n'

            System.out.print("Enter Name of Student " + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.println("Add subject marks below");
            List<Subject> studentSubjects = new ArrayList<>();

            for (Subject subject : subjects) {
                System.out.print("Add marks for " + subject.getName() + " : ");
                float marks = scanner.nextFloat();

                // Creating a new subject instance
                Subject newSubject = new Subject(subject.getId(), subject.getName());
                newSubject.assignMarks(marks);
                studentSubjects.add(newSubject);
                scanner.nextLine(); // consume the '\n'
            }
            students.add(new Student(MIS, name, studentSubjects));
            System.out.println("-------------------------------------------------");
        }

        return students;
    }

    public List<Subject> storeSubjectData(Integer subjectCount, Scanner scanner) {
        List<Subject> subjects = new ArrayList<>();

        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Subject Id : ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // consume the '\n'

            System.out.print("Subject Name : ");
            String name = scanner.nextLine();

            System.out.println("");

            subjects.add(new Subject(id, name));
        }

        return subjects;
    }

    public void printSubjectTopper(List<Student> students, List<Subject> subjects, Integer subjectId) {
        SubjectUtils subjectUtils = new SubjectUtils();
        Student topper = students.get(0);
        String subjectName = subjects.stream().filter(s -> s.getId() == subjectId).toList().get(0).getName();
        for (Student student : students) {
            if (subjectUtils.getSubjectMark(topper, subjectId) < subjectUtils.getSubjectMark(student, subjectId)) {
                topper = student;
            }
        }

        System.out.println("The topper in " + subjectName + " is " + topper.getName());
        System.out.println("-------------------------------------------------");

    }

    public void printTopper(List<Student> students) {
        Student topper = students.get(0);
        for (Student student : students) {
            if (topper.getAllMarks() < student.getAllMarks()) {
                topper = student;
            }
        }

        System.out.println("The Overall topper is " + topper.getName());
        System.out.println("-------------------------------------------------");

    }

    public void printStudentReport(Student student) {
        System.out.println("|---------------------------------|");
        System.out.printf("| Report Sheet of %-15s |\n", student.getName());
        System.out.printf("| MIS: %-26d |\n", student.getMIS());
        System.out.println("|---------------------------------|");
        System.out.printf("| %-13s |  %5s | %6s |\n", "Subject", "Marks", "Grade");
        for (Subject subject : student.getMarks()) {
            System.out.printf("| %-13s | %-6.2f | %-6c |\n", subject.getName(), subject.getMarks(), subject.getGrade());
            System.out.println("|---------------+--------+--------|");
        }
        System.out.println();
    }

}
