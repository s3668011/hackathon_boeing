package app;

public class VulnerablePop {
    private String status;
    private String sex;
    private String ageRange;
    private int totalNumber;
    private double percentage;
    
    VulnerablePop(String status, String sex, String ageRange, int total) {
        this.status = status;
        this.sex = sex;
        this.ageRange = ageRange.substring(1);
        totalNumber = total;
    }

    public void setPercentage(int totalPop) {
        percentage = totalPop > 0? ((double) totalNumber / totalPop) * 100 : 0.0;
    }

    public String getStatus() {
        return status;
    }

    public String getSex() {
        return sex;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public int getTotal() {
        return totalNumber;
    }

    public String getPercentageInString() {
        return String.format("%.2f", percentage);
    }
}
