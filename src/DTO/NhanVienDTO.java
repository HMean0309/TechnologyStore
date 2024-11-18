package DTO;

import java.time.LocalDate;

public class NhanVienDTO {
    private String id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birth;
    private Boolean gender;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getBirth() {
        return birth;
    }
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    public Boolean getGender() {
        return gender;
    }
    public void setGender(Boolean gender) {
        this.gender = gender;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public NhanVienDTO(String id, String name, String phone, String email, LocalDate birth, Boolean gender,
            Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.isDelete = isDelete;
    }


}
