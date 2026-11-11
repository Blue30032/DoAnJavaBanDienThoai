package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class PhieuNhapHang {
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String maPNH;
    private String maNCC;
    private String maNV;
    private LocalDate ngayLap;
    private double tongTien;
    
    //3 ham thiet lap
    public PhieuNhapHang(){
        maPNH="";
        maNCC="";
        maNV="";
        ngayLap=null;
        tongTien=0;
    }
    public PhieuNhapHang(String maPNH,String maNCC,String maNV,LocalDate ngayLap,double tongTien){
        this.maPNH=maPNH;
        this.maNCC=maNCC;
        this.maNV=maNV;
        this.ngayLap=ngayLap;
        this.tongTien=tongTien;
    }
    public PhieuNhapHang(PhieuNhapHang A){
        maPNH=A.maPNH;
        maNCC=A.maNCC;
        maNV=A.maNV;
        ngayLap=A.ngayLap;
        tongTien=A.tongTien;
    }
    
    //lay thong tin
    @Override public String toString() {
        String sb = String.format("PNH %-6s | Ngày: %s | NCC: %s | NV: %s | Tong Tien: %,.0f",
                maPNH, ngayLap.format(dtf), maNCC, maNV, tongTien);
        return sb;
    }
    public void nhap(){
        System.out.print("Nhap ma phieu nhap hang:");
        maPNH=sc.nextLine();
        System.out.print("Nhap ma nha cung cap:");
        maNCC=sc.nextLine();
        System.out.print("Nhap ma nhan vien:");
        maNV=sc.nextLine();
        System.out.println("Nhap ngay lap phieu");
        ngayLap=LocalDate.parse(sc.nextLine(),dtf);
    }
    
    //cac ham get/set
    public String getMaNCC(){
        return maNCC;
    }
    public void setMaNCC(String maNCC){
        this.maNCC=maNCC;
    }
    public String getMaPNH(){
        return maPNH;
    }
    public void setMaPNH(String maPNH){
        this.maPNH=maPNH;
    }
    public String getMaNV(){
        return maNV;
    }
    public void setMaNV(String maNV){
        this.maNV=maNV;
    }
    public LocalDate getNgayLap(){
        return ngayLap;
    }
    public void setNgayLap(LocalDate ngayLap){
        this.ngayLap=ngayLap;
    }
    public double getTongTien(){
        return tongTien;
    }
    public void setTongTien(DSCTPhieuNhapHang dsct){
        tongTien=dsct.tongTienPNH(maPNH);
    }
    public String toFileString(){
        return getMaPNH()+"|"
                +getMaNCC()+"|"
                +getMaNV()+"|"
                +getNgayLap().format(dtf)+"|"
                +getTongTien();
    }
    public static PhieuNhapHang fromFileString(String dong) {
        String[] s = dong.split("\\|");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new PhieuNhapHang(s[0].trim(),s[1].trim(),s[2].trim(),LocalDate.parse(s[3].trim(), dtf),Double.parseDouble(s[4].trim()));
    }
}
