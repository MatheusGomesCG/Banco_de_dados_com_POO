package edu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class DataExtractor {
    public static void main(String[] args) {
        String urlEstatisticas = "http://educacao.dadosabertosbr.com/api/estatisticas?tipoLocal=EST&nomeLocal=PB&codMunicipio=0&indice=0";
        String urlEscolas = "http://educacao.dadosabertosbr.com/api/escolas?nome=aplicacao";
        String urlCidadesPB = "http://educacao.dadosabertosbr.com/api/cidades/pb";

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Extract JSON data using Jackson
            JsonNode estatisticasNode = readJsonFromUrl(urlEstatisticas, objectMapper);
            JsonNode escolasNode = readJsonFromUrl(urlEscolas, objectMapper);
            JsonNode cidadesPBNode = readJsonFromUrl(urlCidadesPB, objectMapper);

            // Process and store data in CSV
            FileWriter csvWriter = new FileWriter("dados_extraidos.csv");
            csvWriter.append("Dados Estatísticas\n");
            csvWriter.append(estatisticasNode.toString());
            csvWriter.append("\n\nDados Escolas\n");
            csvWriter.append(escolasNode.toString());
            csvWriter.append("\n\nDados Cidades PB\n");
            csvWriter.append(cidadesPBNode.toString());
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Dados extraídos e armazenados em 'dados_extraidos.csv'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonNode readJsonFromUrl(String urlString, ObjectMapper objectMapper) throws IOException {
        try {
            URI uri = new URI(urlString);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            try (InputStream inputStream = connection.getInputStream()) {
                return objectMapper.readTree(inputStream);
            }
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URL: " + urlString, e);
        }
    }
}

