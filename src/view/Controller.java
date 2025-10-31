package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Pentru deschidere de alte fereastre
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import proiect.*;




public class Controller {
    int nrIncercari = 0;

    @FXML
    private Button btn;

    @FXML
    private Button btnReset;

    @FXML
    private PasswordField fieldPass;

    @FXML
    private TextField fieldUser;

    @FXML
    private Label labelPass;

    @FXML
    private Label labelUser;
    
    @FXML
    private AnchorPane fereastraPrinc;

    @FXML
    void click(ActionEvent event) {
        login();

    }
    
    @FXML
    void clickReset(ActionEvent event) {
        fieldUser.clear();
        fieldPass.clear();

    }

    @FXML
    void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();

        }

    }

    public void login(){
        
        String user = fieldUser.getText();
        GlobalData.setNume(user);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login succes");
        alert.setHeaderText(null);
        alert.setContentText("Bine ai venit, " + user + "!");
        alert.showAndWait();
    
        try {
            // Încarcă noul FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/selectBani.fxml"));
            Parent root = loader.load();

            //Aici apelam controllerul din cealalta clasa
            ControllerSelBani winBani = loader.getController();
            winBani.setLabelUser(user);

            // Se seteaza stage-ul nou (se lucreaza cu un singur stage))
            Stage stage = (Stage) fereastraPrinc.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setTitle("Fereastra nouă");
            stage.show();

            
            winBani.setLabelUser(user);
            
            
            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }



}
