package DTO;

import java.util.Objects;

public class TaiKhoanDTO {
    private String username;
    private String password;
    private String idNV;
    private String idQuyen;
    private String email;
    private String nameQuyen;
    private Boolean isDelete;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String username, String password, String idNV, String idQuyen, String nameQuyen, Boolean isDelete) {
        this.username = username;
        this.password = password;
        this.idNV = idNV;
        this.idQuyen = idQuyen;
        this.nameQuyen = nameQuyen;
        this.isDelete = isDelete;
    }

    public TaiKhoanDTO(String username, String password, String idNV, String idQuyen, String email, String nameQuyen, Boolean isDelete) {
        this.username = username;
        this.password = password;
        this.idNV = idNV;
        this.idQuyen = idQuyen;
        this.email = email;
        this.nameQuyen = nameQuyen;
        this.isDelete = isDelete;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(String idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameQuyen() {
        return nameQuyen;
    }

    public void setNameQuyen(String nameQuyen) {
        this.nameQuyen = nameQuyen;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getTrangThai() {
        return isDelete ? "Bị khóa" : "Đang hoạt động";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaiKhoanDTO that)) return false;
        return getUsername().equals(that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
