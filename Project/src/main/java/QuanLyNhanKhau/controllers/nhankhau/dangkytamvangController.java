package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.models.TamVang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class dangkytamvangController {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private TextField cccd;

    @FXML
    private DatePicker denNgay;

    @FXML
    private TextField lyDo;

    @FXML
    private TextField maGiayTamVang;

    @FXML
    private TextField noiTamTru;

    @FXML
    private DatePicker tuNgay;

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnXacNhan) {
            if (cccd.getText().isEmpty() || maGiayTamVang.getText().isEmpty() ||
                    tuNgay.getValue() == null || denNgay.getValue() == null ||
                    lyDo.getText().isEmpty() || noiTamTru.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            int idNhanKhau = 10; // cái này từ cccd người dùng nhập vào => idNhanKhau, người làm DB làm cái này nhé
            TamVang tamvang = new TamVang(10, idNhanKhau, maGiayTamVang.getText(), tuNgay.getValue(),
                                    denNgay.getValue(), noiTamTru.getText(), lyDo.getText());
        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
