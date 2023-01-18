package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.views.ChildWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeControllers {

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

    void resetVisible(){
        contentTrangChu.setVisible(false);
        contentNhanKhau.setVisible(false);
        contentHoKhau.setVisible(false);
        contentThongKe.setVisible(false);
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
