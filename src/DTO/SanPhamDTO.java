package DTO;

public class SanPhamDTO {
    private String id;
    private String name;
    private Boolean isDelete;
    private String idCate;
    private Integer baoHanh;
    private String img;
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
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public String getIdCate() {
        return idCate;
    }
    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }
    public Integer getBaoHanh() {
        return baoHanh;
    }
    public void setBaoHanh(Integer baoHanh) {
        this.baoHanh = baoHanh;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public SanPhamDTO(String id, String name, Boolean isDelete, String idCate, Integer baoHanh, String img) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.idCate = idCate;
        this.baoHanh = baoHanh;
        this.img = img;
    }
    
}