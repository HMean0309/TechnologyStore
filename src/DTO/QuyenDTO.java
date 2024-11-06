package DTO;

public class QuyenDTO {
    private String id;
    private String name;
    private String des;
    private boolean isDelete;

    public QuyenDTO(String id, String name, String des, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.des = des;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}
