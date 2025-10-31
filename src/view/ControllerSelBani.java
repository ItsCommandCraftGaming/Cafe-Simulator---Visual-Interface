package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//Pentru deschidere de alte ferestre
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;


import proiect.*;

public class ControllerSelBani {

    
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

    }

    @FXML
    void clkRon(ActionEvent event) {

    }

    @FXML
    void clkUsd(ActionEvent event) {

    }

}
