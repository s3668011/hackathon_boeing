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
public class PageST21 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Statistics</title>";

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

        html = html + "<div class='content'>";
        html = html + "<div class='filters'>";
        html = html + "<div class='sFilter'>";
        html = html + "<h2>Filters</h2>";
        html = html + """
                <form action='/page3.html' method='post'>
                    <label for='status_drop'>Select Status</label>
                    <select id='status_drop' name='status_drop'>
                        <option>homeless</option>
                        <option>at_risk</option>
                    </select><br>
                    <label for='sex_drop'>Select Sex</label>
                    <select id='sex_drop' name='sex_drop'>
                        <option>m</option>
                        <option>f</option>
                    </select><br>
                    <label for='age_drop'>Select Age</label>
                    <select id='age_drop' name='age_drop'>
                        <option>_0_9</option>
                        <option>_10_19</option>
                        <option>_20_29</option>
                        <option>_30_39</option>
                        <option>_40_49</option>
                        <option>_50_59</option>
                        <option>_60_plus</option>
                    </select><br>
                    <button type='submit' class='submitButton'>Apply</button>
                </form>
                """;
        html = html + "</div>";

        String status_drop = context.formParam("status_drop");
        String sex_drop = context.formParam("sex_drop");
        String age_drop = context.formParam("age_drop");

        html = html + "<a href='page4.html'><button class='nextFilter'>Click here to filter by LGA</button></a>";
        html = html + "</div>";
        html = html + "<div class='statHeader'>";
        html = html + "<h2>Sorted Table by Ranking</h2>";
        html = html + "</div>";

        if((status_drop == null) && (sex_drop == null) && (age_drop == null)){
            System.out.println(status_drop + sex_drop + age_drop);
            html = html + """
                <div class='statTable'>
                    <table>
                        <tr>
                            <th>LGA</th>
                            <th>Age</th>
                            <th>Sex</th>
                            <th>Raw</th>
                            <th>% in Total Homeless LGA</th>
                        </tr>
                        """;
                        JDBCConnection JDBC = new JDBCConnection();
                        ArrayList<statSubtaskOne> stat = new ArrayList<statSubtaskOne>();
                        stat = JDBC.statFilters(false, null, null, null);
                        for(statSubtaskOne i : stat){
                            String raw = String.format("%,d", i.getRaw());
                            html = html + "<tr>";
                            html = html + "<td>" + i.getName() + "</td>";
                            html = html + "<td>" + i.getAge() + "</td>";
                            html = html + "<td>" + i.getSex() + "</td>";
                            html = html + "<td>" + raw + "</td>";
                            html = html + "<td>" + i.getTotal() + "%</td>";
                            html = html + "</tr>";
                        }
            html = html + "</table>";
            html = html + "</div>";
        }
        else{
            System.out.println(status_drop + sex_drop + age_drop);
            html = html + """
                <div class='statTable'>
                    <table>
                        <tr>
                            <th>LGA</th>
                            <th>Age</th>
                            <th>Sex</th>
                            <th>Raw</th>
                            <th>% in Total Homeless LGA</th>
                        </tr>
                        """;
                        JDBCConnection JDBC = new JDBCConnection();
                        ArrayList<statSubtaskOne> stat = new ArrayList<statSubtaskOne>();
                        stat = JDBC.statFilters(true, status_drop, sex_drop, age_drop);
                        for(statSubtaskOne i : stat){
                            String raw = String.format("%,d", i.getRaw());
                            html = html + "<tr>";
                            html = html + "<td>" + i.getName() + "</td>";
                            html = html + "<td>" + i.getAge() + "</td>";
                            html = html + "<td>" + i.getSex() + "</td>";
                            html = html + "<td>" + raw + "</td>";
                            html = html + "<td>" + i.getTotal() + "%</td>";
                            html = html + "</tr>";
                        }
            html = html + "</table>";
            html = html + "</div>";
        }

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
