package DTO;

public class OptionSanPhamDTO{
    public String idSP;
    public String color;
    public String isDelete;

    public OptionSanPhamDTO(String idSp, String color, String isDelete)
    {
        this.idSP = idSp;
        this.color = color;
        this.isDelete = isDelete;
    }

    public String getIdSP(){
        return idSP;
    }

    public void setIdSP(String idSp)
    {
       this.idSP = idSp; 
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
}