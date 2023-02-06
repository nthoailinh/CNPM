package QuanLyNhanKhau.controllers.covid;
import QuanLyNhanKhau.controllers.tables.CovidTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.CovidDB;
import QuanLyNhanKhau.services.Update;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateTTNguoiMacController implements Initializable {
    @FXML
    private DatePicker updateNgayMac;

    @FXML
    private TableColumn<CovidTable, String> ngayMac;

    @FXML
    private DatePicker updateNgayKhoi;

    @FXML
    private TableColumn<CovidTable, Integer> IDNguoiMac;

    @FXML
    private TableColumn<CovidTable, String> tinhTrangSK;

    @FXML
    private Button timKiemnguoiCapNhat;

    @FXML
    private TableColumn<CovidTable, String> ketQuaTest;

    @FXML
    private TextField updateTinhTrangSK;
    @FXML
    private TextField updateKetQuaTest;

    @FXML
    private TableView<CovidTable> tableNguoiMac;

    @FXML
    private Label hoTenNguoiMac;

    @FXML
    private Button btnCapNhat;

    @FXML
    private TableColumn<CovidTable, String> hoTen;

    @FXML
    private TableColumn<CovidTable, String> ngayKhoi;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CovidDB covidinDB = new CovidDB();
        ObservableList<CovidTable> listCovid = null;
        try {
            listCovid = covidinDB.getListCovidTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        IDNguoiMac.setCellValueFactory(new PropertyValueFactory<CovidTable, Integer>("id"));
        hoTen.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("hoTen"));
        ngayMac.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayMac"));
        ngayKhoi.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayKhoi"));
        tinhTrangSK.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("tinhTrangSK"));
        ketQuaTest.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ketQuaTest"));
        tableNguoiMac.setItems(listCovid);

        tableNguoiMac.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    CovidTable tmp = tableNguoiMac.getSelectionModel().getSelectedItem();
                    hoTenNguoiMac.setText(tmp.getHoTen());
                    updateNgayMac.setValue(LocalDate.parse(tmp.getNgayMac()));
                    btnCapNhat.setVisible(true);
                    if(tmp.getNgayKhoi().equals("Chưa khỏi") == false) {
                        updateNgayKhoi.setValue(LocalDate.parse(tmp.getNgayKhoi()));
                    }
                    updateTinhTrangSK.setText(tmp.getTinhTrangSK());
                    updateKetQuaTest.setText(tmp.getKetQuaTest());
                    btnCapNhat.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent1) {
                                //UPdate database
                                Update update = new Update();
                                try {
                                    update.updateInfoMacCovid( tmp.getId(), updateNgayMac.getValue(),updateNgayKhoi.getValue(), updateTinhTrangSK.getText(), updateKetQuaTest.getText() );
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            ((Node) mouseEvent1.getSource()).getScene().getWindow().hide();
                        }
                    });
                }
            }
        });
    }
}
