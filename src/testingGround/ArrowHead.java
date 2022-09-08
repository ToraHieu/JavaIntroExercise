package testingGround;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ArrowHead extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        double x1 = 200, y1 = 180;
        double x2 = 100, y2 = 10;

        Line line = new Line(x1, y1, x2, y2);

        double dx = x2 - x1;
        double dy = y2 - y1;

        double length = Math.sqrt(dx * dx + dy * dy);
        double udx = dx / length;
        double udy = dy / length;

        int size = 10;
        double hx1 = x2 - udx * size - udy * size;
        double hy1 = y2 - udy * size + udx * size;
        double hx2 = x2 - udx * size + udy * size;
        double hy2 = y2 - udy * size - udx * size;
        Line h1 = new Line(x2, y2, hx1, hy1);
        h1.setStyle("-fx-stroke: green");
        Line h2 = new Line(x2, y2, hx2, hy2);
        h2.setStyle("-fx-stroke: blue");

        pane.getChildren().addAll(line, h1, h2, new Text(x1, y1, "0"), new Text(x2, y2, "1"));


        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Arrow Head"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
