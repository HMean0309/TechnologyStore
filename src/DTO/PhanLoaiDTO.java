package DTO;

public class PhanLoaiDTO {
    private String id;
    private String name;
    private Boolean isDelete;

    public PhanLoaiDTO(String id, String name, Boolean isDelete) {
        this.id = id;
        this.name = name;
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

    public Boolean isDelete() {
        return isDelete;
    }

    public void setDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    
}
