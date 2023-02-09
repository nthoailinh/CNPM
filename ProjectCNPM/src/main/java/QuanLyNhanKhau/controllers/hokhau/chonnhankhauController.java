package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class chonnhankhauController {

    @FXML
    protected Button btnChon;

    @FXML
    protected Button btnHuy;

    @FXML
    protected Button btnTim;

    @FXML
    protected TextField input;

    @FXML
    protected TableView<NhanKhau> table;

    @FXML
    protected TableColumn<NhanKhau, String> table_gioiTinh;

    @FXML
    protected TableColumn<NhanKhau, String> table_hoTen;

    @FXML
    protected TableColumn<NhanKhau, Integer> table_id;

    @FXML
    protected TableColumn<NhanKhau, LocalDate> table_ngaySinh;

}
