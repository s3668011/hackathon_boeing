package helper;

import java.util.ArrayList;

import app.JDBCConnection;
import app.advFilterJa;

public class stat3test {
    public static void main (String[] args) {
        JDBCConnection JDBC = new JDBCConnection();
        ArrayList<advFilterJa> ar = new ArrayList<advFilterJa>();
        ar = JDBC.advFilters("homeless", "f", "0", "39", "Australia", 500, 1000, "LGA.lga_name DESC");

        for(advFilterJa i : ar){
            System.out.printf("| LgaName: %s | MedAge: %d | MedIncome: %d | MedRent: %d | Total: %.2f | Query: %.1f | Population: %d |\n", i.getLgaName(), i.getMedAge(), i.getMedIncome(), i.getMedRent(), i.getTotalPerc(), i.getQueryPerc(), i.getPopulation());
        }

    }
}
