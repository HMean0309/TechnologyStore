package DTO;

import java.time.LocalDateTime;

public class HoaDonDTO {
    private String id;
    private LocalDateTime ngayLap;
    private Integer orderAmount;
    private Integer discountAmount;
    private String status;
    private String ghiChu;
    private String km;
    private String pttt;
    private String khachHang;
    private String nhanVien;

    public HoaDonDTO(String id, LocalDateTime ngayLap, Integer orderAmount, Integer discountAmount, String status, String ghiChu, String km, String pttt, String khachHang, String nhanVien) {
        this.id = id;
        this.ngayLap = ngayLap;
        this.orderAmount = orderAmount;
        this.discountAmount = discountAmount;
        this.status = status;
        this.ghiChu = ghiChu;
        this.km = km;
        this.pttt = pttt;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
    }

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

    public int getOrderAmount() {
        return orderAmount.intValue();
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getDiscountAmount() {
        return discountAmount.intValue();
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGhichu() {
        return ghiChu;
    }

    public void setGhichu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getKhuyenMai() {
        return km;
    }

    public void setKhuyenMai(String km) {
        this.km = km;
    }

    public String getPhuongThucTT() {
        return pttt;
    }

    public void setPhuongThucTT(String pttt) {
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
    
}
