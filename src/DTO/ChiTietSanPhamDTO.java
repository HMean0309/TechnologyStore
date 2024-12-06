package DTO;

import java.util.Objects;

public class ChiTietSanPhamDTO {
    private String seri;
    private String idSP;
    private String color;
    private Integer price;
    private Integer cost;
    private Boolean isDelete;

    private String idPhieuNhap;
    private String idHoaDon;

    public ChiTietSanPhamDTO(String seri, String idSP, String color, Integer price,
                             Boolean isDelete) {
        this.seri = seri;
        this.idSP = idSP;
        this.color = color;
        this.price = price;
        this.isDelete = isDelete;
    }

    public ChiTietSanPhamDTO(String seri, String idSP, String color,
                             Integer price, Integer cost,
                             String idPhieuNhap, String idHoaDon, Boolean isDelete) {
        this.seri = seri;
        this.idSP = idSP;
        this.color = color;
        this.price = price;
        this.cost = cost;
        this.isDelete = isDelete;
        this.idPhieuNhap = idPhieuNhap;
        this.idHoaDon = idHoaDon;
    }

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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(String idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietSanPhamDTO that)) return false;
        return getSeri().equals(that.getSeri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeri());
    }
}
