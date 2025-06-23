package Student;

import java.sql.*;
import java.util.*;

public class StudentDAO {
    private Connection conn;

    public StudentDAO(Connection conn) {
        // Establish connection to the DB
        this.conn = conn;
    }

    public void addStudent(Student student) {
        // prepared statement
        String querystring = "INSERT INTO students (MIS, firstName, lastName) VALUES (?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(querystring)) {
            // Extracting the data
            stmt.setInt(1, student.getMIS());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());

            // Executing the query
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        // prepared statement to fetch all students from the DB
        String query = "SELECT * FROM students";

        try (
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Extrating the values from the DB
                Student student = new Student(
                        rs.getInt("MIS"),
                        rs.getString("firstName"),
                        rs.getString("lastName"));

                // Add the student info to the list
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return students;
    }

    // Getting all student IDs
    public List<Integer> getAllStudentsMIS() {
        List<Integer> MISList = new ArrayList<>();

        // prepared statement
        String queryString = "SELECT MIS FROM students";

        try (PreparedStatement stmt = conn.prepareStatement(queryString);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MISList.add(rs.getInt("MIS"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return MISList;
    }
}
