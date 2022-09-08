package chapter_16;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Ex_02 extends Application {
    private StackPane paneForShape = new StackPane();
    private RadioButton rbCircle = new RadioButton("Circle ");
    private RadioButton rbSquare = new RadioButton("Square ");
    private RadioButton rbEclipse = new RadioButton("Eclipse ");
    private CheckBox cbFill = new CheckBox("Fill");

    @Override
    public void start(Stage primaryStage) {
        HBox paneForButtons = new HBox();
        paneForButtons.getChildren().addAll(rbCircle, rbSquare, rbEclipse, cbFill);
        paneForButtons.setAlignment(Pos.CENTER);

        ToggleGroup group = new ToggleGroup();
        rbCircle.setToggleGroup(group);
        rbSquare.setToggleGroup(group);
        rbEclipse.setToggleGroup(group);
        rbCircle.setOnAction(event -> eventHandle());
        rbSquare.setOnAction(event -> eventHandle());
        rbEclipse.setOnAction(event -> eventHandle());
        cbFill.setOnAction(event -> eventHandle());

        paneForShape.setStyle("-fx-border-color: blue");

        BorderPane pane = new BorderPane();
        pane.setCenter(paneForShape);
        pane.setBottom(paneForButtons);

        Scene scene = new Scene(pane, 400, 150);
        primaryStage.setTitle("Ex_02");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void eventHandle() {
        paneForShape.getChildren().clear();
        Shape shape;
        if (rbCircle.isSelected())
            shape = new Circle(Math.min(paneForShape.getWidth() / 4,
                    paneForShape.getHeight() / 2 - 10));
        else if (rbSquare.isSelected())
            shape = new Rectangle(Math.min(paneForShape.getWidth() / 2, paneForShape.getHeight() / 2 ),
                    Math.min(paneForShape.getWidth() / 2, paneForShape.getHeight() / 2 ));
        else
            shape = new Ellipse(paneForShape.getWidth() / 2, paneForShape.getHeight() / 2,
                    paneForShape.getWidth() / 4, paneForShape.getHeight() / 4);

        if (cbFill.isSelected())
            shape.setFill(Color.YELLOW);
        else
            shape.setFill(null);

        shape.setStroke(Color.BLACK);
        paneForShape.getChildren().add(shape);
    }
}
