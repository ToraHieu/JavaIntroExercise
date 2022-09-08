package chapter_15;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_29 extends Application {
    private final CarNode car = new CarNode(0, 100);
    private final Pane pane = new Pane(car);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(pane, 200, 100);
        primaryStage.setTitle("Ex_29");
        primaryStage.setScene(scene);
        primaryStage.show();

        CarTimer timer = new CarTimer() {
            @Override
            public void tick(long relativeNow) {
                if (car.getX() >= pane.getWidth()) {
                    car.setX(-50);
                    car.reDraw();
                    return;
                }
                car.setX(car.getX() + getRate());
                car.reDraw();
            }
        };
        timer.start();
        
        pane.setOnMousePressed(e -> timer.pause());
        pane.setOnMouseReleased(e -> timer.play());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                timer.setRate(timer.getRate() + 0.2);
            } else if (event.getCode() == KeyCode.DOWN) {
                timer.setRate(Math.max((timer.getRate() - 0.2), 0.2));
            }
        });

//        Duration duration = Duration.millis(1000);
//        Line path = new Line(25, 85, 75, 85);
//        PathTransition pt = new PathTransition();
//        pt.setDuration(duration);
//        pt.setPath(path);
//        pt.setNode(car);
//        pt.setInterpolator(Interpolator.LINEAR);
//        pt.setOnFinished(event -> {
//            if (path.getStartX() < scene.getWidth() - 25) {
//                path.setStartX(path.getStartX() + 50);
//                path.setEndX(path.getEndX() + 50);
//            } else {
//                path.setStartX(-25);
//                path.setEndX(25);
//            }
//            pt.play();
//        });
//        pt.play();

//        pane.setOnMousePressed(e -> pt.pause());
//        pane.setOnMouseReleased(e -> pt.play());
//
//        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.UP) {
//                pt.setRate(pt.getRate() + 0.2);
//            } else if (event.getCode() == KeyCode.DOWN) {
//                pt.setRate(Math.max((pt.getRate() - 0.2), 0.2));
//            }
//        });
    }

    private abstract static class CarTimer extends AnimationTimer {
        private long animationStart, pauseStart;
        private boolean restartScheduled, pauseScheduled, isPaused, playScheduled;
        private double rate = 1;

        @Override
        public void start() {
            super.start();
            restartScheduled = true;
        }

        public void play() {
            if (isPaused) {
                playScheduled = true;
            }
        }

        public void pause() {
            if (!isPaused) {
                pauseScheduled = true;
            }
        }

        @Override
        public void handle(long now) {
            if (restartScheduled) {
                animationStart = now;
                restartScheduled = false;
            }

            if (pauseScheduled) {
                pauseStart = now;
                isPaused = true;
                pauseScheduled = false;
            }

            if (playScheduled) {
                animationStart += (now - pauseStart);
                isPaused = false;
                playScheduled = false;
            }

            if (!isPaused) {
                long animDuration = now - animationStart;
//                animationDuration.set(animDuration / 1e9);
                tick(animDuration);
            }
        }

        public abstract void tick(long relativeNow);

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }

    public static class CarNode extends Group {
        private double x, y;

        public CarNode(double x, double y) {
            this.x = x;
            this.y= y;
            reDraw();
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public void reDraw() {
            Circle c1 = new Circle(x + 15, y - 5, 5),
                    c2 = new Circle(x + 35, y - 5, 5);
            Rectangle rectangle = new Rectangle(x, y - 20, 50, 10);
            rectangle.setFill(Color.GREEN);
            Polygon polygon = new Polygon(
                    x + 10, y - 20,
                    x + 20, y - 30,
                    x + 30, y - 30,
                    x + 40, y - 20
            );
            polygon.setFill(Color.DARKRED);
            this.getChildren().clear();
            this.getChildren().addAll(c1, c2, rectangle, polygon);
        }
    }
}
