package DTO;

public class ChiTietQuyenDTO {
    private String idQuyen;
    private String idChucNang;
    private Boolean show;
    private Boolean insert;
    private Boolean edit;
    private Boolean delete;

    public ChiTietQuyenDTO(String idQuyen, String idChucNang, Boolean show, Boolean insert, Boolean edit, Boolean delete) {
        this.idQuyen = idQuyen;
        this.idChucNang = idChucNang;
        this.show = show;
        this.insert = insert;
        this.edit = edit;
        this.delete = delete;
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

    public Boolean isShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean isInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

    public Boolean isEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean isDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
