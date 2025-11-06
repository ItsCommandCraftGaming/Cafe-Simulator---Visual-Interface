package fileService;

import java.io.FileWriter;
import java.io.IOException;

public class DataWriteManager {

    public void scrieFisier(String continut) {
        try (FileWriter writer = new FileWriter("src/fileService/listaJucatori.txt", true)) {
            writer.write(continut + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
