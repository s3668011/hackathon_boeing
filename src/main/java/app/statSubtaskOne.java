package app;

public class statSubtaskOne {
    private String lgaName;
    private String status;
    private String age;
    private String sex;
    private int raw;
    private String percTotal;

    public statSubtaskOne (String lgaName, String status, String sex, String age, int count){
        this.lgaName = lgaName;
        this.status = status;
        this.sex = sex;
        this.age = age;
        this.raw = count;
    }

    public String getName(){
        return lgaName;
    }

    public String getAge(){
        return age;
    }

    public String getSex(){
        return sex;
    }

    public int getRaw(){
        return raw;
    }

    public String getStatus(){
        return status;
    }

    public String getTotal(){
        return percTotal;
    }

    public void setTotal(String total){
        this.percTotal = total;
    }

}
