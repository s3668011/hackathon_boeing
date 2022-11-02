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
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection JDBC = new JDBCConnection();
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Home</title>";

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

        // Decoration Border
        html = html + """
            <div class='decoBorder'>
            </div>
        """;

        // Image
        html = html + """
                <div class='image'>
                    <img src=\"homepageBannerImage.png\" alt=\"Homepage-Banner\">
                        <div class=\"text-block\">
                            <h1>A total of """;
                            String totalLga = String.format("%,d", JDBC.TotalLga());
                            html = html + " <span class=\"decoORANGE\"> " + totalLga + "</span>" + """
                                people are homeless just in <span class=\"decoORANGE\">Melbourne</span> alone...</h1>
                            <p>scroll down for more...</p>
                        </div>
                </div>
                """;
        
        // Decoration Border
        html = html + """
            <div class='decoBorder'>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        String TChildren = String.format("%,d", JDBC.TotalChildren());
        String TWoman = String.format("%,d", JDBC.TotalWoman());
        String TElderly = String.format("%,d", JDBC.TotalElderly());
        String TPopulation = String.format("%,d", JDBC.TotalPopulation());

        // Add HTML for the list of pages (as well as topnav)
        html = html + """
                <div class=\"column\">""";
                    html = html + "<h2>More than <span class=\"decoORANGE\">" + TChildren + " children</span> under 10 are without a home</h2>";
                html = html + "</div>";
                html = html + """
                    <div class=\"column\">""";
                    html = html + "<h2>Homelessness affects over <span class=\"decoORANGE\">" + TWoman + " women</span> across Australia</h2>";
                html = html + "</div>";
                html = html + "<div class=\"column\">";
                    html = html + "<h2>Upwards of <span class=\"decoORANGE\">" + TElderly + " elderly</span> people are sleeping rough</h2>";
                html = html + "</div>";
            html = html + "</div>";

        html = html + """
                <div class='decoBorder'></div>
                """;

        html = html + """
                <div class='blueBox'>""";
                    html = html + "<h2 class='whiteBorder'>Australias homeless population is more than <span class='decoORANGE'>" + TPopulation + "</span></h2>";
                html = html + "</div>";

        html = html + """
                <h3 class='statButton'>Find out more:   <a href='page3.html'><button class='button'>Statistics</button></a></h3>
                """;

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
