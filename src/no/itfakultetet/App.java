package no.itfakultetet;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Postgres data = new Postgres();
        String navn = "";

        Scanner sc1 = new Scanner(System.in);

        while (true) {
            System.out.println("Tast inn starten på firmanavnet (eller q for å avslutte):");
            navn = sc1.nextLine();
            if (navn.equals("q"))
                break;
            data.getData(navn);
        }

        sc1.close();
        System.out.println("Farvel...");
        System.exit(0);
    }


    }


