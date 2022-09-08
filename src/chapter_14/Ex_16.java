package chapter_14;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ex_16 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        ReadOnlyDoubleProperty height = pane.heightProperty();
        ReadOnlyDoubleProperty width = pane.widthProperty();

        Line l1 = new Line();
        l1.setStartX(0);
        l1.startYProperty().bind(height.divide(3));
        l1.endXProperty().bind(width);
        l1.endYProperty().bind(height.divide(3));
        l1.setStroke(Color.BLUE);
        
        Line l2 = new Line();
        l2.setStartX(0);
        l2.startYProperty().bind(height.divide(3).multiply(2));
        l2.endXProperty().bind(width);
        l2.endYProperty().bind(height.divide(3).multiply(2));
        l2.setStroke(Color.BLUE);
        
        Line l3 = new Line();
        l3.startXProperty().bind(width.divide(3));
        l3.setStartY(0);
        l3.endXProperty().bind(width.divide(3));
        l3.endYProperty().bind(height);
        l3.setStroke(Color.RED);
        
        Line l4 = new Line();
        l4.startXProperty().bind(width.divide(3).multiply(2));
        l4.setStartY(0);
        l4.endXProperty().bind(width.divide(3).multiply(2));
        l4.endYProperty().bind(height);
        l4.setStroke(Color.RED);
        
        pane.getChildren().addAll(l1, l2, l3, l4);
        
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Ex_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
