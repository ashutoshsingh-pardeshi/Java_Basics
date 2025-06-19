package Java.Subject;

public class Subject {
    private static Integer subjectID = 1;
    private Integer id;
    private String name;
    private Float marks;
    private Float totalMarks;
    private Character grade;

    public Subject(String name, Float totalMarks) {
        this.id = subjectID;
        this.name = name;
        this.totalMarks = totalMarks;
        subjectID++;
    }

    public Subject(Integer id, String name, Float totalMarks) {
        this.id = id;
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public String getName() {
        return name;
    }

    public String getSubjectName(int subjectId) {
        return name;
    }

    public Float getMarks() {
        return marks;
    }

    public Float getTotalMarks() {
        return totalMarks;
    }

    public Character getGrade() {
        return grade;
    }

    public Integer getId() {
        return id;
    }

    public void assignMarks(Float marks) {
        this.marks = marks;
        this.grade = assignGrade(marks);
    }

    Character assignGrade(Float marks) {
        if (marks >= 90)
            return 'A';
        else if (marks >= 80)
            return 'B';
        else if (marks >= 70)
            return 'C';
        else if (marks >= 60)
            return 'D';
        else if (marks >= 50)
            return 'E';
        else
            return 'F';
    }
}