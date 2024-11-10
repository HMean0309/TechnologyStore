package DTO;

public class ThongKeDTO {
    private String key;
    private int value;
    private int count;

    public ThongKeDTO(String key, int value, int count) {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
