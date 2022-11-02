package app;

import java.util.ArrayList;
import java.util.concurrent.atomic.DoubleAdder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/ctg.db";
    private static final String MAIN_DATABASE = "jdbc:sqlite:database/homelessness.db";

    // If you are using the Homelessness data set replace this with the below
    //private static final String DATABASE = "jdbc:sqlite:database/homelessness.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<LGA> getLGAs() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int code16     = results.getInt("lga_code16");
                String name16  = results.getString("lga_name16");

                // Create a LGA Object
                LGA lga = new LGA(code16, name16);

                // Add the lga object to the array
                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public int TotalChildren() {
        // Create the ArrayList of LGA objects to return
        int returnVal = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                SELECT SUM(count) AS TotalChildren FROM HomelessGroup
                WHERE age_group='_0_9';
                    """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int ans = results.getInt("TotalChildren");
                returnVal = ans;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return returnVal;
    }

    public int TotalWoman() {
        // Create the ArrayList of LGA objects to return
        int returnVal = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                SELECT SUM(count) AS TotalWoman FROM HomelessGroup
WHERE sex='f';
                    """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int ans = results.getInt("TotalWoman");
                returnVal = ans;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return returnVal;
    }

    public int TotalElderly() {
        // Create the ArrayList of LGA objects to return
        int returnVal = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                SELECT SUM(count) AS TotalElderly FROM HomelessGroup
Where age_group='_60_plus';
                    """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int ans = results.getInt("TotalElderly");
                returnVal = ans;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return returnVal;
    }

    public int TotalPopulation() {
        // Create the ArrayList of LGA objects to return
        int returnVal = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                SELECT SUM(count) AS TotalPopulation FROM HomelessGroup;
                    """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int ans = results.getInt("TotalPopulation");
                returnVal = ans;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return returnVal;
    }

    public int TotalLga() {
        // Create the ArrayList of LGA objects to return
        int returnVal = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                SELECT SUM(count) AS total FROM HomelessGroup
                INNER JOIN LGA ON HomelessGroup.lga_code=LGA.lga_code
                WHERE lga_name='Melbourne';
                    """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int ans = results.getInt("total");
                returnVal = ans;
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return returnVal;
    }

    // TODO: Add your required methods here
    public void addPersonas(){
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                INSERT INTO PERSONA (name, Attribute, Description)
                VALUES ('Jeff','- Wants to have some understanding about homelessness, - Knows how to browse the internet on computer and phone', 
                '- A father of two, - Pharmacist, - Likes to surf the internet about modern challenges'),
                ('Stanley', '- To learn more about the factors surrounding homelessness in Australia, - To understand how homelessness impacts different demographics, 
                - Be able to interpret data in a simple way, - To complete his research project using real life data on homelessness', 
                '- Knows his way around a website, - Third year student in uni');
                    """;
            statement.execute(query);
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public void addStudents(){
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                INSERT INTO STUDENT (studentID, Name)
                VALUES ('s3941378@student.rmit.edu.au', 'Krisanahari Siva'), ('s3940067@student.rmit.edu.au', 'Victoria Pham');
                    """;
            statement.execute(query);
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public LGA getLGAByCodeOrName(String searchItem){
        Connection connection = null;
        LGA currentLGA;

        String[] searchTerms = searchItem.split("-");
        System.out.println(searchTerms[0]);

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA WHERE lga_code=" + searchTerms[0] 
                + " OR lga_name=\"" + searchTerms[1] + "\"";
            
            ResultSet result = statement.executeQuery(query);

            int lgaCode = result.getInt("lga_code");
            String lgaName = result.getString("lga_name");
            // use wasNull to check if last column read is null
            String lgaType = result.getString("lga_type");
            double area = result.getDouble("area_sqkm");
            double latitude = result.getDouble("latitude");
            double longitude = result.getDouble("longitude");

            
            if (result.wasNull()){
                currentLGA = new LGA(lgaCode, lgaName);
                lgaType="N\\A";
                area = 0;
                currentLGA.setType(lgaType);
                currentLGA.setArea(0);
            } else {
                currentLGA = new LGA(lgaCode, lgaName);
                currentLGA.setType(lgaType);
                currentLGA.setArea(area);
                currentLGA.setLat(latitude);
                currentLGA.setLong(longitude);
            }
            return currentLGA;
            
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public int getPopulationByLGA2018(int lgaCode){
        Connection connection = null;

        System.out.println("LGA code: " + lgaCode);

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM POPULATION WHERE lga_code=" + lgaCode + " AND year=2018;";
            
            ResultSet result = statement.executeQuery(query);

            int year = result.getInt("year");
            int population = result.getInt("population");

            return population;
            
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return 0;
        
    }

    public int getTotalHomeRiskPopByLGA2018(int lgaCode){
        Connection connection = null;

        System.out.println("LGA code: " + lgaCode);

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT SUM(count) AS total FROM HomelessGroup WHERE lga_code=" + lgaCode + " AND year=2018;";
            
            ResultSet result = statement.executeQuery(query);

            int population = result.getInt("total");

            return population;
            
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return 0;
        
    }

    public ArrayList<statSubtaskOne> statFilters(boolean custom, String status, String sex, String age) {
        // Create the ArrayList of LGA objects to return
        ArrayList<statSubtaskOne> stats = new ArrayList<statSubtaskOne>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "";

            if(custom==true){
                query = query.format("SELECT lga_name, status, sex, age_group, count, SUM(count) AS total FROM HomelessGroup INNER JOIN LGA ON HomelessGroup.lga_code=LGA.lga_code WHERE status='%s' AND sex='%s' AND age_group='%s' AND count > 0 GROUP BY lga_name ORDER BY count DESC;", status, sex, age);
            }
            else{
                query = """
                    SELECT lga_name, status, sex, age_group, count, SUM(count) AS total FROM HomelessGroup
                    INNER JOIN LGA ON HomelessGroup.lga_code=LGA.lga_code
                    WHERE count > 200
                    GROUP BY lga_name
                    ORDER BY count DESC;
                        """;
            }

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String addName = results.getString("lga_name");
                String addStatus = results.getString("status");
                String addSex = results.getString("sex");
                String addAge = results.getString("age_group");
                int addRaw = results.getInt("count");
                int addTotal = results.getInt("total");
                double perc = (addRaw*100.0/addTotal);
                String sPerc = "";
                sPerc = sPerc.format("%.2f", perc);

                // Create a LGA Object
                statSubtaskOne st = new statSubtaskOne(addName, addStatus, addSex, addAge, addRaw);
                st.setTotal(sPerc);
                stats.add(st);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return stats;
    }

    public ArrayList<VulnerablePop> getStatsByLGA(LGA lga) {
        // Create the ArrayList of LGA objects to return
        ArrayList<VulnerablePop> stats = new ArrayList<VulnerablePop>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "";

                query = """
                    SELECT * 
                    FROM HomelessGroup
                    WHERE lga_code=""" + lga.getCode16();
                query = query + " AND year=2018;";
            

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String addStatus = results.getString("status");
                String addSex = results.getString("sex");
                String addAge = results.getString("age_group");
                int addRaw = results.getInt("count");

                //statSubtaskOne st = new statSubtaskOne(addStatus, addSex, addAge, addRaw);
                VulnerablePop vp = new VulnerablePop(addStatus, addSex, addAge, addRaw);
                vp.setPercentage(lga.getTotalHomelessAtRiskPopulation());
                stats.add(vp);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return stats;
    }

    public ArrayList<VulnerablePop> getStatsByState(String state) {
        // Create the ArrayList of LGA objects to return
        ArrayList<VulnerablePop> stats = new ArrayList<VulnerablePop>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            String queryState = "";

            switch(state) {
                case("New South Wales"):
                    System.out.println("State is NSW");
                    queryState = queryState + " lga_code < 20000";
                    break;
                case("Victoria"):
                    System.out.println("State is Victoria");
                    queryState = queryState + " lga_code >= 20000 AND lga_code < 30000";
                    break;
                case("Queensland"):
                    System.out.println("State is QLD");
                    queryState = queryState + " lga_code >= 30000 AND lga_code < 40000";
                    break;
                case("South Australia"):
                    System.out.println("State is SA");
                    queryState = queryState + " lga_code >= 40000 AND lga_code < 50000";
                    break;
                case("Western Australia"):
                    System.out.println("State is WA");
                    queryState = queryState + " lga_code >= 50000 AND lga_code < 60000";
                    break;
                case("Tasmania"):
                    System.out.println("State is Tas");
                    queryState = queryState + " lga_code >= 60000 AND lga_code < 70000";
                    break;
                case("Northern Territory"):
                    System.out.println("State is NT");
                    queryState = queryState + " lga_code >= 70000 AND lga_code < 80000";
                    break;
                case("Australian Capital Territory"):
                    System.out.println("State is ACT");
                    queryState = queryState + " lga_code >= 80000 AND lga_code < 90000";
                    break;
                default:
                    System.out.println("State is other");
                    queryState = queryState + " lga_code >= 90000";
                    break;
                
            }

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);
            String query2 = "";

                query2 = query2 + """
                    SELECT  SUM(count) as totalInState
                    FROM HomelessGroup
                    WHERE """ + queryState;
                query2 = query2 + " AND year=2018;";
                System.out.println(query2);
            

            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            int totalPop = results2.getInt("totalInState");


            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "";

                query = """
                    SELECT year, status, sex, age_group, SUM(count) as sum1
                    FROM HomelessGroup
                    WHERE """ + queryState;
                query = query + " AND year=2018 GROUP BY 1, 2, 3, 4;";
            

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String addStatus = results.getString("status");
                String addSex = results.getString("sex");
                String addAge = results.getString("age_group");
                int addRaw = results.getInt("sum1");

                //statSubtaskOne st = new statSubtaskOne(addStatus, addSex, addAge, addRaw);
                VulnerablePop vp = new VulnerablePop(addStatus, addSex, addAge, addRaw);
                vp.setPercentage(totalPop);
                stats.add(vp);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the data for the state
        return stats;
    }

    public ArrayList<VulnerablePop> getStatsByAus() {
        // Create the ArrayList of LGA objects to return
        ArrayList<VulnerablePop> stats = new ArrayList<VulnerablePop>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);
            String query2 = "";

                query2 = query2 + """
                    SELECT  SUM(count) as totalInAus
                    FROM HomelessGroup
                    WHERE year=2018;""" ;
                System.out.println(query2);
            

            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            int totalPop = results2.getInt("totalInAus");


            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "";

                query = """
                    SELECT year, status, sex, age_group, SUM(count) as sum1
                    FROM HomelessGroup
                    WHERE year=2018 GROUP BY 1, 2, 3, 4;""" ;
            

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String addStatus = results.getString("status");
                String addSex = results.getString("sex");
                String addAge = results.getString("age_group");
                int addRaw = results.getInt("sum1");

                //statSubtaskOne st = new statSubtaskOne(addStatus, addSex, addAge, addRaw);
                VulnerablePop vp = new VulnerablePop(addStatus, addSex, addAge, addRaw);
                vp.setPercentage(totalPop);
                stats.add(vp);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the data for the state
        return stats;
    }

    public ArrayList<advFilterJa> advFilters(String status, String sex, String aMin, String aMax, String region, int iMin, int iMax, String sort) {
        // Create the ArrayList of LGA objects to return
        ArrayList<advFilterJa> stats = new ArrayList<advFilterJa>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String incQuery = "";
            String getRegion = regionHelper(region);
            String ageGroup = ageHelper(aMin, aMax);

            incQuery = incQuery.format("SELECT LGA.lga_code, lga_name, MEDAGE, MEDINCOME, MEDRENT, status, sex, age_group, population, SUM(population) AS TotalPop, SUM(count) AS TotalHomeless, count FROM LGA INNER JOIN INCOME2018 ON LGA.lga_code=INCOME2018.lga_code INNER JOIN HomelessGroup ON LGA.lga_code=HomelessGroup.lga_code INNER JOIN POPULATION ON LGA.lga_code=POPULATION.lga_code WHERE count > 0 AND MEDINCOME > %d AND MEDINCOME < %d AND status='%s' AND sex='%s' AND %s AND %s GROUP BY lga_name ORDER BY %s;", iMin, iMax, status, sex, ageGroup, getRegion, sort);

            // Get Result
            ResultSet results = statement.executeQuery(incQuery);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String addName = results.getString("lga_name");
                String addStatus = results.getString("status");
                String addSex = results.getString("sex");
                int addMedAge = results.getInt("MEDAGE");
                int addMedIncome = results.getInt("MEDINCOME");
                int addMedRent = results.getInt("MEDRENT");
                int addPop = results.getInt("population");
                int addTotalPop = results.getInt("TotalPop");
                int addRaw = results.getInt("count");
                int addTotal = results.getInt("TotalHomeless");
                double totalPerc = (addTotal*100.0/addPop);
                double queryPerc = (addRaw*100.0/addTotal);

                // Create a LGA Object
                advFilterJa st = new advFilterJa(addName, addMedAge, addMedIncome, addMedRent, totalPerc, queryPerc, addTotalPop, addStatus, addSex);
                stats.add(st);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return stats;
    }

    private String ageHelper(String aMin, String aMax){
        String retString = "(";

        switch(aMin) {
            case("0"):
                System.out.println("0");
                retString = retString + " age_group='_0_9'";
                if(aMax.equals("9")){
                    break;
                }
                else{
                    retString = retString + " OR";
                }
            case("10"):
                System.out.println("10");
                retString = retString + " age_group='_10_19'";
                if(aMax.equals("19")){
                    break;
                }
                else{
                    retString = retString + " OR";
                }
            case("20"):
                System.out.println("20");
                retString = retString + " age_group='_20_29'";
                if(aMax.equals("29")){
                    break;
                }
                else{
                    retString = retString + " OR";
                }
            case("30"):
                System.out.println("30");
                retString = retString + " age_group='_30_39'";
                if(aMax.equals("39")){
                    break;
                }
                else{
                    retString = retString + " OR";
                }
            case("40"):
                retString = retString + " age_group='_40_49'";
                if(aMax.equals("49")){
                    break;
                }
                else{
                    retString = retString + " OR";
                }
            case("50"):
                retString = retString + " age_group='_50_59'";
                if(aMax.equals("59")){
                    break;
                }
                else{
                    retString = retString + " OR";
                }
            case("60"):
                retString = retString + " age_group='_60_plus'";
                if(aMax.equals("60+")){
                    break;
                }
            default:
                retString = retString + null;
                break;
            
        }
        retString = retString + ")";
        return retString;

    }

    private String regionHelper(String region){
        String queryState = "";

            switch(region) {
                case("Australia"):
                    System.out.println("Whole country");
                    queryState = queryState + "LGA.lga_code >= 10000";
                    break;
                case("New South Wales"):
                    System.out.println("State is NSW");
                    queryState = queryState + " LGA.lga_code < 20000";
                    break;
                case("Victoria"):
                    System.out.println("State is Victoria");
                    queryState = queryState + " LGA.lga_code >= 20000 AND LGA.lga_code < 30000";
                    break;
                case("Queensland"):
                    System.out.println("State is QLD");
                    queryState = queryState + " LGA.lga_code >= 30000 AND LGA.lga_code < 40000";
                    break;
                case("South Australia"):
                    System.out.println("State is SA");
                    queryState = queryState + " LGA.lga_code >= 40000 AND LGA.lga_code < 50000";
                    break;
                case("Western Australia"):
                    System.out.println("State is WA");
                    queryState = queryState + " LGA.lga_code >= 50000 AND LGA.lga_code < 60000";
                    break;
                case("Tasmania"):
                    System.out.println("State is Tas");
                    queryState = queryState + " LGA.lga_code >= 60000 AND LGA.lga_code < 70000";
                    break;
                case("Northern Territory"):
                    System.out.println("State is NT");
                    queryState = queryState + " LGA.lga_code >= 70000 AND LGA.lga_code < 80000";
                    break;
                case("Australian Capital Territory"):
                    System.out.println("State is ACT");
                    queryState = queryState + " LGA.lga_code >= 80000 AND LGA.lga_code < 90000";
                    break;
                default:
                    System.out.println("State is other");
                    queryState = queryState + " LGA.lga_code >= 90000";
                    break;
                
            }

        return queryState;
    }


    // takes filter values as input, if no filters will return default Australia, order by lga code
    public ArrayList<OverTime> getStatsOverTime(FilterSelect filters) {
        // Create the ArrayList of LGA objects to return
        ArrayList<OverTime> stats = new ArrayList<OverTime>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        // check for sort, if there is sort need to add calculation in db and sort by that column (column 7)

        // order of query will be
        // lga code, lga name, homeless 2016, homeless 2018, at risk 2016, at risk 2018, population 2016, pop 2018
        // use this data to create OverTime object
        // the constructor will make the calculations and then add to an arraylist
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "";

                query = """
                    SELECT LGA.lga_code,
                        LGA.lga_name,
                        SUM(h1.count) AS homeless2016,
                        SUM(h2.count) AS homeless2018,
                        SUM(h3.count) AS atrisk2016,
                        SUM(h4.count) AS atrisk2018,
                        p1.population as pop2016, 
                        p2.population as pop2018 """ ;
                
                if (!(filters.getSort() == null ||filters.getSort().equals(""))) {
                    switch(filters.getSort()){
                        case "homelessPerc":
                            query = query + ", CAST ( (CAST (SUM(h2.count) - SUM(h1.count) AS FLOAT) / SUM(h1.count) ) * 100 AS FLOAT) AS homelessPerc ";
                            break;
                        case "homelessDifference":
                            query = query + ", SUM(h2.count) - SUM(h1.count) AS homelessDifference ";
                            break;
                        case "atRiskPerc":
                            query = query + ", CAST ( (CAST (SUM(h4.count) - SUM(h3.count) AS FLOAT) / SUM(h3.count) ) * 100 AS FLOAT) AS atRiskPerc ";
                            break;
                        case "atRiskDifference":
                            query = query + ", (p2.population - p1.population) AS popDifference ";
                            break;
                        case "popPerc":
                            query = query + ", CAST ( (CAST (p2.population - p1.population AS FLOAT) / p1.population) * 100 AS FLOAT) AS popPerc ";
                            break;
                        case "popDifference":
                            query = query + ", (p2.population - p1.population) AS popDifference ";
                            break;
                        default:
                            break;
                    }
                }
                query = query + " ";

                query = query + """
                    FROM HomelessGroup h1 JOIN HomelessGroup h2 
                        ON h1.lga_code = h2.lga_code 
                        AND h1.sex = h2.sex 
                        AND h1.age_group = h2.age_group 
                        AND h1.status = h2.status

                    JOIN HomelessGroup h3 
                        ON h1.lga_code = h3.lga_code
                        AND h1.sex = h3.sex
                        AND h1.age_group = h3.age_group
                        AND h1.year = h3.year

                    JOIN HomelessGroup h4 
                        ON h1.lga_code = h4.lga_code
                        AND h1.age_group = h4.age_group
                        AND h1.sex = h4.sex
                        AND h2.year = h4.year
                        AND h3.status = h4.status

                    JOIN LGA 
                        ON h1.lga_code = LGA.lga_code

                    LEFT JOIN POPULATION p1
                        ON h1.lga_code = p1.lga_code
                        AND h1.year = p1.year

                    LEFT JOIN POPULATION p2
                        ON h1.lga_code = p2.lga_code
                        AND h2.year = p2.year

                    WHERE h1.status = 'homeless'
                        AND h3.status = 'at_risk'
                        AND h1.year = 2016
                        AND h2.year = 2018
                        """;
            if (!(filters.getAgeGroup() == null || filters.getAgeGroup().equals(""))){
                query = query + " AND h1.age_group='" + filters.getAgeGroup() + "'";
            }

            if (!(filters.getSex() == null || filters.getSex().equals("All"))){
                query = query + " AND h1.sex='" + filters.getSex() +"'";
            }

            if (!(filters.getRegion() == null || filters.getRegion().equals(""))){
                switch (filters.getRegion()) {
                    case("New South Wales"):
                    query = query + " AND LGA.lga_code < 20000";
                    break;
                case("Victoria"):
                    System.out.println("State is Victoria");
                    query = query + " AND LGA.lga_code >= 20000 AND LGA.lga_code < 30000";
                    break;
                case("Queensland"):
                    System.out.println("State is QLD");
                    query = query + " AND LGA.lga_code >= 30000 AND LGA.lga_code < 40000";
                    break;
                case("South Australia"):
                    System.out.println("State is SA");
                    query = query + " AND LGA.lga_code >= 40000 AND LGA.lga_code < 50000";
                    break;
                case("Western Australia"):
                    System.out.println("State is WA");
                    query = query + " AND LGA.lga_code >= 50000 AND LGA.lga_code < 60000";
                    break;
                case("Tasmania"):
                    System.out.println("State is Tas");
                    query = query + " AND LGA.lga_code >= 60000 AND LGA.lga_code < 70000";
                    break;
                case("Northern Territory"):
                    System.out.println("State is NT");
                    query = query + " AND LGA.lga_code >= 70000 AND LGA.lga_code < 80000";
                    break;
                case("Australian Capital Territory"):
                    System.out.println("State is ACT");
                    query = query+ " AND LGA.lga_code >= 80000 AND LGA.lga_code < 90000";
                    break;
                case("Other"):
                    System.out.println("State is other");
                    query = query + " AND LGA.lga_code >= 90000";
                    break;
                }
            }

            query = query + " GROUP BY h1.lga_code, h1.status";

            if (!(filters.getSort() == null ||filters.getSort().equals("lga_code"))) {
                query = query + " ORDER BY 9";
            } else {
                query = query + " ORDER BY 1";
            }
            if (!(filters.getOrder() == null ||filters.getOrder().equals("Ascending")) ) {
                query = query + " DESC";
            }
            query = query + ";";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int lgaCode = results.getInt("lga_code");
                String lgaName = results.getString("lga_name");
                int home2016 = results.getInt("homeless2016");
                int home2018 = results.getInt("homeless2018");
                int atRisk2016 = results.getInt("atrisk2016");
                int atRisk2018 = results.getInt("atrisk2018");
                int pop2016 = results.getInt("pop2016");
                int pop2018 = results.getInt("pop2018");

                OverTime data = new OverTime(lgaCode, lgaName, home2016, home2018, atRisk2016, atRisk2018, pop2016, pop2018);
                stats.add(data);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the data for the state
        return stats;
    }

    public Persona[] getPersonas(){
        Connection connection = null;
        Persona[] personas = new Persona[2];

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM PERSONA";
            
            ResultSet result = statement.executeQuery(query);

            int i = 0;

            while (result.next()) {
                String name = result.getString("name");
                String goals = result.getString("Attribute");
                String attributes = result.getString("Description");
                Persona currentPersona = new Persona();
                currentPersona.setName(name);
                currentPersona.setAttributes(attributes);
                currentPersona.setGoals(goals);
                personas[i] = currentPersona;
                i++;
            }

            return personas;
            
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Student[] getStudents(){
        Connection connection = null;
        Student[] students = new Student[2];

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(MAIN_DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM STUDENT";
            
            ResultSet result = statement.executeQuery(query);

            int i = 0;

            while (result.next()) {
                String name = result.getString("name");
                String email = result.getString("StudentID");
                Student currentStudent = new Student(name, email);
                students[i] = currentStudent;
                i++;
            }

            return students;
            
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    

}
