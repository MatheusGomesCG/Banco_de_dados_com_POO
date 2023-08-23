package csv;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Nome", "Idade", "Cidade", "CEP"});
        data.add(new String[]{"João", "25", "São Paulo", "01153000"});
        data.add(new String[]{"Maria", "30", "Rio de Janeiro", "20211901"});
        data.add(new String[]{"Carlos", "22", "Belo Horizonte", "31630900"});

        String filePath = "dados.csv";
        PersistenciaCSV.saveToCsv(data, filePath);
    }
}
