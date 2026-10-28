package model;

import java.io.Serializable;

public class CTPhieuNhapHang implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maPNH, maSP;
    private int soLuong;
    private double donGia;

    public CTPhieuNhapHang(String maPNH, String maSP, int soLuong, double donGia) {
        this.maPNH = maPNH; this.maSP = maSP; this.soLuong = soLuong; this.donGia = donGia;
    }
    public String getMaPNH() { return maPNH; }
    public String getMaSP() { return maSP; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }
    public double getThanhTien() { return soLuong * donGia; }
    @Override public String toString() {
        return String.format("   - %-6s SL:%-3d ĐG nhập:%,13.0f Thành tiền:%,14.0f", maSP, soLuong, donGia, getThanhTien());
    }
}
