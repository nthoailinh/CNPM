package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class TamTru {
    private int id;
    private int idNhanKhau;
    private String maGiayTamTru;
    private LocalDate batDau;
    private LocalDate ketThuc;
    private String lyDo;

    public TamTru(int id, int idNhanKhau, String maGiayTamTru, LocalDate batDau, LocalDate ketThuc, String lyDo) {
        this.id = id;
        this.idNhanKhau = idNhanKhau;
        this.maGiayTamTru = maGiayTamTru;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.lyDo = lyDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getMaGiayTamTru() {
        return maGiayTamTru;
    }

    public void setMaGiayTamTru(String maGiayTamTru) {
        this.maGiayTamTru = maGiayTamTru;
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

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
