package model;

public abstract class Nguoi {
    private String ma;
    private String ho;
    private String ten;
    private String gioiTinh;
    private String SDT;

    public Nguoi() {
        ma = "";
        ho = "";
        ten = "";
        gioiTinh = "";
        SDT = "";
    }

    public Nguoi(String ma, String ho,String ten, String gioiTinh, String SDT) {
        this.ma = ma;
        this.ho=ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.SDT = SDT;
    }

    public Nguoi(Nguoi n){
        this.ma=n.ma;
        this.ho=n.ho;
        this.ten =n.ten;
        this.gioiTinh = n.gioiTinh;
        this.SDT = n.SDT;
    }
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }
    
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
 
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public abstract void xuat();
}
