package chapter_28;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Ex_15 extends Application {
    private static final Insets PADDING_5 = new Insets(5);
    private static final String INSTRUCTION_LABEL = "Click on a cell to flip it";
    private SixteenTailBoard originalBoard;
    private HBox boardsPane;
    private Label statusLbl;

    @Override
    public void start(Stage primaryStage) {
        final SixteenTailModel model = new SixteenTailModel();

        statusLbl = new Label(INSTRUCTION_LABEL);

        originalBoard = new SixteenTailBoard(SixteenTailModel.getNode(0));
        boardsPane = new HBox(5, originalBoard);
        boardsPane.setPadding(PADDING_5);

        Button solveBtn = new Button("Solve"), startOverBtn = new Button("Start Over"),
                randomizeBtn = new Button("Randomize");
        HBox bottom = new HBox(5, solveBtn, startOverBtn, randomizeBtn);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(PADDING_5);

        BorderPane pane = new BorderPane(boardsPane);
        pane.setTop(statusLbl);
        BorderPane.setAlignment(statusLbl, Pos.CENTER);
        pane.setBottom(bottom);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 600, 170);
        scene.getStylesheets().add("chapter_28/BoardGameGUI.css");

        primaryStage.setTitle("Sixteen Tail Problem"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        solveBtn.setOnMouseClicked(e -> {
            if (boardsPane.getChildren().size() == 1) {
                originalBoard.flippedPosition = -1;
                originalBoard.drawBoard();
                List<Integer> path =
                        model.getShortestPath(SixteenTailModel.getIndex(originalBoard.getNode()));
                if (path != null) {
                    for (int i = 1; i < path.size(); i++) {
                        boardsPane.getChildren().add(
                                new SixteenTailBoard(SixteenTailModel.getNode(path.get(i)),
                                        originalBoard.getFlippedPosition(
                                                SixteenTailModel.getNode(path.get(i-1)),
                                                SixteenTailModel.getNode(path.get(i))))
                        );
                        statusLbl.setText("A solution is found.");
                    }
                } else {
                    statusLbl.setText("No solution exist for this pattern.");
                }
            }
        });

        startOverBtn.setOnMouseClicked(e -> setBoard(0));

        randomizeBtn.setOnMouseClicked(e -> setBoard(
                (int) (Math.random()*SixteenTailModel.NUMBER_OF_NODES)));
    }

    private void setBoard(int nodeIndex) {
        boardsPane.getChildren().clear();
        originalBoard.flippedPosition = -1;
        originalBoard.node = SixteenTailModel.getNode(nodeIndex);
        originalBoard.drawBoard();
        boardsPane.getChildren().add(originalBoard);
        statusLbl.setText(INSTRUCTION_LABEL);
    }

    class SixteenTailBoard extends GridPane {
        private static final int BOARD_LENGTH = 4;
        private final SixteenTailBoard.CoinPane[][] coinPanes = new SixteenTailBoard.CoinPane[BOARD_LENGTH][BOARD_LENGTH];
        private final Label[][] coins = new Label[BOARD_LENGTH][BOARD_LENGTH];

        private char[] node;

        private int flippedPosition;

        public SixteenTailBoard(char[] node) {
            this(node, -1);
        }

        public SixteenTailBoard(char[] node, int flippedPosition) {
            this.flippedPosition = flippedPosition;
            setAlignment(Pos.CENTER);

            this.node = node;
            for (int i = 0; i < BOARD_LENGTH; i++) {
                for (int j = 0; j < BOARD_LENGTH; j++) {
                    coins[i][j] = new Label(String.valueOf(node[i*BOARD_LENGTH + j]));
                    SixteenTailBoard.CoinPane pane = coinPanes[i][j] = new SixteenTailBoard.CoinPane(i, j, coins[i][j]);
                    pane.setMinWidth(20);
                    pane.setPadding(PADDING_5);
                    pane.getStyleClass().add("game-grid-cell");
                    if (i == 0) pane.getStyleClass().add("first-row");
                    if (j == 0) pane.getStyleClass().add("first-column");
                    add(pane, j, i);

                    if (flippedPosition == -1) {
                        pane.setOnMouseClicked(event -> {
                            this.flippedPosition = pane.X * BOARD_LENGTH + pane.Y;
                            SixteenTailModel.getFlippedNode(this.node, this.flippedPosition);
                            drawBoard();
                        });
                    }
                }
            }
            drawBoard();
        }

        public int getFlippedPosition(char[] node1, char[] node2) {
            if (node1.length != node2.length)
                return -1;
            for (int i = 0; i < node1.length; i++) {
                if (node1[i] != node2[i]) {
                    int cellBelow = i + BOARD_LENGTH;
                    if (i == 0 && node1[cellBelow] != node2[cellBelow] && node1[1] != node2[1]) return i; // Special case
                    if (node1[cellBelow] != node2[cellBelow])
                        return cellBelow;
                    return i + 1;
                }
            }
            return -1;
        }

        public void drawBoard() {
            List<Integer> flippedCells = getFlippedCells(flippedPosition);
            for (int i = 0; i < BOARD_LENGTH; i++) {
                for (int j = 0; j < BOARD_LENGTH; j++) {
                    coins[i][j].setText(String.valueOf(node[i*BOARD_LENGTH + j]));
                    if (flippedCells.contains(i * BOARD_LENGTH + j))
                        coins[i][j].setStyle("-fx-text-fill: red");
                    else
                        coins[i][j].setStyle("-fx-text-fill: black");
                    if (flippedPosition != -1 && i * BOARD_LENGTH + j == flippedCells.get(0)) {
                        if (!coinPanes[i][j].getStyleClass().contains("game-grid-flipped-cell"))
                            coinPanes[i][j].getStyleClass().add("game-grid-flipped-cell");
                    } else
                        coinPanes[i][j].getStyleClass().remove("game-grid-flipped-cell");
                }
            }
        }

        private List<Integer> getFlippedCells(int flippedPosition) {
            List<Integer> list = new ArrayList<>(5);
            if (flippedPosition != -1) {
                list.add(flippedPosition);
                int x = flippedPosition / BOARD_LENGTH, y = flippedPosition % BOARD_LENGTH;
                if (y - 1 >= 0)
                    list.add(flippedPosition - 1);
                if (y + 1 < BOARD_LENGTH)
                    list.add(flippedPosition + 1);
                if (x - 1 >= 0)
                    list.add(flippedPosition - BOARD_LENGTH);
                if (x + 1 < BOARD_LENGTH)
                    list.add(flippedPosition + BOARD_LENGTH);
            }

            return list;
        }

        public void setNode(char[] node) {
            this.node = node;
            drawBoard();
        }

        public char[] getNode() {
            return node;
        }

        class CoinPane extends StackPane {
            private final int X, Y;

            public CoinPane(int x, int y, Node... children) {
                super(children);
                X = x;
                Y = y;
            }

            public int getX() {
                return X;
            }

            public int getY() {
                return Y;
            }
        }
    }
}
