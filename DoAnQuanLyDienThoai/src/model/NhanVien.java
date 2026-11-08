package model;

public class NhanVien extends Nguoi {
    private String ChucVu;
    private String CCCD;
    private double LuongCB;
    
    public NhanVien() {
        super();
        ChucVu = "";
        CCCD = "";
        LuongCB = 0;  
    }

    public NhanVien(String maNV,String hoNV, String tenNV, String GT,String SDT, String ChucVu,String CCCD, double LuongCB) {
        super(maNV,hoNV,tenNV,GT,SDT);
        this.ChucVu = ChucVu;
        this.CCCD = CCCD;
        this.LuongCB = LuongCB; 
    }

    public NhanVien(NhanVien other) {
        super(other);
        this.ChucVu=other.ChucVu;
        this.CCCD=other.CCCD;
        this.LuongCB=other.LuongCB;   
    }
    
    public String getMaNV() {
        return super.getMa();
    }

    public String getHoNV() {
        return super.getHo();
    }

    public void setHoNV(String hoNV) {
        setHo(hoNV);
    }

    public String getTenNV() {
        return super.getTen();
    }

    public void setTenNV(String tenNV) {
        setTen(tenNV);
    }

    public String getGT() {
        return super.getGioiTinh();
    }

    public void setGT(String GT) {
        setGioiTinh(GT);
    }

    public String getSDT() {
        return super.getSDT();
    }

    public void setSDT(String SDT) {
        setSDT(SDT);
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public double getLuongCB() {
        return LuongCB;
    }

    public void setLuongCB(double LuongCB) {
        if (LuongCB >= 0) {
            this.LuongCB = LuongCB;
        }
    }

    public String getHoTen() {
        return getHo() + " " + getTen();
    }
    @Override
    public void xuat() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %-15s %-10s %-15s %-15s %-15s %,.0f",getMa(), getHo(), getTen(), getGioiTinh(), getSDT(), ChucVu, CCCD, LuongCB);
    }
}
