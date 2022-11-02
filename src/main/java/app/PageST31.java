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
public class PageST31 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page5.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Advanced Filter</title>";

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

        html = html + """
                <div class='content'>
                    <div class='filters'>
                        <div class='sFilter'>
                            <h2>Filter</h2>
                                <form action='/page5.html' method='post'>
                                    <label for='drop_status'>Select Status</label>
                                    <select id='drop_status' name='drop_status'>
                                        <option value='homeless'>Homeless</option>
                                        <option value='at_risk'>At Risk</option>
                                    </select><br>
                                    <label for='drop_sex'>Select Sex</label>
                                    <select id='drop_sex' name='drop_sex'>
                                        <option value='m'>Male</option>
                                        <option value='f'>Female</option>
                                    </select><br>
                                    <label for='drop_aMin'>Select Age Min</label>
                                    <select id='drop_aMin' name='drop_aMin'>
                                        <option>0</option>
                                        <option>10</option>
                                        <option>20</option>
                                        <option>30</option>
                                        <option>40</option>
                                        <option>50</option>
                                        <option>60</option>
                                    </select>
                                    <label for='drop_aMax'>Select Age Max</label>
                                    <select id='drop_aMax' name='drop_aMax'>
                                        <option>9</option>
                                        <option>19</option>
                                        <option>29</option>
                                        <option>39</option>
                                        <option>49</option>
                                        <option>59</option>
                                        <option>60+</option>
                                    </select><br>
                                    <label for='drop_region'>Select Region</label>
                                    <select id='drop_region' name='drop_region'>
                                        <option>Australia</option>
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
                                    <label for='drop_iMin'>Select Min Income ($)</label>
                                    <select id='drop_iMin' name='drop_iMin'>
                                        <option>0</option>
                                        <option selected>250</option>
                                        <option>500</option>
                                        <option>750</option>
                                        <option>1000</option>
                                        <option>1250</option>
                                        <option>1500</option>
                                        <option>1750</option>
                                        <option>2000</option>
                                        <option>2250</option>
                                        <option>2500</option>
                                        <option>2750</option>
                                        <option>3000</option>
                                        <option>3250</option>
                                    </select>
                                    <label for='drop_iMax'>Select Max Income ($)</label>
                                    <select id='drop_iMax' name='drop_iMax'>
                                        <option>0</option>
                                        <option>250</option>
                                        <option>500</option>
                                        <option>750</option>
                                        <option>1000</option>
                                        <option selected>1250</option>
                                        <option>1500</option>
                                        <option>1750</option>
                                        <option>2000</option>
                                        <option>2250</option>
                                        <option>2500</option>
                                        <option>2750</option>
                                        <option>3000</option>
                                        <option>3250</option>
                                    </select><br>
                                    <label for='drop_sort'>Sort By</label>
                                    <select id='drop_sort' name='drop_sort'>
                                        <option value='TotalHomeless DESC'>Homeless Descending</option>
                                        <option value='TotalHomeless ASC'>Homeless Ascending</option>
                                        <option value='LGA.lga_name DESC'>LGA Name Descending</option>
                                        <option value='LGA.lga_name ASC'>LGA Name Ascending</option>
                                        <option value='MEDINCOME DESC'>Income Descending</option>
                                        <option value='MEDINCOME ASC'>Income Ascending</option>
                                    </select><br>
                                    <button type='submit' class='submitButton'>Apply</button>
                                </form>
                            </div>
                            <a href='page6.html'><button class='nextFilter'>Click here for trends over time</button></a>
                        </div>
                        <div class='statHeader'>
                            <h2>Advance Filter</h2>
                        </div>
                """;
        
        int drop_iMin = -1;
        int drop_iMax = -1;

        String drop_status = context.formParam("drop_status");
        String drop_sex = context.formParam("drop_sex");
        String drop_aMin = context.formParam("drop_aMin");
        String drop_aMax = context.formParam("drop_aMax");
        String drop_region = context.formParam("drop_region");
        String siMin = context.formParam("drop_iMin");
        String siMax = context.formParam("drop_iMax");
        String drop_sort = context.formParam("drop_sort");
        if((siMin == null) && (siMax == null)){
            siMin = null;
            siMax = null;
        }
        else{
            drop_iMin = Integer.parseInt(siMin);
            drop_iMax = Integer.parseInt(siMax);
        }
        
        if((drop_status == null) && (drop_sex == null) && (drop_aMax == null) && (drop_aMin == null) && (drop_region == null) && (drop_sort == null) && (siMax == null) && (siMin == null)){
            System.out.println(drop_status + drop_sex + drop_aMin + drop_aMax + drop_region + drop_sort + siMax + siMin);
            html = html + """
                    <div class='statTable'>
                        <table>
                            <tr>
                                <th>No results. Please select filters</th>
                            </tr>
                        </table>
                    </div>
                    """;
        }
        else{
            System.out.println(drop_status + drop_sex + drop_aMin + drop_aMax + drop_region + drop_sort + siMax + siMin);
            html = html + """
                <div class='statTable'>
                <table>
                    <tr>
                        <th>LGA Name</th>
                        <th>Median Age</th>
                        <th>Median Income ($ Weekly)</th>
                        <th>Median Rent ($ Weekly)</th>
                        <th>Total (Homeless in LGA)</th>
                    """;
            if(drop_sex.equals("f")){
                html = html + "<th>Female aged " + drop_aMin + "-" + drop_aMax;
            }
            else{
                html = html + "<th>Male aged " + drop_aMin + "-" + drop_aMax;
            }
            html = html + "</tr>";

            JDBCConnection JDBC = new JDBCConnection();
            ArrayList<advFilterJa> stat = new ArrayList<advFilterJa>();
            stat = JDBC.advFilters(drop_status, drop_sex, drop_aMin, drop_aMax, drop_region, drop_iMin, drop_iMax, drop_sort);

            for(advFilterJa i : stat){
                String getTotPerc = String.format("%.2f", i.getTotalPerc());
                String getQuePerc = String.format("%.2f", i.getQueryPerc());
                String getMedIncome = String.format("%,d", i.getMedIncome());
                String getMedRent = String.format("%,d", i.getMedRent());
                html = html + "<tr>";
                html = html + "<td>" + i.getLgaName() + "</td>";
                html = html + "<td>" + i.getMedAge() + "</td>";
                html = html + "<td>$" + getMedIncome + "</td>";
                html = html + "<td>$" + getMedRent + "</td>";
                html = html + "<td>" + getTotPerc + "%</td>";
                html = html + "<td>" + getQuePerc + "%</td>";
                html = html + "</tr>";
            }
            
            html = html + "</table>";
            html = html + "</div>";

        }

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

}
