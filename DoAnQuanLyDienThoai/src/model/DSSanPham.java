package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import helper.IDocGhiFile;
import helper.Nhaplieu;


public class DSSanPham implements IDocGhiFile {
    // Sức chứa ban đầu của mảng
    private static final int SUC_CHUA_BAN_DAU = 10;

    private int n; // số phần tử thực tử đang dùng
    private SanPham[] ds;
    // constructor 
    public DSSanPham()
    {
        int n=0;
        ds = new SanPham[SUC_CHUA_BAN_DAU];
    }
    public DSSanPham(int sucChua)
    {
        int n = 0;
        ds = new SanPham[sucChua > 0 ? sucChua : SUC_CHUA_BAN_DAU];
    }
    // --- truy  xuất---
    public int getSoLuong()
    {
        return n;
    }
    // trả về danh sách tại ví trị mình cần tìm 
    public SanPham getvitri(int i)
    {
        if(i<0 || i>=n)
            throw new IndexOutOfBoundsException("Lay ra vi tri ds khong hop le: "+i);
        return ds[i];
    }
    // nhập mã trả về vị trí mã đó đang đứng 
    private int timViTri(String maSP)
    {
        if(maSP==null)
            return -1;
        for(int i=0;i<n;i++)
        {
            if(ds[i].getMaSP().equalsIgnoreCase(maSP.trim()))
                return i;
        }
        return -1;
    }   
    /*Phương thức Thêm/Nhập/Xóa/Sửa danh sách */
    // hàm thêm sp . trả về true nếu vị trí không bị trùng 
    public boolean them(SanPham sp)
    {
        if(sp==null || timViTri(sp.getMaSP()) !=-1)
            return false; 
        if(n == ds.length) ds = Arrays.copyOf(ds, ds.length *2 ); // cấp phát thêm mảng động gấp đôi nếu mảng mảng đầy
        {
            ds[n++] = sp;
            return true;
        }   
    }
    // Nhập sản phẩm IOS or android vào để thêm vào ds . trả về true nếu thêm thành công
    public boolean nhapSanPham()
    {
        int loai = Nhaplieu.docSoNguyen("Nhap loai san pham(1.IOS , 2.ANDROID", 1, 2);
        SanPham sp = (loai==1) ? new IOS() : new Android();
        sp.nhap();
        if(!them(sp))
        {
            System.out.println("Ma "+sp.getMaSP()+" da ton tai, khong them dc");
            return false;
        }
        else 
        {
            System.out.println("Da them SP "+sp.getTenSP()+" vao danh sach");
            return true;
        }
    }
    // sửa sản phẩm theo mã trả về true nếu sửa thành công
    public boolean sua(String maSP)
    {
        SanPham sp = timTheoMa(maSP);
        if(sp==null)
            return false;
        sp.sua();
        return true;
    }
    // xóa sản phẩm theo masp dồn các phần tử lên trên. trả về true nếu xóa thành công 
    public boolean xoa(String maSP)
    {
        int i = timViTri(maSP);
        if(i == -1) return false; 
        for(int j = i ;j<n-1;j++)
        {
            ds[j]=ds[j+1];
        }
        ds[--n] = null;
        return true;
    }
    /*Các phương thức tìm kiếm */
    // tìm kiếm theo mã trả về sản phẩm nếu tìm thấy
    public SanPham timTheoMa(String maSP)
    {
        int i = timViTri(maSP);
        return i==-1 ? null : ds[i];
    }
    // tìm kiếm theo tên trả về danh sách sản phẩm có chưa tên đó
    public DSSanPham timkiemTen(String TenSP)
    {
        DSSanPham kq = new DSSanPham();
        String k = (TenSP == null) ? "" : TenSP.trim().toLowerCase();
        for(int i=0;i<n;i++)
        {
            if(ds[i].getTenSP().toLowerCase().contains(k))
            {
                kq.them(ds[i]);
            }
        }
        return kq;
    }
    //tìm theo hãng trả về danh sách sản phẩm có chưa hãng đó
    public DSSanPham timkiemHang(String hang)
    {
        DSSanPham kq = new DSSanPham();
        String k = (hang == null) ? "" : hang.trim().toLowerCase();
        for(int i=0;i<n;i++)
        {
            if(ds[i].getHang().toLowerCase().contains(k))
            {
                kq.them(ds[i]);
            }
        }
        return kq;
    }
    // tìm kiếm các sản phẩm thuộc dòng cao cấp trả về danh sách sản phẩm 
    public DSSanPham timkiemDongCaoCap()
    {
        DSSanPham kq = new DSSanPham();
        for(int i=0;i<n;i++)
        {
            if(ds[i].HangPro_ultra())
            {
                kq.them(ds[i]);
            }
        }
        return kq;
    }
    // tìm kiếm các giá bán lẻ <= x trả về danh sách sản phẩm
    public DSSanPham timGiaNhoHoacBang(double x)
    {
        DSSanPham kq = new DSSanPham();
        for(int i=0;i<n;i++)
        {
            if(ds[i].Giabanle()<=x)
            {
                kq.them(ds[i]);
            }
        }
        return kq;
    }
    // tìm kiếm các giá bán lẻ >=x trả về danh sách sản phẩm 
    public DSSanPham timGiaLonHoacBang(double x)
    {
        DSSanPham kq = new DSSanPham();
        for(int i=0;i<n;i++)
        {
            if(ds[i].Giabanle()>=x)
            {
                kq.them(ds[i]);
            }
        }
        return kq;
    }
    /*Nghiệp vụ bán hàng */
    //bán hàng theo mã sản phẩm và số lượng trả về true nếu bán thành công
    public boolean banhang(String maSP,int sl)
    {
        SanPham sp = timTheoMa(maSP); // tìm sản phẩm theo mã 
        return sp !=null && sp.banhang(sl);
    }
    // nhập hàng theo mã sản phẩm và số lượng trả về true nếu nhập thành công
    public boolean nhaphang(String maSP,int sl)
    {
        SanPham sp = timTheoMa(maSP);
        if (sp == null || sl <= 0) return false;
        sp.nhaphang(sl);
        return true;
    }
    /*Sắp xếp sản phẩm*/
    // dùng selection sort sắp xếp theo giá bán lẻ tăng dần
    public void sapxepTheoGiaBanle()
    {
        for(int i=0;i<n-1;i++)
        {
            int min= i;
            for(int j=i+1;j<n;j++)
            {
                if(ds[j].Giabanle()<ds[min].Giabanle())
                    min=j;
            }
            SanPham temp = ds[i];
            ds[i] = ds[min];
            ds[min] = temp;
        }
    }
    /* Xuất danh sách sản phẩm */
    public void xuatDanhSach()
    {
        if(n==0)
        {
            System.out.println("Danh Sach rong!");
            return;
        }
        for(int i=0;i<n;i++)
        {
            ds[i].xuat();
            System.out.println("--------------------------------------------");
        }
    }
    /*Thống kê */
    // tổng giá trị hàng hóa trong kho = tổng giá bán lẻ * số lượng
    public double TongGiaTriHangHoa()
    {
        double sum=0;
        for(int i=0;i<n;i++)
        {
            sum+=ds[i].Giabanle() * ds[i].getSoLuong();
        }
        return sum;
    }
    // Danh Sach cac san pham sap het hang(Nhap nguong het hang neu sl < nguong neu ra ds)
    public DSSanPham timSaphet(int nguong)
    {
        DSSanPham kq = new DSSanPham();
        for(int i=0;i<n;i++)
        {
            if(ds[i].getSoLuong()<nguong)
            {
                kq.them(ds[i]);
            }
        }
        return kq;
    }
    public void thongKeTheoHang()
    {
        System.out.println("========== Thong ke theo hang ==========");
        if(n==0)
            {
            System.out.println("Danh Sach rong");
            return;
        }
        String[] hang = new String[n];
        int[] soMau = new int[n];
        int[] tonKho = new int[n];
        int soHang =0;
        int tongTon=0;
        for(int i=0;i<n;i++)
        {
            String h = ds[i].getHang();
            int k= -1; // biến tạm dùng để tìm thấy hãng có trong mảng thay chưa 
            for(int j=0;j<soHang;j++)
            {
                if(hang[j].equalsIgnoreCase(h))
                {
                    k=j;
                    break;
                }
            }
            if(k==-1)
            {
                k = soHang++;// lấy giá trị số hạng hiện tại gán cho k rồi số hạng tăng lên +1
                hang[k] = h;
            }
        soMau[k]++;// chỉ cộng thêm 1 vào số mẫu không làm thay đổi k bên trong
        tonKho[k] += ds[i].getSoLuong();
        tongTon += ds[i].getSoLuong();
        }
        System.out.printf("%-10s %8s %10s%n",
            "Hãng", "Số mẫu", "Tồn kho");

        for (int k = 0; k < soHang; k++) {
        System.out.printf("%-10s %8d %10d%n",
                hang[k], soMau[k], tonKho[k]);
        }

        System.out.printf("%-10s %8d %10d%n",
            "Tổng", n, tongTon);
    }
    // Thống kê giá bán lẻ: thấp nhất, cao nhất, trung bình, tổng giá trị tồn kho. 
    public void thongKeGiaBanLe() {
        System.out.println("========== Thống kê giá bán lẻ ==========");
        if (n == 0) {
            System.out.println("(Danh sách trống)");
            return;
        }
        SanPham cao = ds[0];
        SanPham thap = ds[0];
        double tong = 0;
        double giaTriTon =0;
        for(int i=0;i<n;i++)
        {
            double g = ds[i].Giabanle();
            if(g>cao.Giabanle()) cao=ds[i];
            if(g<thap.Giabanle()) thap=ds[i];
            tong+=g;
            giaTriTon += g * ds[i].getSoLuong();
        }
        System.out.printf("Thấp nhất : %s - %,.0f%n", thap.getTenSP(), thap.Giabanle());
        System.out.printf("Cao nhất  : %s - %,.0f%n", cao.getTenSP(), cao.Giabanle());
        System.out.printf("Trung bình: %,.0f%n", tong / n);
        System.out.printf("Tổng giá trị tồn kho (theo giá bán lẻ): %,.0f%n", giaTriTon);
    }
    /*Đọc và Ghi file */
    @Override 
    public boolean ghiFile(String tenFile)
    {
        try {

            Path path = Paths.get(tenFile); // hàm tự tạo thư mục file text

            // Tạo thư mục cha nếu chưa tồn tại
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
        try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(tenFile),StandardCharsets.UTF_8))
        {
            for(int i=0;i<n;i++)
            {
                bw.write(ds[i].toDongFile()); 
                bw.newLine(); // ghi tết tất cả nôi dụng chi tiết vào 1 dòng rồi tạo thêm dòng mới cho đến hết
            }
        }
        return true;
    }
     catch(IOException e){
            System.out.println("Lỗi ghi file "+ tenFile+ ": " +e.getMessage());
            return false;
    }
}
    // đọc file và tạo ra danh sách sản phẩm từ file. trả về true nếu đọc thành công
    @Override 
    public boolean docFile(String tenFile)
    {
        DSSanPham temp = new DSSanPham();
        try(BufferedReader br = Files.newBufferedReader(Paths.get(tenFile),StandardCharsets.UTF_8))
        {
            String dong;
            int lineNumber = 0;
            while((dong = br.readLine())!=null)
            {
                lineNumber++;
                if(dong.trim().isEmpty()) continue; // bỏ qua dòng trống
                try{
                    if(!temp.them(taoTuDong(dong)))
                    {
                        System.out.println("Dòng "+lineNumber+" : Mã sản phẩm đã tồn tại, bỏ qua");
                    }
                } catch(RuntimeException e)
                {
                    System.out.println("Dòng "+lineNumber+" : Lỗi dữ liệu, bỏ qua: "+e.getMessage());
                }
            }
        } catch(IOException e)
        {
            System.out.println("Lỗi đọc file "+tenFile+": "+e.getMessage());
            return false;
        }
        this.ds=temp.ds;
        this.n=temp.n;
        return true;
    }
    //Tách chuỗi thành các trường dữ liệu và tạo ra đối tượng sản phẩm tương ứng
    private static SanPham taoTuDong(String dong)
    {
        String[] p = dong.split("\\|",-1);// tách chuỗi thành các trường dữ liệu(-1 giữ các trường trống)
        String loai =p[0].trim().toUpperCase();
        switch (loai) {
            case "IOS":
                if(p.length!=7)
                {
                    throw new IllegalArgumentException("Dữ liệu IOS không hợp lệ cần 7 Đối tượng");
                }
                return new IOS(p[1].trim(),p[2].trim()
                    ,Integer.parseInt(p[3].trim()),
                    Double.parseDouble(p[4].trim())
                    ,p[5].trim(),Boolean.parseBoolean(p[6].trim())); // tạo constructor isos từ các đối tượng trong mảng p
            case "ANDROID":
                if(p.length!=8)
                {
                    throw new IllegalArgumentException("Dữ liệu Android không hợp lệ cần 8 Đối tượng");
                }
                return new Android(p[1].trim(),p[2].trim()
                    ,Integer.parseInt(p[3].trim()),
                    Double.parseDouble(p[4].trim())
                    ,p[5].trim(),Boolean.parseBoolean(p[6].trim()),Boolean.parseBoolean(p[7].trim())); // tạo constructor android từ các đối tượng trong mảng p   
            default:
                throw new IllegalArgumentException("Loại sản phẩm không hợp lệ: " +p[0]);
        }
    }
}
