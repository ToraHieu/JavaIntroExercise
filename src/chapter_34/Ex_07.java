package chapter_34;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex_07 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://liveexample.pearsoncmg.com/data/Quiz.txt");
            Scanner scanner = new Scanner(url.openStream());
            List<Question> list = new ArrayList<>();

            while (scanner.hasNext()) {
                // Read the question ID
                String sId = scanner.next();
                int questionId = Integer.parseInt(sId.substring(0, sId.indexOf('.')));

                // Read the question
                String question = scanner.nextLine();
                // Read choices a -> d, skip 3 characters "*. "
                String choicea = scanner.nextLine().substring(3);
                String choiceb = scanner.nextLine().substring(3);
                String choicec = scanner.nextLine().substring(3);
                String choiced = scanner.nextLine().substring(3);

                // Read answer, skip 7 characters "Answer:"
                String answer = scanner.nextLine().substring(7);
                list.add(new Question(questionId, question, choicea, choiceb, choicec, choiced, answer));

                // Skip the blank row
                if (scanner.hasNext()) scanner.nextLine();
            }


            // Establish a connection
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Database connected");

            String insertQuery = "INSERT INTO Quiz (questionId, question, choicea, choiceb, choicec, choiced, answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            for (Question q : list) {
                insertStatement.setInt(1, q.questionId);
                insertStatement.setString(2, q.question);
                insertStatement.setString(3, q.choicea);
                insertStatement.setString(4, q.choiceb);
                insertStatement.setString(5, q.choicec);
                insertStatement.setString(6, q.choiced);
                insertStatement.setString(7, q.answer);

                insertStatement.executeUpdate();
            }

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class Question {
        private final int questionId;
        private final String question, choicea, choiceb, choicec, choiced, answer;

        Question(int questionId, String question, String choicea, String choiceb, String choicec, String choiced, String answer) {
            this.questionId = questionId;
            this.question = question;
            this.choicea = choicea;
            this.choiceb = choiceb;
            this.choicec = choicec;
            this.choiced = choiced;
            this.answer = answer;
        }
    }
}
