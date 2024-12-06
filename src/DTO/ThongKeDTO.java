package DTO;

public class ThongKeDTO {
    private String key;
    private Integer DoanhThu;
    private Integer ChiPhi;

    public ThongKeDTO(String key, Integer ChiPhi, Integer DoanhThu) {
        this.key = key;
        this.DoanhThu = DoanhThu;
        this.ChiPhi = ChiPhi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(Integer DoanhThu) {
        this.DoanhThu = DoanhThu;
    }

    public Integer getChiPhi() {
        return ChiPhi;
    }

    public void setChiPhi(Integer ChiPhi) {
        this.ChiPhi = ChiPhi;
    }
     @Override
    public String toString() {
        return "ThongKeDTO{" +
                "key='" + key + '\'' +
                ", DoanhThu=" + DoanhThu +
                ", ChiPhi=" + ChiPhi +
                '}';
    }
}
