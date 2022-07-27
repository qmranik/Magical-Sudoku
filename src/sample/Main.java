package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("#Magical Sudoku");
        primaryStage.setScene(new Scene(root, 720, 485));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
