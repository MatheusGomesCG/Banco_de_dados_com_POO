package aula;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://pt.wikipedia.org/wiki/Fórmula_1").get();
        
        System.out.println(doc.getElementsByTag("h1")); 

        String outputFile = "titles.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            Elements titles = doc.getElementsByTag("h1");

            for (Element title : titles) {
                writer.write(title.text());
                writer.newLine();
            }

            System.out.println("Títulos extraídos e salvos em " + outputFile);
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao tentar extrair os títulos e salvar: " + e.getMessage());
        }
    }
}


