package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.nhankhauDB;
import QuanLyNhanKhau.services.hokhauDB;
import QuanLyNhanKhau.services.trangchuDB;
import QuanLyNhanKhau.views.ChildWindows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.models.HoKhau;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Button COVID;

    @FXML
    private Button DangXuat;

    @FXML
    private Button HoKhau;

    @FXML
    private Button HoKhauChuyenDi;

    @FXML
    private Button HoKhauTachHK;

    @FXML
    private Button HoKhauThemMoi;

    @FXML
    private Button NhanKhau;

    @FXML
    private Button NhanKhauDKTamTru;

    @FXML
    private Button NhanKhauDKTamVang;

    @FXML
    private TextField NhanKhauInput;

    @FXML
    private Button NhanKhauKhaiTu;

    @FXML
    private Button NhanKhauThemMoi;

    @FXML
    private Button NhanKhauTimKiem;

    @FXML
    private Label SoLuongHoKhau;

    @FXML
    private Label SoLuongNhanKhau;

    @FXML
    private Label SoLuongTamTru;

    @FXML
    private Label SoLuongTamVang;

    @FXML
    private Button ThongKe;

    @FXML
    private Button TrangChu;

    @FXML
    private BorderPane contentHoKhau;

    @FXML
    private BorderPane contentNhanKhau;

    @FXML
    private BorderPane contentThongKe;

    @FXML
    private GridPane contentTrangChu;

    @FXML
    private TableView<NhanKhauTable> tableNhanKhau;

    @FXML
    private TableColumn<NhanKhauTable, String> diaChiNK;

    @FXML
    private TableColumn<NhanKhauTable, String> gioiTinhNK;

    @FXML
    private TableColumn<NhanKhauTable, String> hoTenNK;

    @FXML
    private TableColumn<NhanKhauTable, Integer> IDNK;

    @FXML
    private TableColumn<NhanKhauTable, LocalDate> ngaySinhNK;

    @FXML
    private TableView<HoKhauTable> tableHoKhau;

    @FXML
    private TableColumn<HoKhauTable, String> soHK;

    @FXML
    private TableColumn<HoKhauTable, String> hoTenChuHK;

    @FXML
    private TableColumn<HoKhauTable, String> diaChiHK;

    void resetVisible(){
        contentTrangChu.setVisible(false);
        contentNhanKhau.setVisible(false);
        contentHoKhau.setVisible(false);
        contentThongKe.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBunle) {
        TrangChu.setStyle("-fx-background-color: #ebe8f9");
        
        TrangChuController trangchuController = new TrangChuController(SoLuongHoKhau, SoLuongNhanKhau,
                SoLuongTamTru, SoLuongTamVang);
        trangchuController.initialize();

        NhanKhauController nhanKhauController = new NhanKhauController(IDNK, hoTenNK, ngaySinhNK, gioiTinhNK, diaChiNK, tableNhanKhau);
        try {
            nhanKhauController.initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        HoKhauController hokhauController = new HoKhauController(soHK, hoTenChuHK, diaChiHK, tableHoKhau);
        hokhauController.initialize();
    }
    @FXML
    void handleClicksSidebar(ActionEvent event) {
        if(event.getSource() == TrangChu){
            resetVisible();
            contentTrangChu.setVisible(true);
        }
        else if(event.getSource() == NhanKhau){
            resetVisible();
            contentNhanKhau.setVisible(true);
        }
        else if(event.getSource() == HoKhau){
            resetVisible();
            contentHoKhau.setVisible(true);
        }
        else if(event.getSource() == ThongKe){
            resetVisible();
            contentThongKe.setVisible(true);
        }
        else if(event.getSource() == COVID);
        else if(event.getSource() == DangXuat){
            System.exit(0);
        }
    }

    @FXML
    void handleClicksNhanKhau(ActionEvent event) throws IOException {
        if(event.getSource() == NhanKhauThemMoi){
            ChildWindows.show("nhankhau/themmoinhankhau.fxml");
        }
        else if(event.getSource() == NhanKhauDKTamVang){
            ChildWindows.show("nhankhau/dangkytamvang.fxml");
        }
        else if(event.getSource() == NhanKhauDKTamTru){
            ChildWindows.show("nhankhau/dangkytamtru.fxml");
        }
        else if(event.getSource() == NhanKhauKhaiTu){
            ChildWindows.show("nhankhau/khaitu.fxml");
        }
    }

    @FXML
    void handleClicksHoKhau(ActionEvent event) throws IOException {
        if(event.getSource() == HoKhauThemMoi){
            ChildWindows.show("hokhau/themmoihokhau.fxml");
        }
        else if(event.getSource() == HoKhauTachHK){
            ChildWindows.show("hokhau/tachhokhau.fxml");
        }
        else if(event.getSource() == HoKhauChuyenDi){
            ChildWindows.show("hokhau/chuyenhokhau.fxml");
        }
    }
}
