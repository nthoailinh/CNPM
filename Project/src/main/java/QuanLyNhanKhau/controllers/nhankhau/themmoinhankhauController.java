package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.models.CCCD;
import QuanLyNhanKhau.models.NhanKhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

public class themmoinhankhauController {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private TextField cccd;

    @FXML
    private TextField danToc;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton gioiTinhNam;

    @FXML
    private RadioButton gioiTinhNu;

    @FXML
    private TextField hoTen;

    @FXML
    private DatePicker ngayCap;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private TextField ngheNghiep;

    @FXML
    private TextField nguyenQuan;

    @FXML
    private TextField noiCap;

    @FXML
    private TextField noiLamViec;

    @FXML
    private TextField noiSinh;

    @FXML
    private TextField quanHeVoiChuHo;

    @FXML
    private TextField soHoKhau;

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnLuu) {
            if (soHoKhau.getText().isEmpty() || hoTen.getText().isEmpty() ||
                    ngaySinh.getValue() == null || noiSinh.getText().isEmpty() ||
                    (!gioiTinhNam.isSelected() && !gioiTinhNu.isSelected()) ||
                    nguyenQuan.getText().isEmpty() || danToc.getText().isEmpty() ||
                    quanHeVoiChuHo.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            String gioiTinh = gioiTinhNam.isSelected() ? "Nam" : (gioiTinhNu.isSelected() ? "Nữ" : "Không rõ");
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
