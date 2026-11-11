package DoAnBanDienThoai;

import java.io.IOException;

public class QLDSKH {
    public static DSKH dskh = new DSKH();
    
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

    // Sua khach hang theo ma
    public void suaKH() {
        String ma = nhapChuoi("Nhap ma khach hang can sua: ");

        if (dskh.timTheoMa(ma) == null) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        System.out.println("Nhap thong tin moi:");

        String maMoi = nhapChuoi("Nhap ma khach hang: ");
        String ho = nhapChuoi("Nhap ho: ");
        String ten = nhapChuoi("Nhap ten: ");
        String gt = nhapChuoi("Nhap gioi tinh: ");
        String sdt = nhapChuoi("Nhap SDT: ");
        double tongTien = nhapSoThuc("Nhap tong tien da mua: ");

        KhachHang khMoi = new KhachHang(maMoi, ho, ten, gt, sdt, tongTien);

        if (dskh.suaTheoMa(ma, khMoi)) {
            System.out.println("Sua khach hang thanh cong!");
        } else {
            System.out.println("Sua khach hang that bai!");
        }
    }

    // Xoa khach hang
    public void xoaKH() {
        String ma = nhapChuoi("Nhap ma khach hang can xoa: ");

        if (dskh.xoaTheoMa(ma)) {
            System.out.println("Xoa khach hang thanh cong!");
        } else {
            System.out.println("Khong tim thay khach hang!");
        }
    }

    // Tim khach hang theo ma
    public void timTheoMa() {
        String ma = nhapChuoi("Nhap ma khach hang can tim: ");
        KhachHang kh = dskh.timTheoMa(ma);

        if (kh == null) {
            System.out.println("Khong tim thay khach hang!");
        } else {
            KhachHang[] ketQua = new KhachHang[1];
            ketQua[0] = kh;
            xuatKetQua(ketQua);
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
            System.out.println("3. Them k khach hang");
            System.out.println("4. Sua khach hang theo ma");
            System.out.println("5. Xoa khach hang theo ma");
            System.out.println("6. Tim theo ma");
            System.out.println("7. Tim theo ho");
            System.out.println("8. Tim theo ten");
            System.out.println("9. Tim theo gioi tinh");
            System.out.println("10. Tim theo SDT");
            System.out.println("11. Thong ke");
            System.out.println("0. Quay lai");

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 11);

            switch (chon) {
                case 1:
                    dskh.xuatDanhSach();
                    break;
                case 2:
                    themKH();
                    break;
                case 3:
                    int k = nhapSoNguyen("Nhap k: ", 1, 100);

                    for (int i = 1; i <= k; i++) {
                        System.out.println("--- Khach hang thu " + i + "/" + k + " ---");
                        themKH();
                    }
                    break;
                case 4:
                    suaKH();
                    break;
                case 5:
                    xoaKH();
                    break;
                case 6:
                    timTheoMa();
                    break;
                case 7:
                    xuatKetQua(dskh.timTheoHo(nhapChuoi("Nhap ho can tim: ")));
                    break;
                case 8:
                    xuatKetQua(dskh.timTheoTen(nhapChuoi("Nhap ten can tim: ")));
                    break;
                case 9:
                    xuatKetQua(dskh.timTheogtinhKH(nhapChuoi("Nhap gioi tinh: ")));
                    break;
                case 10:
                    xuatKetQua(dskh.timTheoSDT(nhapChuoi("Nhap SDT: ")));
                    break;
                case 11:
                    menuThongKe();
                    break;
            }
        } while (chon != 0);
    }

    // Doc file
    public void docFile() {
        dskh.docFile();
    }

    // Ghi file
    public void ghiFile() {
        try {
            dskh.ghiFile();
            System.out.println("Ghi file khach hang thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi file khach hang!");
        }
    }
}
