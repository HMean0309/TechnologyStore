package DTO;

import java.util.Objects;

public class SanPhamDTO {
    private String id;
    private String name;
    private String img;
    private String idCate;
    private String nameCate;
    private Integer baoHanh;
    private Integer tonKho;
    private Boolean isDelete;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Integer getBaoHanh() {
        return baoHanh;
    }

    public String getBaoHanhString() {
        return getBaoHanh().toString() + " tháng";
    }

    public void setBaoHanh(Integer baoHanh) {
        this.baoHanh = baoHanh;
    }

    public Integer getTonKho() {
        return tonKho;
    }

    public String getTonKhoString() {
        return getTonKho().toString();
    }

    public void setTonKho(Integer tonKho) {
        this.tonKho = tonKho;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public SanPhamDTO(String id, String name, String img, String idCate, String nameCate,
                      Integer baoHanh, Integer tonKho, Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.idCate = idCate;
        this.nameCate = nameCate;
        this.baoHanh = baoHanh;
        this.tonKho = tonKho;
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SanPhamDTO that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}