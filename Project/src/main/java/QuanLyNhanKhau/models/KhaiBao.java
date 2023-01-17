package QuanLyNhanKhau.models;

import java.util.Date;

public class KhaiBao {
    private int id;
    private int idMacCOVID;
    private Date ngayKhaiBao;
    private Date thoiDiemTest;
    private String hinhThucTest;
    private char ketQuaTest;
    private String tinhTrangSucKhoe;

    public KhaiBao(int id, int idMacCOVID, Date ngayKhaiBao, Date thoiDiemTest, String hinhThucTest, char ketQuaTest, String tinhTrangSucKhoe) {
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

    public Date getNgayKhaiBao() {
        return ngayKhaiBao;
    }

    public void setNgayKhaiBao(Date ngayKhaiBao) {
        this.ngayKhaiBao = ngayKhaiBao;
    }

    public Date getThoiDiemTest() {
        return thoiDiemTest;
    }

    public void setThoiDiemTest(Date thoiDiemTest) {
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

