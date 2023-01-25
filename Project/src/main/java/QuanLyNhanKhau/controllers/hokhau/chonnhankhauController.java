package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class chonnhankhauController {
    @FXML
    private Button btnChon;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnTim;

    @FXML
    private TextField cccd;

    @FXML
    private TableView<NhanKhauTable> tableNhanKhau;

    @FXML
    private TableColumn<NhanKhauTable, Integer> idNK;

    @FXML
    private TableColumn<NhanKhauTable, String> hotenNK;

    @FXML
    private TableColumn<NhanKhauTable, LocalDate> ngaySinhNK;

    @FXML
    private TableColumn<NhanKhauTable, String> gioiTinhNK;

    @FXML
    private TableColumn<NhanKhauTable, String> diaChiNK;

    public void initialize() throws SQLException {
        nhankhauDB nhankhauinDB = new nhankhauDB();
        ObservableList<NhanKhauTable> listNK = null;
        try {
            listNK = nhankhauinDB.getListNhanKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, Integer>("id"));
        hotenNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("hoTen"));
        ngaySinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, LocalDate>("ngaySinh"));
        gioiTinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("gioiTinh"));
        diaChiNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("diaChi"));
        tableNhanKhau.setItems(listNK);
    }
    @FXML
    void handleClicks(ActionEvent event) {

    }
}
