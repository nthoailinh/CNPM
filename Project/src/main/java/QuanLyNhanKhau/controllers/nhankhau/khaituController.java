package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.models.KhaiTu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

public class khaituController {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private TextField cccdNguoiKhai;

    @FXML
    private TextField cccdNguoiMat;

    @FXML
    private DatePicker ngayKhaiTu;

    @FXML
    private DatePicker ngayQuaDoi;

    @FXML
    private TextField nguyenNhan;

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnLuu) {
            if (cccdNguoiKhai.getText().isEmpty() || cccdNguoiMat.getText().isEmpty() ||
                    ngayQuaDoi.getValue() == null || ngayKhaiTu.getValue() == null ||
                    nguyenNhan.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thiếu thông tin");
                alert.setHeaderText("Không thể lưu");
                alert.setContentText("Vui lòng điền tất cả các trường");
                alert.showAndWait();
                return;
            }
            int idNhanKhau = 10; // cái này từ cccd người dùng nhập vào => idNhanKhau, người làm DB làm cái này nhé
            KhaiTu khaitu = new KhaiTu(idNhanKhau, nguyenNhan.getText(), ngayQuaDoi.getValue(), ngayKhaiTu.getValue());
        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
