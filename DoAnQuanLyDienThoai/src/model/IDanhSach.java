package model;

public interface IDanhSach<T> {
    void them(T doiTuong);
    void sua(String ma);
    void xoa(String ma);
}
