package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.Update;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.sql.*;

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
    void handleClicks(ActionEvent event) throws SQLException {
        if (event.getSource() == btnLuu) {
            if (soHoKhau.getText().isEmpty() || hoTen.getText().isEmpty() || ngaySinh.getValue() == null || noiSinh.getText().isEmpty() || (!gioiTinhNam.isSelected() && !gioiTinhNu.isSelected()) ||
                    nguyenQuan.getText().isEmpty() || danToc.getText().isEmpty() || quanHeVoiChuHo.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            String gioiTinh = gioiTinhNam.isSelected() ? "Nam" : (gioiTinhNu.isSelected() ? "Nữ" : "Không rõ");

            Connection connection = MySQL.getConnection();
            Update update = new Update();
            // Tìm id hộ khẩu
            PreparedStatement pstmt_hokhau = connection.prepareStatement("SELECT id FROM HoKhau WHERE soHoKhau = ?");
            pstmt_hokhau.setString(1, soHoKhau.getText());
            ResultSet rs = pstmt_hokhau.executeQuery();
            int idHoKhau = 0;
            if (rs.next()) {
                idHoKhau = rs.getInt(1);
            }
            PreparedStatement pstmt_nhankhau = update.NhanKhau(idHoKhau, hoTen.getText(), ngaySinh.getValue(), gioiTinh, noiSinh.getText(),
                    nguyenQuan.getText(), danToc.getText(), ngheNghiep.getText(), noiLamViec.getText(), quanHeVoiChuHo.getText());
            // Tìm ID nhân khẩu
            rs = pstmt_nhankhau.getGeneratedKeys();
            int idNhanKhau = 0;
            if (rs.next()) {
                idNhanKhau = rs.getInt(1);
            }
            update.CCCD(cccd.getText(), idNhanKhau, ngayCap.getValue(), noiCap.getText());

        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
