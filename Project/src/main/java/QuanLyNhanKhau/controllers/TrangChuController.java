package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.services.trangchuDB;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.Map;

public class TrangChuController {
    @FXML
    private Label SoLuongHoKhau;

    @FXML
    private Label SoLuongNhanKhau;

    @FXML
    private Label SoLuongTamTru;

    @FXML
    private Label SoLuongTamVang;

    public TrangChuController(Label SoLuongHoKhau, Label SoLuongNhanKhau, Label SoLuongTamTru, Label SoLuongTamVang) {
        this.SoLuongHoKhau = SoLuongHoKhau;
        this.SoLuongNhanKhau = SoLuongNhanKhau;
        this.SoLuongTamTru = SoLuongTamTru;
        this.SoLuongTamVang = SoLuongTamVang;
    }

    public void initialize(){
        try {
            Map<String, Integer> map = trangchuDB.getQuantity();
            SoLuongNhanKhau.setText(map.get("NhanKhau").toString());
            SoLuongHoKhau.setText(map.get("HoKhau").toString());
            SoLuongTamTru.setText(map.get("TamTru").toString());
            SoLuongTamVang.setText(map.get("TamVang").toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
