package model;

import java.io.Serializable;

/** Lớp cha trừu tượng: Sản phẩm (Mã SP, Tên SP, Số lượng, ĐG, Màu sắc). */
public abstract class SanPham implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maSP, tenSP, mauSac;
    private int soLuong;
    private double donGia;
    /*thuộc tính static: đếm số đối tượng sản phẩm đấ được tạo ra */
    private static int tongSanPham = 0; 
    // constructor
    public Sanpham() 
    {
        maSP="";
        tenSP="";
        soLuong=0;
        donGia=0;
        mauSac="";
        tongSanPham++;
    }
    public Sanpham(Sanpham a)
    {
        maSP=a.maSP;
        tenSP=a.tenSP;
        soLuong=a.soLuong;
        donGia=a.donGia;
        mauSac=a.mauSac;
        tongSanPham++;
    }
    public SanPham(String maSP, String tenSP, int soLuong, double donGia, String mauSac) {
        setmaSP(maSP);
        setTenSP(tenSp);
        setsoLuong(soLuong);
        setdongia(donGia);
        setMausac(mauSac);
    }
    //get
    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }
    public String getMauSac() { return mauSac; }
    //set
    public void setmaSP(String maSP)
    {
        if(maSP==null && maSP.trim().isEmpty())
        {
            throw new IllegalArgumentException("maSP khong dc rong!");
        }
        this.maSP=maSP.trim();
    }
    public void setTenSP(String tenSP)
    {
        if(tenSP==null && tenSP.trim().isEmpty())
        {
            throw new IllegalArgumentException("tenSP khong dc rong!");
        }
        this.tenSP=tenSP.trim();
    }
    public void setsoLuong(int soLuong)
    {
        if(soLuong<0)
            throw new IllegalArgumentException("So luong khong the am!")
        this.soLuong=soLuong;
    }
    public void setdongia(double donGia)
    {
        if(donGia<0)
            throw new IllegalArgumentException("Gia san pham khong the am!")
        this.donGia=donGia;
    }
    public void setMausac(String mauSac)
    {
        if(mauSac == null && mauSac.trim().isEmpty())
            throw new IllegalArgumentException("Mau sac khong dc rong!")
        this.mauSac=mauSac;
    }
    // phương thức static lấy ra số lượng đối tượng sản phẩm được tạo ra
    public static int getTongSoSP()
    {
        return tongSanPham;
    }
    //---NHƯNG PHƯƠNG THỨC CHUNG TRONG SẢN PHẨM---
    /* trừ số lượng hàng khi bán ra */
    public boolean banhang(int sl)
    {
        if(sl<0 || sl>soLuong)
            return false;
        soLuong -= sl;
        return true;
    }
     /* cộng số lượng hàng khi thêm vào */
    public void nhaphang(int sl) 
    {
        if(sl<0)
            throw new IllegalArgumentException("So luong nhap hang phai la so duong");
        soLuong += sl;
        System.out.println("So luong hang hoa da them vao kho tong la: "+ sl ):
    }
    //---NHƯNG PHƯƠNG THỨC ABSTRACT CỦA SẢN PHẨM---
    /** Mỗi loại máy tự mô tả thuộc tính riêng (đa hình). */
    public abstract String thongTinRieng();
    public abstract String loai();
    /*hàm trả về xem máy có phải dòng pro/pro max đối với ios _ ultra đối với android(samsung) */
    public abstract boolean HangPro_ultra();
    /*Hàm trả về giá bán lẻ từng hãng = giá gốc + phụ thu(có thể phụ thu dòng pro/ultra , có thể trừ nếu ưu đãi dòng cũ) */
    public abstract double Giabanle();
    
    @Override
    public String toString() {
        return String.format("%-6s %-8s %-22s SL:%-4d ĐG:%,15.0f Màu:%-8s | %s",
                maSP, loai(), tenSP, soLuong, donGia, mauSac, thongTinRieng());
    }
}
