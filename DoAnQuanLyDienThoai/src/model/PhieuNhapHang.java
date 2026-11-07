import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class PhieuNhapHang {
    private String maPNH;
    private String maNCC;
    private String maNV;
    private LocalDate ngayLap;
    private DSCTPhieuNhapHang chiTiet;
    private double tongTien;
    
    //3 ham thiet lap
    public PhieuNhapHang(){
        maPNH="";
        maNCC="";
        maNV="";
        ngayLap=null;
        chiTiet=new DSCTPhieuNhapHang();
    }
    public PhieuNhapHang(String maPNH,String maNCC,String maNV,LocalDate ngayLap,DSCTPhieuNhapHang ct){
        this.maPNH=maPNH;
        this.maNCC=maNCC;
        this.maNV=maNV;
        this.ngayLap=ngayLap;
        chiTiet= new DSCTPhieuNhapHang(0);
        for(int i=0;i<ct.getN();i++){
            chiTiet.them(ct.getChiTietMang()[i]);
        }
    }
    public PhieuNhapHang(PhieuNhapHang A){
        maPNH=A.maPNH;
        maNCC=A.maNCC;
        maNV=A.maNV;
        ngayLap=A.ngayLap;
        chiTiet= new DSCTPhieuNhapHang(0);
        for(int i=0;i<A.chiTiet.getN();i++){
            chiTiet.them(A.chiTiet.getChiTietMang()[i]);
        }
    }
    
    //lay thong tin
    @Override public String toString() {
        StringBuilder sb = new StringBuilder(String.format("PNH %-6s | Ngày: %s | NCC: %s | NV: %s | Tổng tiền: %,.0f",
                maPNH, ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), maNCC, maNV, getTongTien()));
        for(CTPhieuNhapHang ct: chiTiet.getChiTietMang()){
            sb.append("\n").append(ct);
        }
        return sb.toString();
    }
    
    //cac ham get/set
    public String getMaNCC(){
        return maNCC;
    }
    public void setMaNCC(String s){
        maNCC=s;
    }
    public String getMaPNH(){
        return maPNH;
    }
    public void setMaPNH(String s){
        maPNH=s;
    }
    public String getMaNV(){
        return maNV;
    }
    public void setMaNV(String s){
        maNV=s;
    }
    public LocalDate getNgayLap(){
        return ngayLap;
    }
    public void setNgayLap(LocalDate s){
        ngayLap=s;
    }
    public double getTongTien(){
        
        return tongTien;
    }
    public DSCTPhieuNhapHang getChiTiet(){
        return chiTiet;
    }
}
