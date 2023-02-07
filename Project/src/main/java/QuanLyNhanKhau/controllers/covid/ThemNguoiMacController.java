package QuanLyNhanKhau.controllers.covid;
import QuanLyNhanKhau.controllers.NhanKhauController;
import QuanLyNhanKhau.controllers.tables.CovidTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.Update;
import QuanLyNhanKhau.services.nhankhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemNguoiMacController implements Initializable {

    @FXML
    private TableColumn<NhanKhauTable, String> diaChiNK;

    @FXML
    private Button timKiemNguoiMac;

    @FXML
    private TableColumn<NhanKhauTable, LocalDate> ngaySinhNK;

    @FXML
    private TableColumn<NhanKhauTable, Integer> IDNK;

    @FXML
    private TableColumn<NhanKhauTable, String> hoTenNK;

    @FXML
    private TableColumn<NhanKhauTable, String> gioiTinhNK;
    @FXML
    private Button btnXacNhanNguoiMac;

    @FXML
    private TextField themTinhTrangSK;
    @FXML
    private TextField themHinhThucTest;

    @FXML
    private DatePicker themNgayMac;
    @FXML
    private Label ngaySinhNguoiMac;
    @FXML
    private Label hoTenNguoiMac;
    @FXML
    private Label gioiTinhNguoiMac;
    @FXML
    private DatePicker themNgayKhaiBao;
    @FXML
    private TableView<NhanKhauTable> tableNhanKhau;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nhankhauDB nhankhauinDB = new nhankhauDB();
        ObservableList<NhanKhauTable> listNK = null;
        try {
            listNK = nhankhauinDB.getListNhanKhauNoCovidTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        IDNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, Integer>("id"));
        hoTenNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("hoTen"));
        ngaySinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, LocalDate>("ngaySinh"));
        gioiTinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("gioiTinh"));
        diaChiNK.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("diaChi"));
        tableNhanKhau.setItems(listNK);

        tableNhanKhau.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                NhanKhauTable tmp = tableNhanKhau.getSelectionModel().getSelectedItem();
                hoTenNguoiMac.setText(tmp.getHoTen());
                ngaySinhNguoiMac.setText(tmp.getNgaySinh());
                gioiTinhNguoiMac.setText(tmp.getGioiTinh());
                    btnXacNhanNguoiMac.setVisible(true);
                    btnXacNhanNguoiMac.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent1) {
                            if (themNgayMac.getValue() == null) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Không thể lưu");
                                alert.setHeaderText("Thiếu thông tin");
                                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                                alert.showAndWait();
                                return;
                            }
                            else {
                                //UPdate database
                                    Update update = new Update();
                                try {
                                    update.addMacCovid( tmp.getId(), themTinhTrangSK.getText(),"Dương tính", themNgayMac.getValue(),null, themHinhThucTest.getText(), themNgayMac.getValue(), themNgayKhaiBao.getValue() );
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                            ((Node) mouseEvent1.getSource()).getScene().getWindow().hide();
                        }
                    });
                }
            }
        });
    }
}
