package model;

public class CTPhieuNhapHang {
    private String maPNH;
    private String maSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    
    //3 ham thiet lap
    public CTPhieuNhapHang(){
        maPNH="";
        maSP="";
        soLuong=0;
        donGia=0;
        thanhTien=0;
    }
    public CTPhieuNhapHang(String maPNH,String maSP,int soLuong,double donGia){
        this.maPNH=maPNH;
        this.maSP=maSP;
        this.soLuong=soLuong;
        this.donGia=donGia;
        this.thanhTien=donGia*soLuong;
    }
    public CTPhieuNhapHang(CTPhieuNhapHang A){
        maPNH=A.maPNH;
        maSP=A.maSP;
        soLuong=A.soLuong;
        donGia=A.donGia;
        thanhTien=A.thanhTien;
    }
    
    //lay thong tin
    @Override public String toString() {
        return String.format("|   - %-6s |SL:%-3d |ĐG nhập:%,13.0f |Thành tiền:%,.0f|", maSP, soLuong, donGia, thanhTien);
    }
    
    //cac ham get/set
    public void setMaPNH(String maPNH){
        this.maPNH=maPNH;
    }
    public String getMaPNH(){
        return maPNH;
    }
    public void setMaSP(String maSP){
        this.maSP=maSP;
    }
    public String getMaSP(){
        return maSP;
    }
    public void setSoLuong(int soLuong){
        this.soLuong=soLuong;
    }
    public int getSoLuong(){
        return soLuong;
    }
    public void setDonGia(double donGia){
        this.donGia=donGia;
    }
    public double getDonGia(){
        return donGia;
    }
    public void setThanhTien(int soLuong,double donGia){
        thanhTien=donGia*soLuong;
    }
    public double getThanhTien(){
        return thanhTien;
    }
    public String toFileString(){
        return getMaPNH()+"|"
                +getMaSP()+"|"
                +getSoLuong()+"|"
                +getDonGia()+"|"
                +getThanhTien();
    }
    public static CTPhieuNhapHang fromFileString(String dong){
        String[] s=dong.split("\\|");
        return new CTPhieuNhapHang(s[0].trim(),s[1].trim(),Integer.parseInt(s[2].trim()),Double.parseDouble(s[3].trim()));
    }
}
