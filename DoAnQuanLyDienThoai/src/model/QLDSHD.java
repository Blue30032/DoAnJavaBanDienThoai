
package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class QLDSHD {

    public static DSHoaDon dshd = new DSHoaDon();
    public static DSChiTietHoaDon dscthd = new DSChiTietHoaDon();
    public static DSSanPham dssp = QLDSSP.dssp;

    // ================= MENU QUAN LY HOA DON =================

    public void menu() {
        int chon;

        do {
            System.out.println("\n========== QUAN LY HOA DON ==========");
            System.out.println("1. Xuat danh sach hoa don");
            System.out.println("2. Them hoa don");
            System.out.println("3. Sua hoa don");
            System.out.println("4. Xoa hoa don");
            System.out.println("5. Tim hoa don theo ma");
            System.out.println("6. Xuat danh sach chi tiet hoa don");
            System.out.println("7. Thong ke hoa don");
            System.out.println("0. Quay lai");
            System.out.println("=====================================");

            chon = nhapSoNguyen("Nhap lua chon: ", 0, 7);

            switch (chon) {
                case 1:
                    dshd.xuatDanhSach();
                    break;

                case 2:
                    themHoaDon();
                    break;

                case 3:
                    String maSua = nhapChuoi("Nhap ma hoa don can sua: ");
                    dshd.suaTheoMa(maSua);
                    break;

                case 4:
                    String maXoa = nhapChuoi("Nhap ma hoa don can xoa: ");

                    if (dshd.xoaTheoMa(maXoa)) {
                        System.out.println("Xoa hoa don thanh cong!");
                    } else {
                        System.out.println("Khong tim thay hoa don!");
                    }
                    break;

                case 5:
                    String maTim = nhapChuoi("Nhap ma hoa don can tim: ");
                    HoaDon hd = dshd.timTheoMa(maTim);

                    if (hd != null) {
                        System.out.println("Tim thay hoa don:");
                        System.out.println(hd);
                    } else {
                        System.out.println("Khong tim thay hoa don!");
                    }
                    break;

                case 6:
                    dscthd.xuatDanhSach();
                    break;

                case 7:
                    menuThongKe();
                    break;

                case 0:
                    System.out.println("Quay lai menu chinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }

        } while (chon != 0);
    }

    // ================= MENU THONG KE =================

    public static void menuThongKe() {
        int chon;

        do {
            System.out.println("\n========== THONG KE HOA DON ==========");
            System.out.println("1. Thong ke tong so hoa don va doanh thu");
            System.out.println("2. Thong ke doanh thu theo ngay");
            System.out.println("3. Thong ke doanh thu theo quy");
            System.out.println("4. Thong ke doanh thu theo khach hang");
            System.out.println("0. Quay lai");
            System.out.println("======================================");

            chon = nhapSoNguyenStatic("Nhap lua chon: ", 0, 4);

            switch (chon) {
                case 1:
                    dshd.thongKeTongTien();
                    break;

                case 2:
                    try {
                        System.out.print("Nhap ngay (yyyy-MM-dd): ");
                        LocalDate ngay = LocalDate.parse(
                            QLDSNV.sc.nextLine()
                        );
                        dshd.thongKeTheoNgay(ngay);

                    } catch (DateTimeParseException e) {
                        System.out.println("Ngay khong hop le!");
                    }
                    break;

                case 3:
                    int quy = nhapSoNguyenStatic(
                        "Nhap quy (1-4): ", 1, 4
                    );

                    int nam = nhapSoNguyenStatic(
                        "Nhap nam: ", 1, 9999
                    );

                    dshd.thongKeTheoQuy(quy, nam);
                    break;

                case 4:
                    System.out.print("Nhap ma khach hang: ");
                    String maKH = QLDSNV.sc.nextLine().trim();
                    dshd.thongKeTheoKhachHang(maKH);
                    break;

                case 0:
                    System.out.println("Quay lai menu quan ly hoa don!");
                    break;
            }

        } while (chon != 0);
    }

    // ================= NHAP DU LIEU =================

    public String nhapChuoi(String thongBao) {
        System.out.print(thongBao);
        return QLDSNV.sc.nextLine().trim();
    }

    public int nhapSoNguyen(String thongBao, int min, int max) {
        int so;

        while (true) {
            try {
                System.out.print(thongBao);
                so = Integer.parseInt(QLDSNV.sc.nextLine());

                if (so >= min && so <= max) {
                    return so;
                }

                System.out.println(
                    "Vui long nhap tu " + min + " den " + max
                );

            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    public static int nhapSoNguyenStatic(String thongBao, int min, int max) {

        int so;

        while (true) {
            try {
                System.out.print(thongBao);
                so = Integer.parseInt(QLDSNV.sc.nextLine());

                if (so >= min && so <= max) {
                    return so;
                }

                System.out.println(
                    "Vui long nhap tu " + min + " den " + max
                );

            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    // ================= THEM HOA DON =================

    public void themHoaDon() {

        String maHD = nhapChuoi("Nhap ma hoa don: ");

        if (dshd.timTheoMa(maHD) != null) {
            System.out.println("Ma hoa don da ton tai!");
            return;
        }

        String maNV = nhapChuoi("Nhap ma nhan vien: ");

        if (QLDSNV.dsnv.timTheoMa(maNV) == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        String maKH = nhapChuoi("Nhap ma khach hang: ");

        if (QLDSKH.dskh.timTheoMa(maKH) == null) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        LocalDate ngayLap;

        try {
            String ngay = nhapChuoi("Nhap ngay lap (yyyy-MM-dd): ");
            ngayLap = LocalDate.parse(ngay);

        } catch (DateTimeParseException e) {
            System.out.println("Ngay lap khong hop le!");
            return;
        }

        HoaDon hd = new HoaDon(maHD, maNV, maKH, ngayLap, 0);

        if (!dshd.them(hd)) {
            System.out.println("Them hoa don that bai!");
            return;
        }

        System.out.println("Them hoa don thanh cong!");

        // Nhap cac chi tiet hoa don
        int k = nhapSoNguyen("Nhap so chi tiet hoa don: ", 1, 1000);

        for (int i = 0; i < k; i++) {

            System.out.println(
                "\n--- Chi tiet hoa don thu " + (i + 1) + " ---"
            );

            String maSP = nhapChuoi("Nhap ma san pham: ");

            SanPham sp = dssp.timTheoMa(maSP);

            if (sp == null) {
                System.out.println(
                    "Khong tim thay san pham, bo qua chi tiet nay!"
                );
                continue;
            }

            if (dscthd.timTheoMa(maHD, maSP) != null) {
                System.out.println(
                    "San pham da co trong hoa don, bo qua chi tiet nay!"
                );
                continue;
            }

            if (sp.getSoLuong() <= 0) {
                System.out.println(
                    "San pham da het hang, bo qua chi tiet nay!"
                );
                continue;
            }

            int soLuong = nhapSoNguyen(
                "Nhap so luong ban: ", 1, sp.getSoLuong()
            );

            double donGiaBan = sp.Giabanle();
            double thanhTien = soLuong * donGiaBan;

            CTHoaDon ct = new CTHoaDon(
                maHD, maSP, soLuong, donGiaBan, thanhTien
            );

            if (dscthd.them(ct)) {

                if (dssp.banhang(maSP, soLuong)) {
                    System.out.println(
                        "Them chi tiet hoa don thanh cong!"
                    );
                    System.out.println("Don gia ban: " + donGiaBan);
                    System.out.println("Thanh tien: " + thanhTien);

                } else {
                    dscthd.xoa(maHD, maSP);
                    System.out.println(
                        "Cap nhat so luong san pham that bai!"
                    );
                }

            } else {
                System.out.println("Them chi tiet hoa don that bai!");
            }
        }

        // Cap nhat tong tien hoa don
        dshd.capNhatTongTien(dscthd);

        System.out.println("\nHoan tat them hoa don!");
    }
}
