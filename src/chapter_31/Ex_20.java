package chapter_31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ex_20 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Line");
        StackPane pane1 = new StackPane();
        pane1.getChildren().add(new Line(10, 10, 80, 80));
        tab1.setContent(pane1);
        Tab tab2 = new Tab("Rectangle");
        tab2.setContent(new Rectangle(10, 10, 200, 200));
        Tab tab3 = new Tab("Circle");
        tab3.setContent(new Circle(50, 50, 20));
        Tab tab4 = new Tab("Ellipse");
        tab4.setContent(new Ellipse(10, 10, 100, 80));
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        ToggleGroup group = new ToggleGroup();
        RadioButton rbTop = new RadioButton("Top");
        rbTop.setToggleGroup(group);
        RadioButton rbLeft = new RadioButton("Left");
        rbLeft.setToggleGroup(group);
        RadioButton rbBottom = new RadioButton("Bottom");
        rbBottom.setToggleGroup(group);
        RadioButton rbRight = new RadioButton("Right");
        rbRight.setToggleGroup(group);
        HBox hBox = new HBox(2, rbTop, rbLeft, rbBottom, rbRight);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane(tabPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 300, 250);
        primaryStage.setTitle("DisplayFigure"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window

        rbTop.setOnAction(event -> tabPane.setSide(Side.TOP));
        rbLeft.setOnAction(event -> tabPane.setSide(Side.LEFT));
        rbBottom.setOnAction(event -> tabPane.setSide(Side.BOTTOM));
        rbRight.setOnAction(event -> tabPane.setSide(Side.RIGHT));
    }
}
