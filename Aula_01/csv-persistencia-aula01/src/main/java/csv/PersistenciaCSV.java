package csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PersistenciaCSV {

    public static void saveToCsv(List<String[]> data, String filePath) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT)) {
            for (String[] row : data) {
                csvPrinter.printRecord((Object[]) row);
            }
            System.out.println("Dados salvos com sucesso em " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados em CSV: " + e.getMessage());
        }
    }
}