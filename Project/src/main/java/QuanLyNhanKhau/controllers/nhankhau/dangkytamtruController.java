package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.models.TamTru;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.Update;
import QuanLyNhanKhau.views.ChildWindows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    void handleClicks(ActionEvent event) throws SQLException {
        int idNhanKhau = -2;
        if (event.getSource() == btnKiemTra) {
            idNhanKhau = checkCCCD();
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
                if (idNhanKhau == -2) {
                    idNhanKhau = checkCCCD();
                }
                if (idNhanKhau != -1) {
                    Update update = new Update();
                    update.TamTru(idNhanKhau, maGiayTamTru.getText(), tuNgay.getValue(),
                            denNgay.getValue(), lyDo.getText());
                    // Tắt cửa sổ
                    ((Node) event.getSource()).getScene().getWindow().hide();
                }
            }
            else if (event.getSource() == btnHuy) {
                // Tắt cửa sổ
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    public int checkCCCD() throws SQLException {
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
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Số CMT/CCCD không hợp lệ");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng kiểm tra và nhập lại số CMT/CCCD.");
            alert.showAndWait();
            return idNhanKhau;
        }
    }
}
