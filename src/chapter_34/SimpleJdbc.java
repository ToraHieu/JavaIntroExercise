package chapter_34;

import java.sql.*;

public class SimpleJdbc {
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
        // Establish a connection
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/javabook", "scott", "tiger");
        System.out.println("Database connected");

        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery
                ("select firstName, mi, lastName from Student where lastName "
                        + " = 'Smith'");

        // Iterate through the result and print the student names
        while (resultSet.next())
            System.out.println(resultSet.getString(1) + "\t" +
                    resultSet.getString(2) + "\t" + resultSet.getString(3));

        // Close the connection
        connection.close();
    }
}
