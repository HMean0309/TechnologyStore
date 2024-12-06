package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonDTO {
    private String id;
    private LocalDateTime ngayLap;
    private Integer orderAmount;
    private Integer discountAmount;
    private String idKhachHang;
    private String idNhanVien;
    private String nameKhachHang;
    private String nameNhanVien;
    private Boolean isDelete;

    public HoaDonDTO(String id, LocalDateTime ngayLap, Integer orderAmount, Integer discountAmount, String idKhachHang, String idNhanVien,
                     String nameKhachHang, String nameNhanVien, Boolean isDelete) {
        this.id = id;
        this.ngayLap = ngayLap;
        this.orderAmount = orderAmount;
        this.discountAmount = discountAmount;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.nameKhachHang = nameKhachHang;
        this.nameNhanVien = nameNhanVien;
        this.isDelete = isDelete;
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

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public int getTotal() {
        return orderAmount - discountAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getNameKhachHang() {
        return nameKhachHang;
    }

    public void setNameKhachHang(String nameKhachHang) {
        this.nameKhachHang = nameKhachHang;
    }

    public String getNameNhanVien() {
        return nameNhanVien;
    }

    public void setNameNhanVien(String nameNhanVien) {
        this.nameNhanVien = nameNhanVien;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoaDonDTO hoaDonDTO)) return false;
        return getId().equals(hoaDonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}