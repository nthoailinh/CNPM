package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    ObservableList<NhanKhauTable> listNK;

    public void initialize() {
        nhankhauDB nhankhauinDB = new nhankhauDB();
        listNK = null;
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
    NhanKhauTable handleClicks(ActionEvent event) throws SQLException {
        TableView.TableViewSelectionModel<NhanKhauTable> selectionModel = tableNhanKhau.getSelectionModel();
        // set selection mode to only 1 row
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        if (event.getSource() == btnTim) {
            searchNhanKhauByCCCD();
        }
        if (event.getSource() == btnChon) {
            return selectionModel.getSelectedItem();
        }
        if (event.getSource() == btnHuy) {
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
        // Tắt cửa sổ
        ((Node) event.getSource()).getScene().getWindow().hide();
        return null;
    }

    public void searchNhanKhauByCCCD() throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt_nhankhau = null;
        pstmt_nhankhau = connection.prepareStatement("SELECT * FROM NhanKhau JOIN CCCD ON NhanKhau.id = CCCD.idNhanKhau WHERE cccd = ?");
        pstmt_nhankhau.setString(1, cccd.getText());
        ResultSet rs = pstmt_nhankhau.executeQuery();
        if (rs.next()) {
            int i = rs.getInt("idNhanKhau");
            NhanKhauTable NKFound = listNK.get(i - 1);
            ObservableList<NhanKhauTable> listNKFound = FXCollections.observableArrayList();
            listNKFound.add(NKFound);
            tableNhanKhau.setItems(listNKFound);
        } else {
            tableNhanKhau.setItems(null);
        }

    }
}
