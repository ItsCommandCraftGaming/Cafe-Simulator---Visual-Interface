package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import fileService.*;

public class ControllerJucatoriWin {

    @FXML
    private AnchorPane anchJucatori;

    @FXML
    private Button btnOk;

    @FXML
    private TableColumn<Jucatori, String> colNume;

    @FXML
    private TableColumn<Jucatori, Integer> colScor;

    @FXML
    private TableView<Jucatori> tabelJucatori;

    private ObservableList<Jucatori> listaJucatori = FXCollections.observableArrayList();

    @FXML
    void clkOk(ActionEvent event) {
        Stage stage = (Stage) anchJucatori.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        colNume.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nume"));
        colScor.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("scor"));
        tabelJucatori.setItems(listaJucatori);

        // Poți popula lista direct din DataReadManager:
        DataReadManager drm = new DataReadManager();
        listaJucatori.addAll(drm.getJucatori());
    }

    public void setListaJucatori(java.util.List<Jucatori> jucatori) {
        listaJucatori.setAll(jucatori);
    }

}
