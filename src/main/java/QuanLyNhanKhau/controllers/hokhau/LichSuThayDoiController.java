package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.models.LichSuThayDoi;
import QuanLyNhanKhau.services.HoKhauDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LichSuThayDoiController implements Initializable {

    @FXML
    private TableColumn<LichSuThayDoi, String> soHoKhau;

    @FXML
    private TableColumn<LichSuThayDoi, LocalDate> ngayThayDoi;

    @FXML
    private TableColumn<LichSuThayDoi, String> noiDungThayDoi;

    @FXML
    private TableView<LichSuThayDoi> table;

    @FXML
    private TableColumn<LichSuThayDoi, String> tenNhanKhau;

    private HoKhauDB hokhauDB = new HoKhauDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setTableData(hokhauDB.getLichSuThayDoiTableList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setTableData(ObservableList<LichSuThayDoi> listNK) {
        soHoKhau.setCellValueFactory(new PropertyValueFactory<LichSuThayDoi, String>("soHoKhau"));
        tenNhanKhau.setCellValueFactory(new PropertyValueFactory<LichSuThayDoi, String>("tenNhanKhau"));
        ngayThayDoi.setCellValueFactory(new PropertyValueFactory<LichSuThayDoi, LocalDate>("ngayThayDoi"));
        noiDungThayDoi.setCellValueFactory(new PropertyValueFactory<LichSuThayDoi, String>("noiDungThayDoi"));
        table.setItems(listNK);
    }
}
