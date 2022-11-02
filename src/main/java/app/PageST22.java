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
public class PageST22 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page4.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Advanced Statistics</title>";

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

        html = html + "<div class='filters'>";

        LGA foundLGA = null;
        html = html + "<div id=\"lgaInfo\">";
        
        // to do style the LGA information

        if (searchedLGA == null || searchedLGA.equals("")) {
            html = html + "<h2><i>No Results to show LGA</i></h2>";
        } else {
            foundLGA = jdbc.getLGAByCodeOrName(searchedLGA);
            int lgaPop2018 = jdbc.getPopulationByLGA2018(foundLGA.getCode16());
            foundLGA.setTotalPopulation(lgaPop2018);
            int lgaHomeAtRiskPop2018 = jdbc.getTotalHomeRiskPopByLGA2018(foundLGA.getCode16());
            foundLGA.setTotalHomelessAtRiskPop(lgaHomeAtRiskPop2018);
            System.out.println("LGA object" + foundLGA);
            System.out.println("LGA pop 2018: " + lgaPop2018);
            html = html + "<h3>LGA name: " + foundLGA.getName16() + "</h3>";
            html = html + "<h3>State: " + foundLGA.getState() + "</h3>";
            html = html + "<h3>Type: " + foundLGA.getType() + "</h3>";
            html = html + "<h3>Area: " + foundLGA.getArea() + " sqkm</h3>";
            html = html + "<h3>Population (2018):" + foundLGA.getTotalPopulation() + "</h3>";
        }
        html = html + "</div>";

        
        html = html + "<div class='sFilter'>";
        html = html + "<h2>Filters</h2>";
        html = html + """
            <form action='/page4.html' method='post'>
                <label for='LGAs'>Search for LGA</label>
                <input class=\"dropdown\" list=\"LGAs\" name=\"LGAs\" placeholder=\"LGA code or name...\">
                <datalist id=\"LGAs\">
            
        """;

        html = html + listLGAs();

        html = html + """
                </datalist>
                </input>
                
                <label>Choose % views</label>
            
                <div class=\"checkboxFilter\">
                  <input type=\"checkbox\" id=\"stateView\" name=\"stateView\">view State</input>
                  <br/>
                  <input type=\"checkbox\" id=\"ausView\" name=\"ausView\">view Australia</input>
                </div>
            
                <br/>
                    <button type='submit' class='submitButton'>Apply</button>
                </form>
                """;
        html = html + "</div>";

        String stateView = context.formParam("stateView");
        String ausView = context.formParam("ausView");

        System.out.println(stateView + ausView);

        html = html + "</div>";
        html = html + "<div class='statHeader'>";
        html = html + "<h2>Sorted Table by Ranking</h2>";
        html = html + "</div>";

        if(searchedLGA == null || foundLGA == null){
            html = html + """
                <div class='statTable'>
                    <table>
                        <tr>
                            <th>Table is empty! Please select an LGA</th>
                        </tr>
                    </table>
                </div>
                        """;
        }
        else{
            html = html + """
                <div class='statTable'>
                    <table>
                        <tr>
                            <th>Status</th>
                            <th>Sex</th>
                            <th>Age</th>
                            <th>Total in LGA</th>
                            <th>% in LGA</th>
                            
                        """;

                        if (!(stateView == null)) {
                            html = html + "<th>% in State</th>";
                        }
                        if (!(ausView == null)) {
                            html = html + "<th>% in Australia</th>";
                        }
                        html = html + """
                                     
                        </tr>
                        """;
                        JDBCConnection JDBC = new JDBCConnection();
                        ArrayList<VulnerablePop> lgaStat = new ArrayList<VulnerablePop>();
                        lgaStat = JDBC.getStatsByLGA(foundLGA);

                        ArrayList<VulnerablePop> stateStat = JDBC.getStatsByState(foundLGA.getState());

                        ArrayList<VulnerablePop> ausStat = JDBC.getStatsByAus();
                        int j = 0;
                        for(VulnerablePop i : lgaStat){
                            html = html + "<tr>";
                            html = html + "<td>" + i.getStatus() + "</td>";
                            html = html + "<td>" + i.getSex() + "</td>";
                            html = html + "<td>" + i.getAgeRange() + "</td>";
                            html = html + "<td>" + i.getTotal() + "</td>";
                            html = html + "<td>" + i.getPercentageInString() + "%</td>";
                            if (!(stateView == null)) {
                                html = html + "<td>" + stateStat.get(j).getPercentageInString() +"%</td>";
                            }
                            if (!(ausView == null)) {
                                html = html + "<td>"+ ausStat.get(j).getPercentageInString() +"%</td>";
                            }
                            html = html + "</tr>";
                            j++;

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
