package model;

import java.io.IOException;

public class QLDSKH {
    public static DSKH dskh = new DSKH();
    
    public QLDSKH() {
        dskh.docFile();
    }
    
    public DSKH getDskh() {
        return dskh;
    }

    // Them khach hang
    public void themKH() {
        String ma = nhapChuoi("Nhap ma khach hang: ");
        String ho = nhapChuoi("Nhap ho: ");
        String ten = nhapChuoi("Nhap ten: ");
        String gt = nhapChuoi("Nhap gioi tinh: ");
        String sdt = nhapChuoi("Nhap SDT: ");
        double tongTien = nhapSoThuc("Nhap tong tien da mua: ");

        KhachHang kh = new KhachHang(ma, ho, ten, gt, sdt, tongTien);

        if (dskh.them(kh)) {
            System.out.println("Them khach hang thanh cong!");
        } else {
            System.out.println("Them khach hang that bai!");
        }
    }

    // Nhap so thuc
    public double nhapSoThuc(String thongBao) {
        while (true) {
            try {
                System.out.print(thongBao);
                double so = Double.parseDouble(QLDSNV.sc.nextLine());

                if (so >= 0) {
                    return so;
                }

                System.out.println("Tong tien khong duoc am!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    // Xuat ket qua tim kiem
    public void xuatKetQua(KhachHang[] ds) {
        if (ds.length == 0) {
            System.out.println("Khong tim thay ket qua!");
            return;
        }

        System.out.println("\n========== KET QUA TIM KIEM ==========");
        for (int i = 0; i < ds.length; i++) {
            ds[i].xuat();
        }
    }

    // Nhap chuoi
    public String nhapChuoi(String thongBao) {
        System.out.print(thongBao);
        return QLDSNV.sc.nextLine();
    }

    // Nhap so nguyen
    public int nhapSoNguyen(String thongBao, int min, int max) {
        int so;

        while (true) {
            try {
                System.out.print(thongBao);
                so = Integer.parseInt(QLDSNV.sc.nextLine());

                if (so >= min && so <= max) {
                    return so;
                }

                System.out.println("Vui long nhap tu " + min + " den " + max);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    // Menu thong ke
    public void menuThongKe() {
        int chon;

        do {
            System.out.println("\n========== THONG KE KHACH HANG ==========");
            System.out.println("1. Thong ke theo gioi tinh");
            System.out.println("2. Thong ke theo tong tien");
            System.out.println("0. Quay lai");

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 2);

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

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 10);

            switch (chon) {
                case 1:
                    dskh.xuatDanhSach();
                    break;
                case 2:
                    themKH();
                    break;
                case 3:
                    String maSua = nhapChuoi("Nhap ma khach hang can sua: ");
                    String ho = nhapChuoi("Nhap ho moi: ");
                    String ten = nhapChuoi("Nhap ten moi: ");
                    String gt = nhapChuoi("Nhap gioi tinh moi: ");
                    String sdt = nhapChuoi("Nhap SDT moi: ");
                    double tongTien = nhapSoThuc("Nhap tong tien da mua moi: ");

                    KhachHang khMoi = new KhachHang(maSua, ho, ten, gt, sdt, tongTien);

                    if (dskh.suaTheoMa(maSua, khMoi)) {
                        System.out.println("Sua khach hang thanh cong!");
                    } else {
                        System.out.println("Sua khach hang that bai!");
                    }
                    break;

                case 4:
                    String maXoa = nhapChuoi("Nhap ma khach hang can xoa: ");
                    if (dskh.xoaTheoMa(maXoa)) {
                        System.out.println("Xoa khach hang thanh cong!");
                    } else {
                        System.out.println("Khong tim thay ma khach hang!");
                    }
                    break;

                case 5:
                    String maTim = nhapChuoi("Nhap ma khach hang can tim: ");
                    KhachHang khTim = dskh.timTheoMa(maTim);

                    if (khTim != null) {
                        khTim.xuat();
                    } else {
                        System.out.println("Khong tim thay khach hang!");
                    }
                    break;
                case 6:
                    xuatKetQua(dskh.timTheoHo(nhapChuoi("Nhap ho can tim: ")));
                    break;
                case 7:
                    xuatKetQua(dskh.timTheoTen(nhapChuoi("Nhap ten can tim: ")));
                    break;
                case 8:
                    xuatKetQua(dskh.timTheogtinhKH(nhapChuoi("Nhap gioi tinh: ")));
                    break;
                case 9:
                    xuatKetQua(dskh.timTheoSDT(nhapChuoi("Nhap SDT: ")));
                    break;
                case 10:
                    menuThongKe();
                    break;
            }
        } while (chon != 0);
    }
    public static void main(String[] args) {
        QLDSKH qlkh = new QLDSKH();
        qlkh.menuQLDSKH();
    }
}
