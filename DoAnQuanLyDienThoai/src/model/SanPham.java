package model;

import java.io.Serializable;

/** Lớp cha trừu tượng: Sản phẩm (Mã SP, Tên SP, Số lượng, ĐG, Màu sắc). */
public abstract class SanPham implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maSP, tenSP, mauSac;
    private int soLuong;
    private double donGia;

    public SanPham(String maSP, String tenSP, int soLuong, double donGia, String mauSac) {
        this.maSP = maSP; this.tenSP = tenSP; this.soLuong = soLuong;
        this.donGia = donGia; this.mauSac = mauSac;
    }
    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }

    /** Mỗi loại máy tự mô tả thuộc tính riêng (đa hình). */
    public abstract String thongTinRieng();
    public abstract String loai();

    @Override
    public String toString() {
        return String.format("%-6s %-8s %-22s SL:%-4d ĐG:%,15.0f Màu:%-8s | %s",
                maSP, loai(), tenSP, soLuong, donGia, mauSac, thongTinRieng());
    }
}
