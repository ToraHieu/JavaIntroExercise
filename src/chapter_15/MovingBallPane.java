package chapter_15;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MovingBallPane extends Pane {
    public final double radius = 20;
    private double x = radius, y = radius;
    private Circle circle = new Circle(x, y, radius);
    
    public MovingBallPane() {
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().add(circle);
    }
    
    public void moveLeft() {
        circle.setCenterX(circle.getCenterX() - radius - 10 > 0 ? circle.getCenterX() - 10 : 0 + radius);
    }
    
    public void moveRight() {
        circle.setCenterX(circle.getCenterX() + radius + 10 < getWidth() ? circle.getCenterX() + 10 : getWidth() - radius);
    }
    
    public void moveUp() {
        circle.setCenterY(circle.getCenterY() - radius - 10 > 0 ? circle.getCenterY() - 10 : 0 + radius);
    }
    
    public void moveDown() {
        circle.setCenterY(circle.getCenterY() + radius + 10 < getHeight() ? circle.getCenterY() + 10 : getHeight() - radius);
    }
}
