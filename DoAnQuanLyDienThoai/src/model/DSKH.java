package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import helper.IDocGhiFile;
import helper.Nhaplieu;

public class DSKH implements IDocGhiFile {
    private KhachHang[] ds;
    private int n;
    private static final String TEN_FILE = "KhachHang.txt";

    public DSKH() {
        ds = new KhachHang[0];
        n = 0;
    }

    public DSKH(KhachHang[] ds, int n) {
        this();

        if (ds == null) {
            return;
        }

        for (int i = 0; i < n && i < ds.length; i++) {
            if (ds[i] != null) {
                this.ds = Arrays.copyOf(this.ds, this.n + 1);
                this.ds[this.n] = new KhachHang(ds[i]);
                this.n++;
            }
        }
    }

    public int getN() {
        return n;
    }

    public KhachHang[] getDs() {
        KhachHang[] ketQua = new KhachHang[n];

        for (int i = 0; i < n; i++) {
            ketQua[i] = new KhachHang(ds[i]);
        }

        return ketQua;
    }

//==================THEM KHACH HANG MOI VAO DANH SACH===========================

    public boolean them(KhachHang kh) {
        if (kh == null) {
            return false;
        }

        if (kh.getMaKH() == null || kh.getMaKH().trim().isEmpty()) {
            System.out.println("Ma khach hang khong duoc de trong!");
            return false;
        }

        if (timViTriTheoMa(kh.getMaKH()) != -1) {
            System.out.println("Ma khach hang da ton tai!");
            return false;
        }

        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[n] = new KhachHang(kh);
        n++;

        return true;
    }

//================TIM KHACH HANG THEO MA TRA VE VI TRI =========================

    public int timViTriTheoMa(String maKH) {
        if (maKH == null || maKH.trim().isEmpty()) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaKH().equalsIgnoreCase(maKH.trim())) {
                return i;
            }
        }

        return -1;
    }

//=============TIM THEO MA TRA VE KHACH HANG====================================

    public KhachHang timTheoMa(String maKH) {
        int viTri = timViTriTheoMa(maKH);

        if (viTri == -1) {
            return null;
        }

        return new KhachHang(ds[viTri]);
    }

//==============TIM DS KHACH HANG THEO GIOI TINH================================

    public KhachHang[] timTheogtinhKH(String gtinhKH) {
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        if (gtinhKH == null) {
            return new KhachHang[0];
        }

        for (int i = 0; i < n; i++) {
            if (ds[i].getGtinhKH().equalsIgnoreCase(gtinhKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }

        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }

//======================TIM DS KHACH HANG THEO HO===============================

    public KhachHang[] timTheoHo(String hoKH) {
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        if (hoKH == null) {
            return new KhachHang[0];
        }

        for (int i = 0; i < n; i++) {
            if (ds[i].getHoKH().equalsIgnoreCase(hoKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }

        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }

//======================TIM DS KHACH HANG THEO TEN==============================

    public KhachHang[] timTheoTen(String tenKH) {
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        if (tenKH == null) {
            return new KhachHang[0];
        }

        for (int i = 0; i < n; i++) {
            if (ds[i].getTenKH().equalsIgnoreCase(tenKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }

        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }

//======================TIM KHACH HANG THEO SDT=================================

    public KhachHang[] timTheoSDT(String sdtKH) {
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        if (sdtKH == null) {
            return new KhachHang[0];
        }

        for (int i = 0; i < n; i++) {
            if (ds[i].getSdtKH().equalsIgnoreCase(sdtKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }

        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }

//======================XOA KHACH HANG THEO MA==================================

    public boolean xoa() {
        String maKH = Nhaplieu.docChuoi("Nhap ma khach hang can xoa: ");
        int i = timViTriTheoMa(maKH);

        if (i == -1) {
            System.out.println("Khong tim thay khach hang!");
            return false;
        }

        System.out.println("Khach hang can xoa:");
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

        System.out.println("Da xoa khach hang thanh cong!");
        return true;
    }

//======================SUA KHACH HANG THEO MA==================================

    public boolean sua() {
        String maKH = Nhaplieu.docChuoi("Nhap ma khach hang can sua: ");
        int i = timViTriTheoMa(maKH);

        if (i == -1) {
            System.out.println("Khong tim thay khach hang!");
            return false;
        }

        System.out.println("Khach hang can sua:");
        ds[i].xuat();

        int chon;
        do {
            System.out.println("\n===== CHON THONG TIN CAN SUA =====");
            System.out.println("1. Ma khach hang");
            System.out.println("2. Ho");
            System.out.println("3. Ten");
            System.out.println("4. Gioi tinh");
            System.out.println("5. So dien thoai");

            chon = Nhaplieu.docSoNguyen("Chon: ", 1, 5);

            switch (chon) {
                case 1:
                    String maMoi = Nhaplieu.docChuoi("Nhap ma khach hang moi: ");
                    int viTri = timViTriTheoMa(maMoi);

                    if (maMoi.trim().isEmpty() || (viTri != -1 && viTri != i)) {
                        System.out.println("Ma khach hang khong hop le hoac da ton tai!");
                    } else {
                        ds[i].setMa(maMoi);
                    }
                    break;

                case 2:
                    ds[i].setHoKH(Nhaplieu.docChuoi("Nhap ho moi: "));
                    break;

                case 3:
                    ds[i].setTenKH(Nhaplieu.docChuoi("Nhap ten moi: "));
                    break;

                case 4:
                    ds[i].setGtinhKH(Nhaplieu.docChuoi("Nhap gioi tinh moi: "));
                    break;

                case 5:
                    ds[i].setSdtKH(Nhaplieu.docChuoi("Nhap so dien thoai moi: "));
                    break;
            }

            if (!Nhaplieu.xacNhan("Tiep tuc sua?")) {
                break;
            }

        } while (true);

        System.out.println("Da sua khach hang thanh cong!");
        return true;
    }

//====================THONG KE KHACH HANG THEO GIOI TINH========================

    public void thongKeTheoGioiTinh() {
        if (n == 0) {
            System.out.println("Danh sach khach hang rong!");
            return;
        }

        int nam = 0, nu = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getGtinhKH().equalsIgnoreCase("Nam")) {
                nam++;
            } else if (ds[i].getGtinhKH().equalsIgnoreCase("Nu")) {
                nu++;
            }
        }

        System.out.println("\n========== THONG KE THEO GIOI TINH ==========");
        System.out.printf("%-8s %-20s %15s%n", "STT", "GIOI TINH", "SO LUONG");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-8d %-20s %15d%n", 1, "Nam", nam);
        System.out.printf("%-8d %-20s %15d%n", 2, "Nu", nu);
        System.out.println("-----------------------------------------------");
        System.out.println("Tong so khach hang: " + n);
    }

//====================THONG KE KHACH HANG THEO TONG TIEN========================

    public void thongKeTheoTongTien() {
        if (n == 0) {
            System.out.println("Danh sach khach hang rong!");
            return;
        }

        int duoi5 = 0;
        int tu5den20 = 0;
        int tu20 = 0;

        for (int i = 0; i < n; i++) {
            double tien = ds[i].getTongTien();

            if (tien < 5000000) {
                duoi5++;
            } else if (tien < 20000000) {
                tu5den20++;
            } else {
                tu20++;
            }
        }

        System.out.println("\n========== THONG KE THEO TONG TIEN MUA HANG ==========");
        System.out.printf("%-30s %-20s %-15s%n",
                "Muc tong tien", "So luong khach hang", "Ty le");

        System.out.printf("%-30s %-20d %14.2f%%%n",
                "Duoi 5 trieu", duoi5, duoi5 * 100.0 / n);

        System.out.printf("%-30s %-20d %14.2f%%%n",
                "Tu 5 den duoi 20 trieu", tu5den20, tu5den20 * 100.0 / n);

        System.out.printf("%-30s %-20d %14.2f%%%n",
                "Tu 20 trieu tro len", tu20, tu20 * 100.0 / n);

        System.out.println("Tong so khach hang: " + n);
    }

//===================== XUAT DANH SACH KHACH HANG===============================

    public void xuatDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach khach hang rong!");
            return;
        }

        System.out.println("\n========== DANH SACH KHACH HANG ==========\n");

        System.out.printf("%-6s %-10s %-28s %-12s %-15s %15s%n",
                "STT", "MA KH", "HO VA TEN KHACH HANG", "GIOI TINH", "SO DIEN THOAI", "TONG TIEN");

        System.out.println("-----------------------------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            KhachHang kh = ds[i];

            System.out.printf("%-6d %-10s %-28s %-12s %-15s %,15.0f%n",
                    i + 1,
                    kh.getMaKH(),
                    kh.getHoKH() + " " + kh.getTenKH(),
                    kh.getGtinhKH(),
                    kh.getSdtKH(),
                    kh.getTongTien());
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Tong so khach hang: " + n);
    }

//========================DOC/GHI FILE==========================================

    @Override
    public void docFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(TEN_FILE));
            String dong;

            ds = new KhachHang[0];
            n = 0;

            while ((dong = br.readLine()) != null) {
                if (!dong.trim().isEmpty()) {
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n] = KhachHang.fromFileString(dong);
                    n++;
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file khach hang!");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file khach hang!");
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
            System.out.println("Loi ghi file khach hang!");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file khach hang!");
            }
        }
    }
}
