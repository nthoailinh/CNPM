package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class chonchuhoController extends chonnhankhauController implements Initializable {
    private ObservableList<NhanKhau> listNK;

    private NhanKhau selectedNhanKhau;

    private final NhanKhauDB nhankhaudb = new NhanKhauDB();

    public NhanKhau getSelectedNhanKhau() {
        return selectedNhanKhau;
    }

    @FXML
    void handleClicks(ActionEvent event) throws SQLException {
        if (event.getSource() == btnTim) {
            filterTable(input.getText());
        } else if (event.getSource() == btnChon) {
            if (selectedNhanKhau != null && !nhankhaudb.getCCCD(selectedNhanKhau.getId()).equals("")) {
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Không thể lưu");
                alert.setHeaderText("Vui lòng chọn lại chủ hộ");
                alert.setContentText("Lưu ý: Chủ hộ cần phải có CCCD.");
                alert.showAndWait();
            }
        } else if(event.getSource() == btnHuy){
            selectedNhanKhau = null;
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
    }

    private void filterTable(String input) {
        ObservableList<NhanKhau> listNK1 = getNhanKhauListKhongHoKhau();
        listNK1.removeIf(nhanKhau -> !nhanKhau.getHoTen().toLowerCase().contains(input.toLowerCase()));
        setTableData(listNK1);
    }

    private ObservableList<NhanKhau> getNhanKhauList() {
        NhanKhauDB nhankhauinDB = new NhanKhauDB();
        try {
            return nhankhauinDB.getListNhanKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<NhanKhau> getNhanKhauListKhongHoKhau() {
        ObservableList<NhanKhau> listNKKhongHoKhau = getNhanKhauList();
        listNKKhongHoKhau.removeIf(nhanKhau -> nhanKhau.getIdHoKhau() != 0);
        return listNKKhongHoKhau;
    }

    private void setTableData(ObservableList<NhanKhau> listNK) {
        table_id.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        table_hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        table_ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, LocalDate>("ngaySinh"));
        table_gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        table.setItems(listNK);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listNK = getNhanKhauListKhongHoKhau();
        setTableData(listNK);

        table.setOnMouseClicked(event -> {
            selectedNhanKhau = table.getSelectionModel().getSelectedItem();
        });
    }
}
