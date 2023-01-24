package QuanLyNhanKhau.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    Connection connection = MySQL.getConnection();

    public PreparedStatement CCCD(String cccd, int idNhanKhau, Object ngayCap, String noiCap) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CCCD (cccd, idNhanKhau, ngayCap, noiCap) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, cccd);
        pstmt.setInt(2, idNhanKhau);
        pstmt.setObject(3, ngayCap);
        pstmt.setString(4, noiCap);
        pstmt.executeUpdate();
        return pstmt;
    }

    public void HoKhau() throws SQLException {

    }

    public void KhaiBao() throws SQLException {

    }

    public void KhaiTu() throws SQLException {

    }

    public void LichSuThayDoi() throws SQLException {

    }

    public void MacCOVID() throws SQLException {

    }

    public PreparedStatement NhanKhau(int idHoKhau, String hoTen, Object ngaySinh, String gioiTinh, String noiSinh, String nguyenQuan,
                                      String danToc, String ngheNghiep, String noiLamViec, String quanHeVoiChuHo) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO NhanKhau (`idHoKhau`, `hoTen`, `ngaySinh`, `gioiTinh`, `noiSinh`, " +
                "`nguyenQuan`, `danToc`, `ngheNghiep`, `noiLamViec`, `quanHeVoiChuHo`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idHoKhau);
        pstmt.setString(2, hoTen);
        pstmt.setObject(3, ngaySinh);
        pstmt.setString(4, gioiTinh);
        pstmt.setString(5, noiSinh);
        pstmt.setString(6, nguyenQuan);
        pstmt.setString(7, danToc);
        pstmt.setString(8, ngheNghiep);
        pstmt.setString(9, noiLamViec);
        pstmt.setString(10, quanHeVoiChuHo);
        pstmt.executeUpdate();
        return pstmt;
    }

    public void TamTru() throws SQLException {

    }

    public void TamVang() throws SQLException {

    }
}