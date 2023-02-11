package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.tables.CovidTable;
import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.CovidDB;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThongKeYTeController implements Initializable {

    @FXML
    private TableColumn<CovidTable, Integer> ID;

    @FXML
    private TextField ageEndTextField;

    @FXML
    private TextField ageStartTextField;

    @FXML
    private BorderPane contentThongKe;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private TableColumn<CovidTable, String> gioiTinh;

    @FXML
    private TableColumn<CovidTable, String> hoTen;

    @FXML
    private TableColumn<CovidTable, LocalDate> ngaySinh;

    @FXML
    private Button showStatisticsButton;

    @FXML
    private Label sumStatisticsLabel;

    @FXML
    private TableView<CovidTable> table;

    @FXML
    private ChoiceBox<String> tinhTrangChoiceBox;

    @FXML
    private TableColumn<CovidTable, String> ngayMac;

    @FXML
    private TableColumn<CovidTable, String> ngayKhoi;

    @FXML
    private TableColumn<CovidTable, String> tinhTrangSucKhoe;

    @FXML
    private TableColumn<CovidTable, String> ketQuaTest;

    @FXML
    private DatePicker dateStartPicker;

    @FXML
    private DatePicker dateEndPicker;

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == showStatisticsButton) {
            System.out.println(genderChoiceBox.getValue());
            CovidDB covidDB = new CovidDB();
            ObservableList<CovidTable> list = null;
            String gender = genderChoiceBox.getValue();
            String ageStart = ageStartTextField.getText();
            String ageEnd = ageEndTextField.getText();
            String tinhTrang = tinhTrangChoiceBox.getValue();
            String dateStart = "";
            String dateEnd = "";
            if (dateStartPicker.getValue() != null || dateEndPicker.getValue() != null) {
                if (dateStartPicker.getValue() != null) {
                    dateStart = dateStartPicker.getValue().toString();
                }
                if (dateEndPicker.getValue() != null) {
                    dateEnd = dateEndPicker.getValue().toString();
                }

                try {
                    list = covidDB.getListCovidTable(gender, ageStart, ageEnd, tinhTrang, dateStart, dateEnd);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                ID.setCellValueFactory(new PropertyValueFactory<CovidTable, Integer>("id"));
                hoTen.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("hoTen"));
                ngayMac.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayMac"));
                ngayKhoi.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayKhoi"));
                tinhTrangSucKhoe.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("tinhTrangSK"));
                ketQuaTest.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ketQuaTest"));
                table.setItems(list);

                sumStatisticsLabel.setText(list.size() + " người");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Cần chọn ngày bắt đầu / kết thúc!");
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderChoiceBox.setValue("<lựa chọn>");
        ObservableList<String> listChoice = genderChoiceBox.getItems();
        listChoice.add("<lựa chọn>");
        listChoice.add("Nam");
        listChoice.add("Nữ");

        tinhTrangChoiceBox.setValue("Mắc bệnh");
        ObservableList<String> listChoice2 = tinhTrangChoiceBox.getItems();
        listChoice2.add("Mắc bệnh");
        listChoice2.add("Khỏi bệnh");

        CovidDB covidDB = new CovidDB();
        ObservableList<CovidTable> list = null;
        try {
            list = covidDB.getListCovidTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ID.setCellValueFactory(new PropertyValueFactory<CovidTable, Integer>("id"));
        hoTen.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("hoTen"));
        ngayMac.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayMac"));
        ngayKhoi.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayKhoi"));
        tinhTrangSucKhoe.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("tinhTrangSK"));
        ketQuaTest.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ketQuaTest"));

        table.setItems(list);

        sumStatisticsLabel.setText(list.size() + " người");
    }
}
