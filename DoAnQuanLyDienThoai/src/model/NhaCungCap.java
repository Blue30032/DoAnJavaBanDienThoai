package model;

import java.io.Serializable;

public class NhaCungCap implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maNCC, tenNCC, diaChi;

    public NhaCungCap(String maNCC, String tenNCC, String diaChi) {
        this.maNCC = maNCC; this.tenNCC = tenNCC; this.diaChi = diaChi;
    }
    public String getMaNCC() { return maNCC; }
    public String getTenNCC() { return tenNCC; }
    public String getDiaChi() { return diaChi; }
    @Override public String toString() { return String.format("%-6s %-25s %s", maNCC, tenNCC, diaChi); }
}
