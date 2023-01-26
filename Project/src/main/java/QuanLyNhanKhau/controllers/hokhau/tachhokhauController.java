package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.hokhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private TextField maHoKhau;

    @FXML
    private TextField maHoKhauMoi;

    @FXML
    private TextField ngo;

    @FXML
    private TableView<?> tableChonNguoiSangHoMoi;

    @FXML
    private TableColumn<?, ?> tableChonNguoiSangHoMoi_HoTen;

    @FXML
    private TableColumn<?, ?> tableChonNguoiSangHoMoi_NgaySinh;

    @FXML
    private TableColumn<?, ?> tableChonNguoiSangHoMoi_QuanHeVoiChuHo;

    @FXML
    private TableView<HoKhauTable> tableHoKhauCanTach;

    @FXML
    private TableColumn<HoKhauTable, String> tableHoKhauCanTach_DiaChi;

    @FXML
    private TableColumn<HoKhauTable, String> tableHoKhauCanTach_HoTenChuHo;

    @FXML
    private TableColumn<HoKhauTable, String> tableHoKhauCanTach_MaHoKhau;

    @FXML
    private TableView<?> tableNhungNguoiOHoMoi;

    @FXML
    private TableColumn<?, ?> tableNhungNguoiOHoMoi_HoTen;

    @FXML
    private TableColumn<?, ?> tableNhungNguoiOHoMoi_NgaySinh;

    @FXML
    private TableColumn<?, ?> tableNhungNguoiOHoMoi_QuanHeVoiChuHo;

    @FXML
    private TextField toDanPho;

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleClicksBtnTim(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hokhauDB hokhauinDB = new hokhauDB();
        ObservableList<HoKhauTable> listHK = null;
        try {
            listHK = hokhauinDB.getListHoKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableHoKhauCanTach_MaHoKhau.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("soHoKhau"));
        tableHoKhauCanTach_HoTenChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("hoTen"));
        tableHoKhauCanTach_DiaChi.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("diaChi"));
        tableHoKhauCanTach.setItems(listHK);

        tableHoKhauCanTach.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    HoKhauTable selectedItem = tableHoKhauCanTach.getSelectionModel().getSelectedItem();
                    chuHoHienTai.setText(selectedItem.getHoTen());
                }
            }
        });
    }

}

