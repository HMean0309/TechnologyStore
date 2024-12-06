package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonDTO {
    private String id;
    private LocalDateTime ngayLap;
    private Integer orderAmount;
    private Integer discountAmount;
    private String ghiChu;
    private String km;
    private String pttt;
    private String idKhachHang;
    private String idNhanVien;
    private String namePttt;
    private String nameKhachHang;
    private String nameNhanVien;
    private Boolean isDelete;

    public HoaDonDTO(String id, LocalDateTime ngayLap, Integer orderAmount, Integer discountAmount,
                     String km, String pttt, String idKhachHang, String idNhanVien,
                     String namePttt, String nameKhachHang, String nameNhanVien,
                     Boolean isDelete) {
        this.id = id;
        this.ngayLap = ngayLap;
        this.orderAmount = orderAmount;
        this.discountAmount = discountAmount;
        this.km = km;
        this.pttt = pttt;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.namePttt = namePttt;
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

    public String getNamePttt() {
        return namePttt;
    }

    public void setNamePttt(String namePttt) {
        this.namePttt = namePttt;
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

    public int getTotal() {
        return (orderAmount - discountAmount);
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
