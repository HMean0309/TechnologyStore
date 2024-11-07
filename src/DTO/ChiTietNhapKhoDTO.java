package DTO;

public class ChiTietNhapKhoDTO {
    private String idPN;
    private String seri;
    private Integer cost;

    public ChiTietNhapKhoDTO(String idPN, String seri, int cost) {
        this.idPN = idPN;
        this.seri = seri;
        this.cost = Integer.valueOf(cost);
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
        return cost.intValue();
    }

    public void setCost(int cost) {
        this.cost = Integer.valueOf(cost);
    }
}
