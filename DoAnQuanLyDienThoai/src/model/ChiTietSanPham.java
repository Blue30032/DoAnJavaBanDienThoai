package model;

import java.io.Serializable;
import helper.Nhaplieu;
/** Chi tiết sản phẩm (cấu hình), liên kết với SanPham qua Mã SP. */
public class ChiTietSanPham implements Serializable {
    private static final long serialVersionUID = 1L;
   private String maSP;
    private String heDieuHanh;
    private int dungLuong;          // GB
    private int ram;                // GB
    private String hang;
    private String chip;
    private double manHinh;         // inch
    private int pin;                // mAh
    private int doPhanGiaiCamera;   // MP
    private String congSac;         // USB-C, Lightning...
    private String chatLieu;

    //constructor
    public ChiTietSanPham() {
        this.maSP = "";
        this.heDieuHanh = "";
        this.dungLuong = 0;
        this.ram = 0;
        this.hang = "";
        this.chip = "";
        this.manHinh = 0;
        this.pin = 0;
        this.doPhanGiaiCamera = 0;
        this.congSac = "";
        this.chatLieu = "";
    }

    public ChiTietSanPham(String maSP, String heDieuHanh, int dungLuong, int ram, String hang,
                          String chip, double manHinh, int pin, int doPhanGiaiCamera,
                          String congSac, String chatLieu) {
        setMaSP(maSP);
        setHeDieuHanh(heDieuHanh);
        setDungLuong(dungLuong);
        setRam(ram);
        setHang(hang);
        setChip(chip);
        setManHinh(manHinh);
        setPin(pin);
        setDoPhanGiaiCamera(doPhanGiaiCamera);
        setCongSac(congSac);
        setChatLieu(chatLieu);
    }

    //get
    public String getMaSP() { return maSP; }
    public String getHeDieuHanh() { return heDieuHanh; }
    public int getDungLuong() { return dungLuong; }
    public int getRam() { return ram; }
    public String getHang() { return hang; }
    public String getChip() { return chip; }
    public double getManHinh() { return manHinh; }
    public int getPin() { return pin; }
    public int getDoPhanGiaiCamera() { return doPhanGiaiCamera; }
    public String getCongSac() { return congSac; }
    public String getChatLieu() { return chatLieu; }

    //set
    public void setMaSP(String maSP) {
        if (maSP == null || maSP.trim().isEmpty())
            throw new IllegalArgumentException("Ma sp khong dc rong");
        this.maSP = maSP.trim();
    }

    public void setHeDieuHanh(String heDieuHanh) { this.heDieuHanh = heDieuHanh; }

    public void setDungLuong(int dungLuong) {
        if (dungLuong <= 0) throw new IllegalArgumentException("Dung luong phai > 0");
        this.dungLuong = dungLuong;
    }

    public void setRam(int ram) {
        if (ram <= 0) throw new IllegalArgumentException("RAM phai > 0");
        this.ram = ram;
    }

    public void setHang(String hang) { this.hang = hang; }
    public void setChip(String chip) { this.chip = chip; }

    public void setManHinh(double manHinh) {
        if (manHinh <= 0) throw new IllegalArgumentException("Kich thuoc man hinh phai > 0");
        this.manHinh = manHinh;
    }

    public void setPin(int pin) {
        if (pin <= 0) throw new IllegalArgumentException("Dung luong pin phai > 0");
        this.pin = pin;
    }

    public void setDoPhanGiaiCamera(int doPhanGiaiCamera) {
        if (doPhanGiaiCamera <= 0) throw new IllegalArgumentException("Do phan giai phai > 0");
        this.doPhanGiaiCamera = doPhanGiaiCamera;
    }

    public void setCongSac(String congSac) { this.congSac = congSac; }
    public void setChatLieu(String chatLieu) { this.chatLieu = chatLieu; }

    // ---------- Nhập / xuất ----------
    /** Nhập chi tiết từ bàn phím. Mã SP phải trùng mã của sản phẩm tương ứng. */
    public void nhap() {
        System.out.println("--- Nhap chi tiet san pham ---");
        setMaSP(NhapLieu.docChuoi("Nhap maSP (MASP phai trung voi ma cua SAN PHAM)"));
        nhapThongSo();
    }

    /** Nhập các thông số kỹ thuật (mã SP đã được đặt trước). */
    public void nhapThongSo() {
        setHeDieuHanh(NhapLieu.docChuoi("He dieu hanh"));
        setDungLuong(NhapLieu.docSoNguyen("Dung Luong bo nho (GB)", 1));
        setRam(NhapLieu.docSoNguyen("RAM (GB)", 1));
        setHang(NhapLieu.docChuoi("Hang"));
        setChip(NhapLieu.docChuoi("Chip"));
        setManHinh(NhapLieu.docSoThuc("Man hinh (inch)", 0.1));
        setPin(NhapLieu.docSoNguyen("Pin (mAh)", 1));
        setDoPhanGiaiCamera(NhapLieu.docSoNguyen("Đo phan giai camera (MP)", 1));
        setCongSac(NhapLieu.docChuoi("Cong sac"));
        setChatLieu(NhapLieu.docChuoi("Chat lieu"));
    }

    /** Xuất chi tiết nhiều dòng cho dễ đọc. */
    public void xuat() {
        System.out.println("Chi tiet san pham [" + maSP + "]");
        System.out.printf("   Hang: %s | He đieu hanh: %s | Chip: %s%n", hang, heDieuHanh, chip);
        System.out.printf("   RAM: %dGB | Bo nho: %dGB | Man hinh: %.1f inch | Pin: %dmAh%n",
                ram, dungLuong, manHinh, pin);
        System.out.printf("   Camera: %dMP | Cong sac: %s | Chat lieu: %s%n",
                doPhanGiaiCamera, congSac, chatLieu);
    }

    @Override
    public String toString() {
        return String.format("Hang: %s | HĐH: %s | Chip: %s | RAM: %dGB | Bo nho: %dGB | "
                        + "Man hinh: %.1f\" | Pin: %dmAh | Camera: %dMP | Cong sac: %s | Chat lieu: %s",
                hang, heDieuHanh, chip, ram, dungLuong, manHinh, pin, doPhanGiaiCamera, congSac, chatLieu);
    }
    /* Các hàm liên quan tới file */
    // Hàm làm sạch chuỗi  biến dấu / thành | trong file 
    private static String LamSach(String s)
    {
        return s==null ? "" : s.replace("|", "/");
    }
    public String toDongFile()
    {
        return LamSach(maSP) + "|" + LamSach(heDieuHanh) + "|" + dungLuong + "|" + ram + "|" + LamSach(hang) + "|" 
        + LamSach(chip) + "|" + (manHinh) + "|" + pin + "|" + doPhanGiaiCamera + 
        "|" + LamSach(congSac) + "|" + LamSach(chatLieu);
    }
    public static ChiTietSanPham tuDongFile(String dong)
    {
        String[] p = dong.split("\\|,-1");
        String loai = p[0].trim().toUpperCase();
        if(p.length!=11)
        {
            throw new IllegalArgumentException("Dữ liệu chi tiết sản phẩm không hợp lệ cần 11 đối tượng");
        }
        /*String maSP, String heDieuHanh, int dungLuong, int ram, String hang,
        String chip, double manHinh, int pin, int doPhanGiaiCamera,
        String congSac, String chatLieu) cần đủ các đối tượng mới tạo dc chi tiết sản phẩm */
        return new ChiTietSanPham(p[1].trim(),p[2].trim(),Integer.parseInt(p[3].trim()),Integer.parseInt(p[4].trim()),
                                    p[5].trim(),p[6].trim(),Double.parseDouble(p[7].trim()),Integer.parseInt(p[8].trim()),
                                    Integer.parseInt(p[9].trim()),p[10].trim(),p[11].trim());
    } 
}
