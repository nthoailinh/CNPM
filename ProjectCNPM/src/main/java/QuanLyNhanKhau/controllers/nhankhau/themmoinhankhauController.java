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

public class themmoinhankhauController {

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

            Connection connection = MySQL.getConnection();
            PreparedStatement pstmt_nhankhau = nhankhauDB.addNhanKhau(hoTen.getText(), ngaySinh.getValue(), gioiTinh, noiSinh.getText(),
                    nguyenQuan.getText(), danToc.getText(), ngheNghiep.getText(), noiLamViec.getText());
            // Tìm ID nhân khẩu
            ResultSet rs = pstmt_nhankhau.getGeneratedKeys();
            int idNhanKhau = 0;
            if (rs.next()) {
                idNhanKhau = rs.getInt(1);
            }
            if (cccd.getText() != null && ngayCap.getValue() != null && noiCap.getText() != null) {
                nhankhauDB.addCCCD(cccd.getText(), idNhanKhau, ngayCap.getValue(), noiCap.getText());
            }

        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
