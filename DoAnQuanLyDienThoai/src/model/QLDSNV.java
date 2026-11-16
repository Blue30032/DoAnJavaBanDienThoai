package model;


import helper.Nhaplieu;
public class QLDSNV extends QuanLy  {

    public DSNV getDsnv() {
        return dsnv;
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

            chon = Nhaplieu.docSoNguyen("Chon chuc nang: ", 0, 11);

            switch (chon) {
                case 1:
                    dsnv.xuatDanhSach();
                    break;

                case 2:
                    themNV();
                    break;

                case 3:
                    dsnv.sua();
                    break;

                case 4:
                    dsnv.xoa();
                    break;

                case 5:
                    String maTim = Nhaplieu.docChuoi("Nhap ma nhan vien can tim: ");
                    NhanVien nvTim = dsnv.timTheoMa(maTim);

                    if (nvTim != null) {
                        nvTim.xuat();
                    } else {
                        System.out.println("Khong tim thay nhan vien!");
                    }
                    break;

                case 6:
                    xuatKetQua(dsnv.timTheoHo(
                        Nhaplieu.docChuoi("Nhap ho can tim: ")));
                    break;

                case 7:
                    xuatKetQua(dsnv.timTheoTen(
                        Nhaplieu.docChuoi("Nhap ten can tim: ")));
                    break;

                case 8:
                    xuatKetQua(dsnv.timTheoChucVu(
                        Nhaplieu.docChuoi("Nhap chuc vu: ")));
                    break;

                case 9:
                    xuatKetQua(dsnv.timTheoGT(Nhaplieu.docChuoi("Nhap gioi tinh: ")));
                    break;

                case 10:
                    xuatKetQua(dsnv.timTheoSDT(Nhaplieu.docChuoi("Nhap SDT: ")));
                    break;

                case 11:
                    menuThongKe();
                    break;
            }

        } while (chon != 0);
    }
    
    public void menuThongKe() {
        int chon;
        do {
            System.out.println("\n========== THONG KE NHAN VIEN ==========");
            System.out.println("1. Thong ke theo gioi tinh");
            System.out.println("2. Thong ke theo chuc vu");
            System.out.println("3. Thong ke theo luong");
            System.out.println("0. Quay lai");

            chon = Nhaplieu.docSoNguyen("Chon chuc nang: ", 0, 3);

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
    
    
    public void themNV() {
        String ma = Nhaplieu.docChuoi("Nhap ma nhan vien: ");
        String ho = Nhaplieu.docChuoi("Nhap ho: ");
        String ten = Nhaplieu.docChuoi("Nhap ten: ");
        String gt = Nhaplieu.docChuoi("Nhap gioi tinh: ");
        String sdt = Nhaplieu.docChuoi("Nhap SDT: ");
        String chucVu = Nhaplieu.docChuoi("Nhap chuc vu: ");
        String cccd = Nhaplieu.docChuoi("Nhap CCCD: ");
        double luongCB = Nhaplieu.docSoThuc("Nhap luong co ban: ", 0);

        NhanVien nv = new NhanVien(ma, ho, ten, gt, sdt, chucVu, cccd, luongCB);

        if (dsnv.them(nv)) {
            System.out.println("Them nhan vien thanh cong!");
        } else {
            System.out.println("Them nhan vien that bai!");
        }
    }

    
    

    public void xuatKetQua(NhanVien[] ds) {
        if (ds == null || ds.length == 0) {
            System.out.println("Khong tim thay ket qua!");
            return;
        }

        System.out.println("\n========== KET QUA TIM KIEM ==========");
        for (int i = 0; i < ds.length; i++) {
            ds[i].xuat();
        }
    }
}