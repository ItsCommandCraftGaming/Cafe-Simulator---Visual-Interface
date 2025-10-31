package proiect;


public class ContBancar {
    private String numePrenDetinator;
    private double sold;
    private String moneda;

    public ContBancar(String numePrenDetinator, double sold, String moneda){
        this.numePrenDetinator=numePrenDetinator;
        this.sold=sold;
        this.moneda=moneda;

    }

    public void modSold(double rataModSold){
        if (sold+rataModSold>=0){
            this.sold+=rataModSold;
            if (rataModSold>0) System.out.println("Contul a fost alimentat cu "+rataModSold+" "+this.getMoneda());
        }
        else throw new UnsupportedOperationException();
    }

    public String getNumePren(){
        return this.numePrenDetinator;
    }

    public double getSold(){
        return this.sold;
    }

    public String getMoneda(){
        return this.moneda;
    }
    
}
