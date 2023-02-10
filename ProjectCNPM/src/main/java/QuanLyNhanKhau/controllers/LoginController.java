package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.services.LoginDB;
import QuanLyNhanKhau.views.Windows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    void handleClicks(ActionEvent event) throws IOException, SQLException {
        if (userName.getText().isEmpty()) {
            displayDialog("Thiếu tên đăng nhập", "Nhập đủ tên đăng nhập và mật khẩu!");
        } else if (password.getText().isEmpty()) {
            displayDialog("Thiếu mật khẩu", "Nhập đủ tên đăng nhập và mật khẩu!");
        } else {
            LoginDB login = new LoginDB();
            if (login.validate(userName.getText(), password.getText())) {
                if(userName.getText().equals("totruong")){
                    Windows.show("todanpho.fxml");
                }
                else if(userName.getText().equals("canboyte")){
                    Windows.show("yte.fxml");
                }

                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.close();
            }
            else {
                displayDialog("Sai tên đăng nhập / mật khẩu", "Nhập đúng tên đăng nhập và mật khẩu!");
            }
        }
    }

    private void displayDialog(String message_1, String message_2) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Lỗi");
        alert.setHeaderText(message_1);
        alert.setContentText(message_2);

        alert.showAndWait();
    }

}
