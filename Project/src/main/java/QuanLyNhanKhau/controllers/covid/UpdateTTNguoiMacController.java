package QuanLyNhanKhau.controllers.covid;
import QuanLyNhanKhau.controllers.tables.CovidTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.MacCOVID;
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
    private TableColumn<MacCOVID, String> ngayMac;
    @FXML
    private TableColumn<MacCOVID, String> ngayTest;
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
    private Button timKiemnguoiCapNhat;

    @FXML
    private TableColumn<MacCOVID, String> ketQuaTest;

    @FXML
    private ChoiceBox<String> updateTinhTrangSK;
    @FXML
    private TextField updateKetQuaTest;

    @FXML
    private TableView<MacCOVID> tableNguoiMac;

    @FXML
    private Label hoTenNguoiMac;

    @FXML
    private Button btnCapNhat;

    @FXML
    private TableColumn<MacCOVID, String> hoTen;

    @FXML
    private TableColumn<MacCOVID, String> ngayKhoi;
    @FXML
    private DatePicker updateNgayTest;
    @FXML
    private DatePicker updateNgayKhaiBao;
    @FXML
    private TextField updateHinhThucTest;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTinhTrangSK.setValue("");
        ObservableList<String> listChoice = updateTinhTrangSK.getItems();
        listChoice.add("duong tinh");
        listChoice.add("am tinh");

        CovidDB covidinDB = new CovidDB();
        ObservableList<MacCOVID> listCovid = null;
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
        ngayTest.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("ngayTest"));
        ngayKhaiBao.setCellValueFactory(new PropertyValueFactory<MacCOVID, String>("ngayKhaiBao"));
        tableNguoiMac.setItems(listCovid);

        tableNguoiMac.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    MacCOVID tmp = tableNguoiMac.getSelectionModel().getSelectedItem();
                    hoTenNguoiMac.setText(tmp.getHoTen());
                    updateNgayMac.setValue(LocalDate.parse(tmp.getNgayMac()));
                    btnCapNhat.setVisible(true);
                    if(tmp.getNgayKhoi().equals("Chưa khỏi") == false) {
                        updateNgayKhoi.setValue(LocalDate.parse(tmp.getNgayKhoi()));
                    }
                    updateTinhTrangSK.setValue(tmp.getTinhTrangSK());
                    updateKetQuaTest.setText(tmp.getKetQuaTest());
                    updateNgayTest.setValue(LocalDate.parse(tmp.getNgayTest()));
                    updateHinhThucTest.setText(tmp.getHinhThucTest());
                    btnCapNhat.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent1) {
                                //UPdate database
                                Update update = new Update();
                                try {
                                    update.updateInfoMacCovid( tmp.getId(), updateNgayMac.getValue(),updateNgayKhoi.getValue(), updateTinhTrangSK.getValue(), updateKetQuaTest.getText(), updateHinhThucTest.getText(), updateNgayTest.getValue(), updateNgayKhaiBao.getValue() );
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
