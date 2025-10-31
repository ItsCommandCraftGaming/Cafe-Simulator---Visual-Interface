package proiect;

public class Jocuri {
    private String tip;
    private double recompensa;

    public Jocuri(String tip, double recompensa){
        this.tip=tip;
        this.recompensa=recompensa;
    }

    public String getTip(){
        return this.tip;
    }

    public double getRecomp(){
        return this.recompensa;
    }

}
