package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class KhaiBao {
    private int id;
    private int idMacCOVID;
    private LocalDate ngayKhaiBao;
    private LocalDate thoiDiemTest;
    private String hinhThucTest;
    private char ketQuaTest;
    private String tinhTrangSucKhoe;

    public KhaiBao(int id, int idMacCOVID, LocalDate ngayKhaiBao, LocalDate thoiDiemTest, String hinhThucTest, char ketQuaTest, String tinhTrangSucKhoe) {
        this.id = id;
        this.idMacCOVID = idMacCOVID;
        this.ngayKhaiBao = ngayKhaiBao;
        this.thoiDiemTest = thoiDiemTest;
        this.hinhThucTest = hinhThucTest;
        this.ketQuaTest = ketQuaTest;
        this.tinhTrangSucKhoe = tinhTrangSucKhoe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMacCOVID() {
        return idMacCOVID;
    }

    public void setIdMacCOVID(int idMacCOVID) {
        this.idMacCOVID = idMacCOVID;
    }

    public LocalDate getNgayKhaiBao() {
        return ngayKhaiBao;
    }

    public void setNgayKhaiBao(LocalDate ngayKhaiBao) {
        this.ngayKhaiBao = ngayKhaiBao;
    }

    public LocalDate getThoiDiemTest() {
        return thoiDiemTest;
    }

    public void setThoiDiemTest(LocalDate thoiDiemTest) {
        this.thoiDiemTest = thoiDiemTest;
    }

    public String getHinhThucTest() {
        return hinhThucTest;
    }

    public void setHinhThucTest(String hinhThucTest) {
        this.hinhThucTest = hinhThucTest;
    }

    public char getKetQuaTest() {
        return ketQuaTest;
    }

    public void setKetQuaTest(char ketQuaTest) {
        this.ketQuaTest = ketQuaTest;
    }

    public String getTinhTrangSucKhoe() {
        return tinhTrangSucKhoe;
    }

    public void setTinhTrangSucKhoe(String tinhTrangSucKhoe) {
        this.tinhTrangSucKhoe = tinhTrangSucKhoe;
    }
}

