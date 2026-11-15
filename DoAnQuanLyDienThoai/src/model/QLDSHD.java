package model;

import helper.Nhaplieu;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class QLDSHD extends QuanLy {

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

            chon = Nhaplieu.docSoNguyen("Nhap lua chon", 0, 7);

            switch (chon) {
                case 1:
                    dshd.xuatDanhSach();
                    break;

                case 2:
                    themHoaDon();
                    break;

                case 3:
                    String maSua = Nhaplieu.docChuoi(
                        "Nhap ma hoa don can sua"
                    );
                    dshd.suaTheoMa(maSua);
                    break;

                case 4:
                    String maXoa = Nhaplieu.docChuoi(
                        "Nhap ma hoa don can xoa"
                    );

                    if (dshd.xoaTheoMa(maXoa)) {
                        System.out.println("Xoa hoa don thanh cong!");
                    } else {
                        System.out.println("Khong tim thay hoa don!");
                    }

                    break;

                case 5:
                    String maTim = Nhaplieu.docChuoi(
                        "Nhap ma hoa don can tim"
                    );

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
            }

        } while (chon != 0);
    }

    public void menuChiTiet() {
        int chon;
        do {
            String[] muc = {"Xem danh sach", "Tim theo ma hoa don va ma san pham",
                    "Sua chi tiet", "Xoa chi tiet"};
            chon = Nhaplieu.menu("QUAN LY CHI TIET HOA DON", muc);
            switch (chon) {
                case 1:
                    dscthd.xuatDanhSach();
                    break;
                case 2:
                    CTHoaDon ct = dscthd.timTheoMa(
                            Nhaplieu.docChuoi("Ma hoa don"),
                            Nhaplieu.docChuoi("Ma san pham"));
                    if (ct == null) {
                        System.out.println("Khong tim thay chi tiet hoa don!");
                    } else {
                        ct.xuat();
                    }
                    break;
                case 3:
                    dscthd.sua(Nhaplieu.docChuoi("Ma hoa don"),
                            Nhaplieu.docChuoi("Ma san pham"));
                    break;
                case 4:
                    boolean daXoa = dscthd.xoa(
                            Nhaplieu.docChuoi("Ma hoa don"),
                            Nhaplieu.docChuoi("Ma san pham"));
                    System.out.println(daXoa ? "Xoa thanh cong!" : "Khong tim thay chi tiet!");
                    break;
                default:
                    break;
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

            chon = Nhaplieu.docSoNguyen("Nhap lua chon", 0, 4);

            switch (chon) {
                case 1:
                    dshd.thongKeTongTien();
                    break;

                case 2:
                    try {
                        String ngay = Nhaplieu.docChuoi(
                            "Nhap ngay (yyyy-MM-dd)"
                        );

                        LocalDate ngayLap = LocalDate.parse(ngay);
                        dshd.thongKeTheoNgay(ngayLap);

                    } catch (DateTimeParseException e) {
                        System.out.println("Ngay khong hop le!");
                    }
                    break;

                case 3:
                    int quy = Nhaplieu.docSoNguyen("Nhap quy", 1, 4);

                    int nam = Nhaplieu.docSoNguyen("Nhap nam", 1, 9999);

                    dshd.thongKeTheoQuy(quy, nam);
                    break;

                case 4:
                    String maKH = Nhaplieu.docChuoi("Nhap ma khach hang");
                    dshd.thongKeTheoKhachHang(maKH);
                    break;

                case 0:
                    System.out.println("Quay lai menu quan ly hoa don!");
                    break;
            }

        } while (chon != 0);
    }

    // ================= THEM HOA DON =================

    public void themHoaDon() {

        String maHD = Nhaplieu.docChuoi("Nhap ma hoa don");

        if (dshd.timTheoMa(maHD) != null) {
            System.out.println("Ma hoa don da ton tai!");
            return;
        }

        String maNV = Nhaplieu.docChuoi("Nhap ma nhan vien");

        if (QLDSNV.dsnv.timTheoMa(maNV) == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        String maKH = Nhaplieu.docChuoi("Nhap ma khach hang");

        if (QLDSKH.dskh.timTheoMa(maKH) == null) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        LocalDate ngayLap;

        try {
            String ngay = Nhaplieu.docChuoi(
                "Nhap ngay lap (yyyy-MM-dd)"
            );

            ngayLap = LocalDate.parse(ngay);

        } catch (DateTimeParseException e) {
            System.out.println("Ngay lap khong hop le!");
            return;
        }

        int k = Nhaplieu.docSoNguyen(
            "Nhap so chi tiet hoa don", 1, 1000
        );

        int soChiTietThem = 0;

        // Nhap cac chi tiet hoa don
        for (int i = 0; i < k; i++) {

            System.out.println(
                "\n--- Chi tiet hoa don thu " + (i + 1) + " ---"
            );

            String maSP = Nhaplieu.docChuoi("Nhap ma san pham");

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

            int soLuong = Nhaplieu.docSoNguyen(
                "Nhap so luong ban", 1, sp.getSoLuong()
            );

            double donGiaBan = sp.Giabanle();
            double thanhTien = soLuong * donGiaBan;

            CTHoaDon ct = new CTHoaDon(
                maHD, maSP, soLuong, donGiaBan, thanhTien
            );

            if (dscthd.them(ct)) {

                if (dssp.banhang(maSP, soLuong)) {
                    soChiTietThem++;

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
                System.out.println(
                    "Them chi tiet hoa don that bai!"
                );
            }
        }

        // Kiem tra da them duoc chi tiet hay chua
        if (soChiTietThem == 0) {
            System.out.println(
                "Khong co chi tiet hoa don hop le, khong the them hoa don!"
            );
            return;
        }

        // Them hoa don sau khi da them cac chi tiet
        HoaDon hd = new HoaDon(
            maHD, maNV, maKH, ngayLap, 0
        );

        if (dshd.them(hd)) {

            // Cap nhat tong tien hoa don
            dshd.capNhatTongTien(dscthd);

            System.out.println("\nThem hoa don thanh cong!");
            System.out.println("Hoan tat them hoa don!");

        } else {
            System.out.println("Them hoa don that bai!");
        }
    }
}
