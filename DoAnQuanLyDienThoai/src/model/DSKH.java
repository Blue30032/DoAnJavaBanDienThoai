package DoAnBanDienThoai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DSKH {
    private KhachHang[] ds;
    private int n;
    private static final int MAX = 1000;
    private static final String TEN_FILE = "KhachHang.txt";

    public DSKH() {
        ds = new KhachHang[MAX];
        n = 0;
    }

    public DSKH(KhachHang[] ds, int n) {
        this();
        for (int i = 0; i < n && i < ds.length && i < MAX; i++) {
            if (ds[i] != null) {
                this.ds[this.n] = new KhachHang(ds[i]);
                this.n++;
            }
        }
    }

    public int getN() {
        return n;
    }

    public KhachHang[] getDs() {
        KhachHang[] ketQua = new KhachHang[n];

        for (int i = 0; i < n; i++) {
            ketQua[i] = new KhachHang(ds[i]);
        }

        return ketQua;
    }

    public boolean them(KhachHang kh) {
        if (kh == null || n >= MAX) {
            return false;
        }

        if (timViTriTheoMa(kh.getMaKH()) != -1) {
            System.out.println("Ma khach hang da ton tai!");
            return false;
        }

        ds[n] = new KhachHang(kh);
        n++;
        return true;
    }

    public int timViTriTheoMa(String maKH) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaKH().equalsIgnoreCase(maKH)) {
                return i;   
            }
        }

        return -1;
    }

    public KhachHang timTheoMa(String maKH) {
        int viTri = timViTriTheoMa(maKH);

        if (viTri == -1) {
            return null;
        }

        return new KhachHang(ds[viTri]);
    }

    public KhachHang[] timTheogtinhKH (String gtinhKH){
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getGtinhKH().equalsIgnoreCase(gtinhKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }
        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
    public KhachHang[] timTheoHo (String hoKH){
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getHoKH().equalsIgnoreCase(hoKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }
        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
    public KhachHang[] timTheoTen (String tenKH){
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getTenKH().equalsIgnoreCase(tenKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }
        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
       public KhachHang[] timTheoSDT (String sdtKH){
        KhachHang[] ketQua = new KhachHang[n];
        int dem = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getSdtKH().equalsIgnoreCase(sdtKH)) {
                ketQua[dem] = new KhachHang(ds[i]);
                dem++;
            }
        }
        KhachHang[] dsTimDuoc = new KhachHang[dem];

        for (int i = 0; i < dem; i++) {
            dsTimDuoc[i] = ketQua[i];
        }

        return dsTimDuoc;
    }
    
    public boolean suaTheoMa(String maKH, KhachHang khMoi) {
        int viTri = timViTriTheoMa(maKH);

        if (viTri == -1 || khMoi == null) {
            return false;
        }

        if (!maKH.equalsIgnoreCase(khMoi.getMaKH())
                && timViTriTheoMa(khMoi.getMaKH()) != -1) {
            System.out.println("Ma khach hang moi da ton tai!");
            return false;
        }

        ds[viTri] = new KhachHang(khMoi);
        return true;
    }

    public boolean xoaTheoMa(String maKH) {
        int viTri = timViTriTheoMa(maKH);

        if (viTri == -1) {
            return false;
        }

        for (int i = viTri; i < n - 1; i++) {
            ds[i] = ds[i + 1];
        }

        ds[n - 1] = null;
        n--;
        return true;
    }

    public void thongKeTheoGioiTinh() {
        if (n == 0) {
            System.out.println("Danh sach khach hang rong!");
            return;
        }

        int nam = 0, nu = 0;

        for (int i = 0; i < n; i++) {
            if (ds[i].getGtinhKH().equalsIgnoreCase("Nam")) {
                nam++;
            } else if (ds[i].getGtinhKH().equalsIgnoreCase("Nu")) {
                nu++;
            }
        }

        System.out.println("\n========== THONG KE THEO GIOI TINH ==========");
        System.out.printf("%-8s %-20s %15s%n", "STT", "GIOI TINH", "SO LUONG");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-8d %-20s %15d%n", 1, "Nam", nam);
        System.out.printf("%-8d %-20s %15d%n", 2, "Nu", nu);
        System.out.println("-----------------------------------------------");
        System.out.println("Tong so khach hang: " + n);
    }
    public void thongKeTheoTongTien() {
        if (n == 0) {
            System.out.println("Danh sach khach hang rong!");
            return;
        }

        int duoi5 = 0;
        int tu5den20 = 0;
        int tu20 = 0;

        for (int i = 0; i < n; i++) {
            double tien = ds[i].getTongTien();

            if (tien < 5000000) {
                duoi5++;
            } else if (tien < 20000000) {
                tu5den20++;
            } else {
                tu20++;
            }
        }

        System.out.println("\n========== THONG KE THEO TONG TIEN MUA HANG ==========");
        System.out.printf("%-30s %-20s %-15s%n","Muc tong tien", "So luong khach hang", "Ty le");

        System.out.printf("%-30s %-20d %14.2f%%%n","Duoi 5 trieu", duoi5, duoi5 * 100.0 / n);

        System.out.printf("%-30s %-20d %14.2f%%%n","Tu 5 den duoi 20 trieu", tu5den20, tu5den20 * 100.0 / n);

        System.out.printf("%-30s %-20d %14.2f%%%n","Tu 20 trieu tro len", tu20, tu20 * 100.0 / n);

        System.out.println("Tong so khach hang: " + n);
}
    public void xuatDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach khach hang rong!");
            return;
        }

        System.out.println("\n========== DANH SACH KHACH HANG ==========\n");

        System.out.printf("%-6s %-10s %-28s %-12s %-15s %15s%n",
                "STT", "MA KH", "HO VA TEN KHACH HANG","GIOI TINH", "SO DIEN THOAI", "TONG TIEN");

        System.out.println("-----------------------------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            KhachHang kh = ds[i];

            System.out.printf("%-6d %-10s %-28s %-12s %-15s %,15.0f%n",
                i + 1,
                kh.getMaKH(),
                kh.getHoKH() + " " + kh.getTenKH(),
                kh.getGtinhKH(),
                kh.getSdtKH(),
                kh.getTongTien());
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Tong so khach hang: " + n);
    }
    
    public void docFile() {
        BufferedReader br = null;

        try {
        // Bước 1: Mở file
            br = new BufferedReader(new FileReader(TEN_FILE));

        // Bước 2: Tạo mảng mới để chứa dữ liệu
            KhachHang[] dsMoi = new KhachHang[MAX];
            int soLuong = 0;
            String dong;

        // Bước 3: Đọc từng dòng trong file
            while ((dong = br.readLine()) != null && soLuong < MAX) {

            // Bỏ qua dòng trống
                if (dong.trim().isEmpty()) {
                    continue;
                }

            // Tách dữ liệu theo dấu |
                String[] p = dong.split("\\|", -1);

            // Kiểm tra đủ 6 thông tin
                if (p.length == 6) {
                    String maKH = p[0].trim();

                // Kiểm tra mã khách hàng đã tồn tại chưa
                    if (timTrongMang(dsMoi, soLuong, maKH) == -1) {

                        try {
                            String hoKH = p[1].trim();
                            String tenKH = p[2].trim();
                            String gtinhKH = p[3].trim();
                            String sdtKH = p[4].trim();
                            double tongTien = Double.parseDouble(p[5].trim());

                        // Tạo đối tượng khách hàng
                            dsMoi[soLuong] = new KhachHang(maKH, hoKH, tenKH, gtinhKH, sdtKH, tongTien);

                            soLuong++;

                        } catch (NumberFormatException e) {
                            System.out.println("Bo qua dong loi: " + dong);
                        }
                    }
                }
            }

        // Bước 4: Cập nhật danh sách
            ds = dsMoi;
            n = soLuong;

            System.out.println("Doc file thanh cong!");

        } catch (IOException e) {
            System.out.println("Khong the doc file!");

        } finally {
        // Bước 5: Đóng file
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Loi dong file!");
            }
        }
    }

    private int timTrongMang(
            KhachHang[] mang, int soLuong, String maKH) {

        for (int i = 0; i < soLuong; i++) {
            if (mang[i].getMaKH().equalsIgnoreCase(maKH)) {
                return i;
            }
        }

        return -1;
    }

    public void ghiFile() throws IOException {
    // Bước 1: Mở file
        BufferedWriter bw = new BufferedWriter(
            new FileWriter(TEN_FILE));

    // Bước 2: Ghi file
        for (int i = 0; i < n; i++) {
            KhachHang kh = ds[i]; 

            bw.write(kh.getMaKH() + "|"
                    + kh.getHoKH() + "|"
                    + kh.getTenKH() + "|"
                    + kh.getGtinhKH() + "|"
                    + kh.getSdtKH() + "|"
                    + kh.getTongTien ());

            bw.newLine();
        }

    // Bước 3: Đóng file
        bw.close();
    }
}
 
