package chapter_25;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Ex_13 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Ex13BST<Integer> tree = new Ex13BST<>(); // Create a tree

        BorderPane pane = new BorderPane();
        Text orderResult = new Text("");
        pane.setTop(orderResult);
        BorderPane.setAlignment(orderResult, Pos.CENTER);

        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert"), btDelete = new Button("Delete"),
                btInorder = new Button("Show Inorder"),
                btPreorder = new Button("Show Preorder"),
                btPostorder = new Button("Show Postorder");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btInorder, btPreorder, btPostorder);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayTree();
                view.setStatus(key + " is already in the tree");
            } else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayTree();
                view.setStatus(key + " is not in the tree");
            } else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });

        btInorder.setOnAction(e -> orderResult.setText("Inorder: " + tree.inorderList()));

        btPreorder.setOnAction(e -> orderResult.setText("Preorder: " + tree.preorderList()));

        btPostorder.setOnAction(e -> orderResult.setText("Postorder: " + tree.postorderList()));

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

class Ex13BST<E extends Comparable<E>> extends BST<E> {
    public Ex13BST() {
    }

    public Ex13BST(E[] objects) {
        super(objects);
    }

    public List<E> inorderList() {
        List<E> list = new ArrayList<>();
        inorderList(list, root);
        return list;
    }

    protected void inorderList(List<E> list, Ex13BST.TreeNode<E> root) {
        if (root == null) return;
        inorderList(list, root.left);
        list.add(root.element);
        inorderList(list, root.right);
    }

    public List<E> preorderList() {
        List<E> list = new ArrayList<>();
        preorderList(list, root);
        return list;
    }

    protected void preorderList(List<E> list, Ex13BST.TreeNode<E> root) {
        if (root == null) return;
        list.add(root.element);
        preorderList(list, root.left);
        preorderList(list, root.right);
    }

    public List<E> postorderList() {
        List<E> list = new ArrayList<>();
        postorderList(list, root);
        return list;
    }

    protected void postorderList(List<E> list, Ex13BST.TreeNode<E> root) {
        if (root == null) return;
        postorderList(list, root.left);
        postorderList(list, root.right);
        list.add(root.element);
    }
}