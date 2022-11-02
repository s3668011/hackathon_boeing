package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 */
public class LGA {
   // LGA 2016 Code
   private int code16;

   // LGA 2016 Name
   private String name16;
   private String type;
   private double area;
   private double longitude;
   private double latitude;
   private int totalPopulation2018;
   private int totalHomelessAtRiskPopulation2018;
   private String state;

   /**
    * Create an LGA and set the fields
    */
   public LGA(int code16, String name16) {
      this.code16 = code16;
      this.name16 = name16;

      if (code16 < 20000){
         state = "New South Wales";
      } else if (code16 < 30000) {
         state = "Victoria";
      } else if (code16 < 40000) {
         state = "Queensland";
      } else if (code16 < 50000) {
         state = "South Australia";
      } else if (code16 < 60000) {
         state = "Western Australia";
      } else if (code16 < 70000) {
         state = "Tasmania";
      } else if (code16 < 80000) {
         state = "Northern Territory";
      } else if (code16 < 90000) {
         state = "Australian Capital Territory";
      } else {
         state = "Other territory";
      }
            
   }

   public String getState() {
      return state;
   }

   public void setTotalPopulation(int totalPop) {
      totalPopulation2018 = totalPop;
   }

   public void setTotalHomelessAtRiskPop(int totalHomeAtRisk) {
      totalHomelessAtRiskPopulation2018 = totalHomeAtRisk;
   }

   
   public void setType(String type) {
      this.type = type;
   }

   public void setArea(double area) {
      this.area = area;
   }

   public void setLong(double longitude){
      this.longitude = longitude;
   }

   public void setLat (double latitude) {
      this.latitude = latitude;
   }

   public int getCode16() {
      return code16;
   }

   public String getName16() {
      return name16;
   }

   public String getType() {
      return type;
   }

   public double getArea() {
      return area;
   }

   public double getLongitude() {
      return longitude;
   }

   public double getLatitude() {
      return latitude;
   }

   public int getTotalPopulation() {
      return totalPopulation2018;
   }

   public int getTotalHomelessAtRiskPopulation() {
      return totalHomelessAtRiskPopulation2018;
   }


}
