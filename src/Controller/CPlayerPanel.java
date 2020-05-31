package Controller;

import IleInterdite.Island;
import IleInterdite.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CPlayerPanel{


    @FXML
    public CVuePlayer canvasPlayer1Controller;

    @FXML
    public CVuePlayer canvasPlayer2Controller;

    @FXML
    public CVuePlayer canvasPlayer3Controller;

    @FXML
    public CVuePlayer canvasPlayer4Controller;

    private Island modele;

    public void setModel(Island modele){
        this.modele = modele;
        //this.modele.addObserver(this);

        this.canvasPlayer1Controller.setModel(this.modele);
        this.canvasPlayer1Controller.setIndicePlayer(0);

        this.canvasPlayer2Controller.setModel(this.modele);
        this.canvasPlayer2Controller.setIndicePlayer(1);

        this.canvasPlayer3Controller.setModel(this.modele);
        this.canvasPlayer3Controller.setIndicePlayer(2);

        this.canvasPlayer4Controller.setModel(this.modele);
        this.canvasPlayer4Controller.setIndicePlayer(3);

    }

}
