package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    Ui ui;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ui = new Ui();
        stage.setTitle("Ban Dlazek");
        stage.setScene(ui.constructUi());
        stage.setResizable(false);
        stage.show();

    }
}