package model;
import helper.Nhaplieu;

public class NhaCungCap {
    private static int countNC=0;
    private String maNCC;
    private String tenNCC;
    private String diaChi;
    
    //3 ham thiet lap
    public NhaCungCap(){
        maNCC="";
        tenNCC="";
        diaChi="";
        countNC++;
    }
    public NhaCungCap(String maNCC,String tenNCC,String diaChi){
        this.maNCC=maNCC;
        this.tenNCC=tenNCC;
        this.diaChi=diaChi;
        countNC++;
    }
    public NhaCungCap(NhaCungCap A){
        maNCC=A.maNCC;
        tenNCC=A.tenNCC;
        diaChi=A.diaChi;
        countNC++;
    }
    
    //lay thong tin
    @Override public String toString() { return String.format("%-6s %-25s %s", maNCC, tenNCC, diaChi); }
    public void nhap(){
        maNCC=Nhaplieu.docChuoi("Nhap ma nha cung cap");
        tenNCC=Nhaplieu.docChuoi("Nhap ten nha cung cap");
        diaChi=Nhaplieu.docChuoi("Nhap dia chi");
    }
    //cac ham get/set
    public String getMaNCC(){
        return maNCC;
    }
    public void setMaNCC(String maNCC){
        this.maNCC=maNCC;
    }
    public String getTenNCC(){
        return tenNCC;
    }
    public void setTenNCC(String tenNCC){
        this.tenNCC=tenNCC;
    }
    public String getDiaChi(){
        return diaChi;
    }
    public void setDiaChi(String diaChi){
        this.diaChi=diaChi;
    }
    public static int getCountNC(){
        return countNC;
    }
    public String toFileString(){
        return getMaNCC()+"|"
                +getTenNCC()+"|"
                +getDiaChi();
    }
    public static NhaCungCap fromFileString(String dong){
        String[] s=dong.split("\\|",-1);
        if(s.length !=3) // tránh trường hợp file bị lỗi thiếu dữ liệu không lấy dc đối tượng nhà cung cấp
            throw new IllegalArgumentException("Thieu truong du lieu nha cung cap"); 
        return new NhaCungCap(s[0].trim(),s[1].trim(),s[2].trim());
    }
}
