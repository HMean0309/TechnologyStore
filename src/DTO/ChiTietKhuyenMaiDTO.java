package DTO;

import java.util.Objects;

public class ChiTietKhuyenMaiDTO {
    private String idKM;
    private String idSP;

    public ChiTietKhuyenMaiDTO(String idKM, String idSP) {
        this.idKM = idKM;
        this.idSP = idSP;
    }

    public String getIdKM() {
        return idKM;
    }

    public void setIdKM(String idKM) {
        this.idKM = idKM;
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
        if (!(o instanceof ChiTietKhuyenMaiDTO that)) return false;
        return getIdKM().equals(that.getIdKM()) && getIdSP().equals(that.getIdSP());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdKM(), getIdSP());
    }
}