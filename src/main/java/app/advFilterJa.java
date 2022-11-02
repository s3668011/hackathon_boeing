package app;

public class advFilterJa {
    private String lgaName;
    private int medAge;
    private int medIncome;
    private int medRent;
    private double totalPerc;
    private double queryPerc;
    private int population;
    private String status;
    private String sex;

    public advFilterJa(String lgaName, int medAge, int medIncome, int medRent, double totalPerc, double queryPerc, int population, String status, String sex){
        this.lgaName = lgaName;
        this.medAge = medAge;
        this.medIncome = medIncome;
        this.medRent = medRent;
        this.totalPerc = totalPerc;
        this.queryPerc = queryPerc;
        this.population = population;
        this.status = status;
        this.sex = sex;
    }

    public String getLgaName(){
        return lgaName;
    }

    public int getMedAge(){
        return medAge;
    }

    public int getMedIncome(){
        return medIncome;
    }

    public int getMedRent(){
        return medRent;
    }

    public double getTotalPerc(){
        return totalPerc;
    }

    public double getQueryPerc(){
        return queryPerc;
    }

    public int getPopulation(){
        return population;
    }

    public String getStatus(){
        return status;
    }

    public String getSex(){
        return sex;
    }
    
}
