package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class KhaiTu {
    private int idNhanKhau;
    private String maGiayKhaiTu;
    private String nguyenNhan;
    private LocalDate ngayQuaDoi;
    private LocalDate ngayKhaiTu;
    private int idNguoiKhai;

    public KhaiTu(int idNhanKhau, String maGiayKhaiTu, String nguyenNhan, LocalDate ngayQuaDoi, LocalDate ngayKhaiTu, int idNguoiKhai) {
        this.idNhanKhau = idNhanKhau;
        this.maGiayKhaiTu = maGiayKhaiTu;
        this.nguyenNhan = nguyenNhan;
        this.ngayQuaDoi = ngayQuaDoi;
        this.ngayKhaiTu = ngayKhaiTu;
        this.idNguoiKhai = idNguoiKhai;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getMaGiayKhaiTu() {
        return maGiayKhaiTu;
    }

    public void setMaGiayKhaiTu(String maGiayKhaiTu) {
        this.maGiayKhaiTu = maGiayKhaiTu;
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

    public int getIdNguoiKhai() {
        return idNguoiKhai;
    }

    public void setIdNguoiKhai(int idNguoiKhai) {
        this.idNguoiKhai = idNguoiKhai;
    }
}

