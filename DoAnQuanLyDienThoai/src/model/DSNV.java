package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import helper.IDocGhiFile;
import helper.Nhaplieu;

public class DSNV implements IDocGhiFile {
    private NhanVien[] ds;
    private int n;
    private static final String TEN_FILE = "NhanVien.txt";

    public DSNV() {
        ds = new NhanVien[0];
        n = 0;
    }

    public DSNV(NhanVien[] ds, int n) {
        this();
        if (ds != null) {
            for (int i = 0; i < n && i < ds.length; i++) {
                if (ds[i] != null) {
                    this.ds = Arrays.copyOf(this.ds, this.n + 1);
                    this.ds[this.n] = new NhanVien(ds[i]);
                    this.n++;
                }
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

//==================THEM NHAN VIEN MOI VAO DANH SACH============================

    public boolean them(NhanVien nv) {
        if (nv == null) {
            return false;
        }

        String ma = nv.getMaNV();

        if (ma == null || ma.trim().isEmpty()) {
            return false;
        }

        if (timViTriTheoMa(ma) != -1) {
            return false;
        }

        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[n] = new NhanVien(nv);
        n++;

        return true;
    }

//================TIM NHAN VIEN THEO MA TRA VE VI TRI ==========================

    public int timViTriTheoMa(String maNV) {
        if (maNV == null || maNV.trim().isEmpty()) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (ds[i] != null &&
                ds[i].getMaNV() != null &&
                ds[i].getMaNV().equalsIgnoreCase(maNV.trim())) {
                return i;
            }
        }

        return -1;
    }

//=============TIM THEO MA TRA VE NHAN VIEN=====================================

    public NhanVien timTheoMa(String maNV) {
        int viTri = timViTriTheoMa(maNV);

        if (viTri == -1) {
            return null;
        }

        return new NhanVien(ds[viTri]);
    }

//==============TIM DS NHAN VIEN THEO CHUC VU===================================

    public NhanVien[] timTheoChucVu(String chucVu) {
        if (chucVu == null || chucVu.trim().isEmpty()) {
            return new NhanVien[0];
        }

        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i] != null &&
                ds[i].getChucVu() != null &&
                ds[i].getChucVu().equalsIgnoreCase(chucVu.trim())) {
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

//=============TIM DS NHAN VIEN THEO GIOI TINH==================================

    public NhanVien[] timTheoGT(String GT) {
        if (GT == null || GT.trim().isEmpty()) {
            return new NhanVien[0];
        }

        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i] != null &&
                ds[i].getGT() != null &&
                ds[i].getGT().equalsIgnoreCase(GT.trim())) {
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

//======================TIM DS NHAN VIEN THEO HO================================

    public NhanVien[] timTheoHo(String hoNV) {
        if (hoNV == null || hoNV.trim().isEmpty()) {
            return new NhanVien[0];
        }

        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i] != null &&
                ds[i].getHoNV() != null &&
                ds[i].getHoNV().equalsIgnoreCase(hoNV.trim())) {
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

//======================TIM DS NHAN VIEN THEO TEN===============================

    public NhanVien[] timTheoTen(String tenNV) {
        if (tenNV == null || tenNV.trim().isEmpty()) {
            return new NhanVien[0];
        }

        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i] != null &&
                ds[i].getTenNV() != null &&
                ds[i].getTenNV().equalsIgnoreCase(tenNV.trim())) {
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

//======================TIM NHAN VIEN THEO SDT=================================

    public NhanVien[] timTheoSDT(String SDT) {
        if (SDT == null || SDT.trim().isEmpty()) {
            return new NhanVien[0];
        }

        NhanVien[] ketQua = new NhanVien[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i] != null &&
                ds[i].getSDT() != null &&
                ds[i].getSDT().equalsIgnoreCase(SDT.trim())) {
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

//======================SUA NHAN VIEN THEO MA ================================

    public boolean sua() {
        String maNV = Nhaplieu.docChuoi("Nhap ma nhan vien can sua: ");
        int i = timViTriTheoMa(maNV);

        if (i == -1) {
            System.out.println("Khong tim thay nhan vien!");
            return false;
        }

        System.out.println("Nhan vien can sua:");
        ds[i].xuat();

        int chon;
        do {
            System.out.println("\n===== CHON THONG TIN CAN SUA =====");
            System.out.println("1. Ma nhan vien");
            System.out.println("2. Ho");
            System.out.println("3. Ten");
            System.out.println("4. Gioi tinh");
            System.out.println("5. So dien thoai");
            System.out.println("6. Chuc vu");
            System.out.println("7. CCCD");
            System.out.println("8. Luong co ban");

            chon = Nhaplieu.docSoNguyen("Chon: ", 1, 8);

            switch (chon) {
                case 1:
                    String maMoi = Nhaplieu.docChuoi("Nhap ma moi: ");
                    int viTri = timViTriTheoMa(maMoi);

                    if (maMoi.trim().isEmpty() || (viTri != -1 && viTri != i)) {
                        System.out.println("Ma nhan vien khong hop le hoac da ton tai!");
                    } else {
                        ds[i].setMa(maMoi);
                    }
                    break;

                case 2:
                    ds[i].setHoNV(Nhaplieu.docChuoi("Nhap ho moi: "));
                    break;

                case 3:
                    ds[i].setTenNV(Nhaplieu.docChuoi("Nhap ten moi: "));
                    break;

                case 4:
                    ds[i].setGT(Nhaplieu.docChuoi("Nhap gioi tinh moi: "));
                    break;

                case 5:
                    ds[i].setSDT(Nhaplieu.docChuoi("Nhap so dien thoai moi: "));
                    break;

                case 6:
                    ds[i].setChucVu(Nhaplieu.docChuoi("Nhap chuc vu moi: "));
                    break;

                case 7:
                    ds[i].setCCCD(Nhaplieu.docChuoi("Nhap CCCD moi: "));
                    break;

                case 8:
                    ds[i].setLuongCB(Nhaplieu.docSoThuc("Nhap luong co ban moi: ", 0));
                    break;
            }

            if (!Nhaplieu.xacNhan("Tiep tuc sua?")) {
                break;
            }

        } while (true);

        System.out.println("Da sua nhan vien thanh cong!");
        return true;
    }

//======================XOA NHAN VIEN THEO MA===================================

    public boolean xoa() {
        String maNV = Nhaplieu.docChuoi("Nhap ma nhan vien can xoa: ");
        int i = timViTriTheoMa(maNV);

        if (i == -1) {
            System.out.println("Khong tim thay nhan vien!");
            return false;
        }

        System.out.println("Nhan vien can xoa:");
        ds[i].xuat();

        if (!Nhaplieu.xacNhan("Ban co chac chan muon xoa?")) {
            System.out.println("Da huy xoa!");
            return false;
        }

        for (int j = i; j < n - 1; j++) {
            ds[j] = ds[j + 1];
        }

        n--;
        ds = Arrays.copyOf(ds, n);

        System.out.println("Da xoa nhan vien thanh cong!");
        return true;
    }

//===================== XUAT DANH SACH CAC NHAN VIEN============================

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

//============================THONG KE NHAN VIEN THEO GT========================

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

//=======================THONG KE NHAN VIEN THEO CHUC VU======================

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

//====================THONG KE THEO LUONGCB=====================================

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

//========================DOC/GHI FILE==========================================

    @Override
    public void docFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(TEN_FILE));
            String dong;

            ds = new NhanVien[0];
            n = 0;

            while ((dong = br.readLine()) != null) {
                if (!dong.trim().isEmpty()) {
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n] = NhanVien.fromFileString(dong);
                    n++;
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file nhan vien!");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file nhan vien!");
            }
        }
    }

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
            System.out.println("Loi ghi file nhan vien!");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file nhan vien!");
            }
        }
    }
}
