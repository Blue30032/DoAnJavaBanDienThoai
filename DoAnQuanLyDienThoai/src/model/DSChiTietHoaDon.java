package DoAnBanDienThoai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DSChiTietHoaDon {
    private CTHoaDon[] ds;
    private int n;
    private static final int MAX = 1000;
    private static final String TEN_FILE = "ChiTietHoaDon.txt";

    public DSChiTietHoaDon() {
        ds = new CTHoaDon[MAX];
        n = 0;
    }

    public DSChiTietHoaDon(CTHoaDon[] ds, int n) {
        this();

        if (ds == null || n <= 0) {
            return;
        }

        for (int i = 0; i < n && i < ds.length && i < MAX; i++) {
            if (ds[i] != null) {
                this.ds[this.n] = new CTHoaDon(ds[i]);
                this.n++;
            }
        }
    }

    public int getN() {
        return n;
    }

    public CTHoaDon[] getDs() {
        CTHoaDon[] ketQua = new CTHoaDon[n];

        for (int i = 0; i < n; i++) {
            ketQua[i] = new CTHoaDon(ds[i]);
        }

        return ketQua;
    }

    // Them chi tiet hoa don
    public boolean them(CTHoaDon ct) {
        if (ct == null || n >= MAX) {
            return false;
        }

        if (timViTri(ct.getMaHD(), ct.getMaSP()) != -1) {
            System.out.println("San pham da co trong hoa don nay!");
            return false;
        }

        ds[n] = new CTHoaDon(ct);
        n++;
        return true;
    }

    // Tim vi tri theo ma hoa don va ma san pham
    public int timViTri(String maHD, String maSP) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaHD().equalsIgnoreCase(maHD)
                    && ds[i].getMaSP().equalsIgnoreCase(maSP)) {
                return i;
            }
        }

        return -1;
    }

    // Tim chi tiet hoa don
    public CTHoaDon timTheoMa(String maHD, String maSP) {
        int viTri = timViTri(maHD, maSP);

        if (viTri == -1) {
            return null;
        }

        return new CTHoaDon(ds[viTri]);
    }

    // Sua chi tiet hoa don
    public boolean sua(String maHD, String maSP, CTHoaDon ctMoi) {
        int viTri = timViTri(maHD, maSP);

        if (viTri == -1 || ctMoi == null) {
            return false;
        }

        int viTriTrung = timViTri(ctMoi.getMaHD(), ctMoi.getMaSP());

        if (viTriTrung != -1 && viTriTrung != viTri) {
            System.out.println("Chi tiet hoa don moi da ton tai!");
            return false;
        }

        ds[viTri] = new CTHoaDon(ctMoi);
        return true;
    }

    // Xoa chi tiet hoa don
    public boolean xoa(String maHD, String maSP) {
        int viTri = timViTri(maHD, maSP);

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

    // Xuat danh sach chi tiet hoa don
    public void xuatDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach chi tiet hoa don rong!");
            return;
        }

        System.out.println("==========================================================================");
        System.out.printf("%-12s %-12s %10s %18s %18s%n",
                "Ma hoa don", "Ma san pham",
                "So luong", "Don gia ban", "Thanh tien");
        System.out.println("==========================================================================");

        for (int i = 0; i < n; i++) {
            CTHoaDon ct = ds[i];

            System.out.printf("%-12s %-12s %10d %,18.0f %,18.0f%n",
                    ct.getMaHD(),
                    ct.getMaSP(),
                    ct.getSoLuong(),
                    ct.getDonGiaBan(),
                    ct.getThanhTien());
        }

        System.out.println("==========================================================================");
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

                try {
                    CTHoaDon ct = CTHoaDon.fromFileString(dong);
                    them(ct);
                } catch (IllegalArgumentException e) {
                    System.out.println("Bo qua dong du lieu loi: " + dong);
                }
            }

            System.out.println("Doc file chi tiet hoa don thanh cong!");

        } catch (IOException e) {
            System.out.println("Khong the doc file " + TEN_FILE);

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file!");
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

