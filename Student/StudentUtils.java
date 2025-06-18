package Java.Student;

import java.util.*;

public class StudentUtils {

    public List<Student> storeStudentData(Integer studentCount, List<Subject> subjects) {
        List<Student> students = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < studentCount; i++) {
            System.out.print("Student MIS : ");
            Integer MIS = scanner.nextInt();
            scanner.nextLine(); // consume the '\n'

            System.out.print("Enter Name of Student " + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.println("Add subject marks : ");
            List<Subject> studentSubjects = new ArrayList<>();

            for (Subject subject : subjects) {
                System.out.print("Add marks for" + subject + ": ");
                float marks = scanner.nextFloat();

                Subject newSubject = new Subject(subject.getId(), subject.getName());
                newSubject.assignMarks(marks);
                studentSubjects.add(newSubject);
                scanner.nextLine(); // consume the '\n'
                System.out.println("");
            }

            students.add(new Student(MIS, name, studentSubjects));
        }

        scanner.close();

        return students;
    }

    public List<Subject> storeSubjectData(Integer subjectCount) {
        Scanner scanner = new Scanner(System.in);

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

        scanner.close();

        return subjects;
    }

    // public void printStudents(List<Student> students) {
    // int index = 1;
    // for (Student student : students) {
    // // student.printDetails(index);
    // index++;
    // }
    // }

    public void printSubjectTopper(List<Student> students, List<Subject> subjects, Integer subjectId) {
        Student topper = students.get(0);
        String subjectName = subjects.stream().filter(s -> s.getId() == subjectId).toList().get(0).getName();
        for (Student student : students) {
            if (topper.getSubjectMark(subjectId) < student.getSubjectMark(subjectId)) {
                topper = student;
            }
        }

        System.out.println("The topper in " + subjectName + " is " + topper.getName());
    }

    public void printTopper(List<Student> students) {
        Student topper = students.get(0);
        for (Student student : students) {
            if (topper.getAllMarks() < student.getAllMarks()) {
                topper = student;
            }
        }

        System.out.println("The topper in is " + topper.getName());
    }

}
