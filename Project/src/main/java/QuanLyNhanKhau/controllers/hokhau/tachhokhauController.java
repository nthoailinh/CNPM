package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.hokhauDB;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class tachhokhauController implements Initializable {

    @FXML
    private Button btnChuyenSang;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnQuayLai;

    @FXML
    private Button btnTim;

    @FXML
    private Label chuHoHienTai;

    @FXML
    private Label chuHoMoi;

    @FXML
    private TextField duong;

    @FXML
    private TextField soHoKhau;

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

    @FXML
    private TextField toDanPho;

    private ObservableList<HoKhauTable> listHK = FXCollections.observableArrayList();

    private ObservableList<NhanKhau> listNKCu = FXCollections.observableArrayList();

    private ObservableList<NhanKhau> listNKMoi = FXCollections.observableArrayList();

    private hokhauDB hokhauinDB = new hokhauDB();

    private nhankhauDB nhankhauinDB = new nhankhauDB();


    @FXML
    void handleClicks(ActionEvent event) {
        if(event.getSource() == btnLuu){
            if (soHoKhauMoi.getText().isEmpty() || listNKMoi.isEmpty() || chuHoMoi.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            
        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void handleClicksBtnTim(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            listHK = hokhauinDB.getListHoKhauTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableHoKhauCanTach_soHoKhau.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("soHoKhau"));
        tableHoKhauCanTach_HoTenChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("hoTen"));
        tableHoKhauCanTach_DiaChi.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("diaChi"));
        tableHoKhauCanTach.setItems(listHK);

        tableHoKhauCanTach.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    HoKhauTable selectedHoKhauTable = tableHoKhauCanTach.getSelectionModel().getSelectedItem();
                    chuHoHienTai.setText(selectedHoKhauTable.getHoTen());

                    try {
                        listNKCu = nhankhauinDB.getListNhanKhauWithSoHoKhau(selectedHoKhauTable.getSoHoKhau());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    tableChonNguoiSangHoMoi_HoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
                    tableChonNguoiSangHoMoi_NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
                    tableChonNguoiSangHoMoi_QuanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
                    tableChonNguoiSangHoMoi.setItems(listNKCu);
                }
            }
        });
        tableChonNguoiSangHoMoi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NhanKhau selectedNhanKhau = tableChonNguoiSangHoMoi.getSelectionModel().getSelectedItem();
                btnChuyenSang.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Quan hệ với chủ hộ");
                        dialog.setHeaderText("Nhập quan hệ với chủ hộ:");

                        Optional<String> result = dialog.showAndWait();
                        if (result.isPresent()) {
                            String quanHeVoiChuHo = result.get();
                            if(quanHeVoiChuHo.equals("Chủ hộ")){
                                chuHoMoi.setText(selectedNhanKhau.getHoTen());
                            }
                            selectedNhanKhau.setQuanHeVoiChuHo(quanHeVoiChuHo);
                            listNKCu.remove(selectedNhanKhau);
                            listNKMoi.add(selectedNhanKhau);
                        }
                    }
                });
            }
        });
        tableNhungNguoiOHoMoi_HoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        tableNhungNguoiOHoMoi_NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
        tableNhungNguoiOHoMoi_QuanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
        tableNhungNguoiOHoMoi.setItems(listNKMoi);
    }

}

