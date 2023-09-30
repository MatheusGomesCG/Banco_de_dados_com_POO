package com.br.etapa.etapa03;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import model.Pessoa;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.FileReader;
import java.io.IOException;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class App {
    public static void main(String[] args) throws IOException, CsvValidationException {
    	
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongoClient = new MongoClient("localhost:27017",
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase database = mongoClient.getDatabase("etapa")
                .withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Pessoa> collection = database.getCollection("Pessoa", Pessoa.class);

        try (CSVReader reader = new CSVReader(new FileReader("data/data.csv"))) {
            String[] nextLine;
            boolean firstLineSkipped = false; 
            
            while ((nextLine = reader.readNext()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true; 
                    continue; // 
                }
                
                String id = nextLine[0];
                String nome = nextLine[1];

                Pessoa pessoa = new Pessoa(Integer.parseInt(id), nome);

                collection.insertOne(pessoa);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        mongoClient.close();
    }
}
