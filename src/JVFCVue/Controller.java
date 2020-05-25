package JVFCVue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public GridPane gridTest;
    @FXML
    private Parent bouton1;
    @FXML
    private Bouton bouton1Controller;

    @FXML
    private Parent grille;
    @FXML
    private Grille grilleController;


    public int maVAR;
    private Grille grilleRef;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(bouton1);
        System.out.println(bouton1Controller);
        System.out.println(grille);
        System.out.println(grilleController);
        System.out.println("gridTest"+gridTest);
        maVAR=213;
        bouton1Controller.affiche();
    }



    public Grille getGrilleController(){
        return this.grilleController;
}


}