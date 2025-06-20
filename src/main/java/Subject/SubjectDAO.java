package Subject;

import java.sql.*;
import java.util.*;

public class SubjectDAO {
    private final Connection conn;

    public SubjectDAO(Connection conn) {
        // Establish connection to the DB
        this.conn = conn;
    }

    public void addSubject(Subject subject) {
        // prepared statement
        String queryString = "INSERT INTO subjects (name, totalMarks) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(queryString);) {
            // Filling in the parameters
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getTotalMarks());

            // Executing the query
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();

        // prepared statement to fetch all the subjects from DB
        String queryString = "SELECT * from subjects";

        try (PreparedStatement stmt = conn.prepareStatement(queryString);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getInt("totalMarks"));
                subjects.add(subject);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return subjects;
    }

}
