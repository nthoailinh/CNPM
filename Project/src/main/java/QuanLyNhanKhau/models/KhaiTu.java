package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class KhaiTu {
    private int idNhanKhau;
    private String nguyenNhan;
    private LocalDate ngayQuaDoi;
    private LocalDate ngayKhaiTu;

    public KhaiTu(int idNhanKhau, String nguyenNhan, LocalDate ngayQuaDoi, LocalDate ngayKhaiTu) {
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

    public LocalDate getNgayQuaDoi() {
        return ngayQuaDoi;
    }

    public void setNgayQuaDoi(LocalDate ngayQuaDoi) {
        this.ngayQuaDoi = ngayQuaDoi;
    }

    public LocalDate getNgayKhaiTu() {
        return ngayKhaiTu;
    }

    public void setNgayKhaiTu(LocalDate ngayKhaiTu) {
        this.ngayKhaiTu = ngayKhaiTu;
    }
}

