package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_13 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        Arc a1 = new Arc(100, 100, 80, 80, 0, 2*360/10);
        a1.setFill(Color.RED);
        a1.setType(ArcType.ROUND);
        Text t1 = new Text(110, 80, "Project--20%");
        
        Arc a2 = new Arc(100, 100, 80, 80, 2*360/10, 360/10);
        a2.setFill(Color.BLUE);
        a2.setType(ArcType.ROUND);
        Text t2 = new Text(90, 15, "Quiz--10%");

        
        Arc a3 = new Arc(100, 100, 80, 80, 3*360/10, 3*360/10);
        a3.setFill(Color.GREEN);
        a3.setType(ArcType.ROUND);
        Text t3 = new Text(10, 100, "Midterm--30%");

        
        Arc a4 = new Arc(100, 100, 80, 80, 6*360/10, 4*360/10);
        a4.setFill(Color.ORANGE);
        a4.setType(ArcType.ROUND);
        Text t4 = new Text(100, 180, "Final--40%");

        
        pane.getChildren().addAll(a1, a2, a3, a4, t1, t2, t3, t4);
        
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Ex_14");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
