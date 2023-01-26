package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class NhanKhau {
    private int id;
    private int idHoKhau;
    private String hoTen;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String noiSinh;
    private String nguyenQuan;
    private String danToc;
    private String ngheNghiep;
    private String noiLamViec;
    private String quanHeVoiChuHo;

    public NhanKhau() {}
    public NhanKhau(String hoTen, LocalDate ngaySinh, String quanHeVoiChuHo){
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }
    public NhanKhau(int id, int idHoKhau, String hoTen, LocalDate ngaySinh, String gioiTinh, String noiSinh, String nguyenQuan, String danToc, String ngheNghiep, String noiLamViec, String quanHeVoiChuHo) {
        this.id = id;
        this.idHoKhau = idHoKhau;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.noiSinh = noiSinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

    public String getQuanHeVoiChuHo() {
        return quanHeVoiChuHo;
    }

    public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }
}