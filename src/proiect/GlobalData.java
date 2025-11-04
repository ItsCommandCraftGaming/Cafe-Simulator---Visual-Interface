package proiect;

public class GlobalData {
    private static String nume;
    private static String moneda;
    private static double sold;

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
}
