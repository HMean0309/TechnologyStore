package DTO;

import java.time.LocalDateTime;

public class PhieuNhapKhoDTO {
    private String id;
    private LocalDateTime ngayNhap;
    private Integer total;
    private String idNhanVien;
    private String idNCC;
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
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public PhieuNhapKhoDTO(String id, LocalDateTime ngayNhap, Integer total, String idNhanVien, String idNCC,
            Boolean isDelete) {
        this.id = id;
        this.ngayNhap = ngayNhap;
        this.total = total;
        this.idNhanVien = idNhanVien;
        this.idNCC = idNCC;
        this.isDelete = isDelete;
    }

}