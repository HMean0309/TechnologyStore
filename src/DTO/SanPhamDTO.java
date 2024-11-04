package DTO;

public class SanPhamDTO {
    private String id;
    private String name;
    private String idCate;
    private int baoHanh;
    private String des;
    private String img;

    public SanPhamDTO(){}
    public SanPhamDTO(String id, String name, String idCate, int baoHanh, String des, String img) {
        this.id = id;
        this.name = name;
        this.idCate = idCate;
        this.baoHanh = baoHanh;
        this.des = des;
        this.img = img;
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
    public String getIdCate() {
        return idCate;
    }
    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }
    public int getBaoHanh() {
        return baoHanh;
    }
    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    
}
