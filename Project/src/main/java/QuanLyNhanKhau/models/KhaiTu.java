package QuanLyNhanKhau.models;

import java.util.Date;

public class KhaiTu {
    private int idNhanKhau;
    private String nguyenNhan;
    private Date ngayQuaDoi;
    private Date ngayKhaiTu;

    public KhaiTu(int idNhanKhau, String nguyenNhan, Date ngayQuaDoi, Date ngayKhaiTu) {
        this.idNhanKhau = idNhanKhau;
        this.nguyenNhan = nguyenNhan;
        this.ngayQuaDoi = ngayQuaDoi;
        this.ngayKhaiTu = ngayKhaiTu;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getNguyenNhan() {
        return nguyenNhan;
    }

    public void setNguyenNhan(String nguyenNhan) {
        this.nguyenNhan = nguyenNhan;
    }

    public Date getNgayQuaDoi() {
        return ngayQuaDoi;
    }

    public void setNgayQuaDoi(Date ngayQuaDoi) {
        this.ngayQuaDoi = ngayQuaDoi;
    }

    public Date getNgayKhaiTu() {
        return ngayKhaiTu;
    }

    public void setNgayKhaiTu(Date ngayKhaiTu) {
        this.ngayKhaiTu = ngayKhaiTu;
    }
}

