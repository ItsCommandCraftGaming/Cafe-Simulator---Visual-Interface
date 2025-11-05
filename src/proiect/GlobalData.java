package proiect;

public class GlobalData {
    private static String nume;
    private static String moneda;
    private static double sold;
    private static ThreadViata viata;
    private static final int[] puncteCafele = new int[10];
    private static final double[] sumaCafele = new double[10];

    public static String getNume(){
        return nume;
    }

    public static String getMoneda(){
        return moneda;
    }

    public static double  getSold(){
        return sold;
    }

    public static void setNume(String nume){
        GlobalData.nume = nume;
    }

    public static void setMoneda(String moneda){
        GlobalData.moneda = moneda;
    }

    public static void setSold(double sold){
        GlobalData.sold = sold;
    }

    public static void modSold(double sold){
        GlobalData.sold += sold;
    }

    public static ThreadViata getViata() {
        return viata;
    }

    public static void setViata(ThreadViata v) {
        viata = v;
    }

    public static void setPuncteCafele(int puncte, int i){
        GlobalData.puncteCafele[i] = puncte;
    }

    public static void setSumaCafele(double suma, int i){
        GlobalData.sumaCafele[i] = suma;
    }

    public static int getPuncteCafele(int i){
        return GlobalData.puncteCafele[i];
    }

    public static double getSumaCafele(int i){
        return GlobalData.sumaCafele[i];
    }

}
