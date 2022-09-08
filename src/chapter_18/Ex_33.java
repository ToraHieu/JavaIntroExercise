package chapter_18;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/** Modified from the Ex_32 */
public class Ex_33 extends Application {
    private static final int SIZE = 8;
    /* Tora's note:
     * The "X" and "Y" ("startX", "startY" here and "nextX", "nextY" below) should be named "Row" and "Column" instead, respectively.
     * Because it's stored as the index of the square, rather than raw coordinates.
     * The approach is good, just the naming may cause confusing.
     */
    private int startX = 0;
    private int startY = 0;
    private ArrayList<Point2D> moveHistory = null;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        ChessBoard board = new ChessBoard();
        pane.setCenter(board);
        Button btSolve = new Button("Solve");
        pane.setBottom(btSolve);
        BorderPane.setAlignment(btSolve, Pos.CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("Ex_33"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        board.paint();

        btSolve.setOnAction(e -> {
            /* Tora's note:
            In term of naming again, I think the "moves" should be named "board" or "visitedSquares" instead,
            as it represents the board and indicates which square had been visited.
             */
            boolean[][] moves = new boolean[SIZE][SIZE];
            moves[startX][startY] = true;
            resetMoveHistory();
            addMoveHistory(startX, startY);
            solvePuzzle(moves, 1, startX, startY);
            board.paint();
        });

        scene.widthProperty().addListener(ov -> board.paint());
        scene.heightProperty().addListener(ov -> board.paint());
    }

    // This method does the bulk of the work.
    // I'm not *thrilled* with this solution as
    // it has more redundant code than I'd prefer
    // but it gets the job done and done efficiently.
    // Uses Warnsdorff's heuristic discovered in 1823
    // that says the best move is the one with the
    // fewest next moves. I found it necessary to
    // back up in only one case (3,0) and choose to
    // try the second best move which worked well.
    private boolean solvePuzzle(boolean[][] moves, int numMoves, int x, int y) {
        int nextX;
        int nextY;
        int bestMoveX = 0;
        int bestMoveY = 0;
        int bestMoveX2 = 0;
        int bestMoveY2 = 0;
        int minMoveCount = SIZE; // Knight has max of 8 moves
        int moveCount = 0;

        for (int i = 2; i >= -2; i += -4) {
            for (int j = 1; j >= -1; j += -2) {
                nextX = x + i;
                nextY = y + j;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    moveCount = lookAheadCount(moves, nextX, nextY);
                    if (moveCount <= minMoveCount) {
                        minMoveCount = moveCount;
                        bestMoveX2 = bestMoveX;
                        bestMoveY2 = bestMoveY;
                        bestMoveX = nextX;
                        bestMoveY = nextY;
                    }
                }

                nextX = x + j;
                nextY = y + i;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    moveCount = lookAheadCount(moves, nextX, nextY);
                    if (moveCount <= minMoveCount) {
                        minMoveCount = moveCount;
                        bestMoveX2 = bestMoveX;
                        bestMoveY2 = bestMoveY;
                        bestMoveX = nextX;
                        bestMoveY = nextY;
                    }
                }
            }
        }
        moves[bestMoveX][bestMoveY] = true;
        addMoveHistory(bestMoveX, bestMoveY);
        numMoves++;
        if (numMoves == (SIZE * SIZE))
            return true;
        if (moveCount > 0 && solvePuzzle(moves, numMoves, bestMoveX, bestMoveY)) {
            return true;
        }
        moves[bestMoveX][bestMoveY] = false;
        moves[bestMoveX2][bestMoveY2] = true;
        removeLastMoveHistory();
        addMoveHistory(bestMoveX2, bestMoveY2);
        if (moveCount > 1 && solvePuzzle(moves, numMoves, bestMoveX2, bestMoveY2)) {
            return true;
        }
        moves[bestMoveX2][bestMoveY2] = false;
        removeLastMoveHistory();
        numMoves--;
        return false;
    }

    private int lookAheadCount(boolean[][] moves, int x, int y) {
        int maxCount = 0;
        for (int i = -2; i <= 2; i += 4) {
            for (int j = -1; j <= 1; j += 2) {
                int nextX = x + i;
                int nextY = y + j;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    maxCount++;
                }

                nextX = x + j;
                nextY = y + i;
                if (nextX >= 0 && nextX <= SIZE - 1 && nextY >= 0 && nextY <= SIZE - 1
                        && !moves[nextX][nextY]) {
                    maxCount++;
                }
            }
        }
        return maxCount;
    }

    public void resetMoveHistory() {
        moveHistory = new ArrayList<>(63);
    }

    public void addMoveHistory(int x, int y) {
        moveHistory.add(new Point2D(x, y));
    }

    public void removeLastMoveHistory() {
        moveHistory.remove(moveHistory.size() - 1);
    }

    private class ChessBoard extends Pane {
        ImageView knightImageView = new ImageView("image/knight.jpg");
        // The animation for animation
        Timeline animation;
        // The moving Knight image
        ImageView knightMoveImageView = new ImageView("image/knight.jpg");

        int moveIndex;

        ChessBoard() {
            this.setOnMouseClicked(e -> {
                startX = (int)(e.getX() / (getWidth() / SIZE));
                startY = (int)(e.getY() / (getHeight() / SIZE));
                resetMoveHistory();
                paint();
            });
            EventHandler<ActionEvent> eventHandler = event -> {
                moveIndex++;
                Point2D p1 = moveHistory.get(moveIndex - 1);
                Point2D p2 = moveHistory.get(moveIndex);
                knightMoveImageView.setX(p2.getX() * (getWidth() / SIZE));
                knightMoveImageView.setY(p2.getY() * (getHeight() / SIZE));
                this.getChildren().add(
                        new Line(p1.getX() * (getWidth() / SIZE) + getWidth() / SIZE / 2,
                                p1.getY() * (getHeight() / SIZE) + (getHeight() / SIZE / 2),
                                p2.getX() * (getWidth() / SIZE) + getWidth() / SIZE / 2,
                                p2.getY() * (getHeight() / SIZE) + getHeight() / SIZE / 2));
            };
            animation = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
        }

        protected void paint() {
            animation.stop();
            // Clear previous drawing
            this.getChildren().clear();

            // Add the Knight image
            this.getChildren().add(knightImageView);
            knightImageView.setX(startX * getWidth() / SIZE);
            knightImageView.setY(startY * getHeight() / SIZE);
            knightImageView.setFitWidth(getWidth() / SIZE);
            knightImageView.setFitHeight(getHeight() / SIZE);

            // Draw grid lines
            for (int i = 1; i <= SIZE; i++) {
                this.getChildren().add(
                        new Line(0, i * getHeight() / SIZE, getWidth(), i * getHeight() / SIZE));
                this.getChildren().add(
                        new Line(i * getWidth() / SIZE, 0, i * getWidth() / SIZE, getHeight()));
            }

            // Draw the moves with animation
            if (moveHistory != null && !moveHistory.isEmpty()) {
                // Preparing for animation
                moveIndex = 0;
                animation.setCycleCount(moveHistory.size() - 1);

                // Initialize the first position of Moving Knight
                this.getChildren().add(knightMoveImageView);
                knightMoveImageView.setX(knightImageView.getX());
                knightMoveImageView.setY(knightImageView.getY());
                knightMoveImageView.setFitWidth(knightImageView.getFitWidth());
                knightMoveImageView.setFitHeight(knightImageView.getFitHeight());

                animation.play();
            }
        }
    }
}