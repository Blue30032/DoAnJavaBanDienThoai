package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HoaDon {
    private String maHD;
    private String maNV;
    private String maKH;
    private LocalDate ngayLap;
    private double tongTien;

    public HoaDon() {
        maHD = "";
        maNV = "";
        maKH = "";
        ngayLap = LocalDate.now();
        tongTien = 0;
    }

    public HoaDon(String maHD, String maNV, String maKH, LocalDate ngayLap) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongTien = 0;
    }

    public HoaDon(String maHD, String maNV, String maKH,
                  LocalDate ngayLap, double tongTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public HoaDon(HoaDon hd) {
        this(hd.maHD, hd.maNV, hd.maKH, hd.ngayLap, hd.tongTien);
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        if (ngayLap != null) {
            this.ngayLap = ngayLap;
        }
    }

    public double getTongTien() {
        return tongTien;
    }
    
    public double tinhTongTien(DSChiTietHoaDon dsct) {
        if (dsct == null) {
            return 0;
        }

        double tong = 0;
        CTHoaDon[] ds = dsct.getDs();

        for (int i = 0; i < dsct.getN(); i++) {
            if (ds[i] != null &&
                ds[i].getMaHD().equalsIgnoreCase(maHD)) {
                tong += ds[i].getThanhTien();
            }
        }

        tongTien = tong;
        return tongTien;
    }
    
    public void setTongTien(double tongTien) {
        if (tongTien >= 0) {
            this.tongTien = tongTien;
        }
    } 

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s %-15s %15,.0f VNĐ",
                maHD, maNV, maKH,
                ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                tongTien);
    }
}
