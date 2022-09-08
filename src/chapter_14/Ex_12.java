package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_12 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.BOTTOM_CENTER);
        pane.setSpacing(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        VBox p1 = new VBox();
        p1.setAlignment(Pos.BOTTOM_CENTER);
        p1.setSpacing(5);
        Text t1 = new Text("Project--20%");
        Rectangle r1 = new Rectangle(80, 40);
        r1.setFill(Color.RED);
        p1.getChildren().addAll(t1, r1);
        
        VBox p2 = new VBox();
        p2.setAlignment(Pos.BOTTOM_CENTER);
        p2.setSpacing(5);
        Text t2 = new Text("Quiz--10%");
        Rectangle r2 = new Rectangle(80, 20);
        r2.setFill(Color.BLUE);
        p2.getChildren().addAll(t2, r2);
        
        VBox p3 = new VBox();
        p3.setAlignment(Pos.BOTTOM_CENTER);
        p3.setSpacing(5);
        Text t3 = new Text("Midterm--30%");
        Rectangle r3 = new Rectangle(80, 60);
        r3.setFill(Color.GREEN);
        p3.getChildren().addAll(t3, r3);
        
        VBox p4 = new VBox();
        p4.setAlignment(Pos.BOTTOM_CENTER);
        p4.setSpacing(5);
        Text t4 = new Text("Final--40%");
        Rectangle r4 = new Rectangle(80, 80);
        r4.setFill(Color.ORANGE);
        p4.getChildren().addAll(t4, r4);
        
        pane.getChildren().addAll(p1, p2, p3, p4);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_12");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
