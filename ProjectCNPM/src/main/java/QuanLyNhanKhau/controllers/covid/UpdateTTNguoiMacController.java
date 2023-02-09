package QuanLyNhanKhau.controllers.covid;
import QuanLyNhanKhau.models.MacCOVID;
import QuanLyNhanKhau.services.CovidDB;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.Query;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

public class UpdateTTNguoiMacController implements Initializable {
    @FXML
    private TableColumn<MacCOVID, String> ngayMac;
    @FXML
    private TableColumn<MacCOVID, String> thoiDiemTest;
    @FXML
    private TableColumn<MacCOVID, String> ngayKhaiBao;
    @FXML
    private TableColumn<MacCOVID, String> hinhThucTest;

    @FXML
    private DatePicker updateNgayKhoi;

    @FXML
    private TableColumn<MacCOVID, Integer> IDNguoiMac;

    @FXML
    private TableColumn<MacCOVID, String> tinhTrangSK;

    @FXML
    private TableColumn<MacCOVID, String> ketQuaTest;

    @FXML
    private TextField updateTinhTrangSK;
    @FXML
    private TableView<MacCOVID> tableNguoiMac;

    @FXML
    private Label hoTenNguoiMac;
    @FXML
    private Label cccdNguoiMac;
    @FXML
    private TextField hoTenTimKiem;
    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnCapNhat;
    @FXML
    private Button btnHuy;

    @FXML
    private TableColumn<MacCOVID, String> hoTen;

    @FXML
    private TableColumn<MacCOVID, String> ngayKhoi;
    @FXML
    private DatePicker updateThoiDiemTest;
    @FXML
    private DatePicker updateNgayKhaiBao;
    @FXML
    private TextField updateHinhThucTest;
    @FXML
    private RadioButton btnDuongTinh;
    @FXML
    private RadioButton btnAmTinh;

    private MacCOVID tmp;

    private   ObservableList<MacCOVID> listCovid = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CovidDB covidinDB = new CovidDB();

        try {
            listCovid = covidinDB.getListMacCovid();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        IDNguoiMac.setCellValueFactory(new PropertyValueFactory<MacCOVID, Integer>("id"));
        hoTen.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("hoTen"));
        ngayMac.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("ngayMac"));
        ngayKhoi.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("ngayKhoi"));
        tinhTrangSK.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("tinhTrangSK"));
        ketQuaTest.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("ketQuaTest"));
        hinhThucTest.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("hinhThucTest"));
        thoiDiemTest.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("thoiDiemTest"));
        ngayKhaiBao.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("ngayKhaiBao"));
        tableNguoiMac.setItems(listCovid);

        tableNguoiMac.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                     tmp = tableNguoiMac.getSelectionModel().getSelectedItem();
                    hoTenNguoiMac.setText(tmp.getHoTen());
                    Connection connection = MySQL.getConnection();
                    PreparedStatement pstmt_nhankhau = null;
                    try {
                        pstmt_nhankhau = connection.prepareStatement("SELECT cccd FROM CCCD WHERE idNhanKhau = ?");
                        pstmt_nhankhau.setString(1, String.valueOf(tmp.getId()));
                        ResultSet rs = pstmt_nhankhau.executeQuery();
                        if (rs.next()) {
                            cccdNguoiMac.setText(rs.getString("cccd"));
                        } else {
                            cccdNguoiMac.setText("");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    btnCapNhat.setVisible(true);
                    btnHuy.setVisible(true);
                }
            }
        });
    }
    @FXML
    public void selectedKetQuaTest(ActionEvent event) {
        if(btnDuongTinh.isSelected()) {
            btnAmTinh.setSelected(false);
            updateNgayKhoi.setVisible(false);
        }
        else {
            updateNgayKhoi.setVisible(true);
        }
    }

    @FXML
    public void handleClicks(ActionEvent event) throws  SQLException {
        if (event.getSource() == btnTimKiem) {
            ObservableList<MacCOVID> list_search = FXCollections.observableArrayList();
            String inputHoTen = hoTenTimKiem.getText();
            for(MacCOVID macCOVID : listCovid){
                if(macCOVID.getHoTen().contains(inputHoTen)){
                    list_search.add(macCOVID);
                }
            }
            tableNguoiMac.setItems(list_search);
        }
        if (event.getSource() == btnCapNhat) {
            if(btnAmTinh.isSelected()) {
                if (updateNgayKhaiBao.getValue() == null ||
                        updateHinhThucTest.getText().isEmpty() || updateThoiDiemTest.getValue() == null ||
                        updateTinhTrangSK.getText().isEmpty() || updateNgayKhoi.getValue() ==null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Không thể lưu");
                    alert.setHeaderText("Thiếu thông tin");
                    alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                    alert.showAndWait();
                    return;
                }
            }
            else if(btnDuongTinh.isSelected()) {
                    updateNgayKhoi.setVisible(false);
                if (updateNgayKhaiBao.getValue() == null ||
                        updateHinhThucTest.getText().isEmpty() || updateThoiDiemTest.getValue() == null ||
                        updateTinhTrangSK.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Không thể lưu");
                    alert.setHeaderText("Thiếu thông tin");
                    alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            Query query = new Query();
            if(btnAmTinh.isSelected()) {
                query.addKhaiBao(tmp.getId(), updateTinhTrangSK.getText(), btnAmTinh.getText(), updateHinhThucTest.getText(), updateThoiDiemTest.getValue(), updateNgayKhaiBao.getValue());
                query.updateMacCOVID(tmp.getId(), tmp.getNgayMac(), updateNgayKhoi.getValue().toString());
            } else {
                query.addKhaiBao(tmp.getId(), updateTinhTrangSK.getText(), btnDuongTinh.getText(), updateHinhThucTest.getText(), updateThoiDiemTest.getValue(), updateNgayKhaiBao.getValue());
            }
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else if (event.getSource() == btnHuy) {
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

    }
}
