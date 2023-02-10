package QuanLyNhanKhau.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        Windows.show("login.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}