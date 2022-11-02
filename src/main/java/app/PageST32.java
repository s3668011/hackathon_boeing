package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST32 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page6.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Trends Over Time</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a class=\"active\" href='/'>Home</a>
                <a href='page3.html'>Statistics</a>
                <a href='page4.html'>Statistics by LGA</a>
                <a href='page5.html'>Socioeconomic Filters</a>
                <a href='page6.html'>Trends Over Time</a>
                <a style=\"float:right\" class=\"customAbout\" href='mission.html'>About</a>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";
        JDBCConnection jdbc = new JDBCConnection();

        String searchedLGA = context.formParam("LGAs");
        System.out.println("input" + searchedLGA);

        FilterSelect currentFilters = new FilterSelect();

        html = html + "<div class='filters'>";

        // LGA foundLGA = null;
        

        
        html = html + "<div class='sFilter'>";
        html = html + "<h2>Filters</h2>";
        html = html + """
            <form action='/page6.html' method='post'>
                <label for='region_drop'>Select Region</label>
                    <select id='region_drop' name='region_drop'>
                        <option selected>Australia</option>
                        <option>Australian Capital Territory</option>
                        <option>New South Wales</option>
                        <option>Northern Territory</option>
                        <option>Queensland</option>
                        <option>South Australia</option>
                        <option>Tasmania</option>
                        <option>Victoria</option>
                        <option>Western Australia</option>
                        <option>Other</option>
                        
                    </select><br>
                <label for='sex_drop'>Select Sex</label>
                    <select id='sex_drop' name='sex_drop'>
                        <option selected>All</option>
                        <option value='M'>Male</option>
                        <option value='F'>Female</option>
                    </select>
                <br>
                <label for='age_drop'>Select Age</label>
                    <select id='age_drop' name='age_drop'>
                        <option value='' selected>All ages</option>
                        <option value='_0_9'>0 - 9</option>
                        <option value='_10_19'>10 - 19</option>
                        <option value='_20_29'>20 - 29</option>
                        <option value='_30_39'>30 - 39</option>
                        <option value='_40_49'>40 - 49</option>
                        <option value='_50_59'>50 - 59</option>
                        <option value='_60_plus'>60+</option>
                    </select>
                <br/>
                <label for='sort_drop'>Sort column</label>
                    <select id='sort_drop' name='sort_drop'>
                        <option value='lga_code' selected>lga code</option>
                        <option value='homelessPerc'>Homeless percentage</option>
                        <option value='homelessDifference'>Homeless difference</option>
                        <option value='atRiskPerc'>At risk percentage</option>
                        <option value='atRiskDifference'>At risk difference</option>
                        <option value='popPerc'>Population percentage</option>
                        <option value='popDifference'>Population difference</option>
                    </select>
                <br/>
                <label for='order_drop'>Sort order</label>
                    <select id='order_drop' name='order_drop'>
                        <option >Ascending</option>
                        <option value='DESC'>Descending</option>
                    </select>
                <br>
                    <button type='submit' class='submitButton'>Apply</button>
                </form>
                """;
        html = html + "</div>";

        String region = context.formParam("region_drop");
        System.out.println(region);
        currentFilters.setRegion(region);
        String ageGroup  = context.formParam("age_drop");
        System.out.println(ageGroup);
        currentFilters.setAge(ageGroup);
        String sex = context.formParam("sex_drop");
        currentFilters.setSex(sex);
        String sort = context.formParam("sort_drop");
        currentFilters.setSort(sort);
        System.out.println("sort is: " + sort);
        String order = context.formParam("order_drop");
        currentFilters.setOrder(order);
        System.out.println(order);


        html = html + "<div id=\"lgaInfo\">";
        
        // to do style the LGA information
        // why isn't this working


        
        html = html + "<h3>Your chosen filters</h3>";
        html = html + "<h3>Region: " + currentFilters.getRegion() + "</h3>";
        html = html + "<h3>Age group: " + currentFilters.agePrint() + "</h3>";
        html = html + "<h3>Sex: " + currentFilters.getSex() + "</h3>";
        html = html + "<h3>Sort: " + currentFilters.getSort() + "</h3>";
        html = html + "</div>";

        html = html + "</div>";
        html = html + "<div class='statHeader'>";
        html = html + "<h2>Sorted Table by Ranking</h2>";
        html = html + "</div>";

        
            html = html + """
                <div class='statTable'>
                    <table>
                        <tr>
                            <th colspan='2'>LGA info</th>
                            <th colspan='2'>Homeless data</th>
                            <th colspan='2'>At Risk data</th>
                            <th colspan='2'>Total Population data</th>
                            <th>Ratio</th>
                        </tr>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Difference</th>
                            <th>Percentage Difference</th>
                            <th>Difference</th>
                            <th>Percentage Difference</th>
                            <th>Difference</th>
                            <th>Percentage Difference</th>
                            <th>Homeless:At Risk</th>
                            </tr>    
                        """;

                        JDBCConnection JDBC = new JDBCConnection();


                        ArrayList<OverTime> changeState = JDBC.getStatsOverTime(currentFilters);

                        for(OverTime i : changeState){
                            html = html + "<tr>";
                            html = html + "<td>" + i.getLgaCode() + "</td>";
                            html = html + "<td>" + i.getLgaName() + "</td>";
                            html = html + "<td>" + i.getHomelessDifference() + "</td>";
                            html = html + "<td>" + String.format("%.2f", i.getHomelessPerc()) + "%</td>";
                            html = html + "<td>" + i.getAtRiskDifference() + "</td>"; 
                            html = html + "<td>" + String.format("%.2f", i.getAtRiskPerc()) + "%</td>"; 
                            html = html + "<td>" + i.getPopulationDifference() + "</td>"; 
                            html = html + "<td>" + String.format("%.2f", i.getPopulationPerc()) + "%</td>"; 
                            html = html + "<td>" + String.format("%.2f", i.getRatio()) + "</td>";               

                        }
            html = html + "</table>";
            html = html + "</div>";
        


        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <img class='logo' src='RMITLOGO.png' alt='RMIT logo'></img>
                <p>COSC2803 - Studio Project</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }


    /* Create a method to display list of LGAs in dropdown */
    public String listLGAs() {
        String html = "";

        JDBCConnection jdbc = new JDBCConnection();

        ArrayList<LGA> LGAs = jdbc.getLGAs();

        for (LGA currentLGA: LGAs){
            html = html + "<option value=\"";
            html = html + currentLGA.getCode16() + "-";
            html = html + currentLGA.getName16();
            html = html + "\">";
        }

        return html;
    }

}
