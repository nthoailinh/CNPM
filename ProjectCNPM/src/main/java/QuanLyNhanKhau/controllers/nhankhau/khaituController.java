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

public class khaituController {

    private NhanKhauDB nhankhauDB = new NhanKhauDB();

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnKiemTraNguoiKhai;

    @FXML
    private Button btnKiemTraNguoiMat;

    @FXML
    private Button btnLuu;

    @FXML
    private TextField cccdNguoiKhai;

    @FXML
    private TextField cccdNguoiMat;

    @FXML
    private TextField maGiayKhaiTu;

    @FXML
    private DatePicker ngayKhaiTu;

    @FXML
    private DatePicker ngayQuaDoi;

    @FXML
    private TextField nguyenNhan;

    @FXML
    void handleClicks(ActionEvent event) throws SQLException {
        int idNguoiKhai = -2;
        int idNguoiMat = -2;
        if (event.getSource() == btnKiemTraNguoiKhai) {
            idNguoiKhai = checkCCCD(cccdNguoiKhai);
        } else if (event.getSource() == btnKiemTraNguoiMat) {
            idNguoiMat = checkCCCD(cccdNguoiMat);
        } else {
            if (event.getSource() == btnLuu) {
                if (cccdNguoiKhai.getText().isEmpty() || cccdNguoiMat.getText().isEmpty() ||
                        maGiayKhaiTu.getText().isEmpty() || ngayQuaDoi.getValue() == null ||
                        ngayKhaiTu.getValue() == null || nguyenNhan.getText().isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Không thể lưu");
                    alert.setHeaderText("Thiếu thông tin");
                    alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                    alert.showAndWait();
                    return;
                }
                if (idNguoiKhai == -2) {
                    idNguoiKhai = checkCCCD(cccdNguoiKhai);
                }
                if (idNguoiMat == -2) {
                    idNguoiMat = checkCCCD(cccdNguoiMat);
                }
                if (idNguoiKhai != -1 && idNguoiMat != -1) {
                    nhankhauDB.addKhaiTu(idNguoiMat, maGiayKhaiTu.getText(), nguyenNhan.getText(),
                            ngayQuaDoi.getValue(), ngayKhaiTu.getValue(), idNguoiKhai);
                    // Tắt cửa sổ
                    ((Node) event.getSource()).getScene().getWindow().hide();
                }
            }
            else if (event.getSource() == btnHuy) {
                // Tắt cửa sổ
                ((Node) event.getSource()).getScene().getWindow().hide();
            }

        }
    }

    public int checkCCCD(TextField cccd) throws SQLException {
        Connection connection = MySQL.getConnection();
        // Tìm id nhân khẩu đăng ký tạm vắng
        PreparedStatement pstmt_nhankhau = null;
        pstmt_nhankhau = connection.prepareStatement("SELECT * FROM NhanKhau JOIN CCCD ON NhanKhau.id = CCCD.idNhanKhau  WHERE cccd = ?");
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
