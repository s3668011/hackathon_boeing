package app;

public class FilterSelect {
    String sex;
    String ageGroup;
    String region;
    String sort;
    String order;

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setAge(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String agePrint() {
        String print = "All";
        if (!(ageGroup == null || ageGroup.equals(""))) {
            String[] splitAge = ageGroup.split("_");
            print = splitAge[1] + " - " + splitAge[2];
        }
        return print;
    }

    public String getSex() {
        return sex;
    }

    public String getRegion() {
        return region;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }
}
