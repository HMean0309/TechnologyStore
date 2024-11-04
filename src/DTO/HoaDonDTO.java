package DTO;

import java.time.LocalDateTime;

public class HoaDonDTO {
    private String id;
    private long orderAmount;
    private long discountAmount;
    private String status;
    private String note;
    private String km;
    private String pttt;
    private String khachHang;
    private String nhanVien;
    private LocalDateTime ngayLap;

    public HoaDonDTO() {};
    public HoaDonDTO(String id, long orderAmount, long discountAmount, String status, String note, String km,
            String pttt, String khachHang, String nhanVien, LocalDateTime ngayLap) {
        this.id = id;
        this.orderAmount = orderAmount;
        this.discountAmount = discountAmount;
        this.status = status;
        this.note = note;
        this.km = km;
        this.pttt = pttt;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }
    public long getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(long discountAmount) {
        this.discountAmount = discountAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
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
    public LocalDateTime getNgayLap() {
        return ngayLap;
    }
    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }
}
