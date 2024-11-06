package DTO;

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

    public String getidSP() {
        return idSP;
    }

    public void setidSP(String idSP) {
        this.idSP = idSP;
    }
}
