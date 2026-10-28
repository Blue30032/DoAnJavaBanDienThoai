package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HoaDon implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maHD, maNV, maKH;
    private LocalDate ngayLap;
    private List<CTHoaDon> chiTiet = new ArrayList<CTHoaDon>();

    public HoaDon(String maHD, String maNV, String maKH, LocalDate ngayLap) {
        this.maHD = maHD; this.maNV = maNV; this.maKH = maKH; this.ngayLap = ngayLap;
    }
    public String getMaHD() { return maHD; }
    public String getMaNV() { return maNV; }
    public String getMaKH() { return maKH; }
    public LocalDate getNgayLap() { return ngayLap; }
    public List<CTHoaDon> getChiTiet() { return chiTiet; }
    public void themCT(CTHoaDon ct) { chiTiet.add(ct); }
    public double getTongTien() {
        double t = 0;
        for (CTHoaDon ct : chiTiet) t += ct.getThanhTien();
        return t;
    }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder(String.format("HĐ %-6s | Ngày: %s | NV: %s | KH: %s | Tổng tiền: %,.0f",
                maHD, ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), maNV, maKH, getTongTien()));
        for (CTHoaDon ct : chiTiet) sb.append("\n").append(ct);
        return sb.toString();
    }
}
