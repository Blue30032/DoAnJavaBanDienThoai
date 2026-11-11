package model;

import helper.IDanhSach;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DSPhieuNhapHang implements IDanhSach<PhieuNhapHang>{
    Scanner sc= new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String TEN_FILE="PhieuNhapHang.txt";
    private PhieuNhapHang[] pnh;
    private int n;
    
    //3 ham thiet lap
    public DSPhieuNhapHang(){
        pnh=new PhieuNhapHang[0];
        n=0;
    }
    public DSPhieuNhapHang(int n){
        this.n=n;
        pnh=new PhieuNhapHang[n];
    }
    public DSPhieuNhapHang(DSPhieuNhapHang A){
        pnh=new PhieuNhapHang[A.n];
        n=A.n;
        for(int i=0;i<n;i++){
            pnh[i]=new PhieuNhapHang(A.pnh[i]);
        }
    }
    
    //cac ham them sua xoa
    @Override
    public boolean them(PhieuNhapHang A){
        pnh=Arrays.copyOf(pnh,pnh.length+1);
        pnh[n]=new PhieuNhapHang(A);
        n++;
        return true;
    }
    
    public boolean them(){
        PhieuNhapHang Y=new PhieuNhapHang();
        System.out.print("Nhap ma phieu nhap hang:");
        Y.setMaPNH(sc.nextLine());
        if(findMaPNH(Y.getMaPNH())!=null){
            System.out.println("Phieu nhap hang tren da ton tai!\nThem phieu khong thanh cong!");
            return false;
        }
        System.out.print("Nhap ma nhan vien");
        Y.setMaNV(sc.nextLine());
        System.out.print("Nhap ma nha cung cap:");
        Y.setMaNCC(sc.nextLine());
        System.out.print("Nhap ngay lap phieu (dd/MM/yyyy):");
        LocalDate ngay = LocalDate.parse(sc.nextLine(), dtf);
        Y.setNgayLap(ngay);
        them(Y);
        return true;
    }

    @Override
    public boolean xoa(String MaPNH){
        int i=IfindMaPNH(MaPNH);
        if(i==-1){
            System.out.println("Khong ton tai phieu nhap hang nay!");
            System.out.println("Sua that bai!");
            return false;
        }
        for(int j=i;j<n-1;j++){
            pnh[j]=pnh[j+1];
        }
        n--;
        pnh=Arrays.copyOf(pnh, n);
        System.out.println("Da xoa Phieu Nhap Hang co ma "+MaPNH);
        return true;
    }
    
    @Override
    public boolean sua(String MaPNH){
        int i=IfindMaPNH(MaPNH);
        if(i==-1){
            System.out.println("Khong ton tai phieu nhap hang nay!");
            System.out.println("Sua that bai!");
            return false;
        }
        System.out.println("Da tim thay Phieu Nhap Hang muon sua. Thong tin:");
        System.out.println(pnh[i]);
        System.out.println("\nHay chon muc muon sua:");
        System.out.print("1.Ma Nha Cung Cap.\n2.Ma Nhan Vien.\n3.Ngay Lap Phieu.\n4.Chi Tiet Phieu Nhap Hang.\nChon:");
        int q=sc.nextInt();
        sc.nextLine();
        switch(q){
            case 1:System.out.print("Nhap Ma Nha Cung Cap Moi:");pnh[i].setMaNCC(sc.nextLine());break;
            case 2:System.out.print("Nhap Ma Nhan Vien Moi:");pnh[i].setMaNV(sc.nextLine());break;
            case 3:System.out.print("Nhap Ngay Lap Phieu Moi:");pnh[i].setNgayLap(LocalDate.parse(sc.nextLine(),dtf));break;                 
            default:break;
        }
        System.out.println("Da sua thong tin thanh cong!");
        return true;
    }
    
    //Cac ham tim kiem
    //tim kiem theo ma phieu nhap hang
    public PhieuNhapHang findMaPNH(String maPNH){
        for(int i=0;i<n;i++){
            if(pnh[i].getMaPNH().equals(maPNH)){
                return pnh[i];
            }
        }
        return null;
    }
    public int IfindMaPNH(String maPNH){
        for(int i=0;i<n;i++){
            if(pnh[i].getMaPNH().equals(maPNH)){
                return i;
            }
        }
        return -1;
    }
    //tim kiem theo ma nha cung cap
    public DSPhieuNhapHang findMaNCC(String maNCC){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(pnh[i].getMaNCC().equals(maNCC)){
                x.them(pnh[i]);
            }
        }
        return x;
    }
    //tim kiem theo ma nhan vien
    public DSPhieuNhapHang findMaNV(String maNV){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(pnh[i].getMaNV().equals(maNV)){
                x.them(pnh[i]);
            }
        }
        return x;
    }
    //timkiem theo ngay lap phieu
    public DSPhieuNhapHang findNgayLap(LocalDate ngay){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(pnh[i].getNgayLap().equals(ngay)){
                x.them(pnh[i]);
            }
        }
        return x;
    }
    
    //cac ham thong ke
    //thong ke so luong giao dich ung voi tung nha cung cap
    public void thongkeNCC(){
        System.out.println("=============THONG KE THEO NHA CUNG CAP=============");
        System.out.printf("Ma Nha Cung Cap |So luong giao dich\n");
        String[] ds=new String[0];
        int k=0;
        //tao mang khong trung
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNCC())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNCC();
                k++;
            }
        }
        //dem so lan lap ma nha cung cap
        for(int i=0;i<k;i++){
            int dem=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNCC().equals(ds[i])){
                    dem++;
                }
            }
            System.out.printf("%-16s|%18d\n",ds[i],dem);
        }
    }
    //tuong tu ham thong ke theo nha cung cap
    public void thongkeNV(){
        System.out.println("=============THONG KE THEO NHAN vIEN=============");
        System.out.printf("Ma Nhan Vien |Thuc hien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNV())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNV();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            int dem=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNV().equals(ds[i])){
                    dem++;
                }
            }
            System.out.printf("%-13s|%19d\n",ds[i],dem);
        }
    }
    //thong ke tong tien da chi tra cho tung nha cung cap
    public void thongkeTongTienNCC(){
        System.out.println("=============THONG KE TONG TIEN THEO NHA CUNG CAP=============");
        System.out.printf("Ma Nha Cung Cap |Tong tien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNCC())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNCC();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            double tong=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNCC().equals(ds[i])){
                    tong+=pnh[j].getTongTien();
                }
            }
            System.out.printf("%-16s|%19f\n",ds[i],tong);
        }
    }
    //thong ke tong tien giao dich duoc nhan vien chiu trach nhiem lap phieu
    public void thongkeTongTienNV(){
        System.out.println("=============THONG KE TONG TIEN THEO NHAN VIEN=============");
        System.out.printf("Ma Nhan Vien |Tong tien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNV())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNV();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            double tong=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNV().equals(ds[i])){
                    tong+=pnh[j].getTongTien();
                }
            }
            System.out.printf("%-13s|%19f\n",ds[i],tong);
        }
    }
    
    //xuat thong tin
    // xuat thong tin
    public void xuat() {
        System.out.println("\n========== DANH SACH PHIEU NHAP HANG ==========\n");
        // Xac dinh do rong cac cot
        int stt = 3;
        int maPNH = "MA PNH".length();
        int maNCC = "MA NCC".length();
        int maNV = "MA NV".length();
        int ngayLap = "NGAY LAP".length();
        int tongTien = "TONG TIEN".length();
        for (int i = 0; i < n; i++) {
            maPNH = Math.max(maPNH, pnh[i].getMaPNH().length());
            maNCC = Math.max(maNCC, pnh[i].getMaNCC().length());
            maNV = Math.max(maNV, pnh[i].getMaNV().length());
            ngayLap = Math.max(ngayLap,pnh[i].getNgayLap().format(dtf).length());
            tongTien = Math.max(tongTien,String.valueOf(pnh[i].getTongTien()).length());
            stt = Math.max(stt, String.valueOf(i + 1).length());
        }
        // In tieu de cot
        System.out.printf("%-" + (stt + 3) + "s", "STT");
        System.out.printf("%-" + (maPNH + 3) + "s", "MA PNH");
        System.out.printf("%-" + (maNCC + 3) + "s", "MA NCC");
        System.out.printf("%-" + (maNV + 3) + "s", "MA NV");
        System.out.printf("%-" + (ngayLap + 3) + "s", "NGAY LAP");
        System.out.printf("%s\n", "TONG TIEN");
        // Tinh do dai duong ke
        int tong = stt + maPNH + maNCC + maNV + ngayLap + tongTien + 15;
        System.out.println("-".repeat(tong));
        // In danh sach
        for (int i = 0; i < n; i++) {
            System.out.printf("%-" + (stt + 3) + "s", i + 1);
            System.out.printf("%-" + (maPNH + 3) + "s", pnh[i].getMaPNH());
            System.out.printf("%-" + (maNCC + 3) + "s", pnh[i].getMaNCC());
            System.out.printf("%-" + (maNV + 3) + "s", pnh[i].getMaNV());
            System.out.printf("%-" + (ngayLap + 3) + "s",pnh[i].getNgayLap().format(dtf));
            System.out.printf("%.2f\n", pnh[i].getTongTien());
        }
        System.out.println("-".repeat(tong));
        System.out.println("Tong so phieu nhap hang: " + n);
    }
    public void docFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(TEN_FILE));
            String dong;
            pnh=new PhieuNhapHang[0];
            n = 0;
            while ((dong = br.readLine()) != null) {
                if (!dong.trim().isEmpty()) {
                    pnh=Arrays.copyOf(pnh,n+1);
                    pnh[n] = PhieuNhapHang.fromFileString(dong);
                    n++;
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file phieu nhap hang!");
            System.out.println(e.getMessage());

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file phieu nhap hang!");
            }
        }
    }
    public void ghiFile(){
        BufferedWriter bw = null;
        try{
            bw=new BufferedWriter(new FileWriter(TEN_FILE));
            for(int i=0;i<n;i++){
                bw.write(pnh[i].toFileString());
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Loi doc file phieu nhap hang!");
            System.out.println(e.getMessage());
        }finally{
            try{
                if(bw!=null){
                    bw.close();
                }
            }catch(IOException e){
                System.out.println("Loi dong file phieu nhap hang!");
            }
        }
    }
}
