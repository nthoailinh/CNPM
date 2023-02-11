package QuanLyNhanKhau.controllers;

import QuanLyNhanKhau.controllers.tables.CovidTable;
import QuanLyNhanKhau.services.CovidDB;
import QuanLyNhanKhau.views.Windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class COVIDController implements Initializable {

    private final CovidDB covidDB = new CovidDB();

    @FXML
    private TableColumn<CovidTable, Integer> ID;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button capNhatTT;

    @FXML
    private BorderPane contentCovid;

    @FXML
    private TableColumn<CovidTable, String> hoTen;

    @FXML
    private TableColumn<CovidTable, String> ketQuaTest;

    @FXML
    private TableColumn<CovidTable, String> ngayKhoi;

    @FXML
    private TableColumn<CovidTable, String> ngayMac;

    @FXML
    private TextField hoTenInput;

    @FXML
    private TableView<CovidTable> table;

    @FXML
    private Button themNguoiMac;

    @FXML
    private TableColumn<CovidTable, String> tinhTrangSucKhoe;

    @FXML
    private Button xoaNguoiMac;

    private ObservableList<CovidTable> listCovid;

    @FXML
    void handleClicks(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == themNguoiMac) {
            Parent root = Windows.getRoot("covid/themnguoimac.fxml");
            Scene scene = new Scene(root, 860, 740);
            Stage stage = new Stage();
            stage.setTitle("Thêm người mắc");
            stage.setScene(scene);
            stage.setOnHidden((e) -> populateTable());
            stage.show();
        } else if (event.getSource() == xoaNguoiMac) {
            CovidTable tmp = table.getSelectionModel().getSelectedItem();
            if (tmp != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có muốn xóa không?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    covidDB.deleteMacCovid(tmp.getId());
                    table.getItems().remove(tmp);
                }
            }
        } else if (event.getSource() == capNhatTT) {
            Parent root = Windows.getRoot("covid/capnhatnguoimac.fxml");
            Scene scene = new Scene(root, 1150, 800);
            Stage stage = new Stage();
            stage.setTitle("Thêm người mắc");
            stage.setScene(scene);
            stage.setOnHidden((e) -> populateTable());
            stage.show();
        } else if (event.getSource() == btnTimKiem) {
            ObservableList<CovidTable> list_search = FXCollections.observableArrayList();
            String inputHoTen = hoTenInput.getText();
            for (CovidTable covidTable : listCovid) {
                if (covidTable.getHoTen().toLowerCase().contains(inputHoTen.toLowerCase())) {
                    list_search.add(covidTable);
                }
            }
            table.setItems(list_search);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }

    public void populateTable() {
        CovidDB covidinDB = new CovidDB();
        try {
            listCovid = covidinDB.getListCovidTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ID.setCellValueFactory(new PropertyValueFactory<CovidTable, Integer>("id"));
        hoTen.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("hoTen"));
        ngayMac.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayMac"));
        ngayKhoi.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ngayKhoi"));
        tinhTrangSucKhoe.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("tinhTrangSK"));
        ketQuaTest.setCellValueFactory(new PropertyValueFactory<CovidTable, String>("ketQuaTest"));
        table.setItems(listCovid);
    }
}
