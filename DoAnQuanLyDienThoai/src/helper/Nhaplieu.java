package helper;
import java.util.Scanner;
public class Nhaplieu { 
    //hang so sc de de dang nhap
    private static final Scanner sc = new Scanner(System.in);
    //constructor
    private Nhaplieu(){}
    public static String docChuoi(String nhan)
    {
        while(true)
        {
            System.out.println(nhan+": ");
            String s = sc.nextLine().trim();
            if(!s.isEmpty())
                return s;
            System.out.println("Khong duoc de chuoi, nhap lai!");
        }
    }
    /* nhập số nguyên trong [min,max] */
    public static int docSoNguyen(String nhan,int min,int max)
    {
        while(true)
        {
            System.out.println(nhan + ": ");
            String s = sc.nextLine().trim();
            try
            {
               int v =Integer.parseInt(s);
               if(v >= min && v<=max)
                return v;
               if(max == Integer.MAX_VALUE) System.out.println("gia tri phai >= "+ min +",vui long nhap lai so!");
               else 
                System.out.print("gia tri phai tu "+min +" den " + max + ",nhap lai!");
            }
            catch (NumberFormatException e)
            {
                System.out.println("phai nhap gia tri so nguyen(vi du: 24),nhap lai!");
            }
        }
    }
    /*  nhập số nguyên lớn hơn min */
    public static int docSoNguyen(String nhan,int min)
    {
        return docSoNguyen(nhan, min, Integer.MAX_VALUE);
    }
    /*  nhập số thực >= min(dùng dấu chấm làm dấu thập phân không ) */
    public static double docSoThuc(String nhan,double min)
    {
        System.out.println(nhan + ": ");
        String s = sc.nextLine().trim();
        while(true)
        {
            try{
                double v = Double.parseDouble(s);
                if (v>=min)
                    return v;
                else 
                    System.out.println("gia tri phai >= \"+ min +\",vui long nhap lai so!");
            }
            catch(NumberFormatException e){
                System.out.println("phai nhap gia tri so thuc(vi du: 12400000),nhap lai!");
            }
        }
    }
     /* Nhập có/không: c, co, y, yes, 1 là có; k, khong, n, no, 0 là không. */
    public static boolean docCoKhong(String nhan)
    {
        System.out.println(nhan +": ");
        String s = sc.nextLine().trim().toLowerCase();
        while(true)
        {
            if(s.equals("c") || s.equals("co") || s.equals("y") || s.equals("yes") || s.equals("1") )
                return true;
            if(s.equals("k") || s.equals("khong") || s.equals("n") || s.equals("no") || s.equals("0"))
                return false;
            System.out.println("Vui long chi nhap co hoac khong, nhap lai!");
        }
    }
}
