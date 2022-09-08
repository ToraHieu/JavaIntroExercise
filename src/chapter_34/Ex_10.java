package chapter_34;

import java.sql.*;

public class Ex_10 {
    public static void main(String[] args) {
        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Database connected");

            String insertQuery = "INSERT INTO AGSLog (username, exerciseName) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            String selectQuery = "SELECT s.username, e.exerciseName\n" +
                    "FROM AGSStudent s \n" +
                    "INNER JOIN ExerciseAssigned\te \n" +
                    "\tON s.instructorEmail = e.instructorEmail\n" +
                    "LEFT JOIN AGSLog l \n" +
                    "\tON l.username = s.username AND l.exerciseName = e.exerciseName\n" +
                    "WHERE (l.username AND l.exerciseName) IS NULL";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                insertStatement.setString(1, resultSet.getString(1));
                insertStatement.setString(2, resultSet.getString(2));
                insertStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
