package DoAnBanDienThoai;

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
// sua nhan vien theo ma nv    
    public void suaNV() {
        String ma = nhapChuoi("Nhap ma nhan vien can sua: ");

        if (dsnv.timTheoMa(ma) == null) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        System.out.println("Nhap thong tin moi:");

        String maMoi = nhapChuoi("Nhap ma nhan vien: ");
        String ho = nhapChuoi("Nhap ho: ");
        String ten = nhapChuoi("Nhap ten: ");
        String gt = nhapChuoi("Nhap gioi tinh: ");
        String sdt = nhapChuoi("Nhap SDT: ");
        String chucVu = nhapChuoi("Nhap chuc vu: ");
        String cccd = nhapChuoi("Nhap CCCD: ");
        double luong = nhapSoThuc("Nhap luong co ban: ");

        NhanVien nvMoi = new NhanVien(maMoi, ho, ten, gt, sdt, chucVu, cccd, luong);

        if (dsnv.suaTheoMa(ma, nvMoi)) {
            System.out.println("Sua nhan vien thanh cong!");
        } else {
            System.out.println("Sua nhan vien that bai!");
        }
    }

    public void xoaNV() {
        String ma = nhapChuoi("Nhap ma nhan vien can xoa: ");

        if (dsnv.xoaTheoMa(ma)) {
            System.out.println("Xoa nhan vien thanh cong!");
        } else {
            System.out.println("Khong tim thay nhan vien!");
        }
    }

    public void timTheoMa() {
        String ma = nhapChuoi("Nhap ma nhan vien can tim: ");
        NhanVien nv = dsnv.timTheoMa(ma);

        if (nv == null) {
            System.out.println("Khong tim thay nhan vien!");
        } else {
            NhanVien[] ketQua = new NhanVien[1];
            ketQua[0] = nv;
            xuatKetQua(ketQua);
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
            System.out.println("3. Them k nhan vien");
            System.out.println("4. Sua nhan vien theo ma");
            System.out.println("5. Xoa nhan vien theo ma");
            System.out.println("6. Tim theo ma");
            System.out.println("7. Tim theo ho");
            System.out.println("8. Tim theo ten");
            System.out.println("9. Tim theo chuc vu");
            System.out.println("10. Tim theo gioi tinh");
            System.out.println("11. Tim theo SDT");
            System.out.println("12. Thong ke");
            System.out.println("0. Quay lai");

            chon = nhapSoNguyen("Chon chuc nang: ", 0, 12);

            switch (chon) {
                case 1:
                    dsnv.xuatDanhSach();
                    break;
                case 2:
                    themNV();
                    break;
                case 3:
                    int k = nhapSoNguyen("Nhap k: ", 1, 100);
                    for (int i = 1; i <= k; i++) {
                        System.out.println("--- Nhan vien thu " + i + "/" + k + " ---");
                        themNV();
                    }
                    break;
                case 4:
                    suaNV();
                    break;
                case 5:
                    xoaNV();
                    break;
                case 6:
                    timTheoMa();
                    break;
                case 7:
                    xuatKetQua(dsnv.timTheoHo(nhapChuoi("Nhap ho can tim: ")));
                    break;
                case 8:
                    xuatKetQua(dsnv.timTheoTen(nhapChuoi("Nhap ten can tim: ")));
                    break;
                case 9:
                    xuatKetQua(dsnv.timTheoChucVu(nhapChuoi("Nhap chuc vu: ")));
                    break;
                case 10:
                    xuatKetQua(dsnv.timTheoGT(nhapChuoi("Nhap gioi tinh: ")));
                    break;
                case 11:
                    xuatKetQua(dsnv.timTheoSDT(nhapChuoi("Nhap SDT: ")));
                    break;
                case 12:
                    menuThongKe();
                    break;
            }
        } while (chon != 0);
    }

    public void docFile() {
        dsnv.docFile();
    }

    public void ghiFile() {
        try {
            dsnv.ghiFile();
            System.out.println("Ghi file nhan vien thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi file nhan vien!");
        }
    }
}