package Controller;

import IleInterdite.Island;
import IleInterdite.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CButton implements Initializable {

    /**
     * @FX:ID des controlleurs
     * */
    @FXML public Button butNextRound;
    @FXML public Button butTakeTresor;
    @FXML public ToggleButton Quit;

    @FXML public VBox vbox; // Instance
    private Island modele;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butNextRound.toFront();
        butTakeTresor.toFront();
        Quit.toFront();
    }

    /**
     * @Description setteur modele
     */
    public void setModele(Island modele){
        this.modele = modele;
        modele.notifyObservers();
    }

    /**
     * @Description getteur modele
     */
    public Island getModele(){
        return this.modele;
    }

    /**
     * @Description bouton pour changer change de round
     * @param mouseEvent
     */
    @FXML
    public void nextRound(MouseEvent mouseEvent) {
        modele.nextRound();
        modele.notifyObservers();
    }

    /**
     * @Description gere le bouton takeTresor et permet de récuperer un tresor sur sa tuile
     * @param mouseEvent
     */
    @FXML
    public void takeTresor(MouseEvent mouseEvent) {
        Player p = modele.getRoundOf();
        p.takeArtefact(modele.getListArtefacts());
        modele.notifyObservers();
    }

    /**
     * @Description permet de revenir au menu principal
     * @param event
     * @throws Exception
     */
    @FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if(event.getSource()==Quit){
            stage = (Stage) Quit.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/Vue/VueMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

}
