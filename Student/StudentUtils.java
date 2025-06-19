package Java.Student;

import java.util.*;

import Java.Subject.Subject;
import Java.Subject.SubjectUtils;

public class StudentUtils {

    // Print Menu
    public void printMenu(){
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
        System.out.println("|  6. |  View subject toppper           |"); // printSubjectTopper(students, subjects, subjectID)
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
    public void storeStudentData(List<Student> students, Integer studentCount, List<Subject> subjects, Scanner scanner) {

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
                Subject newSubject = new Subject(subject.getId(), subject.getName(), subject.getTotalMarks());
                newSubject.assignMarks(marks);
                studentSubjects.add(newSubject);
                scanner.nextLine(); // consume the '\n'
            }
            students.add(new Student(MIS, name, studentSubjects));
            System.out.println("-------------------------------------------------");
        }

    }

    // Adding subject to DB
    public void storeSubjectData(List<Subject> subjects, Integer subjectCount, Scanner scanner) {

        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Subject Name : ");
            String name = scanner.nextLine();
            System.out.print("Total Marks : ");
            Float marks = scanner.nextFloat();
            scanner.nextLine(); // consume the '\n'
            System.out.println("");

            subjects.add(new Subject(name, marks));
        }

       
    }

    // Printing subject topper
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

    // Print overall topper
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

    // Print student marks
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

    // Print list of students
    public void displayAllStudents(List<Student> students){
        System.out.println("+-------+-------------+");
        System.out.printf("| %-5s | %-11s |\n", "ID", "Name");
        System.out.println("+-------+-------------+");
        for (Student student : students) {
            System.out.printf("| %-5d | %-11s |\n", student.getMIS(), student.getName());
            System.out.println("+-------+-------------+");
        }
    }
}
