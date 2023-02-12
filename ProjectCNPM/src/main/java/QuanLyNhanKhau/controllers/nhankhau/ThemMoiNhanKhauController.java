package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemMoiNhanKhauController {

    private NhanKhauDB nhankhauDB = new NhanKhauDB();

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
    void handleClicks(ActionEvent event) throws SQLException {
        if (event.getSource() == btnLuu) {
            if (hoTen.getText().isEmpty() || ngaySinh.getValue() == null || noiSinh.getText().isEmpty() || (!gioiTinhNam.isSelected() && !gioiTinhNu.isSelected()) ||
                    nguyenQuan.getText().isEmpty() || danToc.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            String gioiTinh = gioiTinhNam.isSelected() ? "Nam" : (gioiTinhNu.isSelected() ? "Nữ" : "Không rõ");
            PreparedStatement pstmt_nhankhau = nhankhauDB.addNhanKhau(hoTen.getText(), ngaySinh.getValue(), gioiTinh, noiSinh.getText(),
                    nguyenQuan.getText(), danToc.getText(), ngheNghiep.getText(), noiLamViec.getText());
            // Tìm ID nhân khẩu
            ResultSet rs = pstmt_nhankhau.getGeneratedKeys();
            int idNhanKhau = 0;
            if (rs.next()) {
                idNhanKhau = rs.getInt(1);
            }
            if (cccd.getText() != null) {
                if (nhankhauDB.checkExistsCCCD(cccd.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Không thể lưu");
                    alert.setHeaderText("Đã tồn tại số CCCD này trong hệ thống.");
                    alert.setContentText("Vui lòng điền lại số CCCD.");
                    alert.showAndWait();
                    return;
                }
                if (cccd.getText().length() < 12) {
                    if (ngayCap != null && noiCap != null) {
                        nhankhauDB.addCCCD(cccd.getText(), idNhanKhau, ngayCap.getValue(), noiCap.getText());
                    } else if (ngayCap != null) {
                        nhankhauDB.addCCCD(cccd.getText(), idNhanKhau, ngayCap.getValue(), null);
                    } else if (noiCap != null) {
                        nhankhauDB.addCCCD(cccd.getText(), idNhanKhau, null, noiCap.getText());
                    } else {
                        nhankhauDB.addCCCD(cccd.getText(), idNhanKhau, null, null);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Không thể lưu");
                    alert.setHeaderText("Độ dài số CCCD không được vượt quá 12.");
                    alert.setContentText("Vui lòng điền lại số CCCD.");
                    alert.showAndWait();
                    return;
                }
            }

        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
