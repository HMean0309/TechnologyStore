package DTO;

public class TaiKhoanDTO {
    private String username;
    private String password;
    private boolean type;
    private String idNV;
    private String idQuyen;
    private boolean isDelete;

    public TaiKhoanDTO(String username, String password, boolean type, String idNV, String idQuyen, boolean isDelete) {
        this.username = username;
        this.password = password;
        this.type = type;
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

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}
