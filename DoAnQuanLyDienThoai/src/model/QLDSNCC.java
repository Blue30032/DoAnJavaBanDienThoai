package model;

import helper.Nhaplieu;

public class QLDSNCC extends QuanLy {
    public void menu(){
        int chon;
            do{
            System.out.println("\n========== QUAN LY NHA CUNG CAP ==========");
            System.out.println("1. Xem danh sach nha cung cap");
            System.out.println("2. Them nha cung cap");
            System.out.println("3. Sua nha cung cap");
            System.out.println("4. Xoa nha cung cap");
            System.out.println("5. Tim nha cung cap theo ma");
            System.out.println("6. Tim nha cung cap theo ten");
            System.out.println("7. Tim nha cung cap theo dia chi");
            System.out.println("8. Thong ke theo dia chi");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon=Integer.parseInt(Nhaplieu.docDong());
            switch(chon){
                case 1:
                    dsncc.xuat();
                    break;
                case 2:
                    NhaCungCap A=new NhaCungCap();
                    A.nhap();
                    if(dsncc.IfindMaNCC(A.getMaNCC())!=-1){
                        System.out.println("Da ton tai nha cung cap nay!");
                        break;
                    }
                    dsncc.them(A);
                    System.out.println("Da them thanh cong!");
                    break;
                case 3:
                    System.out.print("Nhap ma NCC muon sua: ");
                    String maSua=Nhaplieu.docDong();
                    dsncc.sua(maSua);
                    break;
                case 4:
                    System.out.print("Nhap ma NCC muon xoa: ");
                    String maXoa=Nhaplieu.docDong();
                    dsncc.xoa(maXoa);
                    break;
                case 5:
                    System.out.print("Nhap ma NCC can tim: ");
                    String maTim=Nhaplieu.docDong();
                    NhaCungCap x=dsncc.findMaNCC(maTim);
                    if(x!=null){
                        System.out.println("Tim thay NCC:");
                        System.out.println(x);
                    }
                    else{
                        System.out.println("Khong tim thay NCC!");
                    }
                    break;
                case 6:
                    System.out.print("Nhap ten NCC can tim: ");
                    String ten=Nhaplieu.docDong();
                    DSNhaCungCap dsNCCTen=dsncc.findTen(ten);
                    dsNCCTen.xuat();
                    break;
                case 7:
                    System.out.print("Nhap dia chi can tim: ");
                    String dc=Nhaplieu.docDong();
                    DSNhaCungCap dsDC=dsncc.findDiaChi(dc);
                    dsDC.xuat();
                    break;
                case 8:
                    dsncc.thongkeDiaChi();
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
