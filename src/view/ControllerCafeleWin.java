package view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import proiect.*;

public class ControllerCafeleWin {

    @FXML
    private AnchorPane anchCafele;

    @FXML
    private Label boxSalut;

    @FXML
    private Label boxScor;

    @FXML
    private Label boxSuma;

    @FXML
    private Label boxTimp;

    @FXML
    private Button btnIesire;

    @FXML
    private Button cafea1;

    @FXML
    private Button cafea10;

    @FXML
    private Button cafea2;

    @FXML
    private Button cafea3;

    @FXML
    private Button cafea4;

    @FXML
    private Button cafea5;

    @FXML
    private Button cafea6;

    @FXML
    private Button cafea7;

    @FXML
    private Button cafea8;

    @FXML
    private Button cafea9;

    @FXML
    private Label pct1;

    @FXML
    private Label pct10;

    @FXML
    private Label pct2;

    @FXML
    private Label pct3;

    @FXML
    private Label pct4;

    @FXML
    private Label pct5;

    @FXML
    private Label pct6;

    @FXML
    private Label pct7;

    @FXML
    private Label pct8;

    @FXML
    private Label pct9;

    @FXML
    private ProgressBar progressTime;

    @FXML
    private Label suma1;

    @FXML
    private Label suma10;

    @FXML
    private Label suma2;

    @FXML
    private Label suma3;

    @FXML
    private Label suma4;

    @FXML
    private Label suma5;

    @FXML
    private Label suma6;

    @FXML
    private Label suma7;

    @FXML
    private Label suma8;

    @FXML
    private Label suma9;

    private ThreadViata viata;



    @FXML
    public void initPreturiPuncte(Label[] puncte, Label[] suma){
        //se initializeaza fiecare label cu o valoare random
        Aplicatie app = new Aplicatie();
        app.initCafele();
        app.afisCafele(puncte, suma);


    }

    @FXML
    void clkIesire(ActionEvent event) {
        tranzitie();
    }


    public void submitClick(int i){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainWin.fxml"));
            Parent root = loader.load();

            //Aici apelam controllerul din cealalta clasa
            //ControllerMainWin winMain = loader.getController();

            if (GlobalData.getSold()>=GlobalData.getSumaCafele(i)){
                GlobalData.modSold(-GlobalData.getSumaCafele(i));
                viata.addViata(GlobalData.getPuncteCafele(i));

            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sold insuficient");
                alert.setHeaderText(null);
                alert.setContentText("Nu ai destui bani!");
                alert.showAndWait();
            }
        
            //Se seteaza stage-ul nou (se lucreaza cu un singur stage))
            Stage stage = (Stage) anchCafele.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setTitle("Meniu cafele");
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    boolean play = true;

    
    @FXML
    public void initialize() {
        //aici am colectat toate label-urile
        Label[] puncte = {pct1, pct2, pct3, pct4, pct5, pct6, pct7, pct8, pct9, pct10};
        Label[] suma = {suma1, suma2, suma3, suma4, suma5, suma6, suma7, suma8, suma9, suma10};


        //se verifica thread activ
        if (GlobalData.getViata() == null) {
            viata = new ThreadViata(15);
            viata.start();
            GlobalData.setViata(viata);
        } else {
            viata = GlobalData.getViata();
        }

        initPreturiPuncte(puncte, suma);

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
            Stage stage = (Stage) anchCafele.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }

            
            //Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("GAME OVER!!!");
            //alert.setHeaderText(null);
            //alert.setContentText("Jocul s-a terminat. Timp expirat");
            //alert.showAndWait();
            
        });
        //GlobalData.addError();
    }


    //evenimente click
    @FXML
    void clickCafea1(ActionEvent event) {
        submitClick(0);
    }

    @FXML
    void clickCafea2(ActionEvent event) {
        submitClick(1);
    }

    @FXML
    void clickCafea3(ActionEvent event) {
        submitClick(2);
    }

    @FXML
    void clickCafea4(ActionEvent event) {
        submitClick(3);
    }

    @FXML
    void clickCafea5(ActionEvent event) {
        submitClick(4);
    }

    @FXML
    void clickCafea6(ActionEvent event) {
        submitClick(5);
    }

    @FXML
    void clickCafea7(ActionEvent event) {
        submitClick(6);
    }

    @FXML
    void clickCafea8(ActionEvent event) {
        submitClick(7);
    }

    @FXML
    void clickCafea9(ActionEvent event) {
        submitClick(8);
    }
    
    @FXML
    void clickCafea10(ActionEvent event) {
        submitClick(9);
    }

    public void tranzitie(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainWin.fxml"));
            Parent root = loader.load();

            //Aici apelam controllerul din cealalta clasa
            //ControllerMainWin winMain = loader.getController();
            //winMain.setLabelSalut("Salut, " + GlobalData.getNume());
        
            //Se seteaza stage-ul nou (se lucreaza cu un singur stage))
            Stage stage = (Stage) anchCafele.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setTitle("Fereastra principala");
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
