package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.models.CCCD;
import QuanLyNhanKhau.models.NhanKhau;
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
    private TextField noiTamTru;

    @FXML
    private DatePicker tuNgay;

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnXacNhan) {
            if (soHoKhau.getText().isEmpty() || hoTen.getText().isEmpty() ||
                    ngaySinh.getValue() == null || noiSinh.getText().isEmpty() ||
                    nguyenQuan.getText().isEmpty() || danToc.getText().isEmpty() ||
                    ngheNghiep.getText().isEmpty() || noiLamViec.getText().isEmpty() ||
                    quanHeVoiChuHo.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thiếu thông tin");
                alert.setHeaderText("Không thể lưu");
                alert.setContentText("Vui lòng điền tất cả các trường");
                alert.showAndWait();
                return;
            }
            char gioiTinh = gioiTinhNam.isSelected() ? 'M' : (gioiTinhNu.isSelected() ? 'F' : 'N');
            // ID chưa xử lý, ai kết nối vs db xử lý nhé
            NhanKhau nhankhau = new NhanKhau(10, Integer.parseInt(soHoKhau.getText()), hoTen.getText(),
                    ngaySinh.getValue(), gioiTinh, noiSinh.getText(), nguyenQuan.getText(), danToc.getText(),
                    ngheNghiep.getText(), noiLamViec.getText(), quanHeVoiChuHo.getText());
            CCCD cccd_nhankhau = new CCCD(cccd.getText(), 10, ngayCap.getValue(), noiCap.getText());
        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
