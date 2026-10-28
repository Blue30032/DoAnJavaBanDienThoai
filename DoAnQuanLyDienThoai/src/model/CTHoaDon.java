package model;

import java.io.Serializable;

public class CTHoaDon implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maHD, maSP;
    private int soLuong;
    private double donGiaBan;

    public CTHoaDon(String maHD, String maSP, int soLuong, double donGiaBan) {
        this.maHD = maHD; this.maSP = maSP; this.soLuong = soLuong; this.donGiaBan = donGiaBan;
    }
    public String getMaHD() { return maHD; }
    public String getMaSP() { return maSP; }
    public int getSoLuong() { return soLuong; }
    public double getDonGiaBan() { return donGiaBan; }
    public double getThanhTien() { return soLuong * donGiaBan; }
    @Override public String toString() {
        return String.format("   - %-6s SL:%-3d ĐG bán:%,13.0f Thành tiền:%,14.0f", maSP, soLuong, donGiaBan, getThanhTien());
    }
}
