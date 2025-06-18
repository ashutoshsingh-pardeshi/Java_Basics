package Java.Student;

public class Subject {
    private Integer id;
    private String name;
    private Float marks;
    private Character grade;

    Subject(Integer id, String name) {
        this.id = id;
        this.name = name;
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