package QuanLyNhanKhau.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class COVIDController {

    @FXML
    private TableColumn<?, ?> IDNguoiMac;

    @FXML
    private Button NguoiMacTimKiem;

    @FXML
    private Button ThemNguoiMac;

    @FXML
    private Button XoaNguoiMac;

    @FXML
    private Button capNhatTT;

    @FXML
    private BorderPane contentCovid;

    @FXML
    private TableColumn<?, ?> hoTenNguoiMac;

    @FXML
    private TableColumn<?, ?> ketQuaTest;

    @FXML
    private TableColumn<?, ?> ngayKhoi;

    @FXML
    private TableColumn<?, ?> ngayMac;

    @FXML
    private TextField nguoiMacInput;

    @FXML
    private TableView<?> tableNguoiMac;

    @FXML
    private TableColumn<?, ?> tinhTrangSucKhoe;

    @FXML
    void handleClicksCovid(ActionEvent event) {

    }

}
