package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.Update;
import QuanLyNhanKhau.views.ChildWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class themmoihokhauController {
    @FXML
    private Button btnChon;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnThemThanhVien;

    @FXML
    private TextField maHoKhau;

    @FXML
    private TextField cccdChuHo;

    @FXML
    private TextField chuHo;

    @FXML
    private TextField soNha;

    @FXML
    private TextField ngo;

    @FXML
    private TextField duong;

    @FXML
    private TableView<?> tableThanhVien;

    @FXML
    private TableColumn<?, ?> idTV;

    @FXML
    private TableColumn<?, ?> hoTenTV;

    @FXML
    private TableColumn<?, ?> ngaySinhTV;

    @FXML
    private TableColumn<?, ?> gioiTinhTV;

    @FXML
    private TableColumn<?, ?> quanHeVoiChuHo;

    @FXML
    void handleClicks(ActionEvent event) throws SQLException, IOException {
        if (event.getSource() == btnChon) {
            ChildWindows.show("hokhau/chonnhankhau.fxml");
        }
        if (event.getSource() == btnLuu) {
            if (maHoKhau.getText().isEmpty() || cccdChuHo.getText().isEmpty() || chuHo.getText().isEmpty() ||
                    soNha.getText().isEmpty() || duong.getText().isEmpty() || quanHeVoiChuHo.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            Connection connection = MySQL.getConnection();
            Update update = new Update();
            // Tìm id chủ hộ
            PreparedStatement pstmt_chuho = connection.prepareStatement("SELECT id FROM cccd JOIN NhanKhau ON cccd.idNhanKhau = NhanKhau.id WHERE cccd = ?");
            pstmt_chuho.setString(1, cccdChuHo.getText());
            ResultSet rs = pstmt_chuho.executeQuery();
            int idChuHo = 0;
            if (rs.next()) {
                idChuHo = rs.getInt(1);
            }
            update.HoKhau(maHoKhau.getText(), idChuHo, Integer.parseInt(soNha.getText()), ngo.getText(), duong.getText());
        } else if (event.getSource() == btnHuy) {
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }
}
