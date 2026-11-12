package model;
import helper.Nhaplieu;
/** Sản phẩm chạy iOS: có thêm tính năng Apple ProRes */
public class IOS extends SanPham {
    private static final long serialVersionUID = 1L;
    // dòng pro phụ thu thêm 0.03 vào khi tính sản phẩm bán lẻ
    private static final double Phu_Thu_Pro = 0.03;
    // tương tự với dòng pro max;
    private static final double Phu_Thu_ProMax = 0.05;
    private boolean proRes;
    //constructor
    public IOS()
    {
        super();
        proRes = false;
    }
   public IOS(String maSP,String tenSP,int soLuong,double donGia,String mauSac,boolean proRes)
    {
        super(maSP,tenSP,soLuong,donGia,mauSac);
        this.proRes=proRes;
    }
    //get và set
    public boolean IsproRes()
    {
        return proRes;
    }
    public void setproRes(boolean proRes)
    {
        this.proRes = proRes; 
    }
    // các phương thức riêng 
    @Override 
    public String getHang()
    {
        return "Apple";
    }
    @Override  
    public String getTNrieng()
    {
        return "[IOS] Apple Proges: "+(proRes ? "Co" : "Khong");
    }
    @Override 
    public boolean HangPro_ultra()
    {
        return proRes || tenChua( " pro");
    }
    // phương thức tính giá bán lẻ của điện thoại ios bằng đơn giá *(1 + tyle(Pro && ProMax))
    @Override 
    public double Giabanle()
    {
        double Tyle=0;
        if(tenChua("pro max")) Tyle = Phu_Thu_ProMax;
        else if(HangPro_ultra()) Tyle = Phu_Thu_Pro;
        return getDonGia() *(1 + Tyle);
    }
    @Override 
    public void nhapTNrieng()
    {
        proRes = Nhaplieu.docCoKhong("Dich vu may Apple co Proges (c/k)");
    }
     @Override 
    public String toDongFile()
    {
        return "IOS|" + dongChung() + "|" + proRes;
    }
}
