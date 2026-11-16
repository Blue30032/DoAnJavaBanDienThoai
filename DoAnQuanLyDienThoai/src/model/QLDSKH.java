
package model;

import helper.Nhaplieu;

public class QLDSKH extends QuanLy {

    public DSKH getDskh() {
        return dskh;
    }

    // Menu quan ly khach hang
    public void menuQLDSKH() {
        int chon;

        do {
            System.out.println("\n========== QUAN LY KHACH HANG ==========");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Them 1 khach hang");
            System.out.println("3. Sua khach hang theo ma");
            System.out.println("4. Xoa khach hang theo ma");
            System.out.println("5. Tim theo ma");
            System.out.println("6. Tim theo ho");
            System.out.println("7. Tim theo ten");
            System.out.println("8. Tim theo gioi tinh");
            System.out.println("9. Tim theo SDT");
            System.out.println("10. Thong ke");
            System.out.println("0. Quay lai");

            chon = Nhaplieu.docSoNguyen("Chon chuc nang: ", 0, 10);

            switch (chon) {
                case 1:
                    dskh.xuatDanhSach();
                    break;

                case 2:
                    themKH();
                    break;

                case 3:
                   dskh.sua();                    
                   break;

                case 4:
                   dskh.xoa();
                   break;

                case 5:
                    String maTim = Nhaplieu.docChuoi("Nhap ma khach hang can tim: ");
                    KhachHang khTim = dskh.timTheoMa(maTim);

                    if (khTim != null) {
                        khTim.xuat();
                    } else {
                        System.out.println("Khong tim thay khach hang!");
                    }
                    break;

                case 6:
                    xuatKetQua(dskh.timTheoHo(
                        Nhaplieu.docChuoi("Nhap ho can tim: ")));
                    break;

                case 7:
                    xuatKetQua(dskh.timTheoTen(
                        Nhaplieu.docChuoi("Nhap ten can tim: ")));
                    break;

                case 8:
                    xuatKetQua(dskh.timTheogtinhKH(
                        Nhaplieu.docChuoi("Nhap gioi tinh: ")));
                    break;

                case 9:
                    xuatKetQua(dskh.timTheoSDT(
                        Nhaplieu.docChuoi("Nhap SDT: ")));
                    break;

                case 10:
                    menuThongKe();
                    break;
            }

        } while (chon != 0);
    }

    // Menu thong ke
    public void menuThongKe() {
        int chon;

        do {
            System.out.println("\n========== THONG KE KHACH HANG ==========");
            System.out.println("1. Thong ke theo gioi tinh");
            System.out.println("2. Thong ke theo tong tien");
            System.out.println("0. Quay lai");

            chon = Nhaplieu.docSoNguyen("Chon chuc nang: ", 0, 2);

            switch (chon) {
                case 1:
                    dskh.thongKeTheoGioiTinh();
                    break;

                case 2:
                    dskh.thongKeTheoTongTien();
                    break;
            }

        } while (chon != 0);
    }

    // Them khach hang
    public void themKH() {
        String ma = Nhaplieu.docChuoi("Nhap ma khach hang: ");
        String ho = Nhaplieu.docChuoi("Nhap ho: ");
        String ten = Nhaplieu.docChuoi("Nhap ten: ");
        String gt = Nhaplieu.docChuoi("Nhap gioi tinh: ");
        String sdt = Nhaplieu.docChuoi("Nhap SDT: ");

        KhachHang kh = new KhachHang(ma, ho, ten, gt, sdt, 0);

        if (dskh.them(kh)) {
            System.out.println("Them khach hang thanh cong!");
        } else {
            System.out.println("Them khach hang that bai!");
        }
    }

    // Xuat ket qua tim kiem
    public void xuatKetQua(KhachHang[] ds) {
        if (ds == null || ds.length == 0) {
            System.out.println("Khong tim thay ket qua!");
            return;
        }

        System.out.println("\n========== KET QUA TIM KIEM ==========");
        for (int i = 0; i < ds.length; i++) {
            ds[i].xuat();
        }
    }
}
