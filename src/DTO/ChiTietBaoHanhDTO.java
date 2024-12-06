package DTO;

import java.util.Objects;

public class ChiTietBaoHanhDTO {
    private String idBH;
    private String seri;
    private String color;
    private String idSP;

    public ChiTietBaoHanhDTO(String idBH, String seri, String color, String idSP) {
        this.idBH = idBH;
        this.seri = seri;
        this.color = color;
        this.idSP = idSP;
    }

    public String getIdBH() {
        return idBH;
    }

    public void setIdBH(String idBH) {
        this.idBH = idBH;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietBaoHanhDTO that)) return false;
        return getIdBH().equals(that.getIdBH()) && getSeri().equals(that.getSeri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdBH(), getSeri());
    }
}
