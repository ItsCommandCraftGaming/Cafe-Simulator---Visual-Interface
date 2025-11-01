package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Pentru deschidere de alte ferestre
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.stream.Stream;


import proiect.*;

public class ControllerSelBani{

    
    @FXML
    private AnchorPane anchBani;

    @FXML
    private Button btnEur;

    @FXML
    private Button btnRon;

    @FXML
    private Button btnUsd;

    @FXML
    private Label userLabel;

    public void setLabelUser(String numeUser){
        userLabel.setText(numeUser);
    }

    @FXML
    void clkEuro(ActionEvent event) {
        int moneda = 2;
        String nume = GlobalData.getNume();

        Aplicatie.creareContBanca(moneda, nume);

        schimbareWindow();

    }

    @FXML
    void clkRon(ActionEvent event) {
        int moneda = 1;
        String nume = GlobalData.getNume();

        Aplicatie.creareContBanca(moneda, nume);

        schimbareWindow();

    }

    @FXML
    void clkUsd(ActionEvent event) {
        int moneda = 3;
        String nume = GlobalData.getNume();

        Aplicatie.creareContBanca(moneda, nume);

        schimbareWindow();

    }

    public void schimbareWindow(){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainWin.fxml"));
        Parent root = loader.load();

        //Aici apelam controllerul din cealalta clasa
        ControllerMainWin winMain = loader.getController();
        winMain.setLabelSalut("Salut, " + GlobalData.getNume());
    
        //Se seteaza stage-ul nou (se lucreaza cu un singur stage))
        Stage stage = (Stage) anchBani.getScene().getWindow();
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.setTitle("Meniu Joc");
        stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
