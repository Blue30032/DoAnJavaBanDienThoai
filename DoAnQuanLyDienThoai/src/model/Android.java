package model;

/** Sản phẩm chạy Android: bút cảm ứng, cổng hồng ngoại. */
public class Android extends SanPham {
    private static final long serialVersionUID = 1L;
    private boolean butCamUng, congHongNgoai;

    public Android(String maSP, String tenSP, int soLuong, double donGia, String mauSac,
                   boolean butCamUng, boolean congHongNgoai) {
        super(maSP, tenSP, soLuong, donGia, mauSac);
        this.butCamUng = butCamUng; this.congHongNgoai = congHongNgoai;
    }
    public boolean isButCamUng() { return butCamUng; }
    public boolean isCongHongNgoai() { return congHongNgoai; }
    @Override public String thongTinRieng() {
        return "Bút cảm ứng: " + (butCamUng ? "Có" : "Không") + ", Cổng hồng ngoại: " + (congHongNgoai ? "Có" : "Không");
    }
    @Override public String loai() { return "Android"; }
}
