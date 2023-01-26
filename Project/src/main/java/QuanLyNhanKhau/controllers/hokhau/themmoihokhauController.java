package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.controllers.tables.ThanhVienTable;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.Update;
import QuanLyNhanKhau.views.ChildWindows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class themmoihokhauController {
    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnThemThanhVien;

    @FXML
    private TextField cccd;

    @FXML
    private TextField maHoKhau;

    @FXML
    private TextField soNha;

    @FXML
    private TextField ngo;

    @FXML
    private TextField duong;

    @FXML
    private TableView<ThanhVienTable> tableThanhVien;

    @FXML
    private TableColumn<ThanhVienTable, Integer> idTV;

    @FXML
    private TableColumn<ThanhVienTable, String> hoTenTV;

    @FXML
    private TableColumn<ThanhVienTable, String> ngaySinhTV;

    @FXML
    private TableColumn<ThanhVienTable, String> gioiTinhTV;

    @FXML
    private TableColumn<ThanhVienTable, String> quanHeVoiChuHo;

    @FXML
    void handleClicks(ActionEvent event) throws SQLException, IOException {
        idTV.setCellValueFactory(new PropertyValueFactory<ThanhVienTable, Integer>("id"));
        hoTenTV.setCellValueFactory(new PropertyValueFactory<ThanhVienTable, String>("hoTen"));
        ngaySinhTV.setCellValueFactory(new PropertyValueFactory<ThanhVienTable, String>("ngaySinh"));
        gioiTinhTV.setCellValueFactory(new PropertyValueFactory<ThanhVienTable, String>("gioiTinh"));
        quanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<ThanhVienTable, String>("quanHeVoiChuHo"));

        ObservableList<ThanhVienTable> listTV = FXCollections.observableArrayList();
        if (event.getSource() == btnThemThanhVien) {
            Connection connection = MySQL.getConnection();
            PreparedStatement pstmt_thanhvien = null;
            pstmt_thanhvien = connection.prepareStatement("SELECT * FROM NhanKhau JOIN CCCD ON NhanKhau.id = CCCD.idNhanKhau WHERE cccd = ?");
            pstmt_thanhvien.setString(1, cccd.getText());
            ResultSet rs = pstmt_thanhvien.executeQuery();
            int idThanhVienInDB = -1;
            if (rs.next()) {
                idThanhVienInDB = rs.getInt("idNhanKhau");
                System.out.println(idThanhVienInDB);
                String hoTen = rs.getString("hoTen");
                String ngaySinh = rs.getString("ngaySinh");
                String gioiTinh = rs.getString("gioiTinh");
                String quanHeVoiChuHo = rs.getString("quanHeVoiChuHo");
//                ButtonType btnConfirm = new ButtonType("Xác nhận", ButtonType.OK.getButtonData());
//                ButtonType btnCancel = new ButtonType("Hủy", ButtonType.CANCEL.getButtonData());
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Họ tên nhân khẩu: " + hoTen + "\nSố CMT/CCCD: " + cccd.getText(),
//                        btnConfirm, btnCancel);
//                alert.setTitle("Thêm thành viên vào hộ khẩu");
//                alert.setHeaderText("Bạn có chắc chắn thêm nhân khẩu sau vào hộ khẩu không?");
//                alert.showAndWait();
//                Optional<ButtonType> option = alert.showAndWait();
//                if (option.get() == ButtonType.OK) {
//
//                } else if (option.get() == ButtonType.CANCEL) {
//                    System.out.println("Cancelled!");
//                }
                ThanhVienTable newTV = new ThanhVienTable(idThanhVienInDB, hoTen, ngaySinh, gioiTinh, quanHeVoiChuHo);
                listTV.add(newTV);
                tableThanhVien.setItems(listTV);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Số CMT/CCCD không hợp lệ");
                alert.setHeaderText("Không tìm thấy nhân khẩu có số CMT/CCCD tương ứng.");
                alert.setContentText("Vui lòng kiểm tra và nhập lại số CMT/CCCD.");
                alert.showAndWait();
            }

        }
        if (event.getSource() == btnLuu) {
            if (maHoKhau.getText().isEmpty() || soNha.getText().isEmpty() || duong.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            Connection connection = MySQL.getConnection();
            Update update = new Update();
//            // Tìm id chủ hộ
//            PreparedStatement pstmt_chuho = connection.prepareStatement("SELECT id FROM cccd JOIN NhanKhau ON cccd.idNhanKhau = NhanKhau.id WHERE cccd = ?");
//            pstmt_chuho.setString(1, cccdChuHo.getText());
//            ResultSet rs = pstmt_chuho.executeQuery();
            int idChuHo = 0;
//            if (rs.next()) {
//                idChuHo = rs.getInt(1);
//            }
            update.HoKhau(maHoKhau.getText(), idChuHo, Integer.parseInt(soNha.getText()), ngo.getText(), duong.getText());
        } else if (event.getSource() == btnHuy) {
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

//    public int searchNhanKhaubyCCCD() throws SQLException {
//        Connection connection = MySQL.getConnection();
//        PreparedStatement pstmt_nhankhau = null;
//        pstmt_nhankhau = connection.prepareStatement("SELECT * FROM NhanKhau JOIN CCCD ON NhanKhau.id = CCCD.idNhanKhau WHERE cccd = ?");
//        pstmt_nhankhau.setString(1, cccd.getText());
//        ResultSet rs = pstmt_nhankhau.executeQuery();
//        int idNhanKhau = -1;
//        if (rs.next()) {
//            idNhanKhau = rs.getInt("idNhanKhau");
//            System.out.println(idNhanKhau);
//            String hoTen = rs.getString("hoTen");
//            ButtonType btnConfirm = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
//            ButtonType btnCancel = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Họ tên nhân khẩu: " + hoTen + "\nSố CMT/CCCD: " + cccd.getText(),
//                                    btnConfirm, btnCancel);
//            alert.setTitle("Thêm thành viên vào hộ khẩu");
//            alert.setHeaderText("Bạn có chắc chắn thêm nhân khẩu sau vào hộ khẩu không?");
//            alert.showAndWait();
//            Optional<ButtonType> option = alert.showAndWait();
//            if (option.get() == ButtonType.CANCEL) {
//                idNhanKhau = -1;
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Số CMT/CCCD không hợp lệ");
//            alert.setHeaderText("Không tìm thấy nhân khẩu có số CMT/CCCD tương ứng.");
//            alert.setContentText("Vui lòng kiểm tra và nhập lại số CMT/CCCD.");
//            alert.showAndWait();
//        }
//        return idNhanKhau;
//    }
}
/*        if (event.getSource() == btnChon) {
            FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.views.ChildWindows.class.getResource("hokhau/chonnhankhau.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            chonnhankhauController chonChuHo = new chonnhankhauController();
            fxmlLoader.setController(chonChuHo);
            ActionEvent eventChonChuHo = new ActionEvent();
            NhanKhauTable chuHo = chonChuHo.handleClicks(eventChonChuHo);
            System.out.println(chuHo.getId() + ": " + chuHo.getHoTen() + ": " + chuHo.getNgaySinh() + ": "
                    + chuHo.getGioiTinh() + ": " + chuHo.getDiaChi());
        }*/
