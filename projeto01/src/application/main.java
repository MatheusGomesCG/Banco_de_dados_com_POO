import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
       
        String url = "http://www.gov.br/inep";

        try {
            
            Document document = Jsoup.connect(url).get();

            Elements elementos = document.select(".item"); 
            
            FileWriter csvWriter = new FileWriter("dados.csv");
            csvWriter.append("Nome,Idade\n");

            for (Element elemento : elementos) {
                String nome = elemento.select(".nome").text(); 
                String idade = elemento.select(".idade").text(); 
                csvWriter.append(nome).append(",").append(idade).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("Dados extraídos e salvos em dados.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}