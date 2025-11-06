package proiect;

import java.util.Random;

import interfete.geoService;

public class Geografie extends Jocuri implements geoService{
    private final double recompensa = 8;

    private String intrebare;
    private String raspuns;
    private boolean activ = false;

    @Override
    public double getRecomp(){
        return this.recompensa;
    }

    @Override
    public void exeGeo(){
        Random rn = new Random();
        
        int nrInr = rn.nextInt(1, 5);
        switch(nrInr){
            case 1 ->{
                intrebare = "Capitala Germaniei: ";
                raspuns = "berlin";
                break;
            }
            case 2 ->{
                intrebare = "Capitala Romaniei: ";
                raspuns = "bucuresti";
                break;
            }
            case 3 ->{
                intrebare = "Capitala Rusiei: ";
                raspuns = "moscova";
                break;
            }
            case 4 ->{
                intrebare = "Capitala Italiei: ";
                raspuns = "roma";
                break;
            }
            case 5 ->{
                intrebare = "Capitala Spaniei: ";
                raspuns = "madrid";
                break;
            }
            default->
                throw new AssertionError();
        }
    }

    public String getIntrebare(){
        return this.intrebare;
    }

    public String getRaspuns(){
        return this.raspuns;
    }

    public void setActiv(boolean activ){
        this.activ = activ;
    }

    public boolean isActive(){
        return activ;
    }
}

