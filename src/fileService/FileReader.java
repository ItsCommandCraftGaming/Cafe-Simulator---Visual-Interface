package fileService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String filePath;
    private Scanner scanner;
    private File file;
    
    public FileReader(String filePath){
        this.filePath = filePath;
        this.file = new File(this.filePath);

        try {
            this.scanner = new Scanner(this.file);
        } 
        catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit!! ");

            throw new RuntimeException(e);
        }
    }

    public String citireFisier(){
        return scanner.nextLine();
    }

    public boolean endOfFile(){
        return !scanner.hasNextLine();
    }

    public Jucatori returnareDateJucatori(){
        Jucatori jucator = new Jucatori();
        while(scanner.hasNextLine()){
            String linie = scanner.nextLine();
            if (linie.equals("---")){
                return jucator;
            }
            else if(linie.equals("...")){
                return null;
            }
            else{
                String[] lista = linie.split("->",2);
                if (lista[0].equals("Nume")){
                    jucator.setNume(lista[1]);
                }
                else if (lista[0].equals("Scor")){
                    jucator.setScor(Integer.parseInt(lista[1]));
                }
            }
        }
        return null;
    }

    

}
