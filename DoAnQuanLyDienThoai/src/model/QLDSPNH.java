package model;

import helper.QuanLy;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
public class QLDSPNH extends QuanLy {
    DSPhieuNhapHang dsPNH= new DSPhieuNhapHang();
    DSCTPhieuNhapHang dsCT= new DSCTPhieuNhapHang();
    DSNhaCungCap dsNCC= new DSNhaCungCap();
    DSNV dsNV=new DSNV();
    DSSanPham dsSP=new DSSanPham();
    
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public void menu(){
        int chon;
        do{
            System.out.println("\n========== QUAN LY PHIEU NHAP ==========");
            System.out.println("1. Quan ly phieu nhap hang");
            System.out.println("2. Quan ly chi tiet phieu nhap hang");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon=Integer.parseInt(sc.nextLine());
            switch(chon){
                case 1:
                    menuPNH();
                    break;
                case 2:
                    menuCT();
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        }while(chon!=0);
    }
    public void menuPNH(){
        int chon;
        do{
            System.out.println("\n========== QUAN LY PHIEU NHAP HANG ==========");
            System.out.println("1. Xem danh sach phieu nhap hang");
            System.out.println("2. Them phieu nhap hang");
            System.out.println("3. Sua phieu nhap hang");
            System.out.println("4. Xoa phieu nhap hang");
            System.out.println("5. Tim phieu nhap theo ma");
            System.out.println("6. Tim phieu nhap theo ma nha cung cap");
            System.out.println("7. Tim phieu nhap theo ma nhan vien");
            System.out.println("8. Tim phieu nhap theo ngay lap");
            System.out.println("9. Thong ke giao dich theo nha cung cap");
            System.out.println("10. Thong ke giao dich theo nhan vien");
            System.out.println("11. Thong ke tong tien theo nha cung cap");
            System.out.println("12. Thong ke tong tien theo nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon=Integer.parseInt(sc.nextLine());
            switch(chon){
                case 1:
                    dsPNH.xuat();
                    break;
                case 2:
                    System.out.print("Nhap ma PNH:");
                    String maPNH=sc.nextLine();
                    if(dsPNH.IfindMaPNH(maPNH)!=-1){
                        System.out.println("Da ton tai ma phieu nay!");
                        System.out.println("Them khong thanh cong!");
                        break;
                    }
                    System.out.print("Nhap ma NCC:");
                    String maNCC=sc.nextLine();
                    if(dsNCC.IfindMaNCC(maNCC)==-1){
                        System.out.println("Khong ton tai nha cung cap nay!");
                        System.out.println("Them khong thanh cong!");
                        break;
                    }
                    System.out.print("Nhap ma NV:");
                    String maNV=sc.nextLine();
                    if(dsNV.timViTriTheoMa(maNV)==-1){
                        System.out.println("Khong ton tai nhan vien nay!");
                        System.out.println("Them khong thanh cong!");
                        break;
                    }
                    System.out.print("Nhap ngay lap (dd/MM/yyyy):");
                    LocalDate ngay=LocalDate.parse(sc.nextLine(), dtf);
                    System.out.print("Nhap so luong chi tiet PNH:");
                    int n=Integer.parseInt(sc.nextLine());
                    while(n>0){
                        if(dsCT.them(maPNH,dsSP,dsPNH)){
                            n--;
                        }
                        else{
                            System.out.println("Moi nhap lai!");
                        }
                    }
                    PhieuNhapHang x=new PhieuNhapHang(maPNH,maNCC,maNV,ngay,0);
                    x.setTongTien(dsCT);
                    dsPNH.them(x);
                    break;
                case 3:
                    System.out.print("Nhap ma phieu nhap hang muon sua: ");
                    String maSua=sc.nextLine();
                    dsPNH.sua(maSua);
                    break;
                case 4:
                    System.out.print("Nhap ma phieu nhap hang muon xoa: ");
                    String maXoa=sc.nextLine();
                    dsPNH.xoa(maXoa);
                    break;
                case 5:
                    System.out.print("Nhap ma phieu nhap hang can tim: ");
                    String maTim=sc.nextLine();
                    PhieuNhapHang pnh=dsPNH.findMaPNH(maTim);
                    if(pnh!=null){
                        System.out.println("Tim thay phieu nhap hang:");
                        System.out.println(pnh);
                    }
                    else{
                        System.out.println("Khong tim thay phieu nhap hang!");
                    }
                    break;
                case 6:
                    System.out.print("Nhap ma nha cung cap: ");
                    String mancc=sc.nextLine();
                    dsPNH.findMaNCC(mancc).xuat();
                    break;
                case 7:
                    System.out.print("Nhap ma nhan vien: ");
                    String manv=sc.nextLine();
                    dsPNH.findMaNV(manv).xuat();
                    break;
                case 8:
                    System.out.print("Nhap ngay lap (dd/MM/yyyy): ");
                    LocalDate ngaylap = LocalDate.parse(sc.nextLine(), dtf);
                    dsPNH.findNgayLap(ngaylap).xuat();
                    break;
                case 9:
                    dsPNH.thongkeNCC();
                    break;
                case 10:
                    dsPNH.thongkeNV();
                    break;
                case 11:
                    dsPNH.thongkeTongTienNCC();
                    break;
                case 12:
                    dsPNH.thongkeTongTienNV();
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        }while(chon!=0);
    }
    public void menuCT(){
        int chon;
        do{
            System.out.println("\n========== QUAN LY CHI TIET PHIEU NHAP HANG ==========");
            System.out.println("1. Xem danh sach phieu chi tiet phieu nhap hang");
            System.out.println("2. Them chi tiet phieu nhap hang");
            System.out.println("3. Sua chi tiet phieu nhap hang");
            System.out.println("4. Xoa chi tiet phieu nhap hang");
            System.out.println("5. Tim chi tiet phieu nhap theo ma phieu nhap hang");
            System.out.println("6. Tim chi tiet phieu nhap theo ma san pham");
            System.out.println("7. Thong ke so luong theo ma phieu nhap hang");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon=Integer.parseInt(sc.nextLine());
            switch(chon){
                case 1:
                    dsCT.xuat();
                    break;
                case 2:
                    System.out.print("Nhap ma PNH:");
                    String maPNH=sc.nextLine();
                    if(dsPNH.IfindMaPNH(maPNH)==-1){
                        System.out.println("Khong ton tai ma phieu nay!");
                        System.out.println("Them khong thanh cong!");
                        break;
                    }
                    System.out.print("Nhap ma SP:");
                    String maSP=sc.nextLine();
                    if(dsSP.timTheoMa(maSP)==null){
                        System.out.println("Khong ton tai ma san pham nay!");
                        System.out.println("Them khong thanh cong!");
                        break;
                    }
                    System.out.print("Nhap so luong:");
                    int soLuong=Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap don gia:");
                    double donGia = Double.parseDouble(sc.nextLine());
                    CTPhieuNhapHang x=new CTPhieuNhapHang(maPNH,maSP,soLuong,donGia);
                    dsCT.them(x);
                    break;
                case 3:
                    System.out.print("Nhap ma PNH:");
                    String pn=sc.nextLine();
                    System.out.print("Nhap ma SP:");
                    String sp=sc.nextLine();
                    dsCT.sua(pn,sp,dsSP);
                    break;
                case 4:
                    System.out.print("Nhap ma PNH:");
                    String Pn=sc.nextLine();
                    System.out.print("Nhap ma SP:");
                    String Sp=sc.nextLine();
                    dsCT.xoa(Pn,Sp,dsSP);
                    break;
                case 5:
                    System.out.print("Nhap ma PNH:");
                    String PN=sc.nextLine();
                    dsCT.findPNH(PN).xuat();
                    break;
                case 6:
                    System.out.print("Nhap ma SP:");
                    String SP=sc.nextLine();
                    dsCT.findSP(SP).xuat();
                    break;
                case 7:
                    dsCT.thongkeSoLuong();
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        }while(chon!=0);
    }
}

