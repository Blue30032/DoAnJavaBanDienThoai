package model;

public class CTHoaDon {
    private String maHD;
    private String maSP;
    private int soLuong;
    private double donGiaBan;
    private double thanhTien;

    public CTHoaDon() {
        this("", "", 0, 0);
    }

    public CTHoaDon(String maHD, String maSP, int soLuong, double donGiaBan) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGiaBan = donGiaBan;
        this.thanhTien = soLuong * donGiaBan;
    }

    public CTHoaDon(String maHD, String maSP, int soLuong, double donGiaBan, double thanhTien) {
        this(maHD, maSP, soLuong, donGiaBan);
        this.thanhTien = thanhTien;
    }

    public CTHoaDon(CTHoaDon other) {
        this(other.maHD, other.maSP, other.soLuong, other.donGiaBan, other.thanhTien);
    }

    public String getMaHD() {
        return maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGiaBan() {
        return donGiaBan;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        this.thanhTien = soLuong * donGiaBan;
    }
    public void setDonGiaBan(double donGiaBan) {
        this.donGiaBan = donGiaBan;
        this.thanhTien = soLuong * donGiaBan;
    }
    public void setThanhTien(double thanhTien) { this.thanhTien = thanhTien; }

    public String toFileString() {
        return maHD + "|" + maSP + "|" + soLuong + "|" + donGiaBan + "|" + thanhTien;
    }

    public void xuat() {
        System.out.println(toString());
    }

    public static CTHoaDon fromFileString(String dong) {
        String[] parts = dong.split("\\|");
        if (parts.length < 5) {
            throw new IllegalArgumentException("Dòng chi tiết hóa đơn không hợp lệ");
        }
        return new CTHoaDon(parts[0].trim(), parts[1].trim(),
                Integer.parseInt(parts[2].trim()),
                Double.parseDouble(parts[3].trim()),
                Double.parseDouble(parts[4].trim()));
    }

    @Override
    public String toString() {
        return String.format("Ma HD: %s | Ma SP: %s | So luong: %d | Don gia ban: %.0f | Thanh tien: %.0f",
                maHD, maSP, soLuong, donGiaBan, thanhTien);
    }
}
