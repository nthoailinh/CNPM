package QuanLyNhanKhau.models;

public class HoKhau {
    private int id;
    private int soHoKhau;
    private int idChuHo;
    private int soNha;
    private String ngo;
    private String duong;

    public HoKhau(int id, int soHoKhau, int idChuHo, int soNha, String ngo, String duong) {
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

    public int getSoHoKhau() {
        return soHoKhau;
    }

    public void setSoHoKhau(int soHoKhau) {
        this.soHoKhau = soHoKhau;
    }

    public int getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(int idChuHo) {
        this.idChuHo = idChuHo;
    }

    public int getSoNha() {
        return soNha;
    }

    public void setSoNha(int soNha) {
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

