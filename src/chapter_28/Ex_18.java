package chapter_28;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.List;

public class Ex_18 extends Application {
    private final String instructionText = "Click a square to place the Knight";
    private final Label statusLbl = new Label(instructionText);;

    @Override
    public void start(Stage primaryStage) {
        final String cycleFoundText = "A cycle is found";
        final String cycleNotFoundText = "Cycle not found";

        ChessBoard chessBoard = new ChessBoard();
        Button solveBtn = new Button("Solve");
        solveBtn.setOnMouseClicked(e -> {
            statusLbl.setText(chessBoard.solve() ? cycleFoundText : cycleNotFoundText);
        });

        BorderPane pane = new BorderPane(chessBoard);
        pane.setBottom(solveBtn);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("chapter_28/BoardGameGUI.css");

        primaryStage.setTitle("The Knight Tour Problem"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    class ChessBoard extends Pane {
        private static final int BOARD_LENGTH = KnightTourModel.BOARD_LENGTH;
        private final GridPane board = new GridPane();
        private final ImageView knight = new ImageView("image/knight.jpg");
        private final StackPane[][] squares = new StackPane[BOARD_LENGTH][BOARD_LENGTH];
        private final KnightTourModel knightTourModel = new KnightTourModel();
        private int knightRow, knightColumn;

        public ChessBoard() {
            super();
            getChildren().add(board);
            for (int i = 0; i < BOARD_LENGTH; i++) {
                for (int j = 0; j < BOARD_LENGTH; j++) {
                    int row = i, column = j;
                    StackPane square = squares[i][j] = new StackPane();
                    square.setMinWidth(70);
                    square.setMinHeight(70);
                    square.getStyleClass().add("game-grid-cell");
                    if (row == 0) square.getStyleClass().add("first-row");
                    if (column == 0) square.getStyleClass().add("first-column");
                    board.add(square, column, row);

                    squares[i][j].setOnMouseClicked(e -> {
                        removeLines();
                        if (square.getChildren().isEmpty()) {
                            knightRow = row;
                            knightColumn = column;
                            square.getChildren().add(knight);
                        }
                    });
                }
            }
            squares[0][0].getChildren().add(knight);
        }

        public boolean solve() {
            removeLines();
            List<Integer> moves = knightTourModel
                    .getHamiltonianCycle(knightRow * BOARD_LENGTH + knightColumn);
            if (moves == null)
                return false;

            Point2D currentSquareCenter = squares[knightRow][knightColumn].localToScene(
                    squares[knightRow][knightColumn].getWidth() / 2,
                    squares[knightRow][knightColumn].getHeight() / 2),
            nextSquareCenter;
            for (int index: moves) {
                knightRow = index / BOARD_LENGTH;
                knightColumn = index % BOARD_LENGTH;
                nextSquareCenter = squares[knightRow][knightColumn].localToScene(
                        squares[knightRow][knightColumn].getWidth() / 2,
                        squares[knightRow][knightColumn].getHeight() / 2);
                this.getChildren().add(
                        new Line(currentSquareCenter.getX(), currentSquareCenter.getY(),
                                nextSquareCenter.getX(), nextSquareCenter.getY()));
                currentSquareCenter = nextSquareCenter;
            }
            squares[knightRow][knightColumn].getChildren().add(knight);
            return true;
        }

        public void removeLines() {
            getChildren().clear();
            getChildren().add(board);
            statusLbl.setText(instructionText);
        }
    }
}
