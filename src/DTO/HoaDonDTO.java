package DTO;

import java.time.LocalDateTime;

public class HoaDonDTO {
    private String id;
    private LocalDateTime ngayLap;
    private Integer orderAmount;
    private Integer discountAmount;
    private String ghiChu;
    private String km;
    private String pttt;
    private String khachHang;
    private String nhanVien;
    private Boolean isDelete;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getNgayLap() {
        return ngayLap;
    }
    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }
    public Integer getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }
    public Integer getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    public String getKm() {
        return km;
    }
    public void setKm(String km) {
        this.km = km;
    }
    public String getPttt() {
        return pttt;
    }
    public void setPttt(String pttt) {
        this.pttt = pttt;
    }
    public String getKhachHang() {
        return khachHang;
    }
    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }
    public String getNhanVien() {
        return nhanVien;
    }
    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public HoaDonDTO(String id, LocalDateTime ngayLap, Integer orderAmount, Integer discountAmount, String ghiChu,
            String km, String pttt, String khachHang, String nhanVien, Boolean isDelete) {
        this.id = id;
        this.ngayLap = ngayLap;
        this.orderAmount = orderAmount;
        this.discountAmount = discountAmount;
        this.ghiChu = ghiChu;
        this.km = km;
        this.pttt = pttt;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.isDelete = isDelete;
    }

}
