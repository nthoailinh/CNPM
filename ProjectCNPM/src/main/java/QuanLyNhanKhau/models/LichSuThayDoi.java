package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class LichSuThayDoi {
    private String soHoKhau;
    private String tenNhanKhau;
    private LocalDate ngayThayDoi;
    private String noiDungThayDoi;

    public LichSuThayDoi(){

    }

    public LichSuThayDoi(String soHoKhau, String tenNhanKhau, LocalDate ngayThayDoi, String noiDungThayDoi) {
        this.soHoKhau = soHoKhau;
        this.tenNhanKhau = tenNhanKhau;
        this.ngayThayDoi = ngayThayDoi;
        this.noiDungThayDoi = noiDungThayDoi;
    }

    public String getSoHoKhau() {
        return soHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.soHoKhau = maHoKhau;
    }

    public String getTenNhanKhau() {
        return tenNhanKhau;
    }

    public void setTenNhanKhau(String tenNhanKhau) {
        this.tenNhanKhau = tenNhanKhau;
    }

    public LocalDate getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getNoiDungThayDoi() {
        return noiDungThayDoi;
    }

    public void setNoiDungThayDoi(String noiDungThayDoi) {
        this.noiDungThayDoi = noiDungThayDoi;
    }
}
