package proiect;

public class Cafele {
    private String nume;
    private double pret;
    private String moneda;
    private int viata;

    public Cafele(String nume, double pret, int viata){
        this.nume=nume;
        this.pret=pret;
        this.viata=viata;
    }

    public void modPret(double rataModPret){
        this.pret+=rataModPret;
    }

    public String getNume(){
        return this.nume;
    }

    public double getPret(){
        return this.pret;
    }

    public String getMoneda(){
        return this.moneda;
    }

    public int getViata(){
        return this.viata;
    }

    public void setPret(double pret){
        this.pret=pret;
    }

    public void setViata(int viata){
        this.viata=viata;
    }

}
