package Subject;

public class Subject {
    private static Integer subjectID = 1;
    private Integer ID;
    private String name;
    private Integer totalMarks;

    public Subject(Integer ID, String name, Integer totalMarks) {
        this.ID = ID;
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public Subject(String name, Integer totalMarks) {
        this.ID = subjectID++;
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public Integer getId() {
        return ID;
    }
}