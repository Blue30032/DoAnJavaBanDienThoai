public class NhaCungCap {
    //dem so luong nha cung cap duoc tao
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
    
    //cac ham get/set
    public String getMaNCC(){
        return maNCC;
    }
    public void setMaNCC(String x){
        maNCC=x;
    }
    public String getTenNCC(){
        return tenNCC;
    }
    public void setTenNCC(String x){
        tenNCC=x;
    }
    public String getDiaChi(){
        return diaChi;
    }
    public void setDiaChi(String x){
        diaChi=x;
    }
    public static int getCountNC(){
        return countNC;
    }
}
