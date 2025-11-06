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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Pentru deschidere de alte ferestre
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.stream.Stream;

import fileService.DataWriteManager;
import interfete.AlbNegru;
import proiect.*;
import fileService.*;

public class ControllerMainWin implements AlbNegru{

    @FXML
    private Label boxSalut;

    @FXML
    private Label boxSuma;

    @FXML
    private Label boxScor;

    @FXML
    private Label boxTimp;

    @FXML
    private Button btnCafele;

    @FXML
    private Button btnQuiz;

    @FXML
    private ImageView imgCafele;

    @FXML
    private ImageView imgQuiz;

    @FXML
    private AnchorPane fereastraPrinc;

    @FXML
    void cursorInCafele(MouseEvent event) {
        setareColor(imgCafele);
    }

    @FXML
    void cursorInQuiz(MouseEvent event) {
        setareColor(imgQuiz);
    }

    @FXML
    void cursorOutCafele(MouseEvent event) {
        setareAlbNegru(imgCafele);
    }

    @FXML
    void cursorOutQuiz(MouseEvent event) {
        setareAlbNegru(imgQuiz);
    }
    
    
    @Override
    public void setareAlbNegru(ImageView imagine){
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1);
        imagine.setEffect(grayscale);

    }

    @Override
    public void setareColor(ImageView imagine){
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(1);
        imagine.setEffect(grayscale);

    }

    public void setLabelSalut(String numeUser){
        boxSalut.setText(numeUser);
    }

    public void setLabelSuma(String suma){
        boxSuma.setText(suma);
    }


    //functionalitate thread
    @FXML
    private ProgressBar progressTime;

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

            if (play && viata.getViata()<=0){
                play = false;
                inchide();
                
            }
        }).start();
    
    }

    private static boolean dateSalvate = false;

    public void inchide() {
        Platform.runLater(() -> {
            Stage stage = (Stage) fereastraPrinc.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }
            
            if (!dateSalvate) {
                DataWriteManager dwm = new DataWriteManager();
                dwm.scrieFisier("Nume->" + GlobalData.getNume() + "\nScor->" + viata.getScor() + "\n---");
                dateSalvate = true;
            }
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME OVER!!!");
            alert.setHeaderText(null);
            alert.setContentText("Jocul s-a terminat. Timp expirat");
            alert.showAndWait();
            
            
            
        });
        GlobalData.addError();
    }


    //prima obtiune (cafele)
    @FXML
    void clickCafele(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cafeleWin.fxml"));
            Parent root = loader.load();

            //Aici apelam controllerul din cealalta clasa
            ControllerCafeleWin winMain = loader.getController();
            //winMain.setLabelSalut("Salut, " + GlobalData.getNume());
        
            //Se seteaza stage-ul nou (se lucreaza cu un singur stage))
            Stage stage = (Stage) fereastraPrinc.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setTitle("Meniu cafele");
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    

    @FXML
    private Button btnGeo;

    @FXML
    private Button btnMate;

    //obtiunea 2 (quiz-uri)
    int deschis = 0;
    @FXML
    void clickQuiz(ActionEvent event) {
        if (deschis == 0){
            deschis = 1;
        }
        else{
            deschis = 0;
        }
        
        btnGeo.setOpacity(deschis);
        btnMate.setOpacity(deschis);
        if (deschis == 0){
            btnGeo.setDisable(true);
            btnMate.setDisable(true);
        }
        else{
            btnGeo.setDisable(false);
            btnMate.setDisable(false);
        }
    }

    //quiz-uri (geografie)
    @FXML
    void clickGeo(ActionEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/quizWin.fxml"));
            Parent root = loader.load();

            //Aici apelam controllerul din cealalta clasa
            ControllerQuizWin winQuiz = loader.getController();
            winQuiz.initQuiz("geo");
            
        
            //Se seteaza stage-ul nou (se lucreaza cu un singur stage))
            Stage stage = (Stage) fereastraPrinc.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setTitle("QUIZ Geografie");
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        


    }

    //quiz-uri (matematica)
    @FXML
    void clickMate(ActionEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/quizWin.fxml"));
            Parent root = loader.load();

            //Aici apelam controllerul din cealalta clasa
            ControllerQuizWin winQuiz = loader.getController();
            winQuiz.initQuiz("mate");
            
        
            //Se seteaza stage-ul nou (se lucreaza cu un singur stage))
            Stage stage = (Stage) fereastraPrinc.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setTitle("QUIZ Matematica");
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
