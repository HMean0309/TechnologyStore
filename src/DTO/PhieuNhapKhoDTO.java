package DTO;

import java.time.LocalDateTime;

public class PhieuNhapKhoDTO {
    private String id;
    private LocalDateTime ngayNhap;
    private int total;
    private boolean isDelete;
    private String nhanVien;
    private String idNCC;

    public PhieuNhapKhoDTO(String id, LocalDateTime ngayNhap, int total, boolean isDelete, String nhanVien, String idNCC) {
        this.id = id;
        this.ngayNhap = ngayNhap;
        this.total = total;
        this.isDelete = isDelete;
        this.nhanVien = nhanVien;
        this.idNCC = idNCC;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getIdNCC() {
        return idNCC;
    }

    public void setIdNCC(String idNCC) {
        this.idNCC = idNCC;
    }
}
