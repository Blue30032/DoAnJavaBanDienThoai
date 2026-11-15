package model;
public class QuanLy {
    static DSSanPham dssp=new DSSanPham();
    static DSChiTietSanPham dsctsp=new DSChiTietSanPham();
    static DSKH dskh=new DSKH();
    static DSNV dsnv=new DSNV();
    static DSNhaCungCap dsncc=new DSNhaCungCap();
    static DSHoaDon dshd=new DSHoaDon();
    static DSChiTietHoaDon dscthd=new DSChiTietHoaDon();
    static DSPhieuNhapHang dspnh=new DSPhieuNhapHang();
    static DSCTPhieuNhapHang dsctpnh=new DSCTPhieuNhapHang();
    
    public void docDuLieu(){
        dssp.docFile();
        dsctsp.docFile();
        dskh.docFile();
        dsnv.docFile();
        dsncc.docFile();
        dshd.docFile();
        dscthd.docFile();
        dspnh.docFile();
        dsctpnh.docFile();
    }
    public void ghiDuLieu(){
        dssp.ghiFile();
        dsctsp.ghiFile();
        dskh.ghiFile();
        dsnv.ghiFile();
        dsncc.ghiFile();
        dshd.ghiFile();
        dscthd.ghiFile();
        dspnh.ghiFile();
        dsctpnh.ghiFile();
    }
}
