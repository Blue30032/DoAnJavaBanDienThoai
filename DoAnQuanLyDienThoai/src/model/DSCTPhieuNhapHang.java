import java.util.Arrays;
import java.util.Scanner;
public class DSCTPhieuNhapHang {
    Scanner sc=new Scanner(System.in);
    private CTPhieuNhapHang[] chiTiet;
    private int n;
    public DSCTPhieuNhapHang(){
        chiTiet=new CTPhieuNhapHang[0];
        n=0;
    }
    public DSCTPhieuNhapHang(int n){
        this.n=n;
        chiTiet=new CTPhieuNhapHang[n];
    }
    public DSCTPhieuNhapHang(DSCTPhieuNhapHang A){
        chiTiet=new CTPhieuNhapHang[A.n];
        n=A.n;
        for(int i=0;i<n;i++){
            chiTiet[i]=new CTPhieuNhapHang(A.chiTiet[i]);
        }
    }
    public double tinhTongTien(){
        double x=0;
        for(int i=0;i<n;i++){
            x+=chiTiet[i].getThanhTien();
        }
        return x;
    }
    public void them(CTPhieuNhapHang ct){
        chiTiet=Arrays.copyOf(chiTiet,chiTiet.length+1);
        chiTiet[chiTiet.length-1]=new CTPhieuNhapHang(ct);
        n++;
    }
    public void xoa(int i){
        if(n==0){
            System.out.println("Khong hop le!");
            return;
        }
        for(int j=i;i<n;i++){
            chiTiet[j]=chiTiet[j+1];
        }
        chiTiet=Arrays.copyOf(chiTiet, chiTiet.length-1);
        System.out.println("Da xoa chi tiet yeu cau.");
    }
    public void sua(int i){
        if(i>=n){
            System.out.println("Khong tim thay chi tiet!");
            return;
        }
        System.out.println("Da tim thay chi tiet muon sua.Thong tin:");
        System.out.println(chiTiet[i]);
        System.out.println("Hay chon cho muon sua:");
        System.out.print("1.So Luong.\n2.Don gia.\nChon:");
        int chon=sc.nextInt();
        switch(chon){
            case 1:
                System.out.print("Nhap so luong moi:");
                chiTiet[i].setSoLuong(sc.nextInt());
                break;
            case 2:
                System.out.print("Nhap don gia moi:");
                chiTiet[i].setDonGia(sc.nextDouble());
                break;
            default:break;
        }
        System.out.println("Da sua thong tin thanh cong!");
    }
    public CTPhieuNhapHang[] getChiTiet(){
        return chiTiet;
    }
    public int getN(){
        return n;
    }
    public void xuat(){
        System.out.println("=============DANH SACH CHI TIET PHIEU NHAP HANG=============");
        for(int i=0;i<n;i++){
            System.out.println(chiTiet[i]);
        }
    }
}
