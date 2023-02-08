package QuanLyNhanKhau.services;

import QuanLyNhanKhau.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class Query {
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

    public PreparedStatement HoKhau(String soHoKhau, int idChuHo, int soNha, String ngo, String duong) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO HoKhau (`soHoKhau`, `idChuHo`,  "
                + "`soNha`, `ngo`, `duong`) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, soHoKhau);
        pstmt.setInt(2, idChuHo);
        pstmt.setInt(3, soNha);
        pstmt.setString(4, ngo);
        pstmt.setString(5, duong);
        pstmt.executeUpdate();
        return pstmt;
    }

    public void KhaiBao() throws SQLException {

    }

    public PreparedStatement KhaiTu(int idNhanKhau, String maGiayKhaiTu, String nguyenNhan, Object ngayQuaDoi, Object ngayKhaiTu,
                                    int idNguoiKhai) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO KhaiTu (`idNhanKhau`, `maGiayKhaiTu`,  "
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

    public PreparedStatement NhanKhau(String hoTen, Object ngaySinh, String gioiTinh, String noiSinh, String nguyenQuan,
                                      String danToc, String ngheNghiep, String noiLamViec) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO NhanKhau (`hoTen`, `ngaySinh`, `gioiTinh`, `noiSinh`, " +
                "`nguyenQuan`, `danToc`, `ngheNghiep`, `noiLamViec`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, hoTen);
        pstmt.setObject(2, ngaySinh);
        pstmt.setString(3, gioiTinh);
        pstmt.setString(4, noiSinh);
        pstmt.setString(5, nguyenQuan);
        pstmt.setString(6, danToc);
        pstmt.setString(7, ngheNghiep);
        pstmt.setString(8, noiLamViec);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement updateIDHoKhauCuaNhanKhau(int idNhanKhau, int idHoKhau, String quanHeVoiChuHo) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE NhanKhau SET idHoKhau = ?, quanHeVoiChuHo = ? WHERE id = ?");
        pstmt.setInt(1, idHoKhau);
        pstmt.setString(2, quanHeVoiChuHo);
        pstmt.setInt(3, idNhanKhau);
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

    public int getIDHoKhauBySoHoKhau(String soHoKhau) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM HoKhau WHERE soHoKhau = ?");
        pstmt.setString(1, soHoKhau);
        ResultSet rs = pstmt.executeQuery();
        int idHoKhau = -1;
        if (rs.next()) {
            idHoKhau = rs.getInt("id");
        }
        return idHoKhau;
    }

    public ObservableList<NhanKhau> getListNhanKhauBySoHoKhau(String soHoKhau) throws SQLException {
        ObservableList<NhanKhau> listNK = FXCollections.observableArrayList();
        int idHoKhau = getIDHoKhauBySoHoKhau(soHoKhau);
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM NhanKhau WHERE idHoKhau = ?");
        pstmt.setInt(1, idHoKhau);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            NhanKhau nk = new NhanKhau(rs.getInt("id"), idHoKhau, rs.getString("hoTen"), rs.getDate("ngaySinh").toLocalDate(),
                    rs.getString("gioiTinh"), rs.getString("noiSinh"), rs.getString("nguyenQuan"), rs.getString("danToc"),
                    rs.getString("ngheNghiep"), rs.getString("noiLamViec"), rs.getString("quanHeVoiChuHo"));
            listNK.add(nk);
        }
        return listNK;
    }


    public void deleteHoKhau(String soHoKhau) throws SQLException {
        String deleteCCCD = "DELETE FROM CCCD WHERE idNhanKhau IN (SELECT id FROM NhanKhau WHERE idHoKhau = (SELECT id FROM HoKhau WHERE soHoKhau = ?))";
        String deleteNhanKhau = "DELETE FROM NhanKhau WHERE idHoKhau = (SELECT id FROM HoKhau WHERE soHoKhau = ?)";
        String deleteHoKhau = "DELETE FROM HoKhau WHERE soHoKhau = ?";

        PreparedStatement pstmt1 = connection.prepareStatement(deleteCCCD);
        PreparedStatement pstmt2 = connection.prepareStatement(deleteNhanKhau);
        PreparedStatement pstmt3 = connection.prepareStatement(deleteHoKhau);

        pstmt1.setString(1, soHoKhau);
        pstmt2.setString(1, soHoKhau);
        pstmt3.setString(1, soHoKhau);

        pstmt1.executeUpdate();
        pstmt2.executeUpdate();
        pstmt3.executeUpdate();
    }

    public PreparedStatement addMacCovid(int idNhanKhau, String tinhTrangSK, String ketQuaTest, Object ngayMac
            , Object ngayKhoi) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO MacCOVID (`idNhanKhau`,`tinhTrangSK`, `ketQuaTest`, `ngayMac`, `ngayKhoi`) VALUES ( ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idNhanKhau);
        pstmt.setString(2, tinhTrangSK);
        pstmt.setString(3, ketQuaTest);
        pstmt.setObject(4, ngayMac);
        pstmt.setObject(5, ngayKhoi);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement deleteMacCovid(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM MacCOVID where id = ?",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement updateInfoMacCovid(int id, Object ngayMac, Object ngayKhoi, String tinhTrangSK, String ketQuaTest) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("Update MacCOVID set ngayMac =?, ngayKhoi = ?,  tinhTrangSK = ?, ketQuaTest = ?    where id = ?",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setObject(1, ngayMac);
        pstmt.setObject(2, ngayKhoi);
        pstmt.setString(3, tinhTrangSK);
        pstmt.setString(4, ketQuaTest);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
        return pstmt;
    }


}