package QuanLyNhanKhau.controllers.nhankhau;

import QuanLyNhanKhau.models.NhanKhau;
import QuanLyNhanKhau.services.NhanKhauDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class XemThongTinController implements Initializable {

    @FXML
    private Label cccd;

    @FXML
    private Label danToc;

    @FXML
    private Label gioiTinh;

    @FXML
    private Label hoTen;

    @FXML
    private Label ngayCap;

    @FXML
    private Label ngaySinh;

    @FXML
    private Label ngheNghiep;

    @FXML
    private Label nguyenQuan;

    @FXML
    private Label noiCap;

    @FXML
    private Label noiLamViec;

    @FXML
    private Label noiSinh;

    private NhanKhau nhankhau;

    public void setNhankhau(NhanKhau nhankhau) {
        this.nhankhau = nhankhau;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NhanKhauDB nhankhauDB = new NhanKhauDB();
        PreparedStatement pstmt = null;
        try {
            pstmt = nhankhauDB.getFullCCCD(nhankhau.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String cccd_text = "";
        String ngaycap = "";
        String noicap = "";
        try {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if(rs.getInt("idNhanKhau") != nhankhau.getId()) continue;
                cccd_text = rs.getString("cccd");
                ngaycap = rs.getString("ngaycap");
                noicap = rs.getString("noicap");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cccd.setText(cccd_text);
        danToc.setText(nhankhau.getDanToc());
        gioiTinh.setText(nhankhau.getGioiTinh());
        hoTen.setText(nhankhau.getHoTen());
        ngayCap.setText(ngaycap);
        ngaySinh.setText(nhankhau.getNgaySinh().toString());
        ngheNghiep.setText(nhankhau.getNgheNghiep());
        nguyenQuan.setText(nhankhau.getNguyenQuan());
        noiCap.setText(noicap);
        noiLamViec.setText(nhankhau.getNoiLamViec());
        noiSinh.setText(nhankhau.getNoiSinh());
    }

}
