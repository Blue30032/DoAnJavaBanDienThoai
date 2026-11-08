package DoAnBanDienThoai;

public class CTHoaDon {
    private String maHD;
    private String maSP;
    private int soLuong;
    private double donGiaBan;
    private double thanhTien;

    public CTHoaDon() {
        maHD = "";
        maSP = "";
        soLuong = 0;
        donGiaBan = 0;
        thanhTien = 0;
    }

    public CTHoaDon(String maHD, String maSP,
                    int soLuong, double donGiaBan, double thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGiaBan = donGiaBan;
        this.thanhTien = thanhTien;
    }

    public CTHoaDon(CTHoaDon ct) {
        this.maHD = ct.maHD;
        this.maSP = ct.maSP;
        this.soLuong = ct.soLuong;
        this.donGiaBan = ct.donGiaBan;
        this.thanhTien = ct.thanhTien;
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
        if (soLuong >= 0) {
            this.soLuong = soLuong;
        }
    }

    public void setDonGiaBan(double donGiaBan) {
        if (donGiaBan >= 0) {
            this.donGiaBan = donGiaBan;
        }
    }

    public void setThanhTien(double thanhTien) {
        if (thanhTien >= 0) {
            this.thanhTien = thanhTien;
        }
    }

    @Override
    public String toString() {
        return "Ma HD: " + maHD
                + " | Ma SP: " + maSP
                + " | So luong: " + soLuong
                + " | Don gia ban: " + donGiaBan
                + " | Thanh tien: " + thanhTien;
    }
}
