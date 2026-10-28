package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapHang implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maPNH, maNCC, maNV;
    private LocalDate ngayLap;
    private List<CTPhieuNhapHang> chiTiet = new ArrayList<CTPhieuNhapHang>();

    public PhieuNhapHang(String maPNH, String maNCC, String maNV, LocalDate ngayLap) {
        this.maPNH = maPNH; this.maNCC = maNCC; this.maNV = maNV; this.ngayLap = ngayLap;
    }
    public String getMaPNH() { return maPNH; }
    public String getMaNCC() { return maNCC; }
    public String getMaNV() { return maNV; }
    public LocalDate getNgayLap() { return ngayLap; }
    public List<CTPhieuNhapHang> getChiTiet() { return chiTiet; }
    public void themCT(CTPhieuNhapHang ct) { chiTiet.add(ct); }
    public double getTongTien() {
        double t = 0;
        for (CTPhieuNhapHang ct : chiTiet) t += ct.getThanhTien();
        return t;
    }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder(String.format("PNH %-6s | Ngày: %s | NCC: %s | NV: %s | Tổng tiền: %,.0f",
                maPNH, ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), maNCC, maNV, getTongTien()));
        for (CTPhieuNhapHang ct : chiTiet) sb.append("\n").append(ct);
        return sb.toString();
    }
}
