package DTO;

public class ThongKeDTO {
    private String key;
    private Integer value;
    private Integer count;

    public ThongKeDTO(String key, Integer value, Integer count) {
        this.key = key;
        this.value = value;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
     @Override
    public String toString() {
        return "ThongKeDTO{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", count=" + count +
                '}';
    }
}
