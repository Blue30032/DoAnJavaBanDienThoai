import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DSPhieuNhapHang implements IDanhSach<PhieuNhapHang>{
    Scanner sc= new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private PhieuNhapHang[] pnh;
    private int n;
    
    //3 ham thiet lap
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
    
    //cac ham them sua xoa
    @Override
    public void them(PhieuNhapHang A){
        pnh=Arrays.copyOf(pnh,pnh.length+1);
        pnh[n]=new PhieuNhapHang(A);
        n++;
    }
    public void them(){
        PhieuNhapHang Y=new PhieuNhapHang();
        
        System.out.print("Nhap ma phieu nhap hang:");
        Y.setMaPNH(sc.nextLine());
        
        System.out.print("Nhap ma nhan vien");
        Y.setMaNV(sc.nextLine());
        
        System.out.print("Nhap ma nha cung cap:");
        Y.setMaNCC(sc.nextLine());
        
        System.out.print("Nhap ngay lap phieu (dd/MM/yyyy):");
        LocalDate ngay = LocalDate.parse(sc.nextLine(), dtf);
        Y.setNgayLap(ngay);
        
        System.out.print("Nhap so luong chi tiet phieu nhap hang:");
        int count=sc.nextInt();
        
        sc.nextLine();
        for(int i=0;i<count;i++){
            CTPhieuNhapHang A=new CTPhieuNhapHang();
            A.setMaPNH(Y.getMaPNH())
                    ;
            System.out.print("Nhap ma san pham:");
            A.setMaSP(sc.nextLine());
            
            System.out.print("Nhap so luong nhap ve:");
            A.setSoLuong(sc.nextInt());
            
            System.out.print("Nhap don gia san pham:");
            A.setDonGia(sc.nextDouble());
            sc.nextLine();
            /* tham chieu toi class danh sach san pham
               su dung ham tim san pham*/
            /*
            SanPham X=new SanPham();
            X=qldssp.dssp.timTheoMa(A.getMaSP);
            if(X==null){
                X.setSoLuong(A.getSoLuong());
            }
            else{
                X.setSoLuong(X.getSoLuong()+A.getSoLuong());
            }*/
            Y.getChiTiet().them(A);
        }
        them(Y);
    }
    @Override
    public void xoa(String MaPNH){
        for(int i=0;i<n;i++){
            if(pnh[i].getMaPNH().equals(MaPNH)){
                for(int j=i;j<n-1;j++){
                    pnh[j]=pnh[j+1];
                }
                n--;
                pnh=Arrays.copyOf(pnh, n);
                System.out.println("Da xoa Phieu Nhap Hang co ma "+MaPNH);
                return;
            }
        }
        System.out.println("Khong tim thay Phieu Nhap Hang!");
    }
    @Override
    public void sua(String MaPNH){
        for(int i=0;i<n;i++){
            if(pnh[i].getMaPNH().equals(MaPNH)){
                System.out.println("Da tim thay Phieu Nhap Hang muon sua. Thong tin:");
                System.out.println(pnh[i]);
                System.out.println("\nHay chon muc muon sua:");
                System.out.print("1.Ma Nha Cung Cap.\n2.Ma Nhan Vien.\n3.Ngay Lap Phieu.\n4.Chi Tiet Phieu Nhap Hang.\nChon:");
                int q=sc.nextInt();
                sc.nextLine();
                switch(q){
                    case 1:System.out.print("Nhap Ma Nha Cung Cap Moi:");pnh[i].setMaNCC(sc.nextLine());break;
                    case 2:System.out.print("Nhap Ma Nhan Vien Moi:");pnh[i].setMaNV(sc.nextLine());break;
                    case 3:System.out.print("Nhap Ngay Lap Phieu Moi:");pnh[i].setNgayLap(LocalDate.parse(sc.nextLine(),dtf));break;
                    case 4:System.out.print("Nhap Ma San Pham De Tim Chi Tiet Muon Sua:");pnh[i].getChiTiet().sua(sc.nextLine());break;                   
                    default:break;
                }
                System.out.println("Da sua thong tin thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay Nha Cung Cap!");
    }
    
    //Cac ham tim kiem
    //tim kiem theo ma phieu nhap hang
    public PhieuNhapHang findMaPNH(String maPNH){
        for(int i=0;i<n;i++){
            if(pnh[i].getMaPNH().equals(maPNH)){
                return pnh[i];
            }
        }
        return null;
    }
    //tim kiem theo ma nha cung cap
    public DSPhieuNhapHang findMaNCC(String maNCC){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(pnh[i].getMaNCC().equals(maNCC)){
                x.them(pnh[i]);
            }
        }
        return x;
    }
    //tim kiem theo ma nhan vien
    public DSPhieuNhapHang findMaNV(String maNV){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(pnh[i].getMaNV().equals(maNV)){
                x.them(pnh[i]);
            }
        }
        return x;
    }
    //timkiem theo ngay lap phieu
    public DSPhieuNhapHang findNgayLap(LocalDate ngay){
        DSPhieuNhapHang x=new DSPhieuNhapHang();
        for(int i=0;i<n;i++){
            if(pnh[i].getNgayLap().equals(ngay)){
                x.them(pnh[i]);
            }
        }
        return x;
    }
    
    //cac ham thong ke
    //thong ke so luong giao dich ung voi tung nha cung cap
    public void thongkeNCC(){
        System.out.println("=============THONG KE THEO NHA CUNG CAP=============");
        System.out.printf("Ma Nha Cung Cap |So luong giao dich\n");
        String[] ds=new String[0];
        int k=0;
        //tao mang khong trung
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNCC())){
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
        //dem so lan lap ma nha cung cap
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
    //tuong tu ham thong ke theo nha cung cap
    public void thongkeNV(){
        System.out.println("=============THONG KE THEO NHAN vIEN=============");
        System.out.printf("Ma Nhan Vien |Thuc hien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNCC())){
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
    //thong ke tong tien da chi tra cho tung nha cung cap
    public void thongkeTongTienNCC(){
        System.out.println("=============THONG KE TONG TIEN THEO NHA CUNG CAP=============");
        System.out.printf("Ma Nha Cung Cap |Tong tien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNCC())){
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
    //thong ke tong tien giao dich duoc nhan vien chiu trach nhiem lap phieu
    public void thongkeTongTienNV(){
        System.out.println("=============THONG KE TONG TIEN THEO NHAN VIEN=============");
        System.out.printf("Ma Nhan Vien |Tong tien giao dich\n");
        String[] ds=new String[0];
        int k=0;
        for(int i=0;i<n;i++){
            boolean found=false;
            for(int j=0;j<k;j++){
                if(ds[j].equals(pnh[i].getMaNV())){
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
    
    //xuat thong tin
    public void xuat(){
        System.out.println("=============DANH SACH PHIEU NHAP HANG=============");
        for(int i=0;i<n;i++){
            System.out.println(pnh[i]);
        }
    }
}
