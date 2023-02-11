package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class TamVang {
    private int id;
    private int idNhanKhau;
    private String maGiayTamVang;
    private LocalDate batDau;
    private LocalDate ketThuc;
    private String noiTamTru;
    private String lyDo;

    public TamVang(int id, int idNhanKhau, String maGiayTamVang, LocalDate batDau, LocalDate ketThuc, String noiTamTru, String lyDo) {
        this.id = id;
        this.idNhanKhau = idNhanKhau;
        this.maGiayTamVang = maGiayTamVang;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.noiTamTru = noiTamTru;
        this.lyDo = lyDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getidNhanKhau() {
        return idNhanKhau;
    }

    public void setidNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getMaGiayTamVang() {
        return maGiayTamVang;
    }

    public void setMaGiayTamVang(String maGiayTamVang) {
        this.maGiayTamVang = maGiayTamVang;
    }

    public LocalDate getBatDau() {
        return batDau;
    }

    public void setBatDau(LocalDate batDau) {
        this.batDau = batDau;
    }

    public LocalDate getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(LocalDate ketThuc) {
        this.ketThuc = ketThuc;
    }

    public String getNoiTamTru() {
        return noiTamTru;
    }

    public void setNoiTamTru(String noiTamTru) {
        this.noiTamTru = noiTamTru;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
