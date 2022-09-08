package chapter_35;

import javax.sql.RowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class SimpleRowSet {
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
        // Create a row set
        RowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();;

        // Set RowSet properties
        rowSet.setUrl("jdbc:mysql://localhost/javabook");
        rowSet.setUsername("scott");
        rowSet.setPassword("tiger");
        rowSet.setCommand("select firstName, mi, lastName " +
                "from Student where lastName = 'Smith'");
        rowSet.execute();

        // Iterate through the result and print the student names
        while (rowSet.next())
            System.out.println(rowSet.getString(1) + "\t" +
                    rowSet.getString(2) + "\t" + rowSet.getString(3));

        // Close the connection
        rowSet.close();
    }
}
