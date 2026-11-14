package model;

import java.io.IOException;
import java.util.Scanner;
public class QLDSNV {
    public static DSNV dsnv = new DSNV();
    public static final Scanner sc = new Scanner(System.in);

    public DSNV getDsnv() {
        return dsnv;
    }

    public void themNV() {
        String ma = nhapChuoi("Nhap ma nhan vien: ");
        String ho = nhapChuoi("Nhap ho: ");
        String ten = nhapChuoi("Nhap ten: ");
        String gt = nhapChuoi("Nhap gioi tinh: ");
        String sdt = nhapChuoi("Nhap SDT: ");
        String chucVu = nhapChuoi("Nhap chuc vu: ");
        String cccd = nhapChuoi("Nhap CCCD: ");
        double luong = nhapSoThuc("Nhap luong co ban: ");

        NhanVien nv = new NhanVien(ma, ho, ten, gt, sdt, chucVu, cccd, luong);

        if (dsnv.them(nv)) {
            System.out.println("Them nhan vien thanh cong!");
        } else {
            System.out.println("Them nhan vien that bai!");
        }
    }
    
    
    public double nhapSoThuc(String thongBao) {
        while (true) {
            try {
                System.out.print(thongBao);
                double so = Double.parseDouble(sc.nextLine());

                if (so >= 0) {
                    return so;
                }

                System.out.println("Luong khong duoc am!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    public void xuatKetQua(NhanVien[] ds) {
        if (ds.length == 0) {
            System.out.println("Khong tim thay ket qua!");
            return;
        }

        System.out.println("\n========== KET QUA TIM KIEM ==========");
        for (int i = 0; i < ds.length; i++) {
            ds[i].xuat();
        }
    }

    public String nhapChuoi(String thongBao) {
        
        System.out.print(thongBao);
        return sc.nextLine();
    }

    public int nhapSoNguyen(String thongBao, int min, int max) {
       
        int so;
        while (true) {
            try {
                System.out.print(thongBao);
                so = Integer.parseInt(sc.nextLine());

                if (so >= min && so <= max) {
                    return so;
                }

                System.out.println("Vui long nhap tu " + min + " den " + max);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    public void menuThongKe() {
        int chon;
        do {
            System.out.println("\n========== THONG KE NHAN VIEN ==========");
            System.out.println("1. Thong ke theo gioi tinh");
            System.out.println("2. Thong ke theo chuc vu");
            System.out.println("3. Thong ke theo luong");
            System.out.println("0. Quay lai");

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 3);

            switch (chon) {
                case 1:
                    dsnv.thongKeTheoGioiTinh();
                    break;
                case 2:
                    dsnv.thongKeTheoChucVu();
                    break;
                case 3:
                    dsnv.thongKeTheoLuong();
                    break;
            }
        } while (chon != 0);
    }

    public void menuQLDSNV() {
        int chon;
        do {
            System.out.println("\n========== QUAN LY NHAN VIEN ==========");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Them 1 nhan vien");
            System.out.println("3. Sua nhan vien theo ma");
            System.out.println("4. Xoa nhan vien theo ma");
            System.out.println("5. Tim theo ma");
            System.out.println("6. Tim theo ho");
            System.out.println("7. Tim theo ten");
            System.out.println("8. Tim theo chuc vu");
            System.out.println("9. Tim theo gioi tinh");
            System.out.println("10. Tim theo SDT");
            System.out.println("11. Thong ke");
            System.out.println("0. Quay lai");

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 11);

            switch (chon) {
                case 1:
                    dsnv.xuatDanhSach();
                    break;
                case 2:
                    themNV();
                    break;
                case 3:
                    String  maSua = nhapChuoi("Nhap ma nhan vien can sua: ");
                    String  ho = nhapChuoi("Nhap ho moi: ");
                    String ten = nhapChuoi("Nhap ten moi: ");
                    String gt = nhapChuoi("Nhap gioi tinh moi: ");
                    String sdt = nhapChuoi("Nhap SDT moi: ");
                    String chucVu = nhapChuoi("Nhap chuc vu moi: ");
                    String cccd = nhapChuoi("Nhap CCCD moi: ");
                    double luong = nhapSoThuc("Nhap luong co ban moi: ");

                    NhanVien nvMoi = new NhanVien(maSua, ho, ten, gt, sdt, chucVu, cccd, luong);

                    if (dsnv.suaTheoMa(maSua, nvMoi)) {
                        System.out.println("Sua nhan vien thanh cong!");
                    } else {
                        System.out.println("Sua nhan vien that bai!");
                    }
                    break;
                case 4:
                    dsnv.xoaTheoMa(nhapChuoi("Nhap ma nhan vien can xoa: "));
                    break;
                case 5:
                   String maTim = nhapChuoi("Nhap ma nhan vien can tim: ");
                   NhanVien nvTim = dsnv.timTheoMa(maTim);

                    if (nvTim != null) {
                        nvTim.xuat();
                    } else {
                        System.out.println("Khong tim thay nhan vien!");
                    }
                    break;
                case 6:
                    xuatKetQua(dsnv.timTheoHo(nhapChuoi("Nhap ho can tim: ")));
                    break;
                case 7:
                    xuatKetQua(dsnv.timTheoTen(nhapChuoi("Nhap ten can tim: ")));
                    break;
                case 8:
                    xuatKetQua(dsnv.timTheoChucVu(nhapChuoi("Nhap chuc vu: ")));
                    break;
                case 9:
                    xuatKetQua(dsnv.timTheoGT(nhapChuoi("Nhap gioi tinh: ")));
                    break;
                case 10:
                    xuatKetQua(dsnv.timTheoSDT(nhapChuoi("Nhap SDT: ")));
                    break;
                case 11:
                    menuThongKe();
                    break;
            }
        } while (chon != 0);
    }
    public static void main(String[] args) {
        QLDSNV qlnv = new QLDSNV();
        qlnv.menuQLDSNV(); 
    }
}