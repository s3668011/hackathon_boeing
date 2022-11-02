package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Stand-alone Java file for processing the database CSV files.
 * <p>
 * You can run this file using the "Run" or "Debug" options
 * from within VSCode. This won't conflict with the web server.
 * <p>
 * This program opens a CSV file from the Closing-the-Gap data set
 * and uses JDBC to load up data into the database.
 * <p>
 * To use this program you will need to change:
 * 1. The input file location
 * 2. The output file location
 * <p>
 * This assumes that the CSV files are the the **database** folder.
 * <p>
 * WARNING: This code may take quite a while to run as there will be a lot
 * of SQL insert statments!
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au

 */
public class LGAs2016ProcessCSV {

   // MODIFY these to load/store to/from the correct locations
   
   private static final String DATABASE = "jdbc:sqlite:database/homelessness.db";
   private static final String CSV_FILE = "database/lgas16.csv";


   public static void main (String[] args) {

      // JDBC Database Object
      Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // lga_name
            String lgaName = rowScanner.next();

            // Read type
            String lgaTypeString;
            
            char lgaType; 

            double area;

            double latitude;

            double longitude;

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            // statement.setQueryTimeout(30);

            // Create Insert Statement
            String query = "";
            lgaTypeString = rowScanner.next();

            if (!lgaTypeString.equals("")){
               lgaType = lgaTypeString.charAt(0);
               area = rowScanner.nextDouble();
               latitude = rowScanner.nextDouble();
               longitude = rowScanner.nextDouble();
               query = "INSERT into LGA VALUES ("
                           + lgaCode + ","
                           + "'" + lgaName + "',"
                           + "'" + lgaType + "',"
                           + "" + area + ","
                           + "" + latitude + ","
                           + "" + longitude + ")";
            }
            else {
               query = "INSERT into LGA VALUES ("
                           + lgaCode + ","
                           + "'" + lgaName + "',"
                           + "NULL,"
                           + "0,"
                           + "NULL,"
                           + "NULL)";

            }
            
            // Execute the INSERT
            System.out.println("Executing: " + query);
            statement.execute(query);

            row++;
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      
   }
}
