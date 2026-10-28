package model;

public class NhanVien extends Nguoi {
    private static final long serialVersionUID = 1L;
    private String maNV, ho, chucVu, cccd;
    private double luongCanBan;

    public NhanVien(String maNV, String ho, String tenNV, String gioiTinh, String sdt,
                    String chucVu, String cccd, double luongCanBan) {
        super(tenNV, gioiTinh, sdt);
        this.maNV = maNV; this.ho = ho; this.chucVu = chucVu; this.cccd = cccd; this.luongCanBan = luongCanBan;
    }
    public String getMaNV() { return maNV; }
    public String getHo() { return ho; }
    public String getChucVu() { return chucVu; }
    public String getCccd() { return cccd; }
    public double getLuongCanBan() { return luongCanBan; }
    public void setLuongCanBan(double v) { this.luongCanBan = v; }
    public String getHoTen() { return ho + " " + getTen(); }

    @Override public String toString() {
        return String.format("%-6s %-22s %-5s %-12s %-12s CCCD:%-13s Lương CB: %,.0f",
                maNV, getHoTen(), getGioiTinh(), getSdt(), chucVu, cccd, luongCanBan);
    }
}
