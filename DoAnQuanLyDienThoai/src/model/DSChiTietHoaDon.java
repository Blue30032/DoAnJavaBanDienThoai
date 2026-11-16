
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import helper.IDocGhiFile;
import helper.Nhaplieu;

public class DSChiTietHoaDon implements IDocGhiFile {
    private CTHoaDon[] ds;
    private int n;
    private static final String TEN_FILE = "ChiTietHoaDon.txt";

    public DSChiTietHoaDon() {
        ds = new CTHoaDon[0];
        n = 0;
    }

    public DSChiTietHoaDon(CTHoaDon[] ds, int n) {
        this();

        if (ds == null || n <= 0) {
            return;
        }

        for (int i = 0; i < n && i < ds.length; i++) {
            if (ds[i] != null) {
                this.ds = Arrays.copyOf(this.ds, this.n + 1);
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

//==========================THEM CHI TIET HOA DON===============================

    public boolean them(CTHoaDon ct) {
        if (ct == null) {
            return false;
        }

        if (timViTri(ct.getMaHD(), ct.getMaSP()) != -1) {
            System.out.println("San pham da co trong hoa don nay!");
            return false;
        }

        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[n] = new CTHoaDon(ct);
        n++;

        return true;
    }

//======================TIM VI TRI THEO MA HOA DON VA MA SAN PHAM===============

    public int timViTri(String maHD, String maSP) {
        if (maHD == null || maSP == null) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaHD().equalsIgnoreCase(maHD)
                    && ds[i].getMaSP().equalsIgnoreCase(maSP)) {
                return i;
            }
        }

        return -1;
    }

//=========================TIM CHI TIET HOA DON=================================

    public CTHoaDon timTheoMa(String maHD, String maSP) {
        int viTri = timViTri(maHD, maSP);

        if (viTri == -1) {
            return null;
        }

        return new CTHoaDon(ds[viTri]);
    }

//==========================SUA CHI TIET HOA DON================================

    public boolean sua(String maHD, String maSPCu,
                       String maSPMoi, int soLuongMoi,
                       double donGiaMoi) {

        int i = timViTri(maHD, maSPCu);

        if (i == -1) {
            System.out.println("Khong tim thay chi tiet hoa don!");
            return false;
        }

        if (maSPMoi == null || maSPMoi.trim().isEmpty()
                || soLuongMoi <= 0 || donGiaMoi < 0) {
            System.out.println("Thong tin sua khong hop le!");
            return false;
        }

        // Kiem tra trung san pham trong cung hoa don
        int viTriTrung = timViTri(maHD, maSPMoi);

        if (viTriTrung != -1 && viTriTrung != i) {
            System.out.println("San pham da ton tai trong hoa don!");
            return false;
        }

        // Cap nhat thong tin chi tiet
        ds[i].setMaSP(maSPMoi);
        ds[i].setSoLuong(soLuongMoi);
        ds[i].setDonGiaBan(donGiaMoi);
        ds[i].setThanhTien(soLuongMoi * donGiaMoi);

        return true;
    }



//==========================XOA CHI TIET HOA DON================================

    public boolean xoa(String maHD, String maSP) {
        int i = timViTri(maHD, maSP);

        if (i == -1) {
            System.out.println("Khong ton tai chi tiet hoa don nay!");
            System.out.println("Xoa that bai!");
            return false;
        }

        for (int j = i; j < n - 1; j++) {
            ds[j] = ds[j + 1];
        }

        n--;
        ds = Arrays.copyOf(ds, n);

        System.out.println("Da xoa chi tiet hoa don co ma HD "
                + maHD + " va ma SP " + maSP);

        return true;
    }

//==========================XUAT DANH SACH CHI TIET HOA DON=====================

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
    
//======================= XOA TAT CA THEO MA HD========================
    public void xoaTatCaTheoMaHD(String maHD) {

        for (int i = n - 1; i >= 0; i--) {

            if (ds[i].getMaHD().equalsIgnoreCase(maHD)) {

                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }

                n--;
                ds = Arrays.copyOf(ds, n);
            }
        }
    }   

//===============================DOC FILE=======================================

    @Override
    public void docFile() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(TEN_FILE));
            String dong;

            ds = new CTHoaDon[0];
            n = 0;

            while ((dong = br.readLine()) != null) {
                if (!dong.trim().isEmpty()) {
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n] = CTHoaDon.fromFileString(dong);
                    n++;
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file chi tiet hoa don!");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file chi tiet hoa don!");
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
            System.out.println("Loi ghi file chi tiet hoa don!");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file chi tiet hoa don!");
            }
        }
    }
}
