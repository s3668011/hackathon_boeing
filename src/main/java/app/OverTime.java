package app;

public class OverTime {
   private int lgaCode;
   private String lgaName;
   private int homeless2016;
   private int homeless2018;
   private int atRisk2016;
   private int atRisk2018;
   private int pop2016;
   private int pop2018;
   private double homelessDifference;
   private double homelessPercDiff;
   private double atRiskDifference;
   private double atRiskPercDiff;
   private int popDifference;
   private double popPerc;
   private double ratioHomelessAtRisk;

     OverTime (int lgaCode, String lgaName, int home2016, int home2018, int atR2016, int atR2018, int pop2016, int pop2018) {
        this.lgaCode = lgaCode;
        this.lgaName = lgaName;
        
        homeless2016 = home2016;
        homeless2018 = home2018;
        atRisk2016 = atR2016;
        atRisk2018 = atR2018;
        this.pop2016 = pop2016;
        this.pop2018 = pop2018;

        homelessDifference = homeless2018 - homeless2016;
        atRiskDifference = atRisk2018 - atRisk2016;
        popDifference = pop2018 - pop2016;

        homelessPercDiff = (homelessDifference / homeless2016) * 100;
        atRiskPercDiff = (atRiskDifference / atRisk2016) * 100;
        if (!(pop2016 == 0)) {
         popPerc = (popDifference / pop2016) * 100;
        }
        

        ratioHomelessAtRisk = homelessDifference / atRiskDifference;
     }

     public int getLgaCode() {
      return lgaCode;
     }

     public String getLgaName() {
      return lgaName;
     }

     public double getHomelessDifference() {
        return homelessDifference;
     }

     public double getAtRiskDifference() {
        return atRiskDifference;
     }

     public double getHomelessPerc() {
        return homelessPercDiff;
     }

     public double getAtRiskPerc() {
        return atRiskPercDiff;
     }

      public double getPopulationDifference() {
        return popDifference;
      }

      public double getPopulationPerc() {
        return popPerc;
      }

      public double getRatio() {
        return ratioHomelessAtRisk;
      }
}
