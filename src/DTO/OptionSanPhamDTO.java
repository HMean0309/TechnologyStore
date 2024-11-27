package DTO;

import java.util.Objects;

public class OptionSanPhamDTO {
    public String idSP;
    public String color;
    public Boolean isDelete;

    public OptionSanPhamDTO(String idSp, String color, Boolean isDelete) {
        this.idSP = idSp;
        this.color = color;
        this.isDelete = isDelete;
    }

    public Boolean isDelete() {
        return isDelete;
    }

    public void setDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSp) {
        this.idSP = idSp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptionSanPhamDTO that)) return false;
        return getIdSP().equals(that.getIdSP()) && getColor().equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdSP(), getColor());
    }
}
