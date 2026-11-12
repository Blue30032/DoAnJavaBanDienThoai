package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class DSCTPhieuNhapHang {
    Scanner sc=new Scanner(System.in);
    private static final String TEN_FILE="ChiTietPhieuNhapHang.txt";
    private CTPhieuNhapHang[] chiTiet;
    private int n;
    
    //3 ham thiet lap
    public DSCTPhieuNhapHang(){
        chiTiet=new CTPhieuNhapHang[0];
        n=0;
    }
    public DSCTPhieuNhapHang(int n){
        this.n=n;
        chiTiet=new CTPhieuNhapHang[n];
    }
    public DSCTPhieuNhapHang(DSCTPhieuNhapHang A){
        chiTiet=new CTPhieuNhapHang[A.n];
        n=A.n;
        for(int i=0;i<n;i++){
            chiTiet[i]=new CTPhieuNhapHang(A.chiTiet[i]);
        }
    }
    
    // ham them, sua, xoa
    public void them(CTPhieuNhapHang ct){
        chiTiet=Arrays.copyOf(chiTiet,chiTiet.length+1);
        chiTiet[chiTiet.length-1]=new CTPhieuNhapHang(ct);
        n++;
    }
    public boolean them(String maPNH,DSSanPham dssp,DSPhieuNhapHang dspnh){
        CTPhieuNhapHang x=new CTPhieuNhapHang();
        x.setMaPNH(maPNH);
        System.out.print("Nhap ma SP:");
        x.setMaSP(sc.nextLine());
        if(dssp.timTheoMa(x.getMaSP()) == null) {
            System.out.println("Khong ton tai san pham!");
            return false;
        }
        if(IfindChiTiet(x.getMaPNH(),x.getMaSP())!=-1){
            System.out.println("Chi tiet nay da ton tai!");
            System.out.println("Them chi tiet that bai!");
            return false;
        }
        System.out.print("Nhap so luong nhap hang:");
        x.setSoLuong(Integer.parseInt(sc.nextLine()));
        System.out.print("Nhap don gia ban:");
        x.setDonGia(Double.parseDouble(sc.nextLine()));
        x.setThanhTien(x.getSoLuong(),x.getDonGia());
        them(x);
        dssp.timTheoMa(x.getMaSP()).nhaphang(x.getSoLuong());
        System.out.println("Da them chi tiet thanh cong!");
        return true;
    }
    public boolean xoa(String maPNH,String maSP,DSSanPham dssp){
        int i=IfindChiTiet(maPNH,maSP);
        if(i==-1){
            System.out.println("Khong hop le!");
            return false;
        }
        SanPham x= dssp.timTheoMa(maSP);
        x.setsoLuong(x.getSoLuong()-chiTiet[i].getSoLuong());
        for(int j=i;j<n-1;j++){
            chiTiet[j]=chiTiet[j+1];
        }
        n--;
        chiTiet=Arrays.copyOf(chiTiet, n);
        System.out.println("Da xoa chi tiet yeu cau.");
        return true;
    }
    public boolean sua(String maPNH,String maSP,DSSanPham dssp){
        int i=IfindChiTiet(maPNH,maSP);
        if(i==-1){
            System.out.println("Khong tim thay chi tiet phieu nhap hang!");
            return false;
        }
        System.out.println("Da tim thay chi tiet muon sua.Thong tin:");
        System.out.println(chiTiet[i]);
        System.out.println("Hay chon cho muon sua:");
        System.out.print("1.So Luong.\n2.Don gia.\nChon:");
        int chon;
        do{
            int q=Integer.parseInt(sc.nextLine());
            SanPham X= dssp.timTheoMa(maSP);
            switch(q){
                case 1:
                    int soLuongCu=chiTiet[i].getSoLuong();
                    System.out.print("Nhap so luong moi:");
                    chiTiet[i].setSoLuong(Integer.parseInt(sc.nextLine()));
                    int soLuongMoi=chiTiet[i].getSoLuong();
                    int chenhLech=soLuongMoi-soLuongCu;
                    X.setsoLuong(X.getSoLuong()+chenhLech);
                    break;
                case 2:
                    System.out.print("Nhap don gia moi:");
                    chiTiet[i].setDonGia(Double.parseDouble(sc.nextLine()));
                    break;
                default:break;
            }
            System.out.print("Tiep tuc sua?( 1: Yes / 0: No )");
            chon=Integer.parseInt(sc.nextLine());
        }while(chon==1);
        System.out.println("Da sua thong tin thanh cong!");
        return true;
    }
    
    public DSCTPhieuNhapHang findPNH(String maPNH){
        DSCTPhieuNhapHang x=new DSCTPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(chiTiet[i].getMaPNH().equals(maPNH)){
                x.them(chiTiet[i]);
            }
        }
        return x;
    }
    public DSCTPhieuNhapHang findSP(String maSP){
        DSCTPhieuNhapHang x=new DSCTPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(chiTiet[i].getMaSP().equals(maSP)){
                x.them(chiTiet[i]);
            }
        }
        return x;
    }
    public int IfindChiTiet(String maPNH,String maSP){
        for(int i=0;i<n;i++){
            if(chiTiet[i].getMaPNH().equals(maPNH)&&chiTiet[i].getMaSP().equals(maSP)){
                return i;
            }
        }
        return -1;
    }
    
    public void thongkeSoLuong(){
        System.out.println("=============THONG KE SO LUONG CHI TIET THEO MA PHIEU NHAP HANG=============");
        System.out.printf("Ma PNH |SL chi tiet\n");
        String[] ds=new String[0];
        int k=0;
        //tao mang khong trung
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(chiTiet[i].getMaPNH())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=chiTiet[i].getMaPNH();
                k++;
            }
        }
        //dem so lan lap ma nha cung cap
        for(int i=0;i<k;i++){
            int dem=0;
            for(int j=0;j<n;j++){
                if(chiTiet[j].getMaPNH().equals(ds[i])){
                    dem++;
                }
            }
            System.out.printf("%-6s |%11d\n",ds[i],dem);
        }
    }
    //cac ham bo tro
    public int getN(){
        return n;
    }
    public double tinhTongTien(){
        double t=0;
        for(CTPhieuNhapHang ct : chiTiet){
            t+=ct.getThanhTien();
        }
        return t;
    }
    public double tongTienPNH(String maPNH){
        DSCTPhieuNhapHang x=findPNH(maPNH);
        return x.tinhTongTien();
    }
    //xuat thong tin
    // xuat thong tin
    public void xuat() {
        System.out.println("\n========== DANH SACH CHI TIET PHIEU NHAP HANG ==========\n");
        // Xac dinh do rong cac cot
        int stt = 3;
        int maPNH = "MA PNH".length();
        int maSP = "MA SP".length();
        int soLuong = "SO LUONG".length();
        int donGia = "DON GIA".length();
        int thanhTien = "THANH TIEN".length();
        for (int i = 0; i < n; i++) {
            maPNH = Math.max(maPNH, chiTiet[i].getMaPNH().length());
            maSP = Math.max(maSP, chiTiet[i].getMaSP().length());
            soLuong = Math.max(soLuong,String.valueOf(chiTiet[i].getSoLuong()).length());
            donGia = Math.max(donGia,String.valueOf(chiTiet[i].getDonGia()).length());
            thanhTien = Math.max(thanhTien,String.valueOf(chiTiet[i].getThanhTien()).length());
            stt = Math.max(stt, String.valueOf(i + 1).length());
        }
        // In tieu de cot
        System.out.printf("%-" + (stt + 3) + "s", "STT");
        System.out.printf("%-" + (maPNH + 3) + "s", "MA PNH");
        System.out.printf("%-" + (maSP + 3) + "s", "MA SP");
        System.out.printf("%-" + (soLuong + 3) + "s", "SO LUONG");
        System.out.printf("%-" + (donGia + 3) + "s", "DON GIA");
        System.out.printf("%s\n", "THANH TIEN");
        // Tinh do dai duong ke
        int tong = stt + maPNH + maSP + soLuong + donGia + thanhTien + 15;
        System.out.println("-".repeat(tong));
        // In danh sach
        for (int i = 0; i < n; i++) {
            System.out.printf("%-" + (stt + 3) + "s", i + 1);
            System.out.printf("%-" + (maPNH + 3) + "s", chiTiet[i].getMaPNH());
            System.out.printf("%-" + (maSP + 3) + "s", chiTiet[i].getMaSP());
            System.out.printf("%-" + (soLuong + 3) + "s",chiTiet[i].getSoLuong());
            System.out.printf("%-" + (donGia + 3) + "s",String.format("%.2f", chiTiet[i].getDonGia()));
            System.out.printf("%.2f\n", chiTiet[i].getThanhTien());
        }
        System.out.println("-".repeat(tong));
        System.out.println("Tong so chi tiet: " + n);
    }
    public void docFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(TEN_FILE));
            String dong;
            chiTiet=new CTPhieuNhapHang[0];
            n = 0;
            while ((dong = br.readLine()) != null) {
                if (!dong.trim().isEmpty()) {
                    chiTiet=Arrays.copyOf(chiTiet, n+1);
                    chiTiet[n] = CTPhieuNhapHang.fromFileString(dong);
                    n++;
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file chi tiet PNH!");
            System.out.println(e.getMessage());

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file chi tiet PNH!");
            }
        }
    }
    public void ghiFile(){
        BufferedWriter bw = null;
        try{
            bw=new BufferedWriter(new FileWriter(TEN_FILE));
            for(int i=0;i<n;i++){
                bw.write(chiTiet[i].toFileString());
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Loi doc file chi tiet PNH!");
            System.out.println(e.getMessage());
        }finally{
            try{
                if(bw!=null){
                    bw.close();
                }
            }catch(IOException e){
                System.out.println("Loi dong file chi tiet PNH!");
            }
        }
    }
}
