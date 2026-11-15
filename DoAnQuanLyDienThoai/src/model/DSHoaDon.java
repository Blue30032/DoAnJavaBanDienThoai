
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Arrays;

import helper.IDocGhiFile;
import helper.Nhaplieu;

public class DSHoaDon implements IDocGhiFile {
    private HoaDon[] ds;
    private int n;
    private static final String TEN_FILE = "HoaDon.txt";

    public DSHoaDon() {
        ds = new HoaDon[0];
        n = 0;
    }

    public DSHoaDon(HoaDon[] ds, int n) {
        this();

        if (ds == null || n <= 0) {
            return;
        }

        for (int i = 0; i < n && i < ds.length; i++) {
            if (ds[i] != null) {
                this.ds = Arrays.copyOf(this.ds, this.n + 1);
                this.ds[this.n] = new HoaDon(ds[i]);
                this.n++;
            }
        }
    }

    public int getN() {
        return n;
    }

    public HoaDon[] getDs() {
        HoaDon[] ketQua = new HoaDon[n];

        for (int i = 0; i < n; i++) {
            ketQua[i] = new HoaDon(ds[i]);
        }

        return ketQua;
    }

//==========================THEM HOA DON=========================================

    public boolean them(HoaDon hd) {
        if (hd == null) {
            return false;
        }

        if (timViTriTheoMa(hd.getMaHD()) != -1) {
            System.out.println("Ma hoa don da ton tai!");
            return false;
        }

        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[n] = new HoaDon(hd);
        n++;

        return true;
    }

//======================TIM VI TRI HOA DON THEO MA==============================

    public int timViTriTheoMa(String maHD) {
        if (maHD == null || maHD.trim().isEmpty()) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaHD().equalsIgnoreCase(maHD)) {
                return i;
            }
        }

        return -1;
    }

//=========================TIM HOA DON THEO MA==================================

    public HoaDon timTheoMa(String maHD) {
        int viTri = timViTriTheoMa(maHD);

        if (viTri == -1) {
            return null;
        }

        return new HoaDon(ds[viTri]);
    }

//==========================SUA HOA DON THEO MA=================================

    public boolean suaTheoMa(String maHD) {
        int i = timViTriTheoMa(maHD);

        if (i == -1) {
            System.out.println("Khong ton tai hoa don nay!");
            System.out.println("Sua that bai!");
            return false;
        }

        System.out.println("Da tim thay hoa don muon sua. Thong tin:");
        System.out.println("Ma hoa don: " + ds[i].getMaHD());
        System.out.println("Ma nhan vien: " + ds[i].getMaNV());
        System.out.println("Ma khach hang: " + ds[i].getMaKH());
        System.out.println("Ngay lap: " + ds[i].getNgayLap());
        System.out.println("Tong tien: " + ds[i].getTongTien());

        System.out.println("\nHay chon muc muon sua:");
        System.out.println("1. Ma nhan vien");
        System.out.println("2. Ma khach hang");
        System.out.println("3. Ngay lap hoa don");

        int chon = Nhaplieu.docSoNguyen("Chon", 1, 3);

        switch (chon) {
            case 1:
                ds[i].setMaNV(
                    Nhaplieu.docChuoi("Nhap ma nhan vien moi")
                );
                break;

            case 2:
                ds[i].setMaKH(
                    Nhaplieu.docChuoi("Nhap ma khach hang moi")
                );
                break;

            case 3:
                while (true) {
                    String ngay = Nhaplieu.docChuoi(
                        "Nhap ngay lap moi (yyyy-MM-dd)"
                    );

                    try {
                        ds[i].setNgayLap(LocalDate.parse(ngay));
                        break;
                    } catch (Exception e) {
                        System.out.println(
                            "Ngay lap khong hop le! Vui long nhap lai."
                        );
                    }
                }
                break;
        }

        System.out.println("Da sua thong tin hoa don thanh cong!");
        return true;
    }

//==========================XOA HOA DON THEO MA=================================

    public boolean xoaTheoMa(String maHD) {
        int i = timViTriTheoMa(maHD);

        if (i == -1) {
            System.out.println("Khong ton tai hoa don nay!");
            System.out.println("Xoa that bai!");
            return false;
        }

        for (int j = i; j < n - 1; j++) {
            ds[j] = ds[j + 1];
        }

        n--;
        ds = Arrays.copyOf(ds, n);

        System.out.println("Da xoa hoa don co ma " + maHD);
        return true;
    }

//==========================TINH TONG TIEN HOA DON==============================

    public double tinhTongTien(String maHD, DSChiTietHoaDon dsct) {
        int viTri = timViTriTheoMa(maHD);

        if (viTri == -1 || dsct == null) {
            return 0;
        }

        return ds[viTri].tinhTongTien(dsct);
    }

//======================CAP NHAT TONG TIEN TAT CA HOA DON=======================

    public void capNhatTongTien(DSChiTietHoaDon dsct) {
        if (dsct == null) {
            return;
        }

        for (int i = 0; i < n; i++) {
            ds[i].tinhTongTien(dsct);
        }
    }

//==========================XUAT DANH SACH HOA DON==============================

    public void xuatDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach hoa don rong!");
            return;
        }

        DateTimeFormatter dinhDang =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("================================================================================");
        System.out.printf("%-10s %-10s %-10s %-15s %20s%n",
                "Ma HD", "Ma NV", "Ma KH", "Ngay lap", "Tong tien");
        System.out.println("================================================================================");

        for (int i = 0; i < n; i++) {
            HoaDon hd = ds[i];

            System.out.printf("%-10s %-10s %-10s %-15s %,20.0f VND%n",
                    hd.getMaHD(),
                    hd.getMaNV(),
                    hd.getMaKH(),
                    hd.getNgayLap().format(dinhDang),
                    hd.getTongTien());
        }

        System.out.println("================================================================================");
    }

//======================THONG KE TONG SO HOA DON VA DOANH THU===================

    public void thongKeTongTien() {
        double tongDoanhThu = 0;

        System.out.println("\n===== THONG KE HOA DON =====");
        System.out.println("Tong so hoa don: " + n);

        for (int i = 0; i < n; i++) {
            tongDoanhThu += ds[i].getTongTien();
        }

        System.out.printf("Tong doanh thu: %,.0f VND%n", tongDoanhThu);
    }

//======================THONG KE DOANH THU THEO NGAY===========================

    public void thongKeTheoNgay(LocalDate ngay) {
        int soHoaDon = 0;
        double doanhThu = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getNgayLap().equals(ngay)) {
                soHoaDon++;
                doanhThu += ds[i].getTongTien();
            }
        }

        System.out.println("\n===== THONG KE THEO NGAY =====");
        System.out.println("Ngay lap: " + ngay);
        System.out.println("So hoa don: " + soHoaDon);
        System.out.printf("Doanh thu: %,.0f VND%n", doanhThu);
    }

//======================THONG KE DOANH THU THEO QUY============================

    public void thongKeTheoQuy(int quy, int nam) {
        if (quy < 1 || quy > 4) {
            System.out.println("Quy khong hop le!");
            return;
        }

        int soHoaDon = 0;
        double doanhThu = 0;

        for (int i = 0; i < n; i++) {
            LocalDate ngay = ds[i].getNgayLap();

            if (ngay.getYear() == nam
                    && (ngay.getMonthValue() - 1) / 3 + 1 == quy) {
                soHoaDon++;
                doanhThu += ds[i].getTongTien();
            }
        }

        System.out.println("\n===== THONG KE THEO QUY =====");
        System.out.println("Quy: " + quy + " - Nam: " + nam);
        System.out.println("So hoa don: " + soHoaDon);
        System.out.printf("Doanh thu: %,.0f VND%n", doanhThu);
    }

//======================THONG KE DOANH THU THEO KHACH HANG=====================

    public void thongKeTheoKhachHang(String maKH) {
        int soHoaDon = 0;
        double doanhThu = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaKH().equalsIgnoreCase(maKH)) {
                soHoaDon++;
                doanhThu += ds[i].getTongTien();
            }
        }

        System.out.println("\n===== THONG KE THEO KHACH HANG =====");
        System.out.println("Ma khach hang: " + maKH);
        System.out.println("So hoa don: " + soHoaDon);
        System.out.printf("Tong tien mua hang: %,.0f VND%n", doanhThu);
    }

//===============================DOC FILE=======================================

    @Override
    public void docFile() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(TEN_FILE));
            String dong;

            ds = new HoaDon[0];
            n = 0;

            while ((dong = br.readLine()) != null) {
                if (!dong.trim().isEmpty()) {
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n] = HoaDon.fromFileString(dong);
                    n++;
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file hoa don!");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file hoa don!");
            }
        }
    }

//===============================GHI FILE=======================================

    @Override
    public void ghiFile() {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(TEN_FILE));

            for (int i = 0; i < n; i++) {
                bw.write(ds[i].toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file hoa don!");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file hoa don!");
            }
        }
    }
}
