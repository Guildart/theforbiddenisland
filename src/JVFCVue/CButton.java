package JVFCVue;

import IleInterdite.Island;
import IleInterdite.Observer;
import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CButton{

    @FXML
    public Button butNextRound;

    @FXML
    public Button butTakeTresor;

    private Island modele;


    public void setModele(Island modele){
        this.modele = modele;
        modele.notifyObservers();
    }

    public Island getModele(){
        return this.modele;
    }

    @FXML
    public void nextRound(MouseEvent mouseEvent) {
        modele.nextRound();
        modele.notifyObservers();
    }

    @FXML
    public void takeTresor(MouseEvent mouseEvent) {
    }

}
