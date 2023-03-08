package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThongKeToDanPhoController implements Initializable {

    @FXML
    private TableColumn<NhanKhauTable, Integer> ID;

    @FXML
    private TextField ageEndTextField;

    @FXML
    private TextField ageStartTextField;

    @FXML
    private BorderPane contentThongKe;

    @FXML
    private TableColumn<NhanKhauTable, String> diaChi;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private TableColumn<NhanKhauTable, String> gioiTinh;

    @FXML
    private TableColumn<NhanKhauTable, String> hoTen;


    @FXML
    private DatePicker dateEndPicker;

    @FXML
    private DatePicker dateStartPicker;

    @FXML
    private TableColumn<NhanKhauTable, LocalDate> ngaySinh;

    @FXML
    private Button showStatisticsButton;

    @FXML
    private Label sumStatisticsLabel;

    @FXML
    private TableView<NhanKhauTable> table;

    @FXML
    private ChoiceBox<String> tinhTrangChoiceBox;

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == showStatisticsButton) {
            System.out.println(genderChoiceBox.getValue());
            NhanKhauDB nhankhauinDB = new NhanKhauDB();
            ObservableList<NhanKhauTable> listNK = null;
            String gender = genderChoiceBox.getValue();
            String ageStart = ageStartTextField.getText();
            String ageEnd = ageEndTextField.getText();
            String tinhTrang = tinhTrangChoiceBox.getValue();
            String dateStart = "";
            String dateEnd = "";
            if (dateStartPicker.getValue() != null) {
                dateStart = dateStartPicker.getValue().toString();
            }
            if (dateEndPicker.getValue() != null) {
                dateEnd = dateEndPicker.getValue().toString();
            }

            System.out.println(dateStart);

            try {
                listNK = nhankhauinDB.getListNhanKhau(gender, ageStart, ageEnd, tinhTrang, dateStart, dateEnd);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ID.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, Integer>("id"));
            hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("hoTen"));
            ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, LocalDate>("ngaySinh"));
            gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("gioiTinh"));
            diaChi.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("diaChi"));
            table.setItems(listNK);

            sumStatisticsLabel.setText(listNK.size() + " nhân khẩu");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderChoiceBox.setValue("<lựa chọn>");
        ObservableList<String> listChoice = genderChoiceBox.getItems();
        listChoice.add("<lựa chọn>");
        listChoice.add("Nam");
        listChoice.add("Nữ");

        tinhTrangChoiceBox.setValue("Có hộ khẩu trên địa bàn");
        ObservableList<String> listChoice2 = tinhTrangChoiceBox.getItems();
        listChoice2.add("Có hộ khẩu trên địa bàn");
        listChoice2.add("Tạm trú");
        listChoice2.add("Tạm vắng");

        NhanKhauDB nhankhauinDB = new NhanKhauDB();
        ObservableList<NhanKhauTable> listNK = null;
        try {
            listNK = nhankhauinDB.getListNhanKhauTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ID.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, Integer>("id"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("hoTen"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, LocalDate>("ngaySinh"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("gioiTinh"));
        diaChi.setCellValueFactory(new PropertyValueFactory<NhanKhauTable, String>("diaChi"));
        table.setItems(listNK);

        sumStatisticsLabel.setText(listNK.size() + " nhân khẩu");
    }
}
