import java.util.Scanner;
public class QLDSNCC extends DSNhaCungCap {
    public void menu(){
        int chon;
        Scanner sc = new Scanner(System.in);
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
            chon=sc.nextInt();
            sc.nextLine();
            switch(chon){
                case 1:
                    super.xuat();
                    break;
                case 2:
                    NhaCungCap A=new NhaCungCap();
                    A.nhap();
                    super.them(A);
                    System.out.println("Da them thanh cong!");
                    break;
                case 3:
                    System.out.print("Nhap ma NCC muon sua: ");
                    String maSua=sc.nextLine();
                    super.sua(maSua);
                    break;
                case 4:
                    System.out.print("Nhap ma NCC muon xoa: ");
                    String maXoa=sc.nextLine();
                    super.xoa(maXoa);
                    break;
                case 5:
                    System.out.print("Nhap ma NCC can tim: ");
                    String maTim=sc.nextLine();
                    NhaCungCap x=super.findMaNCC(maTim);
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
                    String ten=sc.nextLine();
                    DSNhaCungCap dsTen=super.findTen(ten);
                    dsTen.xuat();
                    break;
                case 7:
                    System.out.print("Nhap dia chi can tim: ");
                    String dc=sc.nextLine();
                    DSNhaCungCap dsDC=super.findDiaChi(dc);
                    dsDC.xuat();
                    break;
                case 8:
                    super.thongkeDiaChi();
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
