package view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    private ProgressBar progressTime;

    private ThreadViata viata;

    @FXML
    public void initialize() {
        viata = new ThreadViata(15);

        
        new Thread(() -> {
            viata.start();
            while (viata.getStatusBar() > 0) {
                Platform.runLater(() -> {
                    double sold = GlobalData.getSold();
                    boxScor.setText("Scor: " + String.valueOf(viata.getScor()));
                    boxTimp.setText("Timp: " + String.valueOf(viata.getViata()));
                    boxSuma.setText("Suma: " + String.valueOf(String.format("%.2f", sold)) + " " + GlobalData.getMoneda());
                    progressTime.setProgress(viata.getStatusBar() / 100.0);
                });
                try { Thread.sleep(1000); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
            Platform.runLater(() -> boxSuma.setText("Game Over! Scor: " + viata.getScor()));
        }).start();
    
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
