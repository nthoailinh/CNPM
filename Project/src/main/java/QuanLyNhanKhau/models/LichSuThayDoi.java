package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class LichSuThayDoi {
    private int id;
    private int idHoKhau;
    private LocalDate ngayThayDoi;
    private String bangThayDoi;
    private int idBangThayDoi;
    private String thongTinThayDoi;
    private String thayDoiTu;
    private String thayDoiThanh;
    private String nguoiThucHien;

    public LichSuThayDoi(int id, int idHoKhau, LocalDate ngayThayDoi, String bangThayDoi, int idBangThayDoi, String thongTinThayDoi, String thayDoiTu, String thayDoiThanh, String nguoiThucHien) {
        this.id = id;
        this.idHoKhau = idHoKhau;
        this.ngayThayDoi = ngayThayDoi;
        this.bangThayDoi = bangThayDoi;
        this.idBangThayDoi = idBangThayDoi;
        this.thongTinThayDoi = thongTinThayDoi;
        this.thayDoiTu = thayDoiTu;
        this.thayDoiThanh = thayDoiThanh;
        this.nguoiThucHien = nguoiThucHien;
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

    public LocalDate getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getBangThayDoi() {
        return bangThayDoi;
    }

    public void setBangThayDoi(String bangThayDoi) {
        this.bangThayDoi = bangThayDoi;
    }

    public int getIdBangThayDoi() {
        return idBangThayDoi;
    }

    public void setIdBangThayDoi(int idBangThayDoi) {
        this.idBangThayDoi = idBangThayDoi;
    }

    public String getThongTinThayDoi() {
        return thongTinThayDoi;
    }

    public void setThongTinThayDoi(String thongTinThayDoi) {
        this.thongTinThayDoi = thongTinThayDoi;
    }

    public String getThayDoiTu() {
        return thayDoiTu;
    }

    public void setThayDoiTu(String thayDoiTu) {
        this.thayDoiTu = thayDoiTu;
    }

    public String getThayDoiThanh() {
        return thayDoiThanh;
    }

    public void setThayDoiThanh(String thayDoiThanh) {
        this.thayDoiThanh = thayDoiThanh;
    }

    public String getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(String nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }
}
