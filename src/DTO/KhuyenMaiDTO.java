package DTO;

import java.util.Date;
import java.util.Objects;

public class KhuyenMaiDTO {
    private String id;
    private String name;
    private int discountPercent;
    private int minOrder;
    private Date startDate;
    private Date endDate;
    private String status;
    
    public KhuyenMaiDTO(){
        
    }

    public KhuyenMaiDTO(String id, String name, int discountPercent, int minOrder,
            Date startDate, Date endDate, String status) {
        this.id = id;
        this.name = name;
        this.discountPercent = discountPercent;
        this.minOrder = minOrder;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public KhuyenMaiDTO(String id, String name, int discountPercent, int minOrder, String status) {
        
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

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(int minOrder) {
        this.minOrder = minOrder;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenMaiDTO that = (KhuyenMaiDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}