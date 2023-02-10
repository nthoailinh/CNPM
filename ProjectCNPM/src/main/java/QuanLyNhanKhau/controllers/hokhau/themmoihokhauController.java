package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.Query;

import java.net.URL;

import QuanLyNhanKhau.services.nhankhauDB;
import QuanLyNhanKhau.views.Windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class themmoihokhauController implements Initializable {

    private final ObservableList<NhanKhau> listNK = FXCollections.observableArrayList();
    nhankhauDB nhankhaudb = new nhankhauDB();
    @FXML
    private Button btnChon;
    @FXML
    private Button btnHuy;
    @FXML
    private Button btnLuu;
    @FXML
    private Button btnThemThanhVien;
    @FXML
    private Button btnXoaThanhVien;
    @FXML
    private Label cccdChuHo;
    @FXML
    private Label chuHo;
    @FXML
    private TextField duong;
    @FXML
    private TextField ngo;
    @FXML
    private TextField soHoKhau;
    @FXML
    private TextField soNha;
    @FXML
    private TableView<NhanKhau> table;
    @FXML
    private TableColumn<NhanKhau, String> table_gioiTinh;
    @FXML
    private TableColumn<NhanKhau, String> table_hoTen;
    @FXML
    private TableColumn<NhanKhau, Integer> table_id;
    @FXML
    private TableColumn<NhanKhau, LocalDate> table_ngaySinh;
    @FXML
    private TableColumn<NhanKhau, String> table_quanHeVoiChuHo;
    private NhanKhau nhanKhau, nhanKhauChuHo;

    @FXML
    void handleClicks(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == btnChon) {
            FXMLLoader loader = Windows.getLoader("hokhau/chonnhankhau.fxml");
            chonchuhoController chonchuhoController = new chonchuhoController();
            loader.setController(chonchuhoController);
            Parent root = loader.load();
            int width = 768;
            int height = 648;
            String windowTitle = "Chọn chủ hộ";
            Scene scene = new Scene(root, width, height);
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(scene);
            stage.setOnHidden((e) -> {
                nhanKhauChuHo = chonchuhoController.getSelectedNhanKhau();
                if (nhanKhauChuHo != null) {
                    chuHo.setText(nhanKhauChuHo.getHoTen());
                    try {
                        cccdChuHo.setText(nhankhaudb.getCCCD(nhanKhauChuHo.getId()));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    nhanKhauChuHo.setQuanHeVoiChuHo("Chủ hộ");
                    listNK.add(nhanKhauChuHo);
                }
            });
            stage.show();
        } else if (event.getSource() == btnThemThanhVien) {
            FXMLLoader loader = Windows.getLoader("hokhau/chonnhankhau.fxml");
            chonthanhvienController chonthanhvien_controller = new chonthanhvienController();
            loader.setController(chonthanhvien_controller);
            Parent root = loader.load();
            int width = 768;
            int height = 648;
            String windowTitle = "Chọn thành viên của hộ";
            Scene scene = new Scene(root, width, height);
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(scene);
            stage.setOnHidden((e) -> {
                nhanKhau = chonthanhvien_controller.getSelectedNhanKhau();
                if (nhanKhau != null) {
                    if (listNK.stream().noneMatch(nk -> nk.getId() == nhanKhau.getId())) {
                        listNK.add(nhanKhau);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Không thể lưu");
                        alert.setHeaderText("Thành viên đã tồn tại");
                        alert.showAndWait();
                    }
                }
            });
            stage.show();
        } else if (event.getSource() == btnXoaThanhVien) {
            if (nhanKhau != null) {
                listNK.remove(nhanKhau);
            }
        } else if (event.getSource() == btnLuu) {
            if (soHoKhau.getText().isEmpty() || cccdChuHo.getText().isEmpty() || chuHo.getText().isEmpty() ||
                    soNha.getText().isEmpty() || duong.getText().isEmpty() || table_quanHeVoiChuHo.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            Query query = new Query();
            PreparedStatement pstmt = query.HoKhau(soHoKhau.getText(), nhanKhauChuHo.getId(), Integer.parseInt(soNha.getText()), ngo.getText(), duong.getText());
            int idHoKhau = -1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idHoKhau = rs.getInt(1);
                System.out.println(idHoKhau);
            }
            rs.close();
            pstmt.close();
            for (NhanKhau n : listNK) {
                n.setIdHoKhau(idHoKhau);
                query.updateIDHoKhauCuaNhanKhau(n.getId(), idHoKhau, n.getQuanHeVoiChuHo());
            }
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else if (event.getSource() == btnHuy) {
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

    }

    private void setTableData(ObservableList<NhanKhau> listNK) {
        table_id.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        table_hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        table_ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, LocalDate>("ngaySinh"));
        table_gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        table_quanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
        table.setItems(listNK);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableData(listNK);
    }
}