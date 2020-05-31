package Controller;

import IleInterdite.Island;
import IleInterdite.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CButton{

    @FXML
    public Button butNextRound;

    @FXML
    public Button butTakeTresor;

    @FXML
    public ToggleButton Quit;

    public VBox vbox;

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
        Player p = modele.getRoundOf();
        p.takeArtefact(modele.getListArtefacts());
        modele.notifyObservers();
    }


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

    /*public void moveOrDrain(MouseEvent mouseEvent) {
        modele.setTypeAction((modele.getTypeAction()+1)%2);
        if(selectAction.isSelected())
            selectAction.setText("MovePlayer");
        else
            selectAction.setText("DrainWater");
        modele.notifyObservers();
    }*/
}
