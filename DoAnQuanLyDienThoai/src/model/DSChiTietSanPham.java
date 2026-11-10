package model;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import helper.IDocGhiFile;
import helper.Nhaplieu;

public class DSChiTietSanPham implements IDocGhiFile{
    private ChiTietSanPham[] ds;
    private int n;
    private static final int BanDau  =10; // biến static giá trị ban đầu của mảng 
    //constructor
    public DSChiTietSanPham(){ ds = new ChiTietSanPham[BanDau]; n=0;}
    public DSChiTietSanPham(int sucChua) {
        n=0;
        ds = new ChiTietSanPham[ sucChua >0 ? sucChua : BanDau];
    }
    /* Các phương thức cơ bản  */
    // --- truy  xuất---
    public int getN()
        {
            return n;
        }
        // trả về danh sách tại ví trị mình cần tìm 
    public ChiTietSanPham getvitri(int i)
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
    /*Phương thức thêm xóa  */
    public boolean them(ChiTietSanPham CTSP)
    {
        if(CTSP==null || timViTri(CTSP.getMaSP()) !=-1)
            return false;
        if(n==ds.length) ds= Arrays.copyOf(ds, n*2);
        ds[n++]=CTSP;
        return true;
    }
    public boolean xoa(String maSP)
    {
        int i = timViTri(maSP);
        if(i==-1)
            return false;
        for(int j=i;j<n-1;j++)
        {
            ds[j]=ds[j+1];
        }
        ds[--n]=null;
        return true;
    }
    // Các hàm tìm kiếm 
    public ChiTietSanPham timTheoMa(String maSP)
    {
        int i= timViTri(maSP);
        return i == -1 ? null : ds[i];
    }
    public DSChiTietSanPham timTheoHang(String hang) {
        DSChiTietSanPham kq = new DSChiTietSanPham();
        for (int i = 0; i < n; i++) if (ds[i].getHang().equalsIgnoreCase(hang.trim())) kq.them(ds[i]);
        return kq;
    }
    public DSChiTietSanPham timRamTuToiThieu(int ram) {
        DSChiTietSanPham kq = new DSChiTietSanPham();
        for (int i = 0; i < n; i++) if (ds[i].getRam() >= ram) kq.them(ds[i]);
        return kq;
    }
    // Phươn thức xuất thông tin 
    public void xuat() {
        if (n == 0) { System.out.println("(Danh sách trống)"); return; }
        for (int i = 0; i < n; i++) ds[i].xuat();
    }
    /* Thông kê */
    public void ThongKeDungLuong() {

        System.out.println("========== THONG KE THEO DUNG LUONG ==========");

        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }

        int chon;

        do {

            System.out.println("\nChon dung luong muon xem:");
            System.out.println("1. 64GB");
            System.out.println("2. 128GB");
            System.out.println("3. 256GB");
            System.out.println("4. 512GB");
            System.out.println("5. 1TB");
            System.out.println("0. Thoat");

            chon = Nhaplieu.docSoNguyen("Nhap lua chon", 0, 5);

            switch (chon) {

                case 1:
                    xuatTheoDungLuong(64);
                    break;

                case 2:
                    xuatTheoDungLuong(128);
                    break;

                case 3:
                    xuatTheoDungLuong(256);
                    break;

                case 4:
                    xuatTheoDungLuong(512);
                    break;

                case 5:
                    // 1TB = 1024GB
                    xuatTheoDungLuong(1024);
                    break;

                case 0:
                    System.out.println("Ket thuc thong ke!");
                    break;
            }

        } while (chon != 0);
    }

    // Hàm phụ: xuất các chi tiết sản phẩm có dung lượng tương ứng
    private void xuatTheoDungLuong(int dungLuong) {

        boolean timThay = false;

        for (int i = 0; i < n; i++) {

            if (ds[i].getDungLuong() == dungLuong) {

                ds[i].xuat();
                timThay = true;
            }
        }
        if (!timThay)
            System.out.println("Khong co san pham nao co dung luong nay.");
    }
    /* Phương thức đọc và ghi file */
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
    @Override
    public boolean docFile(String tenFile)
    {
        DSChiTietSanPham temp = new DSChiTietSanPham();
        try(BufferedReader br = Files.newBufferedReader(Paths.get(tenFile),StandardCharsets.UTF_8))
        {
            String dong;
            int lineNumber=0;
            while((dong=br.readLine())!=null) // đọc hết tất cả nội dung file đến khi file trống
            {
                lineNumber++; // mỗi lần đọc tăng số dòng
                if(dong.trim().isEmpty())
                    continue;// bỏ dòng nào nào trống 
                try{
                    if(!temp.them(ChiTietSanPham.tuDongFile(dong))) // thêm các đối tượng vào biến tạm 
                    {
                        System.out.println("Dòng "+ lineNumber +" : Mã sản phẩm đã tồn tại, bỏ qua");
                    }
                }catch(RuntimeException e)
                {
                    System.out.println("Dòng "+lineNumber+" : Lỗi dữ liệu, bỏ qua: "+e.getMessage());
                }
            }
        } catch(IOException e)
        {
            System.out.println("Lỗi đọc file "+tenFile+": "+e.getMessage());
            return false;
        }
        // Chỉ thay danh sách hiện tại sau khi đọc file thành công
        this.ds=temp.ds;
        this.n=temp.n;
        return true;  
    }
}
