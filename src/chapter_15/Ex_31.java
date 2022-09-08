package chapter_15;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_31 extends Application {
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Group root = new Group();
//        primaryStage.setScene(new Scene(root, 140, 140));
//
//        Rectangle rect = new Rectangle(1, 1, 40, 40);
//        root.getChildren().add(rect);
//        // comment movePivot to get the default rotation
//        movePivot(rect, -20, -20);
//
//        RotateTransition rt = new RotateTransition(Duration.seconds(4),rect);
//        rt.setToAngle(720);
//        rt.setCycleCount(Timeline.INDEFINITE);
//        rt.setAutoReverse(true);
//        rt.play();
//
//        primaryStage.show();
//    }
//
//    // this is the function you want
//    private void movePivot(Node node, double x, double y){
//        node.getTransforms().add(new Translate(-x,-y));
//        node.setTranslateX(x); node.setTranslateY(y);
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle topCircle = new Circle(150, 20, 5);
        Line line = new Line(150, 20, 150, 180);
        Circle botCircle = new Circle(150, 180, 10);

        Rotate rotate = new Rotate();
        rotate.pivotXProperty().bind(topCircle.centerXProperty());
        rotate.pivotYProperty().bind(topCircle.centerYProperty());

        Group group = new Group(line, botCircle);
        group.getTransforms().add(rotate);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 30)),
                new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), -30))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Arc arc = new Arc(150, 20, 160, 160, -60, -60);
        arc.setType(ArcType.OPEN);
        arc.setStroke(Color.BLACK);
        arc.setFill(null);

        Pane pane = new Pane(topCircle, group, arc);
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Ex_31");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.setOnMousePressed(e -> timeline.pause());
        pane.setOnMouseReleased(e -> timeline.play());
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Line line = new Line(200, 200, 200, 350);
//        Pane pane = new Pane(line);
//        Rotate rotation = new Rotate();
//        rotation.pivotXProperty().bind(line.startXProperty());
//        rotation.pivotYProperty().bind(line.startYProperty());
//
//        line.getTransforms().add(rotation);
//
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
//                new KeyFrame(Duration.seconds(1), new KeyValue(rotation.angleProperty(), 360)));
//
//        Button button = new Button("Rotate");
//        button.setOnAction(evt -> timeline.play());
//        button.disableProperty().bind(timeline.statusProperty().isEqualTo(Animation.Status.RUNNING));
//
//        HBox controls = new HBox(button);
//        controls.setAlignment(Pos.CENTER);
//        controls.setPadding(new Insets(12));
//
//        BorderPane root = new BorderPane(pane, null, null, controls, null);
//        Scene scene = new Scene(root, 400, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
}
