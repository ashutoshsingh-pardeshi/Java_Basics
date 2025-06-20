import Student.Student;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Each student has a name, roll number, and 3 subject marks
        // Calculate average and assign grade(A/B/C)
        // Find highest average

        Properties properties = new Properties();

        // Reading the details of the file sb.properties
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load DB config: " + e.getMessage());
            return;
        }

        // Retrieving the details of url, username and password
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection successful !");
            Student.run(conn);
        } catch (SQLException e) {
            System.out.println("Unable to connect to the DB !" + e);
        }

    }

}
