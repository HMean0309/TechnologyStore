package DTO;

public class NhaCungCapDTO {
    private String id;
    private String name;
    private String phone; 
    private String address;
    private String ward;
    private String district;
    private String city;

    public NhaCungCapDTO(String id, String name, String phone, String address, String ward, String district, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
