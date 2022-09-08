package chapter_32;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ex_01 extends Application {
    final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create tasks
        Runnable printA = new PrintChar('a', 100);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(9);

        // Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);

        Scene scene = new Scene(new StackPane(textArea));
        primaryStage.setTitle("Ex_01");
        primaryStage.setScene(scene);
        primaryStage.show();

        while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive());
        textArea.setText(stringBuilder.toString());
    }

    // The task for printing a specified character in specified times
    class PrintChar implements Runnable {
        private char charToPrint; // The character to print
        private int times; // The times to repeat

        /** Construct a task with specified character and number of
         *  times to print the character
         */
        public PrintChar(char c, int t) {
            charToPrint = c;
            times = t;
        }

        /** Override the run() method to tell the system
         *  what the task to perform
         */
        public void run() {
            for (int i = 0; i < times; i++) {
                synchronized (stringBuilder) {
                    try {
                        stringBuilder.append(charToPrint);
                        if (i % 10 == 0 && (int) (Math.random() * 10) == 0) {
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // The task class for printing number from 1 to n for a given n
    class PrintNum implements Runnable {
        private int lastNum;

        /** Construct a task for printing 1, 2, ... i */
        public PrintNum(int n) {
            lastNum = n;
        }

        /** Tell the thread how to run */
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                synchronized (stringBuilder) {
                    try {
                        stringBuilder.append(" ").append(i);
                        if (i % 10 == 0 && (int) (Math.random() * 10) == 0) {
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}