package model;

/** Sản phẩm chạy iOS: có thêm Apple Pencil (Yes/No). */
public class IOS extends SanPham {
    private static final long serialVersionUID = 1L;
    private boolean applePencil;

    public IOS(String maSP, String tenSP, int soLuong, double donGia, String mauSac, boolean applePencil) {
        super(maSP, tenSP, soLuong, donGia, mauSac);
        this.applePencil = applePencil;
    }
    public boolean isApplePencil() { return applePencil; }
    public void setApplePencil(boolean applePencil) { this.applePencil = applePencil; }
    @Override public String thongTinRieng() { return "Apple Pencil: " + (applePencil ? "Yes" : "No"); }
    @Override public String loai() { return "iOS"; }
}
