package proiect;

import java.util.Random;

import interfete.mateService;

public class Matematica extends Jocuri implements mateService{
    private final double recompensa = 10;

    private String intrebare;
    private int raspuns;
    private boolean activ = false;

    @Override
    public double getRecomp(){
        return this.recompensa;
    }

    @Override
    public void exeMate(){
        Random rn = new Random();
        
        int nrInr = rn.nextInt(1, 5);
        switch(nrInr){
            case 1 ->{
                intrebare = "5+7= ";
                raspuns = 12;
                break;
            }
            case 2 ->{
                intrebare = "15+8= ";
                raspuns = 23;
                break;
            }
            case 3 ->{
                intrebare = "16+43= ";
                raspuns = 59;
                break;
            }
            case 4 ->{
                intrebare = "5*9= ";
                raspuns = 45;
                break;
            }
            case 5 ->{
                intrebare = "17-34= ";
                raspuns = -17;
                break;
            }
            default->
                throw new AssertionError();
        }
    }

    public String getIntrebare(){
        return this.intrebare;
    }

    public int getRaspuns(){
        return this.raspuns;
    }

    public void setActiv(boolean activ){
        this.activ = activ;
    }

    public boolean isActive(){
        return activ;
    }
}
