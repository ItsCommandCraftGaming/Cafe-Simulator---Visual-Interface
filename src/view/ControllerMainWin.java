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

import proiect.*;

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
                progressTime.setProgress(viata.getStatusBar() / 100.0);
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
            Stage stage = (Stage) fereastraPrinc.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME OVER!!!");
            alert.setHeaderText(null);
            alert.setContentText("Jocul s-a terminat. Timp expirat");
            alert.showAndWait();
            
        });
    }

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
    void clickQuiz(ActionEvent event) {


    }

}
