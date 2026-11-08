package model;
public class KhachHang extends Nguoi {
    private double TongTien;

    public KhachHang() {
        super();
        TongTien = 0;        
    }

    public KhachHang(String maKH,String hoKH,String tenKH, String gtinhKH, String sdtKH, double TongTien) {
        super(maKH,hoKH,tenKH,gtinhKH,sdtKH);
        this.TongTien = TongTien;  
    }

    public KhachHang(KhachHang kh) {
        super(kh);
        this.TongTien=kh.TongTien;
    }
    
    public String getMaKH() {
        return getMa();
    }

    public String getHoKH() {
        return getHo();
    }

    public void setHoKH(String hoKH) {
        setHo(hoKH);
    }
    
    public String getTenKH() {
        return getTen();
    }

    public void setTenKH(String tenKH) {
        setTen(tenKH);
    }

    public String getGtinhKH() {
        return getGioiTinh();
    }

    public void setGtinhKH(String gtinhKH) {
        setGioiTinh(gtinhKH);
    }

    public String getSdtKH() {
        return getSDT();
    }

    public void setSdtKH(String sdtKH) {
        setSDT(sdtKH);
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        if (TongTien >= 0) {
            this.TongTien = TongTien;
        }
    }

    public void congTienMua(double tien) {
        if (tien > 0) {
            TongTien += tien;
        }
    }

    @Override
    public void xuat() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %-25s %-12s %-15s %,.0f",getMa(),getHo(),getTen(),getGioiTinh(),getSDT(), TongTien);
    }
}
