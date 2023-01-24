package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.HoKhau;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class NhanKhauController {
    @FXML
    private TableColumn<NhanKhauTable, Integer> IDNK;
    @FXML
    private TableColumn<NhanKhauTable, String> hoTenNK;
    @FXML
    private TableColumn<NhanKhauTable, LocalDate> ngaySinhNK;
    @FXML
    private TableColumn<NhanKhauTable, String> gioiTinhNK;
    @FXML
    private TableColumn<NhanKhauTable, String> diaChiNK;
    @FXML
    private TableView<NhanKhauTable> tableNhanKhau;
    public NhanKhauController(TableColumn<NhanKhauTable, Integer> IDNK, TableColumn<NhanKhauTable, String> hoTenNK, TableColumn<NhanKhauTable, LocalDate> ngaySinhNK,
                              TableColumn<NhanKhauTable, String> gioiTinhNK, TableColumn<NhanKhauTable, String> diaChiNK, TableView<NhanKhauTable> tableNhanKhau){
        this.IDNK = IDNK;
        this.hoTenNK = hoTenNK;
        this.ngaySinhNK = ngaySinhNK;
        this.gioiTinhNK = gioiTinhNK;
        this.diaChiNK = diaChiNK;
        this.tableNhanKhau = tableNhanKhau;
    }
    public void initialize() throws SQLException {
        nhankhauDB nhankhauinDB = new nhankhauDB();
        ObservableList<NhanKhauTable> listNK = null;
        try {
            listNK = nhankhauinDB.getListNhanKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        IDNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, Integer>("id"));
        hoTenNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("hoTen"));
        ngaySinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, LocalDate>("ngaySinh"));
        gioiTinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("gioiTinh"));
        diaChiNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("diaChi"));
        tableNhanKhau.setItems(listNK);
    }
}
