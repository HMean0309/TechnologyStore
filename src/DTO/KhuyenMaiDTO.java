package DTO;

import java.time.LocalDateTime;

public class KhuyenMaiDTO {
    private String id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer donViKM;
    private Integer value;
    private Boolean isDelete;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
    public Integer getDonViKM() {
        return donViKM;
    }
    public void setDonViKM(Integer donViKM) {
        this.donViKM = donViKM;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public KhuyenMaiDTO(String id, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer donViKM,
            Integer value, Boolean isDelete) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.donViKM = donViKM;
        this.value = value;
        this.isDelete = isDelete;
    }

}