package proiect;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Aplicatie{
    private final int nrCafele = 10;
    private final Cafele[] cafele = new Cafele[nrCafele];
    static ContBancar cont;
    static ThreadViata viata;
    static public Jocuri mate, geo;
    

    public static double conversie(double pretPtConversie, String monedaPtConversie, String monedaConvertita){
        switch (monedaPtConversie) {
            case "RON" -> {
                switch (monedaConvertita) {
                    case "RON" ->{
                        return pretPtConversie;
                    }
                    case "EUR" ->{
                        return pretPtConversie/5.08;
                    }
                    case "USD" ->{
                        return pretPtConversie/4.34;
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            case "EUR" -> {
                switch (monedaConvertita) {
                    case "EUR" -> {
                        return pretPtConversie;
                    }
                    case "RON" -> {
                        return pretPtConversie*5.08;
                    }
                    case "USD" -> {
                        return pretPtConversie*1.17;
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            case "USD" -> {
                switch (monedaConvertita) {
                    case "USD" -> {
                        return pretPtConversie;
                    }
                    case "RON" -> {
                        return pretPtConversie*4.34;
                    }    
                    case "EUR" -> {
                        return pretPtConversie*0.85;
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            default ->
                throw new AssertionError();
        }
    }

    public void initCafele(){
        String[] numeCafele = {"Espresso","Doppio","Ristretto","Lungo","Americano","Macchiato","Cappuccino","Latte","Flat White","Café Mocha"};
        Random rn = new Random();  //Suma random
        double min = 10;
        double max = 30;

        if (cafele[0]==null){  //Daca nu a fost creat pana acuma
            for (int i=0;i<nrCafele;i++){
                cafele[i] = new Cafele(numeCafele[i], rn.nextDouble(min,max), (int) rn.nextDouble(min,max)/2);
            }

        }
        else {  //Daca a fost creat
            for (int i=0;i<nrCafele;i++){
                cafele[i].setPret(rn.nextDouble(min,max));
                cafele[i].setViata((int) rn.nextDouble(min,max)/2);

            }
        }

    }

    public void afisCafele(){
            for (int i=0;i<nrCafele;i++){
                System.out.print((i+1)+". "+cafele[i].getNume()+" | ");
                System.out.printf("%.2f",conversie(cafele[i].getPret(), "RON", cont.getMoneda()));  //Afisare cu 2 zecimale
                System.out.println(" "+cont.getMoneda()+" \u001B[42m+"+cafele[i].getViata()+"\u001B[0m");
            }
        
    }

    public static void creareContBanca(int selectie, String nume){
        double sold;
        try{
            switch (selectie) {
                case 1 -> {
                    sold=50;
                    cont = new ContBancar(nume, sold, "RON");
                    break;
                }
                case 2 -> {
                    sold=conversie(50, "RON", "EUR");
                    cont = new ContBancar(nume, sold, "EUR");
                    break;
                }
                case 3 -> {
                    sold=conversie(50, "RON", "USD");
                    cont = new ContBancar(nume, sold, "USD");
                    break;
                }
                default ->{
                    throw new AssertionError();
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Format invalid");
        }


    }

    public static boolean verifCreareCont(){
        return cont!=null;
    }

    public void cumparareCafele(){
        try{
            Scanner sc = new Scanner(System.in);
            int alegere = sc.nextInt();
            double pret = conversie(cafele[alegere-1].getPret(), "RON", cont.getMoneda());  //Conversie din LEI in moneda contului
            //System.out.print("S-a facut conversia din ");
            //System.out.printf("%.2f",cafele[alegere-1].getPret());
            //System.out.print(" RON in ");
            //System.out.printf("%.2f",pret);
            //System.out.println(" "+cont.getMoneda());
            cont.modSold(-pret);                          //Scadere pret
            viata.addViata(cafele[alegere-1].getViata()); //Crestere viata
            
            System.out.print("A mai ramas ");
            System.out.printf("%.2f",cont.getSold());
            System.out.println(" "+cont.getMoneda()+" total in cont");
        }
        catch (ArrayIndexOutOfBoundsException e){          //Daca se introduce un nr mai mare decat 10
            System.out.println("Numarul este invalid");
        }
        catch (NullPointerException e){                    //Daca nu exita cont
            System.out.println("Contul bancar nu a fost creat!! \nVa rog sa va creati unul de la obtiunea nr. 1");
        }
        catch (UnsupportedOperationException e){           //Daca soldul a ajuns sub 0
            System.out.println("Sold insuficient");
        }
        catch (InputMismatchException e){                  //Daca se introduce un alt tip de date de la tastatura
            System.out.println("Format invalid");
        }
        
    }

    public void exeJocuri(){
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        
        System.out.println("1. Matematica\n2. Geografie");
        int nrJoc = sc.nextInt();
        sc.nextLine();
        int nrInr = rn.nextInt(1, 5);
        int raspuns;
        String raspunsS;
        switch (nrJoc) {
            case 1->{
                switch(nrInr){
                    case 1 ->{
                        System.out.println("5+7= ");
                        raspuns = sc.nextInt();
                        if (raspuns==12) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        sc.nextLine();
                        break;
                    }
                    case 2 ->{
                        System.out.println("15+8= ");
                        raspuns = sc.nextInt();
                        if (raspuns==23) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        sc.nextLine();
                        break;
                    }
                    case 3 ->{ 
                        System.out.println("16+43= ");
                        raspuns = sc.nextInt();
                        if (raspuns==59) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        sc.nextLine();
                        break;
                    }
                    case 4 ->{
                        System.out.println("5*9= ");
                        raspuns = sc.nextInt();
                        if (raspuns==45) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        sc.nextLine();
                        break;
                    }
                    case 5 ->{
                        System.out.println("17-34= ");
                        raspuns = sc.nextInt();
                        if (raspuns==-17) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        sc.nextLine();
                        break;
                    }
                    default->
                        throw new AssertionError();
                }
                break;
            }
            case 2->{
                switch(nrInr){
                    case 1 ->{
                        System.out.println("Capitala Germaniei: ");
                        raspunsS = sc.nextLine();
                        if (raspunsS.equalsIgnoreCase("berlin")) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        break;
                    }
                    case 2 ->{
                        System.out.println("Capitala Romaniei: ");
                        raspunsS = sc.nextLine();
                        if (raspunsS.equalsIgnoreCase("bucuresti")) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        break;
                    }
                    case 3 ->{
                        System.out.println("Capitala Rusiei: ");
                        raspunsS = sc.nextLine();
                        if (raspunsS.equalsIgnoreCase("moscova")) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        break;
                    }
                    case 4 ->{
                        System.out.println("Capitala Italiei: ");
                        raspunsS = sc.nextLine();
                        if (raspunsS.equalsIgnoreCase("roma")) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        break;
                    }
                    case 5 ->{
                        System.out.println("Capitala Spaniei: ");
                        raspunsS = sc.nextLine();
                        if (raspunsS.equalsIgnoreCase("madrid")) cont.modSold(mate.getRecomp());
                        else System.out.println("Raspuns gresit!!");
                        break;
                    }
                    default->
                        throw new AssertionError();
                }
                break;
            }
            default->
                throw new AssertionError();
        }

    }
}