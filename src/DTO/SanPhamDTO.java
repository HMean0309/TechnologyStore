package DTO;

import java.util.Objects;

public class SanPhamDTO {
    private String id;
    private String name;
    private boolean isDelete;
    private String idCate;
    private int baoHanh;
    private String des;
    private String img;
    
    public SanPhamDTO(String id, String name, boolean isDelete, String idCate, int baoHanh, String des, String img) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SanPhamDTO that = (SanPhamDTO) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SanPhamDTO{id='" + id + "', name='" + name + "'}";
    }

    
}
