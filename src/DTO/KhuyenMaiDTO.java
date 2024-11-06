package DTO;

import java.time.LocalDateTime;

public class KhuyenMaiDTO {
    private String id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean donViKM;
    private long value;
    private boolean isDelete;
    private String des;

    public KhuyenMaiDTO(String id, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean donViKM, int value, boolean isDelete, String des) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.donViKM = donViKM;
        this.value = value;
        this.isDelete = isDelete;
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartLocalDateTimetime() {
        return startDateTime;
    }

    public void setStartLocalDateTimetime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndLocalDateTimetime() {
        return endDateTime;
    }

    public void setEndLocalDateTimetime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isDonviKM() {
        return donViKM;
    }

    public void setDonviKM(boolean donViKM) {
        this.donViKM = donViKM;
    }

    public long getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
