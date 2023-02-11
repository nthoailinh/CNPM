package QuanLyNhanKhau.controllers.hokhau;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.services.HoKhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChuyenHoKhauController implements Initializable {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnTim;

    @FXML
    private Button btnXoa;

    @FXML
    private TableColumn<HoKhauTable, String> table_DiaChi;

    @FXML
    private TableColumn<HoKhauTable, String> table_HoTenChuHo;

    @FXML
    private TableColumn<HoKhauTable, String> table_soHoKhau;

    @FXML
    private TextField input;

    @FXML
    private Label soHoKhau;

    @FXML
    private Label diaChi;

    @FXML
    private TableView<HoKhauTable> table;

    @FXML
    private Label tenChuHo;

    private HoKhauDB hokhauDB = new HoKhauDB();

    private ObservableList<HoKhauTable> getHoKhauList() {
        HoKhauDB hokhauinDB = new HoKhauDB();
        try {
            return hokhauinDB.getListHoKhauTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleClicks(ActionEvent event) throws SQLException {
        if (event.getSource() == btnTim) {
            ObservableList<HoKhauTable> listHK = getHoKhauList();
            listHK.removeIf(HoKhauTable -> !HoKhauTable.getSoHoKhau().toLowerCase().contains(input.getText().toLowerCase()));
            table.setItems(listHK);
        } else {
            if (event.getSource() == btnXoa) {
                HoKhauTable selectedHoKhauTable = table.getSelectionModel().getSelectedItem();
                if (selectedHoKhauTable != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Xác nhận");
                    alert.setHeaderText("Bạn có chắc chắn muốn xóa hộ khẩu không ?");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        hokhauDB.deleteHoKhau(selectedHoKhauTable.getSoHoKhau());
                        ((Node) event.getSource()).getScene().getWindow().hide();
                    }
                }
            }
            else{
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setOnMouseClicked(event -> {
            HoKhauTable selectedHoKhauTable = table.getSelectionModel().getSelectedItem();
            soHoKhau.setText(selectedHoKhauTable.getSoHoKhau());
            tenChuHo.setText(selectedHoKhauTable.getHoTen());
            diaChi.setText(selectedHoKhauTable.getDiaChi());
        });

        table_soHoKhau.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("soHoKhau"));
        table_HoTenChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("hoTen"));
        table_DiaChi.setCellValueFactory(new PropertyValueFactory<HoKhauTable, String>("diaChi"));
        table.setItems(getHoKhauList());
    }
}
