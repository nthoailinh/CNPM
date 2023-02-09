package QuanLyNhanKhau.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChildWindows {
    public static FXMLLoader getLoader(String link) throws IOException {
        return new FXMLLoader(QuanLyNhanKhau.views.ChildWindows.class.getResource(link));
    }
    public static Parent getRoot(String link) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.views.ChildWindows.class.getResource(link));
        return fxmlLoader.load();
    }
    public static void show(String link) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.views.ChildWindows.class.getResource(link));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}