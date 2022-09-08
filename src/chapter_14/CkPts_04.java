package chapter_14;

import javafx.application.Application;
import javafx.stage.Stage;

public class CkPts_04 extends Application {
    public CkPts_04() {
        System.out.println("Contructor invoked");
    }
    
    @Override
    public void start(Stage arg0) throws Exception {
        System.out.println("start method invoked");
    }
    
    public static void main(String[] args) {
        System.out.println("launch application");
        launch(args);
    }
}
