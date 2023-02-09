package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.Query;
import QuanLyNhanKhau.services.hokhauDB;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class tachhokhauController implements Initializable {
    private final ObservableList<NhanKhau> listNKMoi = FXCollections.observableArrayList();
    @FXML
    private Button btnChuyenSang;
    @FXML
    private Button btnHuy;
    @FXML
    private Button btnLuu;
    @FXML
    private Button btnTim;
    @FXML
    private Label chuHoHienTai;
    @FXML
    private Label chuHoMoi;
    @FXML
    private TextField duong;
    @FXML
    private TextField input;
    @FXML
    private TextField soHoKhauMoi;
    @FXML
    private TextField ngo;
    @FXML
    private TextField soNha;
    @FXML
    private TableView<NhanKhau> tableChonNguoiSangHoMoi;
    @FXML
    private TableColumn<NhanKhau, String> tableChonNguoiSangHoMoi_HoTen;
    @FXML
    private TableColumn<NhanKhau, String> tableChonNguoiSangHoMoi_NgaySinh;
    @FXML
    private TableColumn<NhanKhau, String> tableChonNguoiSangHoMoi_QuanHeVoiChuHo;
    @FXML
    private TableView<HoKhauTable> tableHoKhauCanTach;
    @FXML
    private TableColumn<HoKhauTable, String> tableHoKhauCanTach_DiaChi;
    @FXML
    private TableColumn<HoKhauTable, String> tableHoKhauCanTach_HoTenChuHo;
    @FXML
    private TableColumn<HoKhauTable, String> tableHoKhauCanTach_soHoKhau;
    @FXML
    private TableView<NhanKhau> tableNhungNguoiOHoMoi;
    @FXML
    private TableColumn<NhanKhau, String> tableNhungNguoiOHoMoi_HoTen;
    @FXML
    private TableColumn<NhanKhau, String> tableNhungNguoiOHoMoi_NgaySinh;
    @FXML
    private TableColumn<NhanKhau, String> tableNhungNguoiOHoMoi_QuanHeVoiChuHo;
    private ObservableList<NhanKhau> listNKCu = FXCollections.observableArrayList();

    private ObservableList<HoKhauTable> getHoKhauList() {
        hokhauDB hokhauinDB = new hokhauDB();
        try {
            return hokhauinDB.getListHoKhauTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<NhanKhau> getNhanKhauListWithSoHoKhau(String soHoKhau) {
        nhankhauDB nhankhauinDB = new nhankhauDB();
        try {
            return nhankhauinDB.getListNhanKhauWithSoHoKhau(soHoKhau);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement updateHoKhau(String soHoKhauMoi, int idChuHo, int soNha, String ngo, String duong) throws SQLException {
        Query query = new Query();
        return query.HoKhau(soHoKhauMoi, idChuHo, soNha, ngo, duong);
    }

    @FXML
    void handleClicks(ActionEvent event) throws SQLException {
        if (event.getSource() == btnTim) {
            ObservableList<HoKhauTable> listHK = getHoKhauList();
            listHK.removeIf(HoKhauTable -> !HoKhauTable.getSoHoKhau().toLowerCase().contains(input.getText().toLowerCase()));
            tableHoKhauCanTach.setItems(listHK);
        } else {
            if (event.getSource() == btnLuu) {
                if (soHoKhauMoi.getText().isEmpty() || listNKMoi.isEmpty() || chuHoMoi.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Không thể lưu");
                    alert.setHeaderText("Thiếu thông tin");
                    alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                    alert.showAndWait();
                    return;
                }
                int idNhanKhauChuHo = listNKMoi.stream()
                        .filter(nk -> nk.getQuanHeVoiChuHo().equals("Chủ hộ"))
                        .findFirst()
                        .map(NhanKhau::getId)
                        .orElse(-1);
                PreparedStatement psmt = updateHoKhau(soHoKhauMoi.getText(), idNhanKhauChuHo, Integer.parseInt(soNha.getText()), ngo.getText(), duong.getText());

                ResultSet rs = psmt.getGeneratedKeys();
                int idHoKhau = 0;
                if (rs.next()) {
                    idHoKhau = rs.getInt(1);
                }
                for (NhanKhau n : listNKMoi) {
                    n.setIdHoKhau(idHoKhau);
                }

                Connection connection = MySQL.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE NhanKhau SET idHoKhau = ?, quanHeVoiChuHo = ? WHERE id = ?");
                for (NhanKhau nhanKhau : listNKMoi) {
                    preparedStatement.setInt(1, nhanKhau.getIdHoKhau());
                    preparedStatement.setString(2, nhanKhau.getQuanHeVoiChuHo());
                    preparedStatement.setInt(3, nhanKhau.getId());
                    preparedStatement.executeUpdate();
                }
            }

            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableHoKhauCanTach.setOnMouseClicked(event -> {
            HoKhauTable selectedHoKhauTable = tableHoKhauCanTach.getSelectionModel().getSelectedItem();
            if (selectedHoKhauTable != null) {
                chuHoHienTai.setText(selectedHoKhauTable.getHoTen());

                listNKCu = getNhanKhauListWithSoHoKhau(selectedHoKhauTable.getSoHoKhau());
                tableChonNguoiSangHoMoi_HoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
                tableChonNguoiSangHoMoi_NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
                tableChonNguoiSangHoMoi_QuanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
                tableChonNguoiSangHoMoi.setItems(listNKCu);
            }
        });
        tableChonNguoiSangHoMoi.setOnMouseClicked(event -> {
            NhanKhau selectedNhanKhau = tableChonNguoiSangHoMoi.getSelectionModel().getSelectedItem();
            btnChuyenSang.setOnMouseClicked(click_event -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Quan hệ với chủ hộ");
                dialog.setHeaderText("Nhập quan hệ với chủ hộ:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String quanHeVoiChuHo = result.get();
                    if (quanHeVoiChuHo.equals("Chủ hộ")) {
                        chuHoMoi.setText(selectedNhanKhau.getHoTen());
                    }
                    selectedNhanKhau.setQuanHeVoiChuHo(quanHeVoiChuHo);
                    listNKCu.remove(selectedNhanKhau);
                    listNKMoi.add(selectedNhanKhau);
                }
            });
        });
        tableHoKhauCanTach_soHoKhau.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("soHoKhau"));
        tableHoKhauCanTach_HoTenChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("hoTen"));
        tableHoKhauCanTach_DiaChi.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("diaChi"));
        tableHoKhauCanTach.setItems(getHoKhauList());

        tableNhungNguoiOHoMoi_HoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        tableNhungNguoiOHoMoi_NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
        tableNhungNguoiOHoMoi_QuanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
        tableNhungNguoiOHoMoi.setItems(listNKMoi);
    }
}

