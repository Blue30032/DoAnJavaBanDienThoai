package app;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.*;
import service.CuaHang;

/** Chương trình quản lý cửa hàng điện thoại (console). */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static CuaHang ch;

    public static void main(String[] args) {
        ch = CuaHang.doc();
        int chon;
        do {
            menu();
            chon = docSo("Chọn chức năng: ");
            try {
                switch (chon) {
                    case 1: inDS(ch.getSanPhams()); break;
                    case 2: themSanPham(); break;
                    case 3: xemChiTiet(); break;
                    case 4: inDS(ch.getKhachHangs()); break;
                    case 5: themKhachHang(); break;
                    case 6: inDS(ch.getNhanViens()); break;
                    case 7: themNhanVien(); break;
                    case 8: inDS(ch.getNhaCungCaps()); break;
                    case 9: themNhaCungCap(); break;
                    case 10: lapHoaDon(); break;
                    case 11: inDS(ch.getHoaDons()); break;
                    case 12: lapPhieuNhap(); break;
                    case 13: inDS(ch.getPhieuNhaps()); break;
                    case 14: thongKe(); break;
                    case 0: break;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        } while (chon != 0);
        try { ch.luu(); System.out.println("Đã lưu dữ liệu vào data.dat. Tạm biệt!"); }
        catch (IOException e) { System.out.println("Lỗi lưu file: " + e.getMessage()); }
    }

    private static void menu() {
        System.out.println("\n========== QUẢN LÝ CỬA HÀNG ĐIỆN THOẠI ==========");
        System.out.println(" 1. DS sản phẩm            2. Thêm sản phẩm (+ chi tiết)");
        System.out.println(" 3. Xem chi tiết sản phẩm");
        System.out.println(" 4. DS khách hàng          5. Thêm khách hàng");
        System.out.println(" 6. DS nhân viên           7. Thêm nhân viên");
        System.out.println(" 8. DS nhà cung cấp        9. Thêm nhà cung cấp");
        System.out.println("10. Lập hóa đơn bán       11. DS hóa đơn");
        System.out.println("12. Lập phiếu nhập hàng   13. DS phiếu nhập");
        System.out.println("14. Thống kê doanh thu     0. Lưu & thoát");
        System.out.println("=================================================");
    }

    private static String docChuoi(String nhan) {
        System.out.print(nhan);
        return sc.hasNextLine() ? sc.nextLine().trim() : "0";
    }
    private static int docSo(String nhan) {
        while (true) {
            try { return Integer.parseInt(docChuoi(nhan)); }
            catch (NumberFormatException e) { System.out.println("Vui lòng nhập số nguyên!"); }
        }
    }
    private static double docThuc(String nhan) {
        while (true) {
            try { return Double.parseDouble(docChuoi(nhan)); }
            catch (NumberFormatException e) { System.out.println("Vui lòng nhập số!"); }
        }
    }
    private static boolean docYesNo(String nhan) {
        String s = docChuoi(nhan + " (y/n): ");
        return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
    }
    private static <T> void inDS(List<T> ds) {
        if (ds.isEmpty()) { System.out.println("(Danh sách trống)"); return; }
        for (T t : ds) System.out.println(t);
    }

    private static void themSanPham() {
        String ma = ch.maSPMoi();
        System.out.println("Mã sản phẩm mới: " + ma);
        int loai = docSo("Loại máy (1 = iOS, 2 = Android): ");
        String ten = docChuoi("Tên sản phẩm: ");
        int sl = docSo("Số lượng: ");
        double dg = docThuc("Đơn giá: ");
        String mau = docChuoi("Màu sắc: ");
        SanPham sp;
        if (loai == 1) {
            sp = new IOS(ma, ten, sl, dg, mau, docYesNo("Có Apple Pencil?"));
        } else {
            sp = new Android(ma, ten, sl, dg, mau, docYesNo("Có bút cảm ứng?"), docYesNo("Có cổng hồng ngoại?"));
        }
        System.out.println("--- Chi tiết cấu hình ---");
        String hdh = docChuoi("Hệ điều hành: ");
        int dungLuong = docSo("Dung lượng (GB): ");
        int ram = docSo("RAM (GB): ");
        String hang = docChuoi("Hãng: ");
        String chip = docChuoi("Chip: ");
        String manHinh = docChuoi("Màn hình: ");
        int pin = docSo("Pin (mAh): ");
        String camera = docChuoi("Độ phân giải camera: ");
        String congSac = docChuoi("Cổng sạc: ");
        String chatLieu = docChuoi("Chất liệu: ");
        ch.getSanPhams().add(sp);
        ch.getChiTiets().add(new ChiTietSanPham(ma, hdh, dungLuong, ram, hang, chip, manHinh, pin, camera, congSac, chatLieu));
        System.out.println("Đã thêm sản phẩm " + ma);
    }

    private static void xemChiTiet() {
        String ma = docChuoi("Nhập mã SP: ");
        SanPham sp = ch.timSP(ma);
        if (sp == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm " + ma);
        System.out.println(sp);
        ChiTietSanPham ct = ch.timChiTiet(ma);
        System.out.println(ct == null ? "(Chưa có chi tiết cấu hình)" : ct.toString());
    }

    private static void themKhachHang() {
        String ma = ch.maKHMoi();
        String ten = docChuoi("Tên KH: ");
        String gt = docChuoi("Giới tính: ");
        String sdt = docChuoi("SĐT: ");
        ch.getKhachHangs().add(new KhachHang(ma, ten, gt, sdt));
        System.out.println("Đã thêm khách hàng " + ma);
    }

    private static void themNhanVien() {
        String ma = ch.maNVMoi();
        String ho = docChuoi("Họ: ");
        String ten = docChuoi("Tên: ");
        String gt = docChuoi("Giới tính: ");
        String sdt = docChuoi("SĐT: ");
        String cv = docChuoi("Chức vụ: ");
        String cccd = docChuoi("CCCD: ");
        double luong = docThuc("Lương căn bản: ");
        ch.getNhanViens().add(new NhanVien(ma, ho, ten, gt, sdt, cv, cccd, luong));
        System.out.println("Đã thêm nhân viên " + ma);
    }

    private static void themNhaCungCap() {
        String ma = ch.maNCCMoi();
        String ten = docChuoi("Tên NCC: ");
        String dc = docChuoi("Địa chỉ: ");
        ch.getNhaCungCaps().add(new NhaCungCap(ma, ten, dc));
        System.out.println("Đã thêm nhà cung cấp " + ma);
    }

    private static void lapHoaDon() {
        inDS(ch.getNhanViens());
        String maNV = docChuoi("Mã NV lập hóa đơn: ");
        inDS(ch.getKhachHangs());
        String maKH = docChuoi("Mã KH: ");
        HoaDon hd = new HoaDon(ch.maHDMoi(), maNV, maKH, LocalDate.now());
        inDS(ch.getSanPhams());
        do {
            String maSP = docChuoi("Mã SP: ");
            SanPham sp = ch.timSP(maSP);
            if (sp == null) { System.out.println("Không tìm thấy sản phẩm!"); continue; }
            int sl = docSo("Số lượng (còn " + sp.getSoLuong() + "): ");
            hd.themCT(new CTHoaDon(hd.getMaHD(), sp.getMaSP(), sl, sp.getDonGia()));
        } while (docYesNo("Thêm sản phẩm khác?"));
        ch.lapHoaDon(hd);
        System.out.println("Lập hóa đơn thành công!\n" + hd);
    }

    private static void lapPhieuNhap() {
        inDS(ch.getNhaCungCaps());
        String maNCC = docChuoi("Mã NCC: ");
        inDS(ch.getNhanViens());
        String maNV = docChuoi("Mã NV lập phiếu: ");
        PhieuNhapHang p = new PhieuNhapHang(ch.maPNHMoi(), maNCC, maNV, LocalDate.now());
        inDS(ch.getSanPhams());
        do {
            String maSP = docChuoi("Mã SP nhập: ");
            SanPham sp = ch.timSP(maSP);
            if (sp == null) { System.out.println("Không tìm thấy sản phẩm!"); continue; }
            int sl = docSo("Số lượng nhập: ");
            double dg = docThuc("Đơn giá nhập: ");
            p.themCT(new CTPhieuNhapHang(p.getMaPNH(), sp.getMaSP(), sl, dg));
        } while (docYesNo("Thêm sản phẩm khác?"));
        ch.lapPhieuNhap(p);
        System.out.println("Lập phiếu nhập thành công!\n" + p);
    }

    private static void thongKe() {
        double thu = ch.tongDoanhThu(), chi = ch.tongChiNhap();
        System.out.printf("Số hóa đơn: %d | Tổng doanh thu: %,.0f%n", ch.getHoaDons().size(), thu);
        System.out.printf("Số phiếu nhập: %d | Tổng tiền nhập: %,.0f%n", ch.getPhieuNhaps().size(), chi);
        System.out.printf("Chênh lệch thu - chi: %,.0f%n", thu - chi);
    }
}
