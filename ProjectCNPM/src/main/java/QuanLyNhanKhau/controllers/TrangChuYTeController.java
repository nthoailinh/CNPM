package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.services.TrangChuDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class TrangChuYTeController implements Initializable {

    @FXML
    private Label SoCaKhoi;

    @FXML
    private Label SoCaMac;

    @FXML
    private GridPane contentTrangChu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TrangChuDB trangchuDB = new TrangChuDB();
            Map<String, Integer> map = trangchuDB.getQuantityYTe();
            SoCaKhoi.setText(map.get("SoCaKhoi").toString());
            SoCaMac.setText(map.get("SoCaMac").toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
