package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.models.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ChonNhanKhauController {

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
