package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.tables.CovidTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.MacCOVID;
import QuanLyNhanKhau.services.CovidDB;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class CovidController {
    @FXML
    private TableColumn<CovidTable, Integer> IDNguoiMac;
    @FXML
    private TableColumn<CovidTable, String> hoTenNguoiMac;
    @FXML
    private TableColumn<CovidTable, String> ngayMac;
    @FXML
    private TableColumn<CovidTable, String> tinhTrangSucKhoe;
    @FXML
    private TableColumn<CovidTable, String> ketQuaTest;
    @FXML
    private TableColumn<CovidTable, String> ngayKhoi;
    @FXML
    private TableView<CovidTable> tableNguoiMac;

    public CovidController(TableColumn<CovidTable, Integer> IDNguoiMac, TableColumn<CovidTable, String> hoTenNguoiMac, TableColumn<CovidTable, String> ngayMac, TableColumn<CovidTable, String> ngayKhoi, TableColumn<CovidTable, String> tinhTrangSucKhoe, TableColumn<CovidTable, String> ketQuaTest, TableView<CovidTable> tableNguoiMac) {
        this.IDNguoiMac = IDNguoiMac;
        this.hoTenNguoiMac = hoTenNguoiMac;
        this.ngayMac = ngayMac;
        this.ngayKhoi = ngayKhoi;
        this.tinhTrangSucKhoe = tinhTrangSucKhoe;
        this.ketQuaTest = ketQuaTest;
        this.tableNguoiMac = tableNguoiMac;
    }

    public void initialize(){
        CovidDB covidinDB = new CovidDB();
        ObservableList<CovidTable> listCovid = null;
        try {
            listCovid = covidinDB.getListCovidTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        IDNguoiMac.setCellValueFactory(new PropertyValueFactory<CovidTable, Integer>("id"));
        hoTenNguoiMac.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("hoTen"));
        ngayMac.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayMac"));
        ngayKhoi.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayKhoi"));
        tinhTrangSucKhoe.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("tinhTrangSK"));
        ketQuaTest.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ketQuaTest"));
        tableNguoiMac.setItems(listCovid);
    }
}
