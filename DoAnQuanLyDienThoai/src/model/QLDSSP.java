package model;
import helper.Nhaplieu;
  
public class QLDSSP {
    public static  DSSanPham dssp = new DSSanPham();
    public static  DSChiTietSanPham dsctsp = new DSChiTietSanPham();

    private QLDSSP() { }
    
    // menu quản lý sản phẩm 
    public static void menu()
    {
        int chon;
        do 
        {   String[] muc = {"Xem danh sách", "Thêm sản phẩm", "Xóa sản phẩm",
                    "Sửa sản phẩm", "Tìm kiếm", "Sắp xếp theo giá bán lẻ tăng dần", "Thống kê",
                    "Chi tiết sản phẩm"};
            chon = Nhaplieu.menu("QUẢN LÝ SẢN PHẨM",muc);
            switch(chon)
            {
                case 1:dssp.xuatDanhSach(); break;
                case 2:them(); break;
                case 3:xoa(); break;
                case 4:sua(); break;
                case 5:timKiem(); break;
                case 6:dssp.sapxepTheoGiaBanle(); System.out.println("Da sap xep xong."); dssp.xuatDanhSach();
                case 7:thongKe(); break;
                case 8:menuChiTiet(); break;
                default: break;
            }
        } while(chon!=0);
    }
    private static void them()
    {
        if(dssp.nhapSanPham())
        {
            SanPham sp = dssp.getvitri(dssp.getSoLuong()-1);// trả về vị trí cuối cùng của dssp 
            System.out.println("Đã thêm sản phẩm " + sp.getMaSP() + ".");
            if (Nhaplieu.xacNhan("Nhập luôn chi tiết sản phẩm"))  // hàm boolean nhập "có" nếu muốn thêm chi tiết và sản phẩm
                themChiTiet(sp.getMaSP());
        }
    }
    private static void xoa()
    {
        String ma = Nhaplieu.docChuoi("Nhap maSP can xoa");
        SanPham sp = dssp.timTheoMa(ma);
        sp.xuat();
        if (Nhaplieu.xacNhan("Xác nhận xóa")) // hàm boolean nhập "có" nếu muốn xóa chi tiết và sản phẩm
        {
            dssp.xoa(ma);
            dsctsp.xoa(ma);
            System.out.println("Đã xóa.");
        }
    }
    private static void sua() {
        String ma = Nhaplieu.docChuoi("Mã sản phẩm cần sửa");
        if (!dssp.sua(ma)) System.out.println("Không tìm thấy sản phẩm.");
    }
    private static void timKiem() {
        int chon;
        do {
            String[] muc= {"Theo mã", "Theo tên", "Theo hãng", "Giá bán gốc <= x",
                    "Giá bán gốc >= x", "Dòng cao cấp", "Sắp hết hàng (tồn <= x)"};
            chon = Nhaplieu.menu("TÌM KIẾM SẢN PHẨM",muc);
            DSSanPham kq = null;
            switch (chon) {
                case 1:
                    SanPham sp = dssp.timTheoMa(Nhaplieu.docChuoi("Mã"));
                    if (sp == null) System.out.println("Không tìm thấy."); else sp.xuat();
                    break;
                case 2: kq = dssp.timkiemTen(Nhaplieu.docChuoi("Từ khóa tên")); break;
                case 3: kq = dssp.timkiemHang(Nhaplieu.docChuoi("Hãng")); break;
                case 4: kq = dssp.timGiaNhoHoacBang(Nhaplieu.docSoThuc("Giá tối đa", 0)); break;
                case 5: kq = dssp.timGiaLonHoacBang(Nhaplieu.docSoThuc("Giá tối thiểu", 0)); break;
                case 6: kq = dssp.timkiemDongCaoCap(); break;
                case 7: kq = dssp.timSaphet(Nhaplieu.docSoNguyen("Ngưỡng tồn kho", 0)); break;
                default: break;
            }
            if (kq != null) {
                System.out.println("Tìm thấy " + kq.getSoLuong() + " sản phẩm:");
                kq.xuatDanhSach();
            }
        } while (chon != 0);
    }
    private static void thongKe()
    {
        int chon;
        do 
        {
            String[] muc = {"Theo hãng", "Giá bán lẻ & tồn kho"};
            chon = Nhaplieu.menu("THỐNG KÊ SẢN PHẨM", muc);
            switch (chon) {
                case 1: dssp.thongKeTheoHang(); break;
                case 2: dssp.thongKeGiaBanLe(); break;
                default: break;
            }
        } while (chon != 0);
    }
    // ---------- Chi tiết sản phẩm ----------
    private static void menuChiTiet() {
        int chon;
        do {
            String[] muc = {"Xem tất cả", "Xem theo mã sản phẩm",
                                "Thêm chi tiết", "Sửa chi tiết (nhập lại thông số)", "Xóa chi tiết",
                                "Tìm theo hãng", "Tìm theo RAM >= x","Thống kê theo dung lượng"};
            chon = Nhaplieu.menu("CHI TIẾT SẢN PHẨM", muc);
            switch (chon) {
                case 1: dsctsp.xuat(); break;
                case 2:
                    ChiTietSanPham ct = dsctsp.timTheoMa(Nhaplieu.docChuoi("Mã sản phẩm"));
                    if (ct == null) System.out.println("Chưa có chi tiết."); else ct.xuat();
                    break;
                case 3: themChiTiet(Nhaplieu.docChuoi("Mã sản phẩm")); break;
                case 4: suaChiTiet(); break;
                case 5:
                    System.out.println(dsctsp.xoa(Nhaplieu.docChuoi("Mã sản phẩm")) ? "Đã xóa." : "Không tìm thấy.");
                    break;
                case 6: dsctsp.timTheoHang(Nhaplieu.docChuoi("Hãng")).xuat(); break;
                case 7: dsctsp.timRamTuToiThieu(Nhaplieu.docSoNguyen("RAM tối thiểu (GB)", 1)).xuat(); break;
                case 8: dsctsp.ThongKeDungLuong(); break;
                default: break;
            }
        } while (chon != 0);
    }
    private static void themChiTiet(String ma) {
        // tìm mã sản phẩm trong DSSP nếu không có mã trả về 
        if (dssp.timTheoMa(ma) == null) { System.out.println("Không có sản phẩm mã " + ma + "."); return; }
        // nếu tìm thấy mã thì chỉ sửa dc thôi mã không dc giống nhau 
        if (dsctsp.timTheoMa(ma) != null) { System.out.println("Sản phẩm này đã có chi tiết (hãy dùng Sửa)."); return; }
        ChiTietSanPham ct = new ChiTietSanPham();
        ct.setMaSP(dssp.timTheoMa(ma).getMaSP());
        ct.nhapThongSo();
        dsctsp.them(ct);
        System.out.println("Đã thêm chi tiết.");
    }
    private static void suaChiTiet() {
        ChiTietSanPham ct = dsctsp.timTheoMa(Nhaplieu.docChuoi("Mã sản phẩm"));
        if (ct == null) { System.out.println("Chưa có chi tiết."); return; }
        ct.nhapThongSo();
        System.out.println("Đã cập nhật.");
    }
}   
