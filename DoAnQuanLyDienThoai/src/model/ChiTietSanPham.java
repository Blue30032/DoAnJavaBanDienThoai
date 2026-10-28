package model;

import java.io.Serializable;

/** Chi tiết sản phẩm (cấu hình), liên kết với SanPham qua Mã SP. */
public class ChiTietSanPham implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maSP, heDieuHanh, hang, chip, manHinh, doPhanGiaiCamera, congSac, chatLieu;
    private int dungLuong, ram, pin;

    public ChiTietSanPham(String maSP, String heDieuHanh, int dungLuong, int ram, String hang, String chip,
                          String manHinh, int pin, String doPhanGiaiCamera, String congSac, String chatLieu) {
        this.maSP = maSP; this.heDieuHanh = heDieuHanh; this.dungLuong = dungLuong; this.ram = ram;
        this.hang = hang; this.chip = chip; this.manHinh = manHinh; this.pin = pin;
        this.doPhanGiaiCamera = doPhanGiaiCamera; this.congSac = congSac; this.chatLieu = chatLieu;
    }
    public String getMaSP() { return maSP; }
    public String getHeDieuHanh() { return heDieuHanh; }
    public int getDungLuong() { return dungLuong; }
    public int getRam() { return ram; }
    public String getHang() { return hang; }
    public String getChip() { return chip; }
    public String getManHinh() { return manHinh; }
    public int getPin() { return pin; }
    public String getDoPhanGiaiCamera() { return doPhanGiaiCamera; }
    public String getCongSac() { return congSac; }
    public String getChatLieu() { return chatLieu; }

    @Override public String toString() {
        return "Mã SP: " + maSP + "\n  Hệ điều hành: " + heDieuHanh + "\n  Dung lượng: " + dungLuong + " GB"
             + "\n  RAM: " + ram + " GB\n  Hãng: " + hang + "\n  Chip: " + chip + "\n  Màn hình: " + manHinh
             + "\n  Pin: " + pin + " mAh\n  Camera: " + doPhanGiaiCamera + "\n  Cổng sạc: " + congSac
             + "\n  Chất liệu: " + chatLieu;
    }
}
