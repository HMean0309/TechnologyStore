package DTO;

import java.time.LocalDateTime;

public class PhieuNhapKhoDTO {
    private String id;
    private LocalDateTime ngayNhap;
    private Integer total;
    private boolean isDelete;
    private String id_nhanvien;
    private String idNCC;

    public PhieuNhapKhoDTO(String id, LocalDateTime ngayNhap, int total, String id_nhanvien, String idNCC) {
        this.id = id;
        this.ngayNhap = ngayNhap;
        this.total = total;
        this.id_nhanvien = id_nhanvien;
        this.idNCC = idNCC;
    }

    public PhieuNhapKhoDTO() {
        
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
        return total.intValue();
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

    public String getidNhanVien() {
        return id_nhanvien;
    }

    public void setidNhanVien(String id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }

    public String getIdNCC() {
        return idNCC;
    }

    public void setIdNCC(String idNCC) {
        this.idNCC = idNCC;
    }
}
