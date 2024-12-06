package DTO;

import java.util.Objects;

public class ChiTietPhieuNhapDTO {
    private String idPN;
    private String seri;
    private Integer cost;

    public ChiTietPhieuNhapDTO() {

    }

    public ChiTietPhieuNhapDTO(String idPN, String seri, int cost) {
        this.idPN = idPN;
        this.seri = seri;
        this.cost = cost;
    }

    public String getIdPN() {
        return idPN;
    }

    public void setIdPN(String idPN) {
        this.idPN = idPN;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietPhieuNhapDTO that)) return false;
        return getIdPN().equals(that.getIdPN()) && getSeri().equals(that.getSeri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPN(), getSeri());
    }
}
