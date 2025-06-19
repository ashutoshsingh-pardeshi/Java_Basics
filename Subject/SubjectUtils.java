package Java.Subject;

import java.util.*;

import Java.Student.Student;

public class SubjectUtils {
    public void displayAllSubjects(List<Subject> subjects) {
        System.out.println("+-------+-------------+");
        System.out.printf("| %-5s | %-11s |\n", "ID", "Name");
        System.out.println("+-------+-------------+");
        for (Subject subject : subjects) {
            System.out.printf("| %-5d | %-11s |\n", subject.getId(), subject.getName());
            System.out.println("+-------+-------------+");
        }
    }

    public void displaySubjectbyID(List<Subject> subjects, Integer subjectID) {
        Subject subject = subjects.stream().filter(s -> s.getId() == subjectID).findFirst().orElse(null);
        if (subject != null) {
            System.out.println("+-------+-------------+");
            System.out.printf("| %-5s | %-11s |\n", "ID", "Name");
            System.out.println("+-------+-------------+");
            System.out.printf("| %-5d | %-11s |\n", subject.getId(), subject.getName());
            System.out.println("+-------+-------------+");
        } else
            System.err.println("error: Please enter a valid ID !!");
    }

    public float getSubjectMark(Student student, Integer subjectId) {
        Subject selectedSubject = student.getMarks().stream()
                .filter(s -> s.getId() == subjectId).toList().get(0);
        return selectedSubject.getMarks();
    }

    public char getSubjectGrade(Student student, Integer subjectId) {
        Subject selectedSubject = student.getMarks().stream()
                .filter(s -> s.getId() == subjectId).toList().get(0);
        return selectedSubject.getGrade();
    }
}
