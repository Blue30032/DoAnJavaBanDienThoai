public class CTPhieuNhapHang {
    private String maPNH;
    private String maSP;
    private int soLuong;
    private double donGia;
    public CTPhieuNhapHang(){
        maPNH="";
        maSP="";
        soLuong=0;
        donGia=0;
    }
    public CTPhieuNhapHang(String maPNH,String maSP,int soLuong,long donGia){
        this.maPNH=maPNH;
        this.maSP=maSP;
        this.soLuong=soLuong;
        this.donGia=donGia;
    }
    public CTPhieuNhapHang(CTPhieuNhapHang A){
        maPNH=A.maPNH;
        maSP=A.maSP;
        soLuong=A.soLuong;
        donGia=A.donGia;
    }
    @Override public String toString() {
        return String.format("|   - %-6s |SL:%-3d |ĐG nhập:%,13.0f |Thành tiền:%,14.0f|", maSP, soLuong, donGia, getThanhTien());
    }
    public void setMaPNH(String x){
        maPNH=x;
    }
    public String getMaPNH(){
        return maPNH;
    }
    public void setMaSP(String x){
        maSP=x;
    }
    public String getMaSP(){
        return maSP;
    }
    public void setSoLuong(int x){
        soLuong=x;
    }
    public int getSoLuong(int x){
        return soLuong;
    }
    public void setDonGia(double x){
        donGia=x;
    }
    public double getDonGia(){
        return donGia;
    }
    public double getThanhTien(){
        return soLuong*donGia;
    }
}
