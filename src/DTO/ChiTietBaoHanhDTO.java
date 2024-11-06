package DTO;

public class ChiTietBaoHanhDTO {
    private String idBH;
    private String seri;

    public ChiTietBaoHanhDTO(String idBH, String seri) {
        this.idBH = idBH;
        this.seri = seri;
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
}
