package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Ex_29 extends Application {
    private YearMonth yearMonth = YearMonth.now();
    private Text monthYearText = new Text("Month, Year");
    private GridPane monthBody = new GridPane();
    private Button btnPrior = new Button("Prior");
    private Button btnNext = new Button("Next");

    @Override
    public void start(Stage primaryStage) {
        monthBody.setPadding(new Insets(10));
        monthBody.setHgap(5);


        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(btnPrior, btnNext);
        hBox.setAlignment(Pos.TOP_CENTER);

        BorderPane pane = new BorderPane();
        pane.setTop(monthYearText);
        pane.setCenter(monthBody);
        pane.setBottom(hBox);
        BorderPane.setAlignment(monthYearText, Pos.CENTER);

        updateMonth();

        Scene scene = new Scene(pane, 420, 175);
        primaryStage.setTitle("Ex_29");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnPrior.setOnAction(e -> {
            yearMonth = yearMonth.minusMonths(1);
            updateMonth();
        });
        btnNext.setOnAction(e -> {
            yearMonth = yearMonth.plusMonths(1);
            updateMonth();
        });
    }

    private void updateMonth() {
        monthYearText.setText(yearMonth.format(DateTimeFormatter.ofPattern("MMMM, yyyy")));

        monthBody.getChildren().clear();

        monthBody.addRow(0, new Text("Monday"), new Text("Tuesday"), new Text("Wednesday"), new Text("Thursday"), new Text("Friday"), new Text("Saturday"), new Text("Sunday"));

        // 0 to 6 for Monday to Sunday
        int firstDate = yearMonth.atDay(1).getDayOfWeek().getValue() - 1;

        setPrevMonthDays(firstDate);

        int row = 1;
        for (int date = 1; date <= yearMonth.lengthOfMonth(); date++) {
            monthBody.add(new Text(date + ""), (firstDate + date - 1) % 7, row);
            if ((firstDate + date) % 7 == 0) {
                row++;
            }
        }

        setNextMonthDays(row);

    }

    private void setPrevMonthDays(int currentMonthFirstWeekDate) {
        int prevMonthNumberOfDates = yearMonth.minusMonths(1).lengthOfMonth();

        for (int i = 0; i < currentMonthFirstWeekDate; i++) {
            Text date = new Text(prevMonthNumberOfDates - currentMonthFirstWeekDate + i +  "");
            date.setFill(Color.GRAY);
            monthBody.add(date, i, 1);
        }
    }

    private void setNextMonthDays(int row) {
        // 0 to 6 for Monday to Sunday
        int currentDate = yearMonth.plusMonths(1).atDay(1).getDayOfWeek().getValue() - 1;
        int count = 1;
        while (currentDate % 7 != 0) {
            Text date = new Text(count++ + "");
            date.setFill(Color.GRAY);
            monthBody.add(date, currentDate++, row);
        }
    }
}
