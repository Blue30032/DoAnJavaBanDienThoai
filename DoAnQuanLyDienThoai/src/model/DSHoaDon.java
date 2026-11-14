
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class DSHoaDon {
    private HoaDon[] ds;
    private int n;
    private static final int MAX = 1000;
    private static final String TEN_FILE = "HoaDon.txt";

    public DSHoaDon() {
        ds = new HoaDon[MAX];
        n = 0;
    }

    public DSHoaDon(HoaDon[] ds, int n) {
        this();

        if (ds == null || n <= 0) {
            return;
        }

        for (int i = 0; i < n && i < ds.length && i < MAX; i++) {
            if (ds[i] != null) {
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

    // Them hoa don
    public boolean them(HoaDon hd) {
        if (hd == null || n >= MAX) {
            return false;
        }

        if (timViTriTheoMa(hd.getMaHD()) != -1) {
            System.out.println("Ma hoa don da ton tai!");
            return false;
        }

        ds[n] = new HoaDon(hd);
        n++;
        return true;
    }

    // Tim vi tri hoa don theo ma
    public int timViTriTheoMa(String maHD) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaHD().equalsIgnoreCase(maHD)) {
                return i;
            }
        }

        return -1;
    }

    // Tim hoa don theo ma
    public HoaDon timTheoMa(String maHD) {
        int viTri = timViTriTheoMa(maHD);

        if (viTri == -1) {
            return null;
        }

        return new HoaDon(ds[viTri]);
    }

    // Sua hoa don theo ma
    
    public boolean suaTheoMa(String maHD) {
        int viTri = timViTriTheoMa(maHD);

        if (viTri == -1) {
            System.out.println("Khong tim thay hoa don!");
            return false;
        }

        System.out.println("Nhap thong tin hoa don moi:");

        System.out.print("Nhap ma nhan vien moi: ");
        String maNV = QLDSNV.sc.nextLine();

        System.out.print("Nhap ma khach hang moi: ");
        String maKH = QLDSNV.sc.nextLine();

        System.out.print("Nhap ngay lap moi (yyyy-MM-dd): ");
        LocalDate ngayLap;

        try {
            ngayLap = LocalDate.parse(QLDSNV.sc.nextLine());
        } catch (Exception e) {
            System.out.println("Ngay lap khong hop le!");
            return false;
        }

        HoaDon hdMoi = new HoaDon(
            maHD,
            maNV,
            maKH,
            ngayLap,
            ds[viTri].getTongTien()
        );

        ds[viTri] = hdMoi;

        System.out.println("Sua hoa don thanh cong!");
        return true;
    }
        // Xoa hoa don theo ma
    public boolean xoaTheoMa(String maHD) {
        int viTri = timViTriTheoMa(maHD);

        if (viTri == -1) {
            return false;
        }

        for (int i = viTri; i < n - 1; i++) {
            ds[i] = ds[i + 1];
        }

        ds[n - 1] = null;
        n--;
        return true;
    }

    // Tinh tong tien hoa don
    public double tinhTongTien(String maHD, DSChiTietHoaDon dsct) {
        int viTri = timViTriTheoMa(maHD);

        if (viTri == -1 || dsct == null) {
            return 0;
        }

        return ds[viTri].tinhTongTien(dsct);
    }

    // Cap nhat tong tien cua tat ca hoa don
    public void capNhatTongTien(DSChiTietHoaDon dsct) {
        if (dsct == null) {
            return;
        }

        for (int i = 0; i < n; i++) {
            ds[i].tinhTongTien(dsct);
        }
    }

    // Xuat danh sach hoa don
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
    
    // Thong ke tong so hoa don va tong doanh thu
    public void thongKeTongTien() {
        double tongDoanhThu = 0;

        System.out.println("\n===== THONG KE HOA DON =====");
        System.out.println("Tong so hoa don: " + n);

        for (int i = 0; i < n; i++) {
            tongDoanhThu += ds[i].getTongTien();
        }

        System.out.printf("Tong doanh thu: %,.0f VND%n", tongDoanhThu);
    }

// Thong ke doanh thu theo ngay
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

// Thong ke doanh thu theo quy
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

// Thong ke doanh thu theo khach hang
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

    // Doc file
    public void docFile() {
        n = 0;

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(TEN_FILE));

            String dong;

            while ((dong = br.readLine()) != null && n < MAX) {
                if (dong.trim().isEmpty()) {
                    continue;
                }

                HoaDon hd = HoaDon.fromFileString(dong);
                them(hd);
            }

            System.out.println("Doc file hoa don thanh cong!");

        } catch (IOException e) {
            System.out.println("Khong the doc file " + TEN_FILE);
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

    // Ghi file
    public void ghiFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(TEN_FILE));

        for (int i = 0; i < n; i++) {
            bw.write(ds[i].toFileString());
            bw.newLine();
        }

        bw.close();
    }
}
