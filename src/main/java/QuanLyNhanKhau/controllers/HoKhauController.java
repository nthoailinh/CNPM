package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.hokhau.XemThongTinController;
import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.models.HoKhau;
import QuanLyNhanKhau.services.HoKhauDB;
import QuanLyNhanKhau.views.Windows;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.ResourceBundle;

public class HoKhauController implements Initializable {

    private final HoKhauDB hokhauDB = new HoKhauDB();

    @FXML
    private Button btnChuyenDi;

    @FXML
    private Button btnCacThayDoi;

    @FXML
    private Button btnSuaThongTin;

    @FXML
    private Button btnTachHoKhau;

    @FXML
    private Button btnThemMoi;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnXemThongTin;

    @FXML
    private BorderPane contentHoKhau;

    @FXML
    private TableColumn<HoKhauTable, String> diaChi;

    @FXML
    private TableColumn<HoKhauTable, String> hoTenChuHo;

    @FXML
    private TextField input;

    @FXML
    private TableColumn<HoKhauTable, String> soHoKhau;

    @FXML
    private TableView<HoKhauTable> table;

    @FXML
    void handleClicks(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == btnTimKiem) {
            filterTable(input.getText());
            return;
        }

        String fxmlFile = "";
        String windowTitle = "";
        int width = 0, height = 0;
        String soHoKhau = "";
        XemThongTinController xemThongTinController = null;


        if (event.getSource() == btnXemThongTin){
            HoKhauTable selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            fxmlFile = "hokhau/xemthongtin.fxml";
            windowTitle = "Xem thông tin";
            width = 960;
            height = 720;

            soHoKhau = selected.getSoHoKhau();
            xemThongTinController = new XemThongTinController();
            xemThongTinController.setSoHoKhau(soHoKhau);
        }
        else if (event.getSource() == btnThemMoi) {
            fxmlFile = "hokhau/themmoihokhau.fxml";
            windowTitle = "Thêm mới hộ khẩu";
            width = 960;
            height = 720;
        } else if (event.getSource() == btnSuaThongTin) {
            fxmlFile = "hokhau/suahokhau.fxml";
            windowTitle = "Sửa thông tin hộ khẩu";
            width = 960;
            height = 720;
        } else if (event.getSource() == btnTachHoKhau) {
            fxmlFile = "hokhau/tachhokhau.fxml";
            windowTitle = "Tách hộ khẩu";
            width = 960;
            height = 720;
        } else if (event.getSource() == btnChuyenDi) {
            fxmlFile = "hokhau/chuyenhokhau.fxml";
            windowTitle = "Chuyển hộ khẩu";
            width = 960;
            height = 720;
        } else if (event.getSource() == btnXemThongTin) {
            fxmlFile = "hokhau/xemthongtin.fxml";
            windowTitle = "Xem thông tin";
            width = 960;
            height = 720;
        } else if (event.getSource() == btnCacThayDoi) {
            fxmlFile = "hokhau/lichsuthaydoi.fxml";
            windowTitle = "Các thay đổi";
            width = 783 ;
            height = 720;
        }

        Parent root = Windows.getRoot(fxmlFile);
        if (xemThongTinController != null) {
            FXMLLoader loader = Windows.getLoader(fxmlFile);
            loader.setController(xemThongTinController);
            root = loader.load();
        }

        Scene scene = new Scene(root, width, height);
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.setOnHidden((e) -> populateTable());
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }

    private void filterTable(String input) {
        ObservableList<HoKhauTable> listHK = getHoKhauList();
        listHK.removeIf(HoKhauTable -> !HoKhauTable.getSoHoKhau().toLowerCase().contains(input.toLowerCase()));
        setTableData(listHK);
    }

    private void populateTable() {
        setTableData(getHoKhauList());
    }

    private ObservableList<HoKhauTable> getHoKhauList() {
        HoKhauDB hokhauinDB = new HoKhauDB();
        try {
            return hokhauinDB.getListHoKhauTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTableData(ObservableList<HoKhauTable> listNK) {
        soHoKhau.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("soHoKhau"));
        hoTenChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("hoTen"));
        diaChi.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("diaChi"));
        table.setItems(listNK);
    }

}
