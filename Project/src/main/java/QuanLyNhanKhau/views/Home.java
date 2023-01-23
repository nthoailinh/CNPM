package QuanLyNhanKhau.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import QuanLyNhanKhau.models.NhanKhau;

import java.io.IOException;

public class Home extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
