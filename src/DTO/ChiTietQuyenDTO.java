package DTO;

public class ChiTietQuyenDTO {
    private String idQuyen;
    private String idChucNang;
    private boolean show;
    private boolean insert;
    private boolean edit;
    private boolean delete;

    public ChiTietQuyenDTO(String idQuyen, String idChucNang, boolean show, boolean insert, boolean edit, boolean delete) {
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

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
