
package DoAnBanDienThoai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
    public boolean suaTheoMa(String maHD, HoaDon hdMoi) {
        int viTri = timViTriTheoMa(maHD);

        if (viTri == -1 || hdMoi == null) {
            return false;
        }

        if (!maHD.equalsIgnoreCase(hdMoi.getMaHD())
                && timViTriTheoMa(hdMoi.getMaHD()) != -1) {
            System.out.println("Ma hoa don moi da ton tai!");
            return false;
        }

        ds[viTri] = new HoaDon(hdMoi);
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
