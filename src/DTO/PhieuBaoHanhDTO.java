package DTO;

import java.time.LocalDateTime;

public class PhieuBaoHanhDTO {
    private String id;
    private LocalDateTime ngayLap;
    private boolean isDelete;
    private String idNhanVien;
    private String idHoaDon;
    private LocalDateTime ngayTraHang;

    public PhieuBaoHanhDTO(String id, LocalDateTime ngayLap, boolean isDelete, String idNhanVien, String idHoaDon, LocalDateTime ngayTraHang) {
        this.id = id;
        this.ngayLap = ngayLap;
        this.isDelete = isDelete;
        this.idNhanVien = idNhanVien;
        this.idHoaDon = idHoaDon;
        this.ngayTraHang = ngayTraHang;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public LocalDateTime getNgayTraHang() {
        return ngayTraHang;
    }

    public void setNgayTraHang(LocalDateTime ngayTraHang) {
        this.ngayTraHang = ngayTraHang;
    }
}
