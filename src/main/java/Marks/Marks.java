package Marks;

public class Marks {
    private int studentMIS;
    private int subjectID;
    private int marks;
    private char grade;

    public Marks(Integer studentMIS, Integer subjectID, Integer marks) {
        this.studentMIS = studentMIS;
        this.subjectID = subjectID;
        this.marks = marks;
        this.grade = assignGrade(marks, subjectID);
    }

    Character assignGrade(Integer marks, Integer subjectID) {
        // Fetch totalMarks from subjects using the subjectID
        int totalMarks = 100; // dummy placeholder

        if ((marks / totalMarks) * 100 >= 90)
            return 'A';
        else if ((marks / totalMarks) * 100 >= 80)
            return 'B';
        else if ((marks / totalMarks) * 100 >= 70)
            return 'C';
        else if ((marks / totalMarks) * 100 >= 60)
            return 'D';
        else if ((marks / totalMarks) * 100 >= 50)
            return 'E';
        else
            return 'F';
    }

    public int getStudentMIS() {
        return studentMIS;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public int getMarks() {
        return marks;
    }

    public char getGrades() {
        return grade;
    }

}
