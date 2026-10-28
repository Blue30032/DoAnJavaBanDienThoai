package model;

public class KhachHang extends Nguoi {
    private static final long serialVersionUID = 1L;
    private String maKH;
    private double tongTienDaMua;

    public KhachHang(String maKH, String tenKH, String gioiTinh, String sdt) {
        super(tenKH, gioiTinh, sdt);
        this.maKH = maKH;
    }
    public String getMaKH() { return maKH; }
    public double getTongTienDaMua() { return tongTienDaMua; }
    public void setTongTienDaMua(double v) { this.tongTienDaMua = v; }

    @Override public String toString() {
        return String.format("%-6s %-22s %-5s %-12s Tổng đã mua: %,.0f", maKH, getTen(), getGioiTinh(), getSdt(), tongTienDaMua);
    }
}
