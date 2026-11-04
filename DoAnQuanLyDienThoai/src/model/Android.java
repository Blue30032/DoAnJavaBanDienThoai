package model;
import helper.Nhaplieu;
/** Sản phẩm chạy Android: bút cảm ứng, cổng hồng ngoại. */
public class Android extends SanPham {
    private static final long serialVersionUID = 1L;
    private static final double Phu_Thu_SamSung_Utra = 0.05;
    private static final double Phu_Thu_Hang_Khac_Pro = 0.03;
    /*
     - Bảng hãng: cột 0 là tên hãng hiển thị, các cột sau là từ khóa tìm trong tên máy.
     - Không ghi hết tên tất cả các hãng, hãng nào không có tên ghi là "Khác".
     */
    private  static final String[][] Bang_Hang = 
    {
        {"Samsung", "samsung", "galaxy"},
        {"Xiaomi", "xiaomi", "redmi", "poco"},
        {"Oppo", "oppo", "reno"},
        {"Vivo", "vivo"},
        {"Realme", "realme"},
        {"Honor", "honor"},
        {"Nokia", "nokia"}
    };
    private boolean butCamUng, congHongNgoai;
    //constructor
    public Android()
    {
        super();
        Butcamung = false;
        Conghongngoai = false;
    }
    public Android(String maSP, String tenSP, int soLuong, double donGia, String mauSac,
                   boolean butCamUng, boolean congHongNgoai) {
        super(maSP, tenSP, soLuong, donGia, mauSac);
        this.butCamUng = butCamUng; this.congHongNgoai = congHongNgoai;
    }
    //get 
    public boolean IsButcamung()
    {
        return Butcamung;
    }
    public boolean IsConghongngoai()
    {
        return Conghongngoai;
    }
    //set
    public void setButcamung(boolean Butcamung)
    {
        this.Butcamung=Butcamung;
    }
    public void setConghongngoai(boolean Conghongngoai)
    {
        this.Conghongngoai=Conghongngoai;
    }
    /* Trả hãng theo tên máy vd Galaxy 24 -> trả tên ra hãng samsung */
    @Override 
    public String getHang()
    {
        String ten = getTenSP().toLowerCase();
        for(String[] dong : Bang_Hang) // quét từng dòng trong bảng hàng ( lấy dòng từ bảng 2 chiều ) 
        {
            for(int i=1;i<dong.length;i++)
            {
                if(ten.contains(dong[i])) // hàm contains trả về true nếu tìm tên điện thoại giống với bãng hãng ở trên
                    return dong[0];
            }
        }
        return "Khac"; // nếu không có là hãng samsung khác chưa liệt kê
    }
    @Override
    public String getTNrieng()
    {
        return "[Android] But cam ung: " + (Butcamung ? "Co" : "Khong")
        +  " | Cong hong ngoai: " + (Conghongngoai ? "Co" : "Khong");
    }
    // Hàm có phải dòng cao cấp hay không chứa các tên: "Pro || Utra || Flip || Fold "
    @Override 
    public boolean HangPro_ultra()
    {
        return Butcamung || tenChua("utra") || tenChua("pro") || tenChua("flip");
    }
    @Override 
    public double Giabanle()
    {
        double tyle =0;
        if(HangPro_ultra())
        {
            tyle=getHang().equals("SamSung") ? Phu_Thu_SamSung_Utra : Phu_Thu_Hang_Khac_Pro;
        }
        return getDonGia()*(1+tyle);
    }
    @Override 
    public void nhapTNrieng()
    {
        Butcamung = Nhaplieu.docCoKhong("Dich vu may co But cam ung (c/k)");
        Conghongngoai = Nhaplieu.docCoKhong("Dich vu may co Cong hong ngoai (c/k):");
    }
}
