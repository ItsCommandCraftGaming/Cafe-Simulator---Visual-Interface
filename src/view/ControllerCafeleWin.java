package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
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

        Aplicatie app = new Aplicatie();
        app.initCafele();
        app.afisCafele(puncte, suma);


    }

    
    @FXML
    public void initialize() {
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
                    progressTime.setProgress(viata.getStatusBar() / 100.0);
                });
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
            Platform.runLater(() -> boxSuma.setText("Game Over! Scor: " + viata.getScor()));
        }).start();
    }


}
