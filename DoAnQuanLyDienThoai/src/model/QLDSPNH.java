import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
public class QLDSPNH extends QuanLy {
    static DSPhieuNhapHang ds= new DSPhieuNhapHang();
    @Override
    public void menu(){
        Scanner sc = new Scanner(System.in);
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
            chon=sc.nextInt();
            sc.nextLine();
            switch(chon){
                case 1:
                    ds.xuat();
                    break;
                case 2:
                    ds.them();
                    System.out.println("Da them phieu nhap hang thanh cong!");
                    break;
                case 3:
                    System.out.print("Nhap ma phieu nhap hang muon sua: ");
                    String maSua=sc.nextLine();
                    ds.sua(maSua);
                    break;
                case 4:
                    System.out.print("Nhap ma phieu nhap hang muon xoa: ");
                    String maXoa=sc.nextLine();
                    ds.xoa(maXoa);
                    break;
                case 5:
                    System.out.print("Nhap ma phieu nhap hang can tim: ");
                    String maTim=sc.nextLine();
                    PhieuNhapHang pnh=ds.findMaPNH(maTim);
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
                    String maNCC=sc.nextLine();
                    DSPhieuNhapHang dsNCC=ds.findMaNCC(maNCC);
                    dsNCC.xuat();
                    break;
                case 7:
                    System.out.print("Nhap ma nhan vien: ");
                    String maNV=sc.nextLine();
                    DSPhieuNhapHang dsNV=ds.findMaNV(maNV);
                    dsNV.xuat();
                    break;
                case 8:
                    System.out.print("Nhap ngay lap (dd/MM/yyyy): ");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate ngay = LocalDate.parse(sc.nextLine(), dtf);
                    DSPhieuNhapHang dsNgay = ds.findNgayLap(ngay);
                    dsNgay.xuat();
                    break;
                case 9:
                    ds.thongkeNCC();
                    break;
                case 10:
                    ds.thongkeNV();
                    break;
                case 11:
                    ds.thongkeTongTienNCC();
                    break;
                case 12:
                    ds.thongkeTongTienNV();
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

