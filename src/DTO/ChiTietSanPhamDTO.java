package DTO;

import java.time.LocalDate;

public class ChiTietSanPhamDTO {
    private String seri;
    private String idSP;
    private String color;
    private Integer price;
    private LocalDate ngayNhap;
    private Boolean isDelete;
    public String getSeri() {
        return seri;
    }
    public void setSeri(String seri) {
        this.seri = seri;
    }
    public String getIdSP() {
        return idSP;
    }
    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public LocalDate getNgayNhap() {
        return ngayNhap;
    }
    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public ChiTietSanPhamDTO(String seri, String idSP, String color, Integer price, LocalDate ngayNhap,
            Boolean isDelete) {
        this.seri = seri;
        this.idSP = idSP;
        this.color = color;
        this.price = price;
        this.ngayNhap = ngayNhap;
        this.isDelete = isDelete;
    }

}
