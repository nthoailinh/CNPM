package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.models.HoKhau;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.HoKhauDB;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class XemThongTinController implements Initializable {

    @FXML
    private Label cccdChuHo;

    @FXML
    private Label chuHo;

    @FXML
    private Label duong;

    @FXML
    private Label ngo;

    @FXML
    private Label soHoKhau;

    @FXML
    private Label soNha;

    @FXML
    private TableView<NhanKhau> table;
    @FXML
    private TableColumn<NhanKhau, String> table_gioiTinh;
    @FXML
    private TableColumn<NhanKhau, String> table_hoTen;
    @FXML
    private TableColumn<NhanKhau, Integer> table_id;
    @FXML
    private TableColumn<NhanKhau, LocalDate> table_ngaySinh;
    @FXML
    private TableColumn<NhanKhau, String> table_quanHeVoiChuHo;

    private String soHoKhau_text;

    private NhanKhauDB nhankhauDB = new NhanKhauDB();

    private HoKhauDB hokhauDB = new HoKhauDB();

    public void setSoHoKhau(String soHoKhau) {
        this.soHoKhau_text = soHoKhau;
    }

    public ObservableList<NhanKhau> getNhanKhauList(String soHoKhau) throws SQLException {
        return nhankhauDB.getListNhanKhauBySoHoKhau(soHoKhau);
    }

    private void setTableData(ObservableList<NhanKhau> listNK) {
        table_id.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        table_hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        table_ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, LocalDate>("ngaySinh"));
        table_gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        table_quanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
        table.setItems(listNK);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<NhanKhau> listNK = null;
        try {
            listNK = getNhanKhauList(soHoKhau_text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        NhanKhau nhanKhauChuHo = listNK.stream()
                .filter(nk -> nk.getQuanHeVoiChuHo().equals("Chủ hộ"))
                .findFirst()
                .orElse(null);
        setTableData(listNK);
        soHoKhau.setText(soHoKhau_text);
        chuHo.setText(nhanKhauChuHo.getHoTen());
        try {
            cccdChuHo.setText(nhankhauDB.getCCCD(nhanKhauChuHo.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HoKhau hoKhau = null;
        try {
            hoKhau = hokhauDB.getHoKhau(soHoKhau_text);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        soNha.setText(hoKhau.getSoNha());
        ngo.setText(hoKhau.getNgo());
        duong.setText(hoKhau.getDuong());
    }
}
