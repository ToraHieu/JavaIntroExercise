package chapter_32;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Ex_19 extends Application {
    @Override
    public void start(Stage primaryStage) {
        int[] list1 = new int[50], list2 = new int[50], list3 = new int[50];
        for (int i = 0; i < list1.length; i++) {
            list1[i] = i + 1;
            list2[i] = i + 1;
            list3[i] = i + 1;
        }
        shuffleArray(list1);
        shuffleArray(list2);
        shuffleArray(list3);

        SortPane sortPane1 = new SortPane(list1);
        SortPane sortPane2 = new SortPane(list2);
        SortPane sortPane3 = new SortPane(list3);

        HBox hbox = new HBox(sortPane1, sortPane2, sortPane3);
        hbox.setPadding(new Insets(10, 40, 10, 40));
        hbox.setSpacing(10);

        HBox.setHgrow(sortPane1, Priority.ALWAYS);
        HBox.setHgrow(sortPane2, Priority.ALWAYS);
        HBox.setHgrow(sortPane3, Priority.ALWAYS);

        Scene scene = new Scene(hbox, 800, 250);
        primaryStage.setTitle("Ex_19");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            // Selection Sort
            for (int i = 0; i < list1.length - 1;) {
                int minIndex = i;
                for (int j = i + 1; j < list1.length; j++) {
                    if (list1[j] < list1[minIndex])
                        minIndex = j;
                }

                int temp = list1[minIndex];
                list1[minIndex] = list1[i];
                list1[i] = temp;
                final int f = ++i;
                Platform.runLater(() -> {
                    sortPane1.setCurrentIndex(f);
                    sortPane1.updateColumns();
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            // Insertion Sort
            for (int i = 1; i < list2.length;) {
                int key = list2[i];
                int j = i - 1;
                while (j >= 0 && list2[j] > key) {
                    list2[j + 1] = list2[j];
                    j = j - 1;
                }
                list2[j + 1] = key;
                final int f = ++i;
                Platform.runLater(() -> {
                    sortPane2.setCurrentIndex(f - 1);
                    sortPane2.updateColumns();
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            // Bubble Sort
            for (int i = 0; i < list3.length - 1;) {
                for (int j = 0; j < list3.length - i - 1; j++) {
                    if (list3[j] > list3[j + 1]) {
                        int temp = list3[j];
                        list3[j] = list3[j + 1];
                        list3[j + 1] = temp;
                    }
                }
                final int f = ++i;
                Platform.runLater(() -> {
                    sortPane3.setCurrentIndex(f);
                    sortPane3.updateColumns();
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    // Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static class SortPane extends Pane {
        private final int[] numbers;
        private final Rectangle[] columns;

        private int currentIndex = -1;

        public SortPane(int[] numbers) {
            this.numbers = numbers;
            columns = new Rectangle[numbers.length];
            drawPane();

            setOnResize();
            setPrefSize(100, 100);
        }

        public int[] getNumbers() {
            return numbers;
        }

        public int getCurrentIndex() {
            return currentIndex;
        }

        public void setCurrentIndex(int currentIndex) {
            if (this.currentIndex >= 0)
                columns[this.currentIndex].setFill(Color.WHITE);
            this.currentIndex = currentIndex;
            if (currentIndex > 0 && currentIndex < columns.length) {
                columns[currentIndex].setFill(Color.GREEN);
            }
        }

        public void updateColumns() {
            double unit = heightProperty().doubleValue() / columns.length;
            for (int i = 0; i < columns.length; i++) {
                columns[i].setHeight(numbers[i] * unit);
                columns[i].setY(heightProperty().doubleValue() - columns[i].getHeight());
            }
        }

        public void drawPane() {
            drawColumns();
        }

        private void drawColumns() {
            for (int i = 0; i < columns.length; i++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(null);
                rectangle.setStroke(Color.BLACK);

                columns[i] = rectangle;
            }
            getChildren().addAll(columns);
        }

        private void setOnResize() {
            heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {
                    double unit = newValue.doubleValue() / columns.length;
                    for (int i = 0; i < columns.length; i++) {
                        columns[i].setHeight(numbers[i] * unit);
                        columns[i].setY(newValue.doubleValue() - columns[i].getHeight());
                    }
                }
            });

            widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {
                    double unit = newValue.doubleValue() / columns.length;
                    for (int i = 0; i < columns.length; i++) {
                        columns[i].setWidth(unit);
                        columns[i].setX(unit * i);
                    }
                }
            });
        }
    }
}
