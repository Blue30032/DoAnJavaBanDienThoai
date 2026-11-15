package model;

import helper.Nhaplieu;

/**
 * Lop quan ly toan bo do an: tao cac lop quan ly con, doc/ghi file va hien menu chinh.
 * Ke thua QuanLy de dung chung cac danh sach static (dssp, dskh, dshd...).
 * Main chi con mot dong: new QLDoAn().menu();
 */
public class QLDoAn extends QuanLy {
    private final QLDSKH qlkh = new QLDSKH();
    private final QLDSNV qlnv = new QLDSNV();
    private final QLDSNCC qlncc = new QLDSNCC();
    private final QLDSHD qlhd = new QLDSHD();
    private final QLDSPNH qlpnh = new QLDSPNH();

    public void menu() {
        docDuLieu();   // doc tat ca file .txt (file chua co thi bo qua)
        String[] muc = {
            "Quan ly san pham",
            "Quan ly khach hang",
            "Quan ly nhan vien",
            "Quan ly nha cung cap",
            "Quan ly hoa don",
            "Quan ly phieu nhap hang",
            "Thong ke tong hop"
        };
        int chon;
        do {
            chon = Nhaplieu.menuChinh("QUAN LY CUA HANG DIEN THOAI", muc);
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
                case 7:
                    thongKeTongHop();
                    break;
                case 0:
                    System.out.println("\nDang luu du lieu...");
                    ghiDuLieu();
                    System.out.println("Da luu du lieu thanh cong!");
                    System.out.println("Ket thuc chuong trinh.");
                    break;
                default:
                    break;
            }
        } while (chon != 0);
    }

    private void thongKeTongHop() {
        double thu = 0;
        HoaDon[] hd = dshd.getDs();
        for (int i = 0; i < dshd.getN(); i++) {
            thu += hd[i].getTongTien();
        }
        System.out.println("\n===== THONG KE TONG HOP =====");
        System.out.println("So khach hang  : " + dskh.getN());
        System.out.println("So nhan vien   : " + dsnv.getN());
        System.out.println("So hoa don     : " + dshd.getN());
        System.out.printf("Tong doanh thu : %,.0f VND%n", thu);
    }
}
