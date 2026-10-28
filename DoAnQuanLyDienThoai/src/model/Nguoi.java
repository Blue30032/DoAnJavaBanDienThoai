package model;

import java.io.Serializable;

/** Lớp cha chung cho Khách hàng và Nhân viên (tên, giới tính, SĐT). */
public abstract class Nguoi implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ten, gioiTinh, sdt;

    public Nguoi(String ten, String gioiTinh, String sdt) {
        this.ten = ten; this.gioiTinh = gioiTinh; this.sdt = sdt;
    }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getGioiTinh() { return gioiTinh; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
}
