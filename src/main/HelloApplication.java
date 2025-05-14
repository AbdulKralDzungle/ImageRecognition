package main;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.beans.EventHandler;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int gridSize = 28;
        int cellSize = 10;


        stage.setTitle("Ban Dlazek");
        Pane pane = new Pane();

        Button restartBt = new Button("Restart");
        restartBt.setScaleX(1);
        restartBt.setScaleY(1);
        restartBt.setLayoutY(320);


        Rectangle[][] rectangle = new Rectangle[28][28];
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

        Scene scene1 = new Scene(pane, 320, 400);
        stage.setScene(scene1);
        stage.setResizable(false);

        pane.getChildren().add(restartBt);

        pane.setOnMouseDragged(pavel -> {
            Point pointerLocation = MouseInfo.getPointerInfo().getLocation();

            int sceneX = pointerLocation.x;
            sceneX -= pane.getScene().getWindow().getX();
            sceneX -= pane.getScene().getX();

            int sceneY = pointerLocation.y;
            sceneY -= pane.getScene().getWindow().getY();
            sceneY -= pane.getScene().getY();
            System.out.println(sceneX);
            System.out.println(sceneY);

            int x = (int) Math.floor((double) sceneX / cellSize);
            int y = (int) Math.floor((double) sceneY / cellSize);
            x--;
            y--;
            try {
                rectangle[x][y].setFill(Color.WHITE);
            } catch (Exception _) {

            }

        });


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}