package proiect;

public class GlobalData {
    private static String nume;
    private static String moneda;

    public static String getNume(){
        return nume;
    }

    public static String getMoneda(){
        return moneda;
    }

    public static void setNume(String nume){
        GlobalData.nume = nume;
    }

    public static void setMoneda(String moneda){
        GlobalData.moneda = moneda;
    }
}
