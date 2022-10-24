package no.itfakultetet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Postgres {

    private Connection conn;

    public Postgres() {
        String url = "jdbc:postgresql://itfakultetet.no/brreg";
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

        List<String> firmaListe = new ArrayList<>();

        Statement st;
        int i = 0;
        System.out.println("Orgnummer\tNavn\tAnsatte");
        System.out.println("-----------------------------------------");

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT orgnr, navn, ansatte_antall FROM enheter WHERE navn ilike '" + navn + "%'");
            while (rs.next()) {
                String firmaStreng = rs.getString("orgnr") + "\t" + rs.getString("navn") + "\t" + rs.getString("ansatte_antall");
                firmaListe.add(firmaStreng);
                i++;
            }

            System.out.println("Antall bedrifter funnet: " + i);
            rs.close();
            st.close();
            lagreData(firmaListe);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        public void lagreData(List<String> firmaListe) throws IOException {

            Scanner lagre = new Scanner(System.in);
            System.out.println("1. Lagre som csv-fil   2. Lagre som Excel-fil");
            String lagreSom = lagre.nextLine();
            if (lagreSom.equals("1")) {
                CSV.lagreTilCsv(firmaListe);
            } else if (lagreSom.equals("2")) {
                Excel.lagreTilExcel(firmaListe);
            }
        }




}
