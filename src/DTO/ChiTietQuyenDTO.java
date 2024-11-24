package DTO;

import java.util.Objects;

public class ChiTietQuyenDTO {
    private String idQuyen;
    private String idChucNang;
    private String permission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietQuyenDTO that)) return false;
        return getIdQuyen().equals(that.getIdQuyen()) && getIdChucNang().equals(that.getIdChucNang()) && getPermission().equals(that.getPermission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdQuyen(), getIdChucNang(), getPermission());
    }

    public String getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(String idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getIdChucNang() {
        return idChucNang;
    }

    public void setIdChucNang(String idChucNang) {
        this.idChucNang = idChucNang;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public ChiTietQuyenDTO(String idQuyen, String idChucNang, String permission) {
        this.idQuyen = idQuyen;
        this.idChucNang = idChucNang;
        this.permission = permission;
    }
}
