package QuanLyNhanKhau.controllers.covid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateTTNguoiMacController {
    @FXML
    private static DatePicker updateNgayMac;


    @FXML
    private static Label hoTenUpdate;
    @FXML
    private static TextField updateKQTest;

    @FXML
    private static TextField UpdateTinhTrangSK;

    @FXML
    private static DatePicker updateNgayKhoi;

    public UpdateTTNguoiMacController(String hoTen, String ngayMac, String ngayKhoi, String tinhTrangSK, String ketQuaTest ) {
        hoTenUpdate.setText(hoTen);
        updateNgayMac.setValue(LocalDate.parse(ngayMac));
        updateNgayKhoi.setValue(LocalDate.parse(ngayKhoi));
        UpdateTinhTrangSK.setText(tinhTrangSK);
        updateKQTest.setText(ketQuaTest);
    }
}
