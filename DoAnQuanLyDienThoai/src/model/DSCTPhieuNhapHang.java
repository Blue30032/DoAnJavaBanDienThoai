import java.util.Arrays;
import java.util.Scanner;
public class DSCTPhieuNhapHang {
    Scanner sc=new Scanner(System.in);
    private CTPhieuNhapHang[] chiTiet;
    private int n;
    
    //3 ham thiet lap
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
    
    //3 ham them, sua, xoa
    public void them(CTPhieuNhapHang ct){
        chiTiet=Arrays.copyOf(chiTiet,chiTiet.length+1);
        chiTiet[chiTiet.length-1]=new CTPhieuNhapHang(ct);
        n++;
    }
    public void xoa(CTPhieuNhapHang A){
        int index=findIndex(A);
        if(index==-1){
            System.out.println("Khong hop le!");
            return;
        }
        for(int j=index;j<n-1;j++){
            chiTiet[j]=chiTiet[j+1];
        }
        chiTiet=Arrays.copyOf(chiTiet, chiTiet.length-1);
        System.out.println("Da xoa chi tiet yeu cau.");
    }
    public void sua(String maSP){
        for(int i=0;i<n;i++){
            if(chiTiet[i].getMaSP().equals(maSP)){
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
        }
    }
    
    //cac ham bo tro
    public int getN(){
        return n;
    }
    public CTPhieuNhapHang[] getChiTietMang(){
        return chiTiet;
    }
    public double tinhTongTien(){
        double t=0;
        for(CTPhieuNhapHang ct : chiTiet){
            t+=ct.getThanhTien();
        }
        return t;
    }
    public int findIndex(CTPhieuNhapHang A){
        for(int i=0;i<n;i++){
            if(chiTiet[i].getMaSP().equals(A.getMaSP())){
                return i;
            }
        }
        return -1;
    }
    
    //xuat thong tin
    public void xuat(){
        System.out.println("=============DANH SACH CHI TIET PHIEU NHAP HANG=============");
        for(int i=0;i<n;i++){
            System.out.println(chiTiet[i]);
        }
    }
}
