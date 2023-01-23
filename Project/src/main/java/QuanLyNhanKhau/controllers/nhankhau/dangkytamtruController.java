package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.models.TamTru;
import QuanLyNhanKhau.views.ChildWindows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class dangkytamtruController {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnKiemTra;

    @FXML
    private Button btnXacNhan;

    @FXML
    private TextField cccd;

    @FXML
    private DatePicker denNgay;

    @FXML
    private TextField lyDo;

    @FXML
    private TextField maGiayTamTru;

    @FXML
    private DatePicker tuNgay;

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnKiemTra) {
            ChildWindows.show("nhankhau/kiemtrathongtinnhankhau.fxml");
        } else {
            if (event.getSource() == btnXacNhan) {
                if (cccd.getText().isEmpty() || maGiayTamTru.getText().isEmpty() ||
                        tuNgay.getValue() == null || denNgay.getValue() == null || lyDo.getText().isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Không thể lưu");
                    alert.setHeaderText("Thiếu thông tin");
                    alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                    alert.showAndWait();
                    return;
                }
                int idNhanKhau = 10; // cái này từ cccd người dùng nhập vào => idNhanKhau, người làm DB làm cái này nhé
                TamTru tamtru = new TamTru(10, idNhanKhau, maGiayTamTru.getText(), tuNgay.getValue(),
                        denNgay.getValue(), lyDo.getText());
            }
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

}
