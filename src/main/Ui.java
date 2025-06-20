package main;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.controol.NetworkThred;

import java.awt.*;

public class Ui {
    private double[][] grid;
    private javafx.scene.control.TextField input;
    private Rectangle[][] rectangle;
    private Scene scene1;
    private Button restartBt;
    private final int gridSize = 28;
    private final int cellSize = 10;
    private Pane pane;
    private Button comandButton;
    private VBox vbox;
    private Thread thread;
    private Text text;
    private Text text2;
    private NetworkThred networkThred;

    public Scene constructUi() {
        pane = new Pane();
        vbox = new VBox();
        initialize();
        addActions();
        pane.getChildren().add(restartBt);
        pane.getChildren().add(vbox);
        return scene1;
    }

    private void initialize() {
        networkThred = new NetworkThred();
        thread = new Thread(networkThred);
        grid = new double[28][28];
        rectangle = new Rectangle[28][28];

        text = new Text();
        text.setX(100);
        text.setY(340);
        text.setFill(Color.BLACK);
        text.setText("Hello World");

        text2 = new Text();
        text2.setX(0);
        text2.setY(420);
        text2.setFill(Color.BLACK);
        text2.setText("all commands for controlling are in documentation");

        input = new TextField();
        vbox.getChildren().add(input);
        vbox.setLayoutY(360);

        restartBt = new Button("Restart");
        restartBt.setScaleX(1);
        restartBt.setScaleY(1);
        restartBt.setLayoutY(320);

        comandButton = new Button("submit");
        comandButton.setScaleX(1);
        comandButton.setScaleY(1);
        comandButton.setLayoutY(360);
        comandButton.setLayoutX(200);

        scene1 = new Scene(pane, 320, 450);
        pane.getChildren().add(text);
        pane.getChildren().add(text2);
        pane.getChildren().add(comandButton);
        // pane.getChildren().add(textArea);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Rectangle r = new Rectangle();
                r.setHeight(cellSize);
                r.setWidth(cellSize);
                r.setX(i * (cellSize + 1));
                r.setY(j * (cellSize + 1));

                rectangle[i][j] = r;
                pane.getChildren().add(rectangle[i][j]);
            }
        }
        networkThred.setInput(grid);
        thread.start();
    }

    public void setExit() {
        networkThred.setExit(true);
    }

    /**
     * this method is to define actions of various parts of the stage
     */
    private void addActions() {

        //here is handled the the painting using cursor
        pane.setOnMouseDragged(e -> {
            Point pointerLocation = MouseInfo.getPointerInfo().getLocation();

            int sceneX = pointerLocation.x;
            sceneX -= (int) pane.getScene().getWindow().getX();
            sceneX -= (int) pane.getScene().getX();

            int sceneY = pointerLocation.y;
            sceneY -= (int) pane.getScene().getWindow().getY();
            sceneY -= (int) pane.getScene().getY();

            int x = (int) Math.floor((double) sceneX / (cellSize + 1));
            int y = (int) Math.floor((double) sceneY / (cellSize + 1));
            try {
                rectangle[x][y].setFill(javafx.scene.paint.Color.WHITE);
                grid[x][y] = 1;
                networkThred.setInput(grid);
                text.setText(String.valueOf(networkThred.getOutput()));

            } catch (Exception _) {
                //mouse is out of bounds, this exception is kinda used instead of if statement to ignore those situations
            }

        });

        //----------------------------------
        //this restarts the the black/white grid upon clicking the button
        restartBt.setOnAction(e -> {
            grid = new double[28][28];
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    rectangle[i][j].setFill(Color.BLACK);
                }
            }
            if (!thread.isAlive()) {
                networkThred.setInput(grid);
                text.setText(String.valueOf(networkThred.getOutput()));
            }

        });
        //this shuts down app when the networkThread is finished
        pane.setOnMouseMoved(e -> {
            if (networkThred.isDone()) {
                Platform.exit();
            }
        });

        comandButton.setOnAction(e -> {
            try {
                networkThred.setCommandText(input.getText());
            } catch (Exception ex) {
                text.setText(ex.getMessage());
            }

        });
    }

}
