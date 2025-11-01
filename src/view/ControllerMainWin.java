package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import proiect.*;

public class ControllerMainWin{

    @FXML
    private Label boxSalut;

    @FXML
    private Label boxSuma;

    @FXML
    private Label boxTimp;

    @FXML
    private AnchorPane fereastraPrinc;

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
                    boxSuma.setText(String.valueOf(viata.getScor()));
                    progressTime.setProgress(viata.getStatusBar() / 100.0);
                });
                try { Thread.sleep(1000); } 
                catch (InterruptedException e) { e.printStackTrace(); }
            }
            Platform.runLater(() -> boxSuma.setText("Game Over! Scor: " + viata.getScor()));
        }).start();
    
    }

}
