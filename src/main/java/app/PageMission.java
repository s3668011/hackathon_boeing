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
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>About Us</title>";

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

        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        // creating pull req

        // Image
        html = html + """
                <div class='header image'>
                    <img src="https://images.unsplash.com/photo-1518398046578-8cca57782e17?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"/>
                        <div class="mission-block">
                            <h1>Our Mission</h1>
                            <p>Homelessness affects thousands of people across Australia. We've developed this site to try and spread awareness around the issue, as well as allow visitors to see real statistics on how homelessness impacts different Australian demographics.</p>
                        </div>
                </div>
                """;

        // Decoration Border
        html = html + """
            <div class='decoBorder'>
            </div>
        """;

        Student[] students = jdbc.getStudents();
        
        // adding our names and student numbers
        html = html + """
            <div class=\"aboutblock\">
                
                <div class=\"row darkrow\">
                <h2 id=\"aboutheading\">ABOUT US</h2>
                <div class=\"aboutcolumn student\">                        
                        """;
                html = html + "<p>" + students[0].getName() + "<br>";
                html = html + "<span>" + students[0].getEmail() + "</span></p></div>";
                    
                html = html + "<div class=\"aboutcolumn student\"><p>";
                html = html + students[1].getName() + "<br>";
                html = html + "<span>" + students[0].getEmail() + "</span></p></div>"; 
                html = html + "</div>";
            // adding personas
            Persona[] personas = jdbc.getPersonas();
            html = html + "<div class=\"personas\"><h2 id=\"personaheading\">PERSONAS</h2>";

            html = html + """
                <div class="personaColumn">
                    <div class="card">
                        <img src="Stanley_persona.png" alt="Stanley">
                        <div class="container">
                                
                                """;
            html = html +   "<h4><b>" + personas[1].getName() + "</b></h4>";
            html = html + "<p>Attributes:</p>";

            html = html + "<p>" + personas[1].getAttributes()[0] + "</p>";
            html = html + "<p>" + personas[1].getAttributes()[1] + "</p>";
            html = html + "<p>Goals:</p>";
            html = html + "<p>" + personas[1].getGoals()[0] + "</p>";
            html = html + "<p>" + personas[1].getGoals()[1] + "</p></div></div></div>";

            html = html + """
                <div class="personaColumn">
                    <div class="card">
                        <img src="Jeff_persona.png" alt="Stanley">
                        <div class="container">
                                
                                """;
            html = html +   "<h4><b>" + personas[0].getName() + "</b></h4>";
            html = html + "<p>Attributes:</p>";

            html = html + "<p>" + personas[0].getAttributes()[0] + "</p>";
            html = html + "<p>" + personas[0].getAttributes()[1] + "</p>";
            html = html + "<p>Goals:</p>";
            html = html + "<p>" + personas[0].getGoals()[0] + "</p>";
            html = html + "<p>" + personas[0].getGoals()[1] + "</p></div></div></div>";



            // close div for personas
            html = html + "</div></br>";

            html = html + """
                <div class=\"howToBlock\">
                    <h2 class=\"howtoheading\">HOW TO USE OUR SITE</h2>
                    <div class=\"row howtorow\">
                    <div class=\"howtocontent\">
                        <a href='/'>Home page</a>
                        <p>
                        This is our landing page, with 3 facts which stood out to us, as well as a broad overview of what we hope to achieve. 
                        </p>
                        <a href='/page3.html'>Statistics</a>
                        <p>
                        This is our general statistics page.....
                        </p>
                        <a href='/page4.html'>Advanced Statistics by LGA</a>
                        <p>
                        On this page you can filter data by LGA, State or Australia wide
                        </p>
                        <a href='/page5.html'>Advanced Filter</a>
                        <p>
                        On this page you can see what proportion of selected demographics are homeless.
                        </p>
                        <a href='/page6.html'>Trends over time</a>
                        <p>
                        Here you can see how homelessness has changed over time (between 2016 and 2018)
                        </p>
                    </div>
                </div>
                """;

            



        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='aboutfooter'>
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
