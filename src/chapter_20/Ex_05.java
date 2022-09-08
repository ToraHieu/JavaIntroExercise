package chapter_20;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Ex_05 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        MultipleBallPane ballPane = new MultipleBallPane();
        ballPane.setStyle("-fx-border-color: yellow");

        Button btSuspend = new Button("Suspend");
        Button btResume = new Button("Resume");
        Button btAdd = new Button("+");
        Button btSubtract = new Button("-");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btSuspend, btResume, btAdd, btSubtract);
        hBox.setAlignment(Pos.CENTER);

        // Add or remove a ball
        btAdd.setOnAction(e -> ballPane.add());
        btSubtract.setOnAction(e -> ballPane.subtract());

        // Pause and resume animation
        btSuspend.setOnAction(e -> ballPane.pause());
        btResume.setOnAction(e -> ballPane.play());

        ballPane.setOnMousePressed(event -> {
            for (Node node : new ArrayList<>(ballPane.getChildren())) {
                if (ballPane.getChildren().contains(node))
                    if (node.contains(event.getX(), event.getY())) {
                        ballPane.getChildren().remove(node);
                    }
            }
        });

        // Use a scroll bar to control animation speed
        ScrollBar sbSpeed = new ScrollBar();
        sbSpeed.setMax(20);
        sbSpeed.setValue(10);
        ballPane.rateProperty().bindBidirectional(sbSpeed.valueProperty());

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setTop(sbSpeed);
        pane.setBottom(hBox);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Ex_05");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class MultipleBallPane extends Pane {
        private Timeline animation;

        public MultipleBallPane() {
            // Create and animation for moving the ball
            animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
            animation.setCycleCount(Timeline.INDEFINITE);
//            animation.play(); // Start animation
        }

        /** Grenerate a ball with random color, random location and 5 radius. */
        public void add() {
            Color color = new Color(Math.random(), Math.random(), Math.random(), 1); // Original: Opacity = 0.5;
            double radius = Math.random() * 8 + 16;
            getChildren().add(new Ball(Math.random() * (getWidth() - 2 * radius) + radius, Math.random() * (getHeight() - 2 * radius) + radius,
                    radius, color,(int) (Math.random() * 15) - 7, (int) (Math.random() * 15) - 7));
        }

        /** Remove the last ball that is added */
        public void subtract() {
            if (getChildren().size() > 0) {
                getChildren().remove(getChildren().size() - 1);
            }
        }

        public void play() {
            animation.play();
        }

        public void pause() {
            animation.pause();
        }

        public void increaseSpeed() {
            animation.setRate(animation.getRate() + 0.1);
        }

        public void decreaseSpeed() {
            animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
        }

        public DoubleProperty rateProperty() {
            return animation.rateProperty();
        }

        protected void moveBall() {
            List<Node> mirror = new ArrayList<>(getChildren());
            for (Node node : mirror) {
                if (!getChildren().contains(node))
                    continue;
                Ball ball = (Ball) node;
                // Check boundaries
                if (ball.getCenterX() < ball.getRadius() ||
                        ball.getCenterX() > getWidth() - ball.getRadius()) {
                    ball.dx *= -1; // Change ball move direction
                }
                if (ball.getCenterY() < ball.getRadius() ||
                        ball.getCenterY() > getHeight() - ball.getRadius()) {
                    ball.dy *= -1;
                }

                // Adjust ball position
                ball.setCenterX(ball.dx + ball.getCenterX());
                ball.setCenterY(ball.dy + ball.getCenterY());
                // Check for collision
                for (Node other : this.getChildren()) {
                    Ball otherBall = (Ball) other;
                    if (ball == otherBall)
                        continue;
                    double distant = new Point2D(ball.getCenterX(), ball.getCenterY())
                            .distance(otherBall.getCenterX(), otherBall.getCenterY());
                    if (distant <= ball.getRadius() + otherBall.getRadius()) {
                        handleCollision(ball, otherBall);
                        break;
                    }
                }
            }
        }

        private void handleCollision(Ball b1, Ball b2) {
//            Ball winningBall = getChildren().indexOf(b1) < getChildren().indexOf(b2) ? b1 : b2;
            Ball winner, loser;

            if (b1.getPow() - b2.getPow() > 0) {
                winner = b1;
                loser = b2;
            } else {
                winner = b2;
                loser= b1;
            }

            double newRadius = winner.getRadius() + loser.getRadius() * 0.3;

            // Check boundaries
            if (winner.getCenterX() < newRadius)
                winner.setCenterX(newRadius);
            else if (winner.getCenterX() > getWidth() - newRadius)
                winner.setCenterX(getWidth() - newRadius);

            if (winner.getCenterY() < newRadius)
                winner.setCenterY(newRadius);
            else if (winner.getCenterY() > getHeight() - newRadius)
                winner.setCenterY(getHeight() - newRadius);

            winner.setRadius(newRadius);
            winner.dx += Math.signum(winner.dx) * loser.dx * 0.2;
            winner.dy += Math.signum(winner.dy) * loser.dy * 0.2;

            // If the firstBall is b1 then the b2 is removed and vice versa
//            getChildren().remove(winner == b1 ? b2 : b1);
            getChildren().remove(loser);
        }
    }

    class Ball extends Circle {
        private double dx = 1, dy = 1;

        Ball(double x, double y, double radius, Color color) {
            super(x, y, radius);
            setFill(color);
        }

        Ball(double x, double y, double radius, Color color, double dx, double dy) {
            super(x, y, radius);
            setFill(color);
            this.dx = dx;
            this.dy = dy;
        }

        public double getPow() {
            return getRadius() * 0.4 + Math.abs(dx + dy) * 0.6;
        }
    }
}
