package DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class BaoHanhDTO {
    private String id;
    private LocalDateTime ngayBaoHanh;
    private LocalDate ngayTraHang;
    private String idNhanVien;
    private String nameNhanVien;
    private String idHoaDon;
    private String idKhachHang;
    private String nameKhachHang;
    private Boolean isDelete;

    public BaoHanhDTO(String id, LocalDateTime ngayBaoHanh, LocalDate ngayTraHang,
                      String idNhanVien, String nameNhanVien,
                      String idHoaDon, String idKhachHang, String nameKhachHang,
                      Boolean isDelete) {
        this.id = id;
        this.ngayBaoHanh = ngayBaoHanh;
        this.ngayTraHang = ngayTraHang;
        this.idNhanVien = idNhanVien;
        this.nameNhanVien = nameNhanVien;
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.nameKhachHang = nameKhachHang;
        this.isDelete = isDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getNgayBaoHanh() {
        return ngayBaoHanh;
    }

    public void setNgayBaoHanh(LocalDateTime ngayBaoHanh) {
        this.ngayBaoHanh = ngayBaoHanh;
    }

    public LocalDate getNgayTraHang() {
        return ngayTraHang;
    }

    public void setNgayTraHang(LocalDate ngayTraHang) {
        this.ngayTraHang = ngayTraHang;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getNameNhanVien() {
        return nameNhanVien;
    }

    public void setNameNhanVien(String nameNhanVien) {
        this.nameNhanVien = nameNhanVien;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getNameKhachHang() {
        return nameKhachHang;
    }

    public void setNameKhachHang(String nameKhachHang) {
        this.nameKhachHang = nameKhachHang;
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
        if (!(o instanceof BaoHanhDTO that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
