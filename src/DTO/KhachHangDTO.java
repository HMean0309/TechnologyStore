package DTO;

public class KhachHangDTO {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String district;
    private String ward;
    private String city;
    private Boolean isDelete;
    
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public KhachHangDTO(String id, String name, String phone, String address, String district, String ward, String city,
            Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.district = district;
        this.ward = ward;
        this.city = city;
        this.isDelete = isDelete;
    }

    
}
