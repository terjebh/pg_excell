package no.itfakultetet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CSV {
    public static void lagreTilCsv(List<String> firmaListe) {
        try ( BufferedWriter fil = Files.newBufferedWriter(Paths.get("firma.csv"), StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {

            fil.append("Orgnummer\tFirmanavn\tAnsatte\n");
            firmaListe.forEach(a -> {
                try {
                    fil.append(a+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            fil.flush();
            System.out.println("Filen: firma.csv er lagret.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
