package QuanLyNhanKhau.models;

public class HoKhau {
    private int id;
    private String soHoKhau;
    private int idChuHo;
    private String soNha;
    private String ngo;
    private String duong;

    public HoKhau(int id, String soHoKhau, int idChuHo, String soNha, String ngo, String duong) {
        this.id = id;
        this.soHoKhau = soHoKhau;
        this.idChuHo = idChuHo;
        this.soNha = soNha;
        this.ngo = ngo;
        this.duong = duong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoHoKhau() {
        return soHoKhau;
    }

    public void setSoHoKhau(String soHoKhau) {
        this.soHoKhau = soHoKhau;
    }

    public int getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(int idChuHo) {
        this.idChuHo = idChuHo;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getNgo() {
        return ngo;
    }

    public void setNgo(String ngo) {
        this.ngo = ngo;
    }

    public String getDuong() {
        return duong;
    }

    public void setDuong(String duong) {
        this.duong = duong;
    }
}

