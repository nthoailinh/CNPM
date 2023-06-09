package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.models.HoKhau;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.HoKhauDB;
import QuanLyNhanKhau.services.NhanKhauDB;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class SuaHoKhauController implements Initializable {

    private final NhanKhauDB nhankhauDB = new NhanKhauDB();
    private final HoKhauDB hokhauDB = new HoKhauDB();
    private ObservableList<NhanKhau> listNK = FXCollections.observableArrayList();
    @FXML
    private Button btnChon;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnKiemTra;

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

    public int getIDHoKhau(String soHoKhau) throws SQLException {
        return hokhauDB.getIDHoKhauBySoHoKhau(soHoKhau);
    }

    public ObservableList<NhanKhau> getNhanKhauList(String soHoKhau) throws SQLException {
        return nhankhauDB.getListNhanKhauBySoHoKhau(soHoKhau);
    }

    @FXML
    void handleClicks(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == btnKiemTra) {
            int idHoKhau = getIDHoKhau(soHoKhau.getText());
            if (idHoKhau == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Số hộ khẩu không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng kiểm tra và nhập lại số hộ khẩu.");
                alert.showAndWait();
            } else {
                listNK = getNhanKhauList(soHoKhau.getText());
                nhanKhauChuHo = listNK.stream()
                        .filter(nk -> nk.getQuanHeVoiChuHo().equals("Chủ hộ"))
                        .findFirst()
                        .orElse(null);
                setTableData(listNK);
                chuHo.setText(nhanKhauChuHo.getHoTen());
                cccdChuHo.setText(nhankhauDB.getCCCD(nhanKhauChuHo.getId()));
                HoKhau hoKhau = null;
                try {
                    hoKhau = hokhauDB.getHoKhau(soHoKhau.getText());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                soNha.setText(hoKhau.getSoNha());
                ngo.setText(hoKhau.getNgo());
                duong.setText(hoKhau.getDuong());
            }
        } else if (event.getSource() == btnChon) {
            // Thay đổi quan hệ với chủ hộ của chủ hộ cũ
            int idChuHoCu = nhanKhauChuHo.getId();
            listNK.removeIf(nhankhau -> nhankhau.getId() == nhanKhauChuHo.getId());
            NhanKhau nhanKhauChuHoCu = new NhanKhau(nhanKhauChuHo.getId(), nhanKhauChuHo.getIdHoKhau(), nhanKhauChuHo.getHoTen(),
                    nhanKhauChuHo.getNgaySinh(), nhanKhauChuHo.getGioiTinh(), nhanKhauChuHo.getNoiSinh(),
                    nhanKhauChuHo.getNguyenQuan(), nhanKhauChuHo.getDanToc(), nhanKhauChuHo.getNgheNghiep(),
                    nhanKhauChuHo.getNoiLamViec(), "");

            FXMLLoader loader = Windows.getLoader("hokhau/chonnhankhau.fxml");
            SuaChuHoController suachuhoController = new SuaChuHoController();
            int idHoKhau = getIDHoKhau(soHoKhau.getText());
            if (idHoKhau != -1) {
                suachuhoController.setIdHoKhau(getIDHoKhau(soHoKhau.getText()));
                loader.setController(suachuhoController);
                Parent root = loader.load();
                int width = 768;
                int height = 648;
                String windowTitle = "Chọn chủ hộ";
                Scene scene = new Scene(root, width, height);
                Stage stage = new Stage();
                stage.setTitle(windowTitle);
                stage.setScene(scene);
                stage.setOnHidden((e) -> {
                    nhanKhauChuHo = suachuhoController.getSelectedNhanKhau();
                    if (nhanKhauChuHo != null) {
                        chuHo.setText(nhanKhauChuHo.getHoTen());
                        try {
                            cccdChuHo.setText(nhankhauDB.getCCCD(nhanKhauChuHo.getId()));
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        nhanKhauChuHo.setQuanHeVoiChuHo("Chủ hộ");
                        listNK.removeIf(nhankhau -> nhankhau.getId() == nhanKhauChuHo.getId());
                        listNK.add(nhanKhauChuHo);
                        try {
                            hokhauDB.addThayDoiNhanKhauTrongHoKhau(soHoKhau.getText(), nhanKhauChuHo.getHoTen(), "Trở thành chủ hộ");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        if (nhanKhauChuHo.getId() != idChuHoCu) {
                            TextInputDialog dialog = new TextInputDialog();
                            dialog.setTitle("Quan hệ với chủ hộ của chủ hộ cũ");
                            dialog.setHeaderText("Nhập quan hệ với chủ hộ mới");
                            Optional<String> result = dialog.showAndWait();
                            result.ifPresent(value -> {
                                if (value.trim().equals("")) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Không thể lưu");
                                    alert.setHeaderText("Vui lòng nhập quan hệ với chủ hộ.");
                                    alert.showAndWait();
                                    return;
                                }
                                if (!value.trim().equals("Chủ hộ")) {
                                    nhanKhauChuHoCu.setQuanHeVoiChuHo(value);
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Không thể lưu");
                                    alert.setHeaderText("Không thể thêm thành viên là chủ hộ.");
                                    alert.showAndWait();
                                    return;
                                }
                            });
                        }
                        listNK.add(nhanKhauChuHoCu);

                        table.setItems(listNK);
                    }
                });
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Số hộ khẩu không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng kiểm tra và nhập lại số hộ khẩu.");
                alert.showAndWait();
            }
        } else if (event.getSource() == btnThemThanhVien) {
            FXMLLoader loader = Windows.getLoader("hokhau/chonnhankhau.fxml");
            SuaThanhVienController suathanhvien_controller = new SuaThanhVienController();
            int idHoKhau = getIDHoKhau(soHoKhau.getText());
            if (idHoKhau != -1) {
                suathanhvien_controller.setIdHoKhau(idHoKhau);
                loader.setController(suathanhvien_controller);
                Parent root = loader.load();
                int width = 768;
                int height = 648;
                String windowTitle = "Chọn thành viên của hộ";
                Scene scene = new Scene(root, width, height);
                Stage stage = new Stage();
                stage.setTitle(windowTitle);
                stage.setScene(scene);
                stage.setOnHidden((e) -> {
                    nhanKhau = suathanhvien_controller.getSelectedNhanKhau();
                    if (nhanKhau != null) {
                        if (listNK.stream().noneMatch(nk -> nk.getId() == nhanKhau.getId())) {
                            listNK.add(nhanKhau);
                            setTableData(listNK);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Không thể lưu");
                            alert.setHeaderText("Thành viên đã tồn tại");
                            alert.showAndWait();
                        }
                    }
                    try {
                        hokhauDB.addThayDoiNhanKhauTrongHoKhau(soHoKhau.getText(), nhanKhau.getHoTen(), "Chuyển đến hộ khẩu mới");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                });
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Số hộ khẩu không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng kiểm tra và nhập lại số hộ khẩu.");
                alert.showAndWait();
            }

        } else if (event.getSource() == btnXoaThanhVien) {
            nhanKhau = table.getSelectionModel().getSelectedItem();
            if (nhanKhau != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có muốn xóa không?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (nhanKhau.getId() != nhanKhauChuHo.getId()) {
                        listNK.removeIf(nk -> nk.getId() == nhanKhau.getId());
                    }
                }
                nhankhauDB.removeNhanKhau(nhanKhau.getId());
                hokhauDB.addThayDoiNhanKhauTrongHoKhau(soHoKhau.getText(), nhanKhau.getHoTen(), "Bị xóa khỏi hộ khẩu");
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
            PreparedStatement pstmt = hokhauDB.updateHoKhau(soHoKhau.getText(), nhanKhauChuHo.getId(), soNha.getText(), ngo.getText(), duong.getText());
            pstmt.close();
            for (NhanKhau n : listNK) {
                nhankhauDB.updateIDHoKhauCuaNhanKhau(n.getId(), nhanKhauChuHo.getIdHoKhau(), n.getQuanHeVoiChuHo());
            }
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else if (event.getSource() == btnHuy) {
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

    }

    private void setTableData(ObservableList<NhanKhau> listNK) {
        table.setEditable(true);
        table_id.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        table_hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        table_ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, LocalDate>("ngaySinh"));
        table_gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        table_quanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
        table_quanHeVoiChuHo.setCellFactory(TextFieldTableCell.forTableColumn());
        table_quanHeVoiChuHo.setOnEditCommit(event -> {
            NhanKhau obj = event.getRowValue();
            obj.setQuanHeVoiChuHo(event.getNewValue());
        });
        table.setItems(listNK);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableData(listNK);
    }

}
