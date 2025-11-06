package fileService;

public class Jucatori {
    private String nume;
    private int scor;

    public Jucatori(String nume, int scor){
        this.nume=nume;
        this.scor=scor;
    }

    public Jucatori(){
        
    }

    @Override
    public String toString(){
        return "Nume: "+this.nume+", Scor: " + this.scor;
    }

    public void setNume(String nume){
        this.nume=nume;

    }

    public void setScor(int scor){
        this.scor=scor;

    }

    public String getNume(){
        return this.nume;
    }
    public int getScor(){
        return this.scor;
    }

}
