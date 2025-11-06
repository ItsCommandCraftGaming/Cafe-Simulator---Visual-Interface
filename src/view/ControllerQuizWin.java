package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

//Pentru deschidere de alte ferestre
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import proiect.*;

public class ControllerQuizWin {

    private Geografie geo = new Geografie();
    private Matematica mate = new Matematica();

    @FXML
    private AnchorPane anchQuiz;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField imputIntrebare;

    @FXML
    private Label intrebare;

    @FXML
    private Label boxSalut;

    @FXML
    private Label boxScor;

    @FXML
    private Label boxSuma;

    @FXML
    private Label boxTimp;

    @FXML
    private ProgressBar progressTime;

    public void setLabelIntrebare(String text){
        intrebare.setText(text);
    }

    public String getRaspuns(){
        return imputIntrebare.getText();
    }

    //functie click submit
    public void submit(){
        if (geo.isActive()){
            String raspunsOrig = geo.getRaspuns();
            String raspuns = getRaspuns();
            double sold = geo.getRecomp();

            if(raspunsOrig.equalsIgnoreCase(raspuns)){
                double soldConv = Aplicatie.conversie(sold, "RON", GlobalData.getMoneda());
                GlobalData.modSold(soldConv);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ai primit");
                alert.setHeaderText(null);
                alert.setContentText("Ai primit" + String.format("%.2f", soldConv) + GlobalData.getMoneda());
                alert.showAndWait();

                tranzitie();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(":(((");
                alert.setHeaderText(null);
                alert.setContentText("Raspuns gresit");
                alert.showAndWait();

                tranzitie();
            }
            geo.setActiv(false);
        }
        else if (mate.isActive()){
            try{
                int raspunsOrig = mate.getRaspuns();
                int raspuns = Integer.parseInt(getRaspuns());
                double sold = mate.getRecomp();

                if(raspunsOrig == raspuns){
                    double soldConv = Aplicatie.conversie(sold, "RON", GlobalData.getMoneda());
                    GlobalData.modSold(soldConv);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ai primit");
                    alert.setHeaderText(null);
                    alert.setContentText("Ai primit" + String.format("%.2f", soldConv) + GlobalData.getMoneda());
                    alert.showAndWait();

                    tranzitie();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(":(((");
                    alert.setHeaderText(null);
                    alert.setContentText("Raspuns gresit");
                    alert.showAndWait();

                    tranzitie();
                }
                geo.setActiv(false);
            }
            catch(NumberFormatException e){ //<- daca s-a introdus litere in loc de cifre
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Eroare de imput");
                alert.setHeaderText(null);
                alert.setContentText("Ai introdus un format gresit");
                alert.showAndWait();

            }
        }
    }

    @FXML  //Submit pentru buton
    void clkSubmit(ActionEvent event) {
        submit();


    }

    @FXML //Submit pentru tasta ENTER
    void enterSubmit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            submit();
    
        }
    }

    public void tranzitie(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainWin.fxml"));
            Parent root = loader.load();

            //Aici apelam controllerul din cealalta clasa
            //ControllerMainWin winMain = loader.getController();
            //winMain.setLabelSalut("Salut, " + GlobalData.getNume());
        
            //Se seteaza stage-ul nou (se lucreaza cu un singur stage))
            Stage stage = (Stage) anchQuiz.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setTitle("Fereastra principala");
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void initQuiz(String tipIntrebare){
        if (tipIntrebare.equals("geo")){
            geo.exeGeo();
            geo.setActiv(true);
            String intrebare = geo.getIntrebare();
            setLabelIntrebare(intrebare);


        }
        else if (tipIntrebare.equals("mate")){
            mate.exeMate();
            mate.setActiv(true);
            String intrebare = mate.getIntrebare();
            setLabelIntrebare(intrebare);

        }

    }

    //mecania pentru thread
    private ThreadViata viata;

    boolean play = true;


    @FXML
    public void initialize() {
        //se verifica thread activ
        if (GlobalData.getViata() == null) {
            viata = new ThreadViata(15);
            viata.start();
            GlobalData.setViata(viata);
        } else {
            viata = GlobalData.getViata();
        }
        
        new Thread(() -> {
        while (viata.getStatusBar() > 0) {
            Platform.runLater(() -> {
                double sold = GlobalData.getSold();
                boxScor.setText("Scor: " + viata.getScor());
                boxTimp.setText("Timp: " + viata.getViata());
                boxSuma.setText("Suma: " + String.format("%.2f", sold) + " " + GlobalData.getMoneda());
                boxSalut.setText("Salut, " + GlobalData.getNume());
                progressTime.setProgress(viata.getStatusBar() / 100.0);

                //culoare bara
                if (viata.getViata()>=10){
                    progressTime.setStyle("-fx-accent: #4c91afff");
                }
                else if (viata.getViata()<10){
                    progressTime.setStyle("-fx-accent: #ff0000ff");
                }
            });
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
                //Platform.runLater(() -> boxSuma.setText("Game Over! Scor: " + viata.getScor()));
                if (play && viata.getViata()<=0){
                play = false;
                inchide();
            }
        }).start();
    
    }

    public void inchide() {
        Platform.runLater(() -> {
            Stage stage = (Stage) anchQuiz.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }
            
            
            
            //Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("GAME OVER!!!");
            //alert.setHeaderText(null);
            //alert.setContentText("Jocul s-a terminat. Timp expirat");
            //alert.showAndWait();
            
        });
        GlobalData.addError();
    }

}
