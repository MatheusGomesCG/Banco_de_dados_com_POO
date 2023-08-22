package aula3;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class PassageirosXML {
    public static void main(String[] args) {
        try {
            File inputFile = new File("passageiros.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("passageiro");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nome = element.getElementsByTagName("nome").item(0).getTextContent();
                    String origem = element.getElementsByTagName("origem").item(0).getTextContent();
                    String destino = element.getElementsByTagName("destino").item(0).getTextContent();

                    System.out.println("Passageiro: " + nome);
                    System.out.println("Origem: " + origem);
                    System.out.println("Destino: " + destino);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
