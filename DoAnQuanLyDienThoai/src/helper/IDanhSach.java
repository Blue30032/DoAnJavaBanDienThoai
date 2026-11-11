package helper;

public interface IDanhSach<T> {
    boolean them(T doiTuong);
    boolean sua(String ma);
    boolean xoa(String ma);
}
