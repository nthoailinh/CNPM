package QuanLyNhanKhau.controllers.nhankhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class themmoinhankhauController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private TextField cccd;

    @FXML
    private TextField danToc;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton gioiTinhNam;

    @FXML
    private RadioButton gioiTinhNu;

    @FXML
    private TextField hoTen;

    @FXML
    private DatePicker ngayCap;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private TextField ngheNghiep;

    @FXML
    private TextField nguyenQuan;

    @FXML
    private TextField noiCap;

    @FXML
    private TextField noiLamViec;

    @FXML
    private TextField noiSinh;

    @FXML
    private TextField quanHeVoiChuHo;

    @FXML
    private TextField soHoKhau;

    @FXML
    void handleClicks(ActionEvent event) {
        if(event.getSource() == btnCancel){
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

}
