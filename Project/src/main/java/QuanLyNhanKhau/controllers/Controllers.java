package QuanLyNhanKhau.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

public class Controllers {

    @FXML
    private Button btnCOVID;

    @FXML
    private Button btnDangXuat;

    @FXML
    private Button btnHoKhau;

    @FXML
    private Button btnNhanKhau;

    @FXML
    private Button btnThongKe;

    @FXML
    private Button btnTrangChu;

    @FXML
    private BorderPane contentHoKhau;

    @FXML
    private BorderPane contentNhanKhau;

    @FXML
    private BorderPane contentThongKe;

    @FXML
    private GridPane contentTrangChu;

    @FXML
    void handleClicks(ActionEvent event) {
        if(event.getSource() == btnTrangChu){
            resetVisible();
            contentTrangChu.setVisible(true);
        }
        else if(event.getSource() == btnNhanKhau){
            resetVisible();
            contentNhanKhau.setVisible(true);
        }
        else if(event.getSource() == btnHoKhau){
            resetVisible();
            contentHoKhau.setVisible(true);
        }
        else if(event.getSource() == btnThongKe){
            resetVisible();
            contentThongKe.setVisible(true);
        }
        else if(event.getSource() == btnCOVID);
        else if(event.getSource() == btnDangXuat);
    }

    void resetVisible(){
        contentTrangChu.setVisible(false);
        contentNhanKhau.setVisible(false);
        contentHoKhau.setVisible(false);
        contentThongKe.setVisible(false);
    }

}
