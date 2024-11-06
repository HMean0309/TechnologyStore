package DTO;

public class ChiTietHoaDonDTO {
    private String idHoaDon;
    private String seri;
    private long donGia;

    public ChiTietHoaDonDTO(String idHoaDon, String seri, int donGia) {
        this.idHoaDon = idHoaDon;
        this.seri = seri;
        this.donGia = donGia;
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

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
