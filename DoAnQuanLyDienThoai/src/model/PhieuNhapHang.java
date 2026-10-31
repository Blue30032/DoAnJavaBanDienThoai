import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class PhieuNhapHang {
    private String maPNH;
    private String maNCC;
    private String maNV;
    private LocalDate ngayLap;
    private DSCTPhieuNhapHang chiTiet;
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
            chiTiet.them(ct.getChiTiet()[i]);
        }
    }
    public PhieuNhapHang(PhieuNhapHang A){
        maPNH=A.maPNH;
        maNCC=A.maNCC;
        maNV=A.maNV;
        ngayLap=A.ngayLap;
        chiTiet= new DSCTPhieuNhapHang(0);
        chiTiet= new DSCTPhieuNhapHang(0);
        for(int i=0;i<A.chiTiet.getN();i++){
            chiTiet.them(A.chiTiet.getChiTiet()[i]);
        }
    }
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
        return chiTiet.tinhTongTien();
    }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder(String.format("PNH %-6s | Ngày: %s | NCC: %s | NV: %s | Tổng tiền: %,.0f",
                maPNH, ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), maNCC, maNV, chiTiet.tinhTongTien()));
        for(CTPhieuNhapHang ct: chiTiet.getChiTiet()){
            sb.append("\n").append(ct);
        }
        return sb.toString();
    }
}
