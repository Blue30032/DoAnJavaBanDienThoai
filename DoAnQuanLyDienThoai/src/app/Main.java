package app;

import helper.Nhaplieu;
import model.*;

public class Main {
    public static void main(String[] args) {
        // Tao cac doi tuong quan ly
        QLDSKH qlkh = new QLDSKH();
        QLDSNV qlnv = new QLDSNV();
        QLDSNCC qlncc = new QLDSNCC();
        QLDSHD qlhd = new QLDSHD();
        QLDSPNH qlpnh = new QLDSPNH();

        // Doc du lieu tu cac file TXT
        QuanLy quanLy = new QuanLy();
        quanLy.docDuLieu();
        int chon;
        do {
            String[] muc = {
                "Quan ly san pham",
                "Quan ly khach hang",
                "Quan ly nhan vien",
                "Quan ly nha cung cap",
                "Quan ly hoa don",
                "Quan ly phieu nhap hang"
            };
            chon = Nhaplieu.menuChinh(
                    "QUAN LY CUA HANG DIEN THOAI",
                    muc
            );
            switch (chon) {
                case 1:
                    QLDSSP.menu();
                    break;
                case 2:
                    qlkh.menuQLDSKH();
                    break;
                case 3:
                    qlnv.menuQLDSNV();
                    break;
                case 4:
                    qlncc.menu();
                    break;
                case 5:
                    qlhd.menu();
                    break;
                case 6:
                    qlpnh.menu();
                    break;
                case 0:
                    System.out.println("\nDang luu du lieu...");
                    quanLy.ghiDuLieu();
                    System.out.println(
                            "Da luu du lieu thanh cong!"
                    );
                    System.out.println("Ket thuc chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
}
