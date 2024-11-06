package DTO;

public class ChiTietSanPhamDTO {
    private String seri;
    private String idSP;
    private String color;
    private long price;
    private boolean isDelete;

    public ChiTietSanPhamDTO(String seri, String idSP, String color, long price, boolean isDelete) {
        this.seri = seri;
        this.idSP = idSP;
        this.color = color;
        this.price = price;
        this.isDelete = isDelete;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getidSP() {
        return idSP;
    }

    public void setidSP(String idSP) {
        this.idSP = idSP;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
}
