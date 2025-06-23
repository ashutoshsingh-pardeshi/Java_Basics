package Marks;

import java.sql.*;
import java.util.*;

public class MarksDAO {
    private Connection conn;

    public MarksDAO(Connection conn) {
        // Establish connection to the DB
        this.conn = conn;
    }

    // General marks entry
    public void addMarks(Marks marks) {
        // prepared query
        String queryString = "INSERT INTO marks VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            // Adding the data to the query
            stmt.setInt(1, marks.getStudentMIS());
            stmt.setInt(2, marks.getSubjectID());
            // Handling null values for marks
            if (marks.getMarks() == null) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, marks.getMarks());
            }
            // stmt.setString(4, String.valueOf(marks.getGrades()));

            // executing the query
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error from MarksDOA.java : " + e);
        }
    }

    // add specific subject marks for a student
    public void addSubjectMarks(Integer subjectID, Integer studentMIS, Integer marks) {

        // prepared query
        String queryString = """
                UPDATE marks
                SET marks = ?
                WHERE student_MIS = ? AND subject_id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            // adding parameters to the queryString
            stmt.setInt(1, marks);
            stmt.setInt(2, studentMIS);
            stmt.setInt(3, subjectID);

            // executing query
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error from MarksDOA.java : " + e);
        }
    }

    // Record for subject details
    public record SubjectDetails(
            Integer totalMarks,
            Integer marks,
            Character grade) {
    }

    // Record of Student report
    public record StudentReport(
            Integer MIS,
            String firstName,
            String lastName,
            HashMap<String, SubjectDetails> subjectMarks) {
    }

    public StudentReport getStudentMarks(Integer studentMIS) {
        // prepared query string
        String getMarks = """
                SELECT MIS, firstName, lastName, Name AS subjectName, marks, totalMarks
                	FROM marks m
                    JOIN students stud
                		ON stud.MIS = m.student_MIS
                	JOIN subjects sub
                		ON sub.id = m.subject_id
                    WHERE MIS = ?;
                """;

        HashMap<String, SubjectDetails> subjectDetails = new HashMap<>();
        boolean once = true;
        String firstName = "", lastName = "";
        try (PreparedStatement stmt = conn.prepareStatement(getMarks)) {
            // Adding parameter value
            stmt.setInt(1, studentMIS);

            // Firing the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer marks = rs.getInt("marks");
                    if (rs.wasNull())
                        marks = null;
                    if (once) {
                        firstName = rs.getString("firstName");
                        lastName = rs.getString("lastName");
                    }
                    subjectDetails.put(
                            rs.getString("subjectName"),
                            new SubjectDetails(
                                    rs.getInt("totalMarks"),
                                    marks,
                                    null));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        StudentReport studentReport = new StudentReport(studentMIS, firstName, lastName, subjectDetails);
        return studentReport;

    }

}
