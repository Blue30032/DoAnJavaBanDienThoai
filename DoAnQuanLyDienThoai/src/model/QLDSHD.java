
package model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class QLDSHD {

    public static DSHoaDon dshd = new DSHoaDon();
    public static DSChiTietHoaDon dscthd = new DSChiTietHoaDon();

    // Dùng chung danh sách sản phẩm của bạn phụ trách
    public static DSSanPham dssp = QLDSSP.dssp;

    // ================= NHẬP DỮ LIỆU =================

    public String nhapChuoi(String thongBao) {
        System.out.print(thongBao);
        return QLDSNV.sc.nextLine();
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

                System.out.println("Vui long nhap tu " + min + " den " + max);

            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    public double nhapSoThuc(String thongBao) {
        double so;

        while (true) {
            try {
                System.out.print(thongBao);
                so = Double.parseDouble(QLDSNV.sc.nextLine());

                if (so >= 0) {
                    return so;
                }

                System.out.println("Gia tri khong duoc am!");

            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    public boolean xacNhan(String thongBao) {
        System.out.print(thongBao + " (y/n): ");
        String chon = QLDSNV.sc.nextLine();

        return chon.equalsIgnoreCase("y");
    }

    // ================= THÊM HÓA ĐƠN =================

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

        if (dshd.them(hd)) {
            System.out.println("Them hoa don thanh cong!");
        } else {
            System.out.println("Them hoa don that bai!");
        }
    }

    // ================= SỬA HÓA ĐƠN =================

    public void suaHoaDon() {

        String ma = nhapChuoi("Nhap ma hoa don can sua: ");

        HoaDon hd = dshd.timTheoMa(ma);

        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }

        System.out.println("Nhap thong tin moi:");

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

        HoaDon hdMoi = new HoaDon(
            ma, maNV, maKH, ngayLap, hd.getTongTien()
        );

        if (dshd.suaTheoMa(ma, hdMoi)) {
            System.out.println("Sua hoa don thanh cong!");
        } else {
            System.out.println("Sua hoa don that bai!");
        }
    }

    // ================= XÓA HÓA ĐƠN =================

    public void xoaHoaDon() {

        String ma = nhapChuoi("Nhap ma hoa don can xoa: ");

        HoaDon hd = dshd.timTheoMa(ma);

        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }

        // Kiểm tra hóa đơn còn chi tiết hay không
        CTHoaDon[] ds = dscthd.getDs();

        for (int i = 0; i < dscthd.getN(); i++) {
            if (ds[i] != null && ds[i].getMaHD().equals(ma)) {
                System.out.println("Hoa don van con chi tiet!");
                System.out.println("Hay xu ly chi tiet hoa don truoc.");
                return;
            }
        }

        if (xacNhan("Ban co chac muon xoa hoa don")) {
            if (dshd.xoaTheoMa(ma)) {
                System.out.println("Xoa hoa don thanh cong!");
            } else {
                System.out.println("Xoa hoa don that bai!");
            }
        }
    }

    // ================= TÌM HÓA ĐƠN =================

    public void timTheoMa() {

        String ma = nhapChuoi("Nhap ma hoa don can tim: ");

        HoaDon hd = dshd.timTheoMa(ma);

        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
        } else {
            System.out.println(hd);
        }
    }

    // ================= THÊM K HÓA ĐƠN =================

    public void themKHoaDon() {

        int k = nhapSoNguyen("Nhap so luong hoa don can them: ", 1, 100);

        for (int i = 1; i <= k; i++) {
            System.out.println("\n--- Hoa don thu " + i + "/" + k + " ---");
            themHoaDon();
        }
    }

    // ================= THÊM CHI TIẾT HÓA ĐƠN =================

    public void themChiTietHoaDon() {

        String maHD = nhapChuoi("Nhap ma hoa don: ");

        if (dshd.timTheoMa(maHD) == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }

        String maSP = nhapChuoi("Nhap ma san pham: ");

        SanPham sp = dssp.timTheoMa(maSP);

        if (sp == null) {
            System.out.println("Khong tim thay san pham!");
            return;
        }

        if (dscthd.timTheoMa(maHD, maSP) != null) {
            System.out.println("San pham da co trong hoa don!");
            return;
        }

        int soLuong = nhapSoNguyen(
            "Nhap so luong ban: ", 1, sp.getSoLuong()
        );

        // Lấy giá bán lẻ của sản phẩm
        double donGiaBan = sp.Giabanle();

        // Tính thành tiền
        double thanhTien = soLuong * donGiaBan;

        CTHoaDon ct = new CTHoaDon(
            maHD, maSP, soLuong, donGiaBan, thanhTien
        );

        if (dscthd.them(ct)) {

            if (dssp.banhang(maSP, soLuong)) {

                // Cập nhật tổng tiền hóa đơn
                dshd.capNhatTongTien(dscthd);

                System.out.println("Them chi tiet hoa don thanh cong!");
                System.out.println("Don gia ban: " + donGiaBan);
                System.out.println("Thanh tien: " + thanhTien);

            } else {
                dscthd.xoa(maHD, maSP);
                System.out.println("Cap nhat so luong san pham that bai!");
            }

        } else {
            System.out.println("Them chi tiet hoa don that bai!");
        }
    }

    // ================= XUẤT CHI TIẾT THEO HÓA ĐƠN =================

    public void timChiTietTheoMaHD() {

        String maHD = nhapChuoi("Nhap ma hoa don: ");

        if (dshd.timTheoMa(maHD) == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }

        CTHoaDon[] ds = dscthd.getDs();
        boolean timThay = false;

        for (int i = 0; i < dscthd.getN(); i++) {

            if (ds[i] != null && ds[i].getMaHD().equals(maHD)) {
                ds[i].xuat();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Hoa don chua co chi tiet!");
        }
    }

    // ================= MENU CHI TIẾT HÓA ĐƠN =================

    public void menuChiTietHoaDon() {

        int chon;

        do {
            System.out.println("\n========== QUAN LY CHI TIET HOA DON ==========");
            System.out.println("1. Xem danh sach chi tiet hoa don");
            System.out.println("2. Them chi tiet hoa don");
            System.out.println("3. Tim chi tiet theo ma hoa don");
            System.out.println("0. Quay lai");

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 3);

            switch (chon) {
                case 1:
                    dscthd.xuatDanhSach();
                    break;

                case 2:
                    themChiTietHoaDon();
                    break;

                case 3:
                    timChiTietTheoMaHD();
                    break;
            }

        } while (chon != 0);
    }

    // ================= ĐỌC FILE =================

    public void docFile() {

        dshd.docFile();
        dscthd.docFile();

        dshd.capNhatTongTien(dscthd);

        System.out.println("Doc file hoa don va chi tiet hoa don thanh cong!");
    }

    // ================= GHI FILE =================

    public void ghiFile() {

        try {
            dshd.ghiFile();
            dscthd.ghiFile();

            System.out.println("Ghi file hoa don va chi tiet hoa don thanh cong!");

        } catch (IOException e) {
            System.out.println("Loi ghi file hoa don!");
        }
    }

    // ================= MENU QUẢN LÝ HÓA ĐƠN =================

    public void menuQLDSHoaDon() {

        int chon;

        do {
            System.out.println("\n========== QUAN LY HOA DON ==========");
            System.out.println("1. Xem danh sach hoa don");
            System.out.println("2. Them 1 hoa don");
            System.out.println("3. Them k hoa don");
            System.out.println("4. Sua hoa don theo ma");
            System.out.println("5. Xoa hoa don theo ma");
            System.out.println("6. Tim hoa don theo ma");
            System.out.println("7. Quan ly chi tiet hoa don");
            System.out.println("8. Doc file");
            System.out.println("9. Ghi file");
            System.out.println("0. Quay lai");

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 9);

            switch (chon) {

                case 1:
                    dshd.xuatDanhSach();
                    break;

                case 2:
                    themHoaDon();
                    break;

                case 3:
                    themKHoaDon();
                    break;

                case 4:
                    suaHoaDon();
                    break;

                case 5:
                    xoaHoaDon();
                    break;

                case 6:
                    timTheoMa();
                    break;

                case 7:
                    menuChiTietHoaDon();
                    break;

                case 8:
                    docFile();
                    break;

                case 9:
                    ghiFile();
                    break;
            }

        } while (chon != 0);
    }
}
