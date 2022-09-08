package chapter_28;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Ex_11 extends Application {
    private static final Insets padding5 = new Insets(5);

    @Override
    public void start(Stage primaryStage) {
        final NineTailModel model = new NineTailModel();

        NineTailBoard originalBoard = new NineTailBoard(NineTailModel.getNode(0));
        HBox boardsPane = new HBox(5, originalBoard);
        boardsPane.setPadding(padding5);

        Button startBtn = new Button("Solve"), startOverBtn = new Button("Start Over");
        HBox bottom = new HBox(5, startBtn, startOverBtn);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(padding5);

        BorderPane pane = new BorderPane(boardsPane);
        pane.setBottom(bottom);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 130);
        scene.getStylesheets().add("chapter_28/BoardGameGUI.css");

        primaryStage.setTitle("Nine Tail Problem"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        startBtn.setOnMouseClicked(e -> {
            if (boardsPane.getChildren().size() == 1) {
                originalBoard.flippedPosition = -1;
                originalBoard.drawBoard();
                List<Integer> path =
                        model.getShortestPath(NineTailModel.getIndex(originalBoard.getNode()));
                for (int i = 1; i < path.size(); i++) {
                    boardsPane.getChildren().add(
                            new NineTailBoard(NineTailModel.getNode(path.get(i)),
                                    originalBoard.getFlippedPosition(
                                            NineTailModel.getNode(path.get(i-1)),
                                            NineTailModel.getNode(path.get(i))))
                    );
                }
            }
        });

        startOverBtn.setOnMouseClicked(e -> {
            boardsPane.getChildren().clear();
            originalBoard.flippedPosition = -1;
            originalBoard.node = NineTailModel.getNode(0);
            originalBoard.drawBoard();
            boardsPane.getChildren().add(originalBoard);
        });
    }

    class NineTailBoard extends GridPane {
        private static final int BOARD_LENGTH = 3;
        private final CoinPane[][] coinPanes = new CoinPane[BOARD_LENGTH][BOARD_LENGTH];
        private final Label[][] coins = new Label[BOARD_LENGTH][BOARD_LENGTH];

        private char[] node;

        private int flippedPosition;

        public NineTailBoard(char[] node) {
            this(node, -1);
        }

        public NineTailBoard(char[] node, int flippedPosition) {
            this.flippedPosition = flippedPosition;
            setAlignment(Pos.CENTER);

            this.node = node;
            for (int i = 0; i < BOARD_LENGTH; i++) {
                for (int j = 0; j < BOARD_LENGTH; j++) {
                    coins[i][j] = new Label(String.valueOf(node[i*BOARD_LENGTH + j]));
                    CoinPane pane = coinPanes[i][j] = new CoinPane(i, j, coins[i][j]);
                    pane.setMinWidth(20);
                    pane.setPadding(padding5);
                    pane.getStyleClass().add("game-grid-cell");
                    if (i == 0) pane.getStyleClass().add("first-row");
                    if (j == 0) pane.getStyleClass().add("first-column");
                    add(pane, j, i);

                    if (flippedPosition == -1) {
                        pane.setOnMouseClicked(event -> {
                            this.flippedPosition = pane.X * BOARD_LENGTH + pane.Y;
                            NineTailModel.getFlippedNode(this.node, this.flippedPosition);
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