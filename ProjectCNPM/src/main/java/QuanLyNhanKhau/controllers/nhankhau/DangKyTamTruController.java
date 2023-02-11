package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangKyTamTruController {

    private NhanKhauDB nhankhauDB = new NhanKhauDB();

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

    public int checkCCCD() throws SQLException {
        if (cccd.getText() == null || cccd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Số CMT/CCCD không hợp lệ");
            alert.setHeaderText(null);
            alert.setContentText("Số CMT/CCCD không được để trống.");
            alert.showAndWait();
            return -1;
        }
        Connection connection = MySQL.getConnection();
        // Tìm id nhân khẩu đăng ký tạm trú
        PreparedStatement pstmt_nhankhau = null;
        pstmt_nhankhau = connection.prepareStatement("SELECT * FROM NhanKhau JOIN CCCD ON NhanKhau.id = CCCD.idNhanKhau WHERE cccd = ?");
        pstmt_nhankhau.setString(1, cccd.getText());
        ResultSet rs = pstmt_nhankhau.executeQuery();
        int idNhanKhau = -1;
        if (rs.next()) {
            idNhanKhau = rs.getInt("idNhanKhau");
            System.out.println(idNhanKhau);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Số CMT/CCCD hợp lệ");
            alert.setHeaderText(null);
            String hoTen = rs.getString("hoTen");
            alert.setContentText("Tìm thấy nhân khẩu " + hoTen + " có số CMT/CCCD " + cccd.getText());
            alert.showAndWait();
            return idNhanKhau;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Số CMT/CCCD không hợp lệ");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng kiểm tra và nhập lại số CMT/CCCD.");
            alert.showAndWait();
            return idNhanKhau;
        }
    }

    @FXML
    void handleClicks(ActionEvent event) throws SQLException {
        int idNhanKhau = -2;
        if (event.getSource() == btnKiemTra) {
            idNhanKhau = checkCCCD();
        } else if (event.getSource() == btnXacNhan) {
            if (cccd.getText().isEmpty() || maGiayTamTru.getText().isEmpty() ||
                    tuNgay.getValue() == null || denNgay.getValue() == null || lyDo.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            if (idNhanKhau == -2) {
                idNhanKhau = checkCCCD();
            }
            if (idNhanKhau != -1) {
                nhankhauDB.addTamTru(idNhanKhau, maGiayTamTru.getText(), tuNgay.getValue(),
                        denNgay.getValue(), lyDo.getText());
                // Close window
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
        } else if (event.getSource() == btnHuy) {
            // Close window
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

}

