package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.nhankhauDB;
import QuanLyNhanKhau.views.ChildWindows;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {

    @FXML
    private Button btnDKTamTru;

    @FXML
    private Button btnDKTamVang;

    @FXML
    private Button btnKhaiTu;

    @FXML
    private Button btnThemMoi;

    @FXML
    private Button btnTimKiem;

    @FXML
    private BorderPane contentNhanKhau;

    @FXML
    private TableColumn<NhanKhauTable, Integer> ID;

    @FXML
    private TableColumn<NhanKhauTable, String> diaChi;

    @FXML
    private TableColumn<NhanKhauTable, String> gioiTinh;

    @FXML
    private TableColumn<NhanKhauTable, String> hoTen;

    @FXML
    private TextField input;

    @FXML
    private TableColumn<NhanKhauTable, LocalDate> ngaySinh;

    @FXML
    private TableView<NhanKhauTable> table;

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnTimKiem) {
            filterTable(input.getText());
        } else {
            Parent root = null;
            String fxmlFile = "";
            String windowTitle = "";
            int width = 0, height = 0;

            if (event.getSource() == btnThemMoi) {
                fxmlFile = "nhankhau/themmoinhankhau.fxml";
                windowTitle = "Thêm mới nhân khẩu";
                width = 896;
                height = 672;
            } else if (event.getSource() == btnDKTamVang) {
                fxmlFile = "nhankhau/dangkytamvang.fxml";
                windowTitle = "Đăng ký tạm vắng";
                width = 768;
                height = 576;
            } else if (event.getSource() == btnDKTamTru) {
                fxmlFile = "nhankhau/dangkytamtru.fxml";
                windowTitle = "Đăng ký tạm trú";
                width = 768;
                height = 480;
            } else if (event.getSource() == btnKhaiTu) {
                fxmlFile = "nhankhau/khaitu.fxml";
                windowTitle = "Khai tử";
                width = 784;
                height = 512;
            }

            root = ChildWindows.getRoot(fxmlFile);
            Scene scene = new Scene(root, width, height);
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(scene);
            stage.setOnHidden((e) -> populateTable());
            stage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }

    private void filterTable(String input) {
        ObservableList<NhanKhauTable> listNK = getNhanKhauList();
        listNK.removeIf(nhanKhauTable -> !nhanKhauTable.getHoTen().toLowerCase().contains(input.toLowerCase()));
        setTableData(listNK);
    }

    private void populateTable() {
        setTableData(getNhanKhauList());
    }

    private ObservableList<NhanKhauTable> getNhanKhauList() {
        nhankhauDB nhankhauinDB = new nhankhauDB();
        try {
            return nhankhauinDB.getListNhanKhauTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTableData(ObservableList<NhanKhauTable> listNK) {
        ID.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, Integer>("id"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("hoTen"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, LocalDate>("ngaySinh"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("gioiTinh"));
        diaChi.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("diaChi"));
        table.setItems(listNK);
    }

}
