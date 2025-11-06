package fileService;

import java.util.ArrayList;

public class DataReadManager {
    static ArrayList<Jucatori> jucatori = new ArrayList<>();

    
    public void citireFisier(){
        FileReader filereader = new FileReader("src/fileService/listaJucatori.txt");

        jucatori.clear();  //Resetam lista
        while (!filereader.endOfFile()) {
            jucatori.add(filereader.returnareDateJucatori());
        }

    }
    
    public ArrayList<Jucatori> getJucatori(){
        citireFisier();
        return jucatori;

    }
}
