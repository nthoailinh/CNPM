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

    public PreparedStatement KhaiTu(int idNhanKhau, String maGiayKhaiTu, String nguyenNhan, Object ngayQuaDoi, Object ngayKhaiTu,
                                    int idNguoiKhai) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO KhaiTu (`idNhanKhau`, `maGiayKhaiTu`, "
                        + "`nguyenNhan`, `ngayQuaDoi`, `ngayKhaiTu`, `idNguoiKhai`) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idNhanKhau);
            pstmt.setString(2, maGiayKhaiTu);
            pstmt.setObject(3, nguyenNhan);
            pstmt.setObject(4, ngayQuaDoi);
            pstmt.setObject(5, ngayKhaiTu);
            pstmt.setInt(6, idNguoiKhai);
            pstmt.executeUpdate();
            return pstmt;
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

    public PreparedStatement TamTru(int idNhanKhau, String maGiayTamTru, Object batDau, Object ketThuc, String lyDo)
            throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TamTru (`idNhanKhau`, `maGiayTamTru`, "
                        + "`batDau`, `ketThuc`, `lyDo`) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idNhanKhau);
        pstmt.setString(2, maGiayTamTru);
        pstmt.setObject(3, batDau);
        pstmt.setObject(4, ketThuc);
        pstmt.setString(5, lyDo);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement TamVang(int idNhanKhau, String maGiayTamVang, Object batDau, Object ketThuc,
                                     String noiTamTru, String lyDo) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TamVang (`idNhanKhau`, `maGiayTamVang`, "
                + "`batDau`, `ketThuc`, `noiTamTru`, `lyDo`) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idNhanKhau);
        pstmt.setString(2, maGiayTamVang);
        pstmt.setObject(3, batDau);
        pstmt.setObject(4, ketThuc);
        pstmt.setString(5, noiTamTru);
        pstmt.setString(6, lyDo);
        pstmt.executeUpdate();
        return pstmt;
    }
}