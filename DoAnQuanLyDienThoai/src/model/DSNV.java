package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DSNV {
    private NhanVien[] ds;
    private int n;
    private static final int MAX = 1000;
    private static final String TEN_FILE = "NhanVien.txt";

    public DSNV() {   
        ds = new NhanVien[MAX];
        n = 0;
    }

    public DSNV(NhanVien[] ds, int n) {
        this();
        for (int i = 0; i < n && i < ds.length && i < MAX; i++) {
            if (ds[i] != null) {
                this.ds[this.n] = new NhanVien(ds[i]);
                this.n++;
            }
        }
    }

    public int getN() {
        return n;
    }

    public NhanVien[] getDs() {
        NhanVien[] ketQua = new NhanVien[n];

        for (int i = 0; i < n; i++) {
            ketQua[i] = new NhanVien(ds[i]);
        }

        return ketQua;
    }

    public boolean them(NhanVien nv) {
        if (nv == null || n >= MAX) {
            return false;
        }

        if (timViTriTheoMa(nv.getMaNV()) != -1) {
            System.out.println("Ma nhan vien da ton tai!");
            return false;
        }

        ds[n] = new NhanVien(nv);
        n++;
        return true;
    }

    public int timViTriTheoMa(String maNV) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNV().equalsIgnoreCase(maNV)) {
                return i;
            }
        }

        return -1;
    }

    public NhanVien timTheoMa(String maNV) {
        int viTri = timViTriTheoMa(maNV);

        if (viTri == -1) {
            return null;
        }

        return new NhanVien(ds[viTri]);
    }
    
    public NhanVien[] timTheoChucVu(String ChucVu) {
        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getChucVu().equalsIgnoreCase(ChucVu)) {
                ketQua[dem] = new NhanVien(ds[i]);
                dem++;
            }
        }
        NhanVien[] dsTimDuoc = new NhanVien[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
    public NhanVien[] timTheoGT (String GT){
        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getGT().equalsIgnoreCase(GT)) {
                ketQua[dem] = new NhanVien(ds[i]);
                dem++;
            }
        }
        NhanVien[] dsTimDuoc = new NhanVien[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
    public NhanVien[] timTheoHo (String hoNV){
        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getHoNV().equalsIgnoreCase(hoNV)) {
                ketQua[dem] = new NhanVien(ds[i]);
                dem++;
            }
        }
        NhanVien[] dsTimDuoc = new NhanVien[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
    public NhanVien[] timTheoTen (String tenNV){
        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getTenNV().equalsIgnoreCase(tenNV)) {
                ketQua[dem] = new NhanVien(ds[i]);
                dem++;
            }
        }
        NhanVien[] dsTimDuoc = new NhanVien[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
       public NhanVien[] timTheoSDT (String SDT){
        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getSDT().equalsIgnoreCase(SDT)) {
                ketQua[dem] = new NhanVien(ds[i]);
                dem++;
            }
        }
        NhanVien[] dsTimDuoc = new NhanVien[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
    public boolean suaTheoMa(String maNV, NhanVien nvMoi) {
        int viTri = timViTriTheoMa(maNV);

        if (viTri == -1 || nvMoi == null) {
            return false;
        }

        if (!maNV.equalsIgnoreCase(nvMoi.getMaNV())
                && timViTriTheoMa(nvMoi.getMaNV()) != -1) {
            System.out.println("Ma nhan vien moi da ton tai!");
            return false;
        }

        ds[viTri] = new NhanVien(nvMoi);
        return true;
    }

    public boolean xoaTheoMa(String maNV) {
        int viTri = timViTriTheoMa(maNV);

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

    public void xuatDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        System.out.println("\n========================== DANH SACH NHAN VIEN ==========================\n");

        System.out.printf("%-6s %-8s %-18s %-12s %-10s %-13s %-15s %-15s %15s%n",
            "STT", "MA NV", "HO", "TEN", "GIOI TINH",
            "SDT", "CHUC VU", "CCCD", "LUONG CO BAN");

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            NhanVien nv = ds[i];

            System.out.printf("%-6d %-8s %-18s %-12s %-10s %-13s %-15s %-15s %,15.0f%n",
                i + 1,
                nv.getMaNV(),
                nv.getHoNV(),
                nv.getTenNV(),
                nv.getGT(),
                nv.getSDT(),
                nv.getChucVu(),
                nv.getCCCD(),
                nv.getLuongCB());
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Tong so nhan vien: " + n);
    }

    public void thongKeTheoGioiTinh() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        int nam = 0, nu = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getGT().equalsIgnoreCase("Nam")) {
                nam++;
            } else if (ds[i].getGT().equalsIgnoreCase("Nu")) {
                nu++;
            }
        }

        System.out.println("\n========== THONG KE THEO GIOI TINH ==========\n");
        System.out.printf("%-8s %-20s %15s%n", "STT", "GIOI TINH", "SO LUONG");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-8d %-20s %15d%n", 1, "Nam", nam);
        System.out.printf("%-8d %-20s %15d%n", 2, "Nu", nu);
        System.out.println("-----------------------------------------------");
        System.out.println("Tong so nhan vien: " + n);
    }
    
    public void thongKeTheoChucVu() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        String[] chucVu = new String[n];
        int[] soLuong = new int[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            boolean daCo = false;

            for (int j = 0; j < dem; j++) {
                if (chucVu[j].equalsIgnoreCase(ds[i].getChucVu())) {
                    soLuong[j]++;
                    daCo = true;
                    break;
                }
            }

            if (!daCo) {
                chucVu[dem] = ds[i].getChucVu();
                soLuong[dem] = 1;
               dem++;
            }
        }

        System.out.println("\n========== THONG KE THEO CHUC VU ==========\n");
        System.out.printf("%-8s %-25s %15s%n", "STT", "CHUC VU", "SO LUONG");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < dem; i++) {
            System.out.printf("%-8d %-25s %15d%n",
                    i + 1, chucVu[i], soLuong[i]);
        }

        System.out.println("------------------------------------------------");
        System.out.println("Tong so nhan vien: " + n);
    }
    
    public void thongKeTheoLuong() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        int duoi5 = 0;
        int tu5den10 = 0;
        int tu10 = 0;

        for (int i = 0; i < n; i++) {
            double luong = ds[i].getLuongCB();

            if (luong < 5000000) {
                duoi5++;
            } else if (luong < 10000000) {
                tu5den10++;
            } else {
                tu10++;
            }
        }

        System.out.println("\n========== THONG KE THEO LUONG CO BAN ==========");
        System.out.printf("%-25s %-20s %-15s%n",
            "Muc luong", "So luong nhan vien", "Ty le");

        System.out.printf("%-25s %-20d %-14.2f%%%n",
            "Duoi 5 trieu", duoi5, duoi5 * 100.0 / n);

        System.out.printf("%-25s %-20d %-14.2f%%%n",
            "Tu 5 den duoi 10 trieu", tu5den10, tu5den10 * 100.0 / n);

        System.out.printf("%-25s %-20d %-14.2f%%%n",
            "Tu 10 trieu tro len", tu10, tu10 * 100.0 / n);

        System.out.println("Tong so nhan vien: " + n);
}
    
   public void docFile() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(TEN_FILE));

            NhanVien[] dsMoi = new NhanVien[MAX];
            int soLuong = 0;
            String dong;

            while ((dong = br.readLine()) != null && soLuong < MAX) {
                if (dong.trim().isEmpty()) {
                    continue;
                }

                String[] p = dong.split("\\|", -1);

                if (p.length != 8) {
                    System.out.println("Bo qua dong khong hop le: " + dong);
                    continue;
                }

                dsMoi[soLuong] = NhanVien.fromFileString(dong);
                soLuong++;
            }

            ds = dsMoi;
            n = soLuong;

            System.out.println("Doc file nhan vien thanh cong!");

        } catch (IOException e) {
            System.out.println("Khong the doc file " + TEN_FILE);

        } catch (NumberFormatException e) {
            System.out.println("Du lieu trong file khong hop le!");

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
 
   public void ghiFile() throws IOException {
           BufferedWriter bw = new BufferedWriter(new FileWriter(TEN_FILE));

            for (int i = 0; i < n; i++) {
                bw.write(ds[i].toFileString());
                bw.newLine();
            }

                bw.close();
    }
}

