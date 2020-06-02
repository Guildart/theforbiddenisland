package Controller;

import Enumeration.Artefacts;
import Enumeration.Etat;
import IleInterdite.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

public class CVue implements Observer {
    /**
    * @FX:ID des controlleurs
    * */
    @FXML public Parent vueIsland;
    @FXML public Parent vueButton;
    @FXML public Parent vuePlayers;
    @FXML public Parent vueGame;
    @FXML public AnchorPane ancreParent;

    /**
     * @Instances des controlleurs
     * */
    @FXML private CVueIsland vueIslandController;
    @FXML private CButton vueButtonController;
    @FXML private CPlayerPanel vuePlayersController;
    @FXML private CVueGame vueGameController;

    /**
     * @Instances classiques
     * */
    private Island modele;

    /**
     * @Description c'est ici qu'on instancie tous les fichiers fxml en leur donnant le modèle
     * @param modele
     */
    public void setModele(Island modele){
        this.modele = modele;
        modele.addObserver(this);

        // On set les modele
        vueButtonController.setModele(this.modele);
        vueIslandController.setModel(this.modele);
        vuePlayersController.setModel(this.modele);
        vueGameController.setModele(this.modele);

    }
    @Override
    public void update() {

    }

}