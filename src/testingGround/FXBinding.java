package testingGround;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FXBinding extends Application {
    @Override
    public void start(Stage primaryStage) {
        int[] points = {1, 4, 3, 5, 2, 8, 9, 10, 7, 6};
        Rectangle[] columns = new Rectangle[points.length];

        for (int i = 0; i < columns.length; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(null);
            rectangle.setStroke(Color.BLACK);

            columns[i] = rectangle;
        }

        Pane pane = new Pane();
        pane.getChildren().addAll(columns);

        pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                double unit = newValue.doubleValue() / columns.length;
                for (int i = 0; i < columns.length; i++) {
                    columns[i].setHeight(points[i] * unit);
                    columns[i].setY(newValue.doubleValue() - columns[i].getHeight());
                }
            }
        });

        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                double unit = newValue.doubleValue() / columns.length;
                for (int i = 0; i < columns.length; i++) {
                    columns[i].setWidth(unit);
                    columns[i].setX(unit * i);
                }
            }
        });

        Scene scene = new Scene(pane, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
