package DTO;

public class ChiTietHoaDonDTO {
    private String idHoaDon;
    private String seri;
    private Integer donGia;

    public ChiTietHoaDonDTO(String idHoaDon, String seri, int donGia) {
        this.idHoaDon = idHoaDon;
        this.seri = seri;
        this.donGia = Integer.valueOf(donGia);
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public int getDonGia() {
        return donGia.intValue();
    }

    public void setDonGia(int donGia) {
        this.donGia = Integer.valueOf(donGia);
    }
}
