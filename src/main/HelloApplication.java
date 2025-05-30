package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class HelloApplication extends Application {
    private int[][] grid;
    private Rectangle[][] rectangle;
    private Scene scene1;
    private Button restartBt;
    private final int gridSize = 28;
    private final int cellSize = 10;
    private Pane pane;

    public static void main(String[] args) {
        launch(args);

        //Ui ui = new Ui();
    }

    private void initialize() {

        grid = new int[28][28];
        rectangle = new Rectangle[28][28];
        pane = new Pane();
        scene1 = new Scene(pane, 320, 400);
        restartBt = new Button("Restart");

        restartBt.setScaleX(1);
        restartBt.setScaleY(1);
        restartBt.setLayoutY(320);

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
    }

    private void addActions() {
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
                grid[x][y] = 256;
            } catch (Exception _) {

            }

        });
        restartBt.setOnAction(e -> {
            grid = new int[28][28];
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    rectangle[i][j].setFill(Color.BLACK);
                }
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        initialize();
        addActions();
        stage.setTitle("Ban Dlazek");
        stage.setScene(scene1);
        stage.setResizable(false);
        //------------------------------------
        pane.getChildren().add(restartBt);
        stage.show();
        //------------------------------------
    }
}