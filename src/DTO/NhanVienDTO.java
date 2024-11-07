package DTO;

public class NhanVienDTO {
    private String id;
    private String name;
    private String phone;
    private boolean isDelete;

    public NhanVienDTO(String id, String name, String phone, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isDelete = isDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}
