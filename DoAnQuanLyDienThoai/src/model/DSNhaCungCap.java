import java.util.Arrays;
import java.util.Scanner;
public class DSNhaCungCap implements IDanhSach<NhaCungCap>{
    Scanner sc= new Scanner(System.in);
    private NhaCungCap[] ncc;
    private int n;
    
    //3 ham thiet lap
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
    
    //ham them,sua,xoa
    @Override
    public void them(NhaCungCap A){ 
        ncc=Arrays.copyOf(ncc,ncc.length+1);
        ncc[n]=new NhaCungCap(A);
        n++;
    }
    @Override
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
    @Override
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
    
    //cac ham tim kiem
    //ham tim kiem theo ma nha cung cap, tra ve 1 nha cung cap
    public NhaCungCap findMaNCC(String maNCC){
        for(int i=0;i<n;i++){
            if(ncc[i].getMaNCC().equals(maNCC)){
                return ncc[i];
            }
        }
        return null;
    }
    //ham tim kiem theo ten nha cung cap,tra ve danh sach cac nha cung cap
    public DSNhaCungCap findTen(String tenNCC){
        DSNhaCungCap x= new DSNhaCungCap();
        for(int i=0;i<n;i++){
            if(ncc[i].getTenNCC().equals(tenNCC)){
                x.them(ncc[i]);
            }
        }
        return x;
    }
    //ham tim kiem theo dia chi,tra ve danh sach
    public DSNhaCungCap findDiaChi(String diaChi){
        DSNhaCungCap x= new DSNhaCungCap();
        for(int i=0;i<n;i++){
            if(ncc[i].getDiaChi().equalsIgnoreCase(diaChi)){
                x.them(ncc[i]);
            }
        }
        return x;
    }
    
    //cac ham thong ke
    public void thongkeDiaChi(){
        System.out.println("=============THONG KE THEO DIA CHI=============");
        System.out.printf("Dia chi        |So luong\n");
        String[] ds=new String[0];
        int k=0;
        //lap 1 mang chua cac dia chi khong trung
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equalsIgnoreCase(ncc[i].getDiaChi())){
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
        //dem so lan lap lai cua cac dia chi
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
    
    //xuat thong tin
    public void xuat() {
        System.out.println("\n========== DANH SACH NHA CUNG CAP ==========\n");
        // Xac dinh do rong cac cot
        int stt = 3;
        int ma = "MA NCC".length();
        int ten = "TEN NHA CUNG CAP".length();
        int dc = "DIA CHI".length();
        for (int i = 0; i < n; i++) {
            ma = Math.max(ma, ncc[i].getMaNCC().length());
            ten = Math.max(ten, ncc[i].getTenNCC().length());
            dc = Math.max(dc, ncc[i].getDiaChi().length());
            stt = Math.max(stt, String.valueOf(i + 1).length());
        }
        // In tieu de cot
        System.out.printf("%-" + (stt + 3) + "s", "STT");
        System.out.printf("%-" + (ma + 3) + "s", "MA NCC");
        System.out.printf("%-" + (ten + 3) + "s", "TEN NHA CUNG CAP");
        System.out.printf("%s\n", "DIA CHI");
        // Tinh do dai duong ke
        int tong = stt + ma + ten + dc + 9;
        System.out.println("-".repeat(tong));
        // In danh sach
        for (int i = 0; i < n; i++) {
            System.out.printf("%-" + (stt + 3) + "s", i + 1);
            System.out.printf("%-" + (ma + 3) + "s", ncc[i].getMaNCC());
            System.out.printf("%-" + (ten + 3) + "s", ncc[i].getTenNCC());
            System.out.println(ncc[i].getDiaChi());
        }
        System.out.println("-".repeat(tong));
        System.out.println("Tong so nha cung cap: " + n);
    }
}
