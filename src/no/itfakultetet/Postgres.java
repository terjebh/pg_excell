package no.itfakultetet;

import java.sql.*;
import java.util.Properties;

public class Postgres {

    private Connection conn;

    public Postgres() {
        String url = "jdbc:postgresql://s1.itfakultetet.no/brreg";
        Properties props = new Properties();
        props.setProperty("user", "kurs");
        props.setProperty("password", "kurs123");

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.println("Forbindelse ikke akseptert fordi " + e.getMessage());
            e.printStackTrace();
        }

    } // end constructor

    public void getData(String navn) {

        Statement st;
        int i = 0;
        System.out.println("Orgnummer\tNavn");
        System.out.println("-----------------------------------------");

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT orgnr, navn FROM enheter WHERE navn ilike '" + navn + "%'");
            while (rs.next()) {
                System.out.println(rs.getString("orgnr") + "\t" + rs.getString("navn"));
                i++;
            }

            System.out.println("Antall bedrifter funnet: "+i);
            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
