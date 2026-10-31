import java.util.Arrays;
import java.util.Scanner;
public class DSNhaCungCap extends NhaCungCap {
    Scanner sc= new Scanner(System.in);
    private NhaCungCap[] ncc;
    private int n;
    public DSNhaCungCap(){
        ncc=new NhaCungCap[0];
        n=0;
    }
    public DSNhaCungCap(int n){
        this.n=n;
        ncc=new NhaCungCap[n];
    }
    public DSNhaCungCap(DSNhaCungCap A){
        ncc=new NhaCungCap[A.n];
        n=A.n;
        for(int i=0;i<n;i++){
            ncc[i]=new NhaCungCap(A.ncc[i]);
        }
    }
    public void them(NhaCungCap A){ 
        ncc=Arrays.copyOf(ncc,ncc.length+1);
        ncc[n]=new NhaCungCap(A);
        n++;
    }
    public void xoa(String MaNCC){
        for(int i=0;i<n;i++){
            if(ncc[i].getMaNCC().equals(MaNCC)){
                for(int j=i;j<n-1;j++){
                    ncc[j]=ncc[j+1];
                }
                n--;
                ncc=Arrays.copyOf(ncc,n);
                System.out.println("Da xoa thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay Nha Cung Cap!");
    }
    public void sua(String MaNCC){
        for(int i=0;i<n;i++){
            if(ncc[i].getMaNCC().equals(MaNCC)){
                System.out.println("Da tim thay Nha Cung Cap muon sua. Thong tin:");
                System.out.println(ncc[i]);
                System.out.println("\nHay chon muc muon sua:");
                System.out.print("1.Ten Nha Cung Cap.\n2.Dia Chi Nha Cung Cap.\nChon:");
                int q=sc.nextInt();
                sc.nextLine();
                switch(q){
                    case 1:System.out.print("Nhap Ten Nha Cung Cap Moi:");ncc[i].setTenNCC(sc.nextLine());break;
                    case 2:System.out.print("Nhap Dia Chi Moi:");ncc[i].setDiaChi(sc.nextLine());break;
                    default:break;
                }
                System.out.println("Da sua thong tin thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay Nha Cung Cap!");
    }
    public NhaCungCap findMaNCC(String maNCC){
        for(int i=0;i<n;i++){
            if(ncc[i].getMaNCC().equals(maNCC)){
                return ncc[i];
            }
        }
        return null;
    }
    public DSNhaCungCap findTen(String tenNCC){
        DSNhaCungCap x= new DSNhaCungCap();
        x.ncc=new NhaCungCap[1];
        int y=0;
        for(int i=0;i<n;i++){
            if(ncc[i].getTenNCC().equals(tenNCC)){
                x.them(ncc[i]);
                y++;
            }
        }
        x.n=y;
        return x;
    }
    public DSNhaCungCap findDiaChi(String diaChi){
        DSNhaCungCap x= new DSNhaCungCap();
        x.ncc=new NhaCungCap[1];
        for(int i=0;i<n;i++){
            if(ncc[i].getDiaChi().equalsIgnoreCase(diaChi)){
                x.them(ncc[i]);
            }
        }
        return x;
    }
    public void thongkeDiaChi(){
        System.out.println("=============THONG KE THEO DIA CHI=============");
        System.out.printf("Dia chi        |So luong\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ncc[j].getDiaChi().equalsIgnoreCase(ncc[i].getDiaChi())){
                    found=true;
                    break;
                }
            }
            if(!found){
                ds=Arrays.copyOf(ds,ds.length+1);
                ds[k]=ncc[i].getDiaChi();
                k++;
            }
        }
        for(int i=0;i<k;i++){
            int dem=0;
            for(int j=0;j<n;j++){
                if(ncc[j].getDiaChi().equalsIgnoreCase(ds[i])){
                    dem++;
                }
            }
            System.out.printf("%-15s|%7d\n",ds[i],dem);
        }
    }
    public void xuat(){
        System.out.println("=============DANH SACH NHA CUNG CAP=============");
        for(int i=0;i<n;i++){
            System.out.println(ncc[i]);
        }
    }
}
