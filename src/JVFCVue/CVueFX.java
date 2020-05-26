package JVFCVue;

import IleInterdite.Island;
import IleInterdite.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CVueFX implements Initializable, Observer {
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

    private Island modele;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(bouton1);
        System.out.println(bouton1Controller);
        System.out.println(grille);
        System.out.println(grilleController);
        System.out.println("gridTest"+gridTest);
        maVAR=213;
        bouton1Controller.affiche();
        modele = new Island();
        modele.addObserver(this);

        grilleController.setModel(this.modele); // ici je set le model dans la grille

    }

    public void Controller1(Island modele){
        //System.out.println("modele "+gridTest);
        //this.modele = modele;
        //grilleController.setModel(modele);
    }



    public Grille getGrilleController(){
        return this.grilleController;
}



    @Override
    public void update() {

    }
}