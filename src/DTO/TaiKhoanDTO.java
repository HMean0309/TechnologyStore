package DTO;

public class TaiKhoanDTO {
    private String username;
    private String password;
    private String idNV;
    private String idQuyen;
    private Boolean isDelete;

    public TaiKhoanDTO(String username, String password, String idNV, String idQuyen, Boolean isDelete) {
        this.username = username;
        this.password = password;
        this.idNV = idNV;
        this.idQuyen = idQuyen;
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

    public Boolean isDelete() {
        return isDelete;
    }

    public void setDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
