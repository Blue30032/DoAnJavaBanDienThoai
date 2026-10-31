import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
public class DSPhieuNhapHang {
    Scanner sc= new Scanner(System.in);
    private PhieuNhapHang[] pnh;
    private int n;
    public DSPhieuNhapHang(){
        pnh=new PhieuNhapHang[0];
        n=0;
    }
    public DSPhieuNhapHang(int n){
        this.n=n;
        pnh=new PhieuNhapHang[n];
    }
    public DSPhieuNhapHang(DSPhieuNhapHang A){
        pnh=new PhieuNhapHang[A.n];
        n=A.n;
        for(int i=0;i<n;i++){
            pnh[i]=new PhieuNhapHang(A.pnh[i]);
        }
    }
    public void them(PhieuNhapHang A){
        pnh=Arrays.copyOf(pnh,pnh.length+1);
        pnh[n]=new PhieuNhapHang(A);
        n++;
    }
    public void xoa(String MaPNH){
        if(pnh[n-1].getMaPNH().equals(MaPNH)){
            n--;
            return;
        }
        for(int i=0;i<n-1;i++){
            if(pnh[i].getMaPNH().equals(MaPNH)){
                for(int j=i;j<n-1;j++){
                    pnh[j]=pnh[j+1];
                }
                System.out.println("Da xoa Phieu Nhap Hang co ma "+MaPNH);
                return;
            }
        }
        System.out.println("Khong tim thay Phieu Nhap Hang!");
    }
    public void sua(String MaPNH){
        for(int i=0;i<n;i++){
            if(pnh[i].getMaPNH().equals(MaPNH)){
                System.out.println("Da tim thay Phieu Nhap Hang muon sua. Thong tin:");
                System.out.println(pnh[i]);
                System.out.println("\nHay chon muc muon sua:");
                System.out.print("1.Ma Nha Cung Cap.\n2.Ma Nhan Vien.\n3.Ngay Lap Phieu.\nChon:");
                int q=sc.nextInt();
                sc.nextLine();
                switch(q){
                    case 1:System.out.print("Nhap Ma Nha Cung Cap Moi:");pnh[i].setMaNCC(sc.nextLine());break;
                    case 2:System.out.print("Nhap Ma Nhan Vien Moi:");pnh[i].setMaNV(sc.nextLine());break;
                    case 3:System.out.print("Nhap Ngay Lap Phieu Moi:");pnh[i].setNgayLap(LocalDate.parse(sc.nextLine()));break;
                    default:break;
                }
                System.out.println("Da sua thong tin thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay Nha Cung Cap!");
    }
    public PhieuNhapHang findMaPNH(String maPNH){
        for(int i=0;i<n;i++){
            if(pnh[i].getMaPNH().equals(maPNH)){
                return pnh[i];
            }
        }
        return null;
    }
    public DSPhieuNhapHang findMaNCC(String maNCC){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        x.pnh=new PhieuNhapHang[1];
        int y=0;
        for(int i=0;i<n;i++){
            if(pnh[i].getMaNCC().equals(maNCC)){
                x.them(pnh[i]);
                y++;
            }
        }
        x.n=y;
        return x;
    }
    public DSPhieuNhapHang findMaNV(String maNV){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        x.pnh=new PhieuNhapHang[1];
        int y=0;
        for(int i=0;i<n;i++){
            if(pnh[i].getMaNV().equals(maNV)){
                x.them(pnh[i]);
                y++;
            }
        }
        x.n=y;
        return x;
    }
    public DSPhieuNhapHang findNgayLap(LocalDate ngay){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        x.pnh=new PhieuNhapHang[1];
        int y=0;
        for(int i=0;i<n;i++){
            if(pnh[i].getNgayLap().equals(ngay)){
                x.them(pnh[i]);
                y++;
            }
        }
        x.n=y;
        return x;
    }
    public void thongkeNCC(){
        System.out.println("=============THONG KE THEO NHA CUNG CAP=============");
        System.out.printf("Ma Nha Cung Cap |So luong giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(pnh[j].getMaNCC().equals(pnh[i].getMaNCC())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNCC();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            int dem=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNCC().equals(ds[i])){
                    dem++;
                }
            }
            System.out.printf("%-16s|%18d\n",ds[i],dem);
        }
    }
    public void thongkeNV(){
        System.out.println("=============THONG KE THEO NHAN vIEN=============");
        System.out.printf("Ma Nhan Vien |Thuc hien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(pnh[j].getMaNCC().equals(pnh[i].getMaNCC())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNCC();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            int dem=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNCC().equals(ds[i])){
                    dem++;
                }
            }
            System.out.printf("%-13s|%19d\n",ds[i],dem);
        }
    }
    public void thongkeTongTienNCC(){
        System.out.println("=============THONG KE TONG TIEN THEO NHA CUNG CAP=============");
        System.out.printf("Ma Nha Cung Cap |Tong tien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(pnh[j].getMaNCC().equals(pnh[i].getMaNCC())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNCC();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            double tong=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNCC().equals(ds[i])){
                    tong+=pnh[j].getTongTien();
                }
            }
            System.out.printf("%-16s|%19f\n",ds[i],tong);
        }
    }
    public void thongkeTongTienNV(){
        System.out.println("=============THONG KE TONG TIEN THEO NHAN VIEN=============");
        System.out.printf("Ma Nhan Vien |Tong tien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(pnh[j].getMaNV().equals(pnh[i].getMaNV())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=pnh[i].getMaNV();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            double tong=0;
            for(int j=0;j<n;j++){
                if(pnh[j].getMaNV().equals(ds[i])){
                    tong+=pnh[j].getTongTien();
                }
            }
            System.out.printf("%-13s|%19f\n",ds[i],tong);
        }
    }
    public void xuat(){
        System.out.println("=============DANH SACH PHIEU NHAP HANG=============");
        for(int i=0;i<n;i++){
            System.out.println(pnh[i]);
        }
    }
}
