package com.unifacisa.Aula04;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class App {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, getPessoas());
		String jsonString = writer.toString();
		
		try (PrintStream out = new PrintStream(new FileOutputStream("output.json"))) {
            System.setOut(out);
            System.out.println(jsonString);
        }

        String jsonInput = "[{\"id\":1,\"nome\":\"Lucas\"},{\"id\":2,\"nome\":\"Maria\"},{\"id\":3,\"nome\":\"Jose\"}]";
        List<Pessoa> p = mapper.readValue(jsonInput, new TypeReference<List<Pessoa>>() {});
        System.out.println("Pessoa: " + p);

		}

	private static List<Pessoa> getPessoas() {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		Pessoa p1 = new Pessoa();
        p1.setId(1);
        p1.setNome("Lucas");
        p1.setSobrenome("Silva");
        p1.setIdade(28);
        p1.setCidade("Campina Grande");

        Pessoa p2 = new Pessoa();
        p2.setId(2);
        p2.setNome("Maria");
        p2.setSobrenome("Santos");
        p2.setIdade(24);
        p2.setCidade("São Paulo");

        Pessoa p3 = new Pessoa();
        p3.setId(3);
        p3.setNome("Jose");
        p3.setSobrenome("Lima");
        p3.setIdade(32);
        p3.setCidade("Rio de Janeiro");

        pessoas.add(p1);
        pessoas.add(p2);
        pessoas.add(p3);
		
		return pessoas;
	}
}
