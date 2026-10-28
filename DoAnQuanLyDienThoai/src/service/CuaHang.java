package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

/** Lớp nghiệp vụ: lưu toàn bộ dữ liệu và xử lý bán hàng / nhập hàng. */
public class CuaHang implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE = "data.dat";

    private List<SanPham> sanPhams = new ArrayList<SanPham>();
    private List<ChiTietSanPham> chiTiets = new ArrayList<ChiTietSanPham>();
    private List<KhachHang> khachHangs = new ArrayList<KhachHang>();
    private List<NhanVien> nhanViens = new ArrayList<NhanVien>();
    private List<NhaCungCap> nhaCungCaps = new ArrayList<NhaCungCap>();
    private List<HoaDon> hoaDons = new ArrayList<HoaDon>();
    private List<PhieuNhapHang> phieuNhaps = new ArrayList<PhieuNhapHang>();

    public List<SanPham> getSanPhams() { return sanPhams; }
    public List<ChiTietSanPham> getChiTiets() { return chiTiets; }
    public List<KhachHang> getKhachHangs() { return khachHangs; }
    public List<NhanVien> getNhanViens() { return nhanViens; }
    public List<NhaCungCap> getNhaCungCaps() { return nhaCungCaps; }
    public List<HoaDon> getHoaDons() { return hoaDons; }
    public List<PhieuNhapHang> getPhieuNhaps() { return phieuNhaps; }

    public String maSPMoi() { return String.format("SP%03d", sanPhams.size() + 1); }
    public String maKHMoi() { return String.format("KH%03d", khachHangs.size() + 1); }
    public String maNVMoi() { return String.format("NV%03d", nhanViens.size() + 1); }
    public String maNCCMoi() { return String.format("NCC%02d", nhaCungCaps.size() + 1); }
    public String maHDMoi() { return String.format("HD%03d", hoaDons.size() + 1); }
    public String maPNHMoi() { return String.format("PNH%03d", phieuNhaps.size() + 1); }

    public SanPham timSP(String ma) {
        for (SanPham s : sanPhams) if (s.getMaSP().equalsIgnoreCase(ma)) return s;
        return null;
    }
    public ChiTietSanPham timChiTiet(String ma) {
        for (ChiTietSanPham c : chiTiets) if (c.getMaSP().equalsIgnoreCase(ma)) return c;
        return null;
    }
    public KhachHang timKH(String ma) {
        for (KhachHang k : khachHangs) if (k.getMaKH().equalsIgnoreCase(ma)) return k;
        return null;
    }
    public NhanVien timNV(String ma) {
        for (NhanVien n : nhanViens) if (n.getMaNV().equalsIgnoreCase(ma)) return n;
        return null;
    }
    public NhaCungCap timNCC(String ma) {
        for (NhaCungCap n : nhaCungCaps) if (n.getMaNCC().equalsIgnoreCase(ma)) return n;
        return null;
    }

    /** Lập hóa đơn: kiểm tra tồn kho, trừ kho, cộng tổng tiền đã mua của khách. */
    public void lapHoaDon(HoaDon hd) {
        KhachHang kh = timKH(hd.getMaKH());
        if (kh == null) throw new IllegalArgumentException("Không tìm thấy khách hàng " + hd.getMaKH());
        if (timNV(hd.getMaNV()) == null) throw new IllegalArgumentException("Không tìm thấy nhân viên " + hd.getMaNV());
        if (hd.getChiTiet().isEmpty()) throw new IllegalArgumentException("Hóa đơn chưa có sản phẩm");
        for (CTHoaDon ct : hd.getChiTiet()) {
            SanPham sp = timSP(ct.getMaSP());
            if (sp == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm " + ct.getMaSP());
            if (ct.getSoLuong() <= 0) throw new IllegalArgumentException("Số lượng phải > 0");
            if (sp.getSoLuong() < ct.getSoLuong())
                throw new IllegalArgumentException("Sản phẩm " + sp.getTenSP() + " chỉ còn " + sp.getSoLuong());
        }
        for (CTHoaDon ct : hd.getChiTiet()) {
            SanPham sp = timSP(ct.getMaSP());
            sp.setSoLuong(sp.getSoLuong() - ct.getSoLuong());
        }
        kh.setTongTienDaMua(kh.getTongTienDaMua() + hd.getTongTien());
        hoaDons.add(hd);
    }

    /** Lập phiếu nhập: cộng số lượng vào kho. */
    public void lapPhieuNhap(PhieuNhapHang p) {
        if (timNCC(p.getMaNCC()) == null) throw new IllegalArgumentException("Không tìm thấy nhà cung cấp " + p.getMaNCC());
        if (timNV(p.getMaNV()) == null) throw new IllegalArgumentException("Không tìm thấy nhân viên " + p.getMaNV());
        if (p.getChiTiet().isEmpty()) throw new IllegalArgumentException("Phiếu nhập chưa có sản phẩm");
        for (CTPhieuNhapHang ct : p.getChiTiet()) {
            if (timSP(ct.getMaSP()) == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm " + ct.getMaSP());
            if (ct.getSoLuong() <= 0) throw new IllegalArgumentException("Số lượng phải > 0");
        }
        for (CTPhieuNhapHang ct : p.getChiTiet()) {
            SanPham sp = timSP(ct.getMaSP());
            sp.setSoLuong(sp.getSoLuong() + ct.getSoLuong());
        }
        phieuNhaps.add(p);
    }

    public double tongDoanhThu() {
        double t = 0;
        for (HoaDon h : hoaDons) t += h.getTongTien();
        return t;
    }
    public double tongChiNhap() {
        double t = 0;
        for (PhieuNhapHang p : phieuNhaps) t += p.getTongTien();
        return t;
    }

    public void luu() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE));
        try { oos.writeObject(this); } finally { oos.close(); }
    }
    public static CuaHang doc() {
        File f = new File(FILE);
        if (f.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                try { return (CuaHang) ois.readObject(); } finally { ois.close(); }
            } catch (Exception e) {
                System.out.println("Không đọc được data.dat, tạo dữ liệu mẫu mới.");
            }
        }
        CuaHang ch = new CuaHang();
        ch.taoDuLieuMau();
        return ch;
    }

    private void taoDuLieuMau() {
        nhaCungCaps.add(new NhaCungCap("NCC01", "Công ty FPT Trading", "Hà Nội"));
        nhaCungCaps.add(new NhaCungCap("NCC02", "Công ty Digiworld", "TP.HCM"));
        nhanViens.add(new NhanVien("NV001", "Nguyễn Văn", "An", "Nam", "0901234567", "Quản lý", "079200000001", 12000000));
        nhanViens.add(new NhanVien("NV002", "Trần Thị", "Bình", "Nữ", "0912345678", "Bán hàng", "079200000002", 8000000));
        khachHangs.add(new KhachHang("KH001", "Lê Minh Châu", "Nữ", "0987654321"));
        khachHangs.add(new KhachHang("KH002", "Phạm Quốc Dũng", "Nam", "0977123456"));
        sanPhams.add(new IOS("SP001", "iPhone 15 Pro Max", 10, 29990000, "Titan", true));
        sanPhams.add(new Android("SP002", "Samsung S24 Ultra", 8, 27990000, "Đen", true, false));
        sanPhams.add(new Android("SP003", "Xiaomi 14T Pro", 15, 14990000, "Xanh", false, true));
        chiTiets.add(new ChiTietSanPham("SP001", "iOS 17", 256, 8, "Apple", "A17 Pro", "6.7\" OLED", 4422, "48MP", "USB-C", "Titan"));
        chiTiets.add(new ChiTietSanPham("SP002", "Android 14", 512, 12, "Samsung", "Snapdragon 8 Gen 3", "6.8\" AMOLED", 5000, "200MP", "USB-C", "Khung Titan"));
        chiTiets.add(new ChiTietSanPham("SP003", "Android 14", 256, 12, "Xiaomi", "Dimensity 9300+", "6.67\" AMOLED", 5000, "50MP", "USB-C", "Kính + nhôm"));
    }
}
