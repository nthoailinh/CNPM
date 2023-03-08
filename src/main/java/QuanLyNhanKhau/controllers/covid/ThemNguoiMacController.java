package QuanLyNhanKhau.controllers.covid;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.CovidDB;
import QuanLyNhanKhau.services.MySQL;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemNguoiMacController implements Initializable {
    private final NhanKhauDB nhankhauDB = new NhanKhauDB();
    private final CovidDB covidDB = new CovidDB();
    @FXML
    private TableView<NhanKhauTable> tableNhanKhau;
    @FXML
    private TableColumn<NhanKhau, Integer> IDNK;
    @FXML
    private TableColumn<NhanKhau, String> hoTenNK;
    @FXML
    private TableColumn<NhanKhau, LocalDate> ngaySinhNK;
    @FXML
    private TableColumn<NhanKhau, String> gioiTinhNK;
    @FXML
    private TableColumn<NhanKhau, String> diaChiNK;
    @FXML
    private TextField hoTenTimKiem;
    @FXML
    private Label hoTenNguoiMac;
    @FXML
    private Label cccdNguoiMac;
    @FXML
    private DatePicker ngayKhaiBao;
    @FXML
    private DatePicker thoiDiemTest;
    @FXML
    private TextField hinhThucTest;
    @FXML
    private TextField tinhTrangSK;
    @FXML
    private Button btnHuy;
    @FXML
    private Button btnTimKiem;
    @FXML
    private Button btnXacNhan;
    private ObservableList<NhanKhauTable> listNK = FXCollections.observableArrayList();
    private int idNguoiMac;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listNK = nhankhauDB.getListNhanKhauTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        IDNK.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        hoTenNK.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        ngaySinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhau, LocalDate>("ngaySinh"));
        gioiTinhNK.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        diaChiNK.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChi"));
        tableNhanKhau.setItems(listNK);

        tableNhanKhau.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NhanKhauTable selectedNhanKhau = tableNhanKhau.getSelectionModel().getSelectedItem();
                Connection connection = MySQL.getConnection();
                // Tìm cccd nhân khẩu
                PreparedStatement pstmt_nhankhau = null;
                try {
                    pstmt_nhankhau = connection.prepareStatement("SELECT cccd FROM CCCD WHERE idNhanKhau = ?");
                    idNguoiMac = selectedNhanKhau.getId();
                    pstmt_nhankhau.setString(1, String.valueOf(idNguoiMac));
                    ResultSet rs = pstmt_nhankhau.executeQuery();
                    hoTenNguoiMac.setText(selectedNhanKhau.getHoTen());
                    if (rs.next()) {
                        cccdNguoiMac.setText(rs.getString("cccd"));
                    } else {
                        cccdNguoiMac.setText("");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    void handleClicks(ActionEvent event) throws SQLException {
        if (event.getSource() == btnTimKiem) {
            ObservableList<NhanKhauTable> listNK_search = FXCollections.observableArrayList();
            String inputHoTen = hoTenTimKiem.getText();
            for (NhanKhauTable nk : listNK) {
                if (nk.getHoTen().contains(inputHoTen)) {
                    listNK_search.add(nk);
                }
            }
            tableNhanKhau.setItems(listNK_search);
        }

        if (event.getSource() == btnXacNhan) {
            if (hoTenNguoiMac.getText().isEmpty() || ngayKhaiBao.getValue() == null ||
                    hinhThucTest.getText().isEmpty() || thoiDiemTest.getValue() == null ||
                    tinhTrangSK.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Thiếu thông tin");
                alert.setContentText("Vui lòng điền tất cả các trường bắt buộc.\nCác trường có dấu (*) là các trường bắt buộc.");
                alert.showAndWait();
                return;
            }
            Connection connection = MySQL.getConnection();
            // Check nhân khẩu đã có trong bảng mắc covid chưa
            PreparedStatement pstmt_nhankhauCovid = null;
            pstmt_nhankhauCovid = connection.prepareStatement("SELECT * FROM MacCOVID WHERE idNhanKhau = ? AND ngayKhoi IS NULL");
            pstmt_nhankhauCovid.setString(1, String.valueOf(idNguoiMac));
            ResultSet rs = pstmt_nhankhauCovid.executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Nhân khẩu vừa chọn đã được thêm trước đó và vẫn đang mắc Covid.");
                alert.setContentText("Chỉ thêm người mắc mới hoặc người tái nhiễm.\nChọn cập nhật trạng thái đối với người đang mắc Covid.");
                alert.showAndWait();
                return;
            } else {
                covidDB.addMacCOVID(idNguoiMac, String.valueOf(thoiDiemTest.getValue()));

                connection = MySQL.getConnection();
                // Liên kết khóa ngoài giữa MacCOVID với KhaiBao
                PreparedStatement pstmt_nguoiMacMoiThem = null;
                pstmt_nguoiMacMoiThem = connection.prepareStatement("SELECT * FROM MacCOVID WHERE idNhanKhau = ? AND ngayMac = ? AND ngayKhoi IS NULL");
                pstmt_nguoiMacMoiThem.setString(1, String.valueOf(idNguoiMac));
                System.out.println(thoiDiemTest.getValue());
                pstmt_nguoiMacMoiThem.setString(2, String.valueOf(thoiDiemTest.getValue()));
                rs = pstmt_nguoiMacMoiThem.executeQuery();
                if (rs.next()) {
                    int idMacCOVID = rs.getInt("id");
                    covidDB.addKhaiBao(idMacCOVID, String.valueOf(ngayKhaiBao.getValue()),
                            String.valueOf(thoiDiemTest.getValue()), hinhThucTest.getText(),
                            "Dương tính", tinhTrangSK.getText());
                }
            }
            System.out.println("String.valueOf(thoiDiemTest.getValue())");
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else if (event.getSource() == btnHuy) {
            // Tắt cửa sổ
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }
}
