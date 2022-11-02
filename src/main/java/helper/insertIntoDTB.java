package helper;

import app.JDBCConnection;

public class insertIntoDTB {
    public static void main (String[] args) {
        JDBCConnection JDBC = new JDBCConnection();
        JDBC.addStudents();
    }
}
