package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuNhapDTO {
    private String id;
    private LocalDateTime ngayNhap;
    private Integer total;
    private String idNhanVien;
    private String idNCC;
    private String nameNhanVien;
    private String nameNCC;
    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDateTime ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getIdNCC() {
        return idNCC;
    }

    public void setIdNCC(String idNCC) {
        this.idNCC = idNCC;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getNameNhanVien() {
        return nameNhanVien;
    }

    public void setNameNhanVien(String nameNhanVien) {
        this.nameNhanVien = nameNhanVien;
    }

    public String getNameNCC() {
        return nameNCC;
    }

    public void setNameNCC(String nameNCC) {
        this.nameNCC = nameNCC;
    }

    public PhieuNhapDTO(String id, LocalDateTime ngayNhap, Integer total, 
            String idNhanVien, String idNCC,
                        String nameNhanVien, String nameNCC, Boolean isDelete) {
        this.id = id;
        this.ngayNhap = ngayNhap;
        this.total = total;
        this.idNhanVien = idNhanVien;
        this.idNCC = idNCC;
        this.nameNhanVien = nameNhanVien;
        this.nameNCC = nameNCC;
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhieuNhapDTO that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}