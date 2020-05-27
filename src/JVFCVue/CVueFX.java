package JVFCVue;

import IleInterdite.Island;
import IleInterdite.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CVueFX implements Initializable, Observer {
    @FXML
    public GridPane gridTest;
    @FXML
    private Parent bouton1;

    @FXML
    private Parent grille;

    @FXML
    private Parent butVBOX;

    @FXML
    private Grille grilleController;

    @FXML
    private CButton butVBOXController;

    @FXML
    public Parent player0;
    @FXML
    private Cvueplayer player0Controller;

    @FXML
    public Parent player1;
    @FXML
    private Cvueplayer player1Controller;

    @FXML
    public Parent player2;
    @FXML
    private Cvueplayer player2Controller;

    @FXML
    public Parent player3;
    @FXML
    private Cvueplayer player3Controller;

    public int maVAR;

    private Island modele;

    public int TAILLE = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* System.out.println(butVBOX);
        System.out.println(grille);
        System.out.println(grilleController);
        System.out.println("gridTest "+gridTest);
        System.out.println("griplayer0ControllerdTest "+player0Controller );*/
        maVAR=213;
        modele = new Island();
        modele.addObserver(this);
        System.out.println("test");
        butVBOXController .setModele(this.modele);
        grilleController.setModel(this.modele); // ici je set le model dans la grille
        player0Controller.setModel(this.modele); // ici je set le model dans la grille
        player0Controller.setIndicePlayer(0);

        player1Controller.setModel(this.modele); // ici je set le model dans la grille
        player1Controller.setIndicePlayer(1);

        player2Controller.setModel(this.modele); // ici je set le model dans la grille
        player2Controller.setIndicePlayer(2);
        /*
        player3Controller.setModel(this.modele); // ici je set le model dans la grille
        player3Controller.setIndicePlayer(3);
        */

        Island mod = butVBOXController.getModele();
        Island mod2 = grilleController.getModele();
        System.out.println(mod);
        System.out.println(mod2);

    }


    public void Controller1(Island modele){

    }



    public Grille getGrilleController(){
        return this.grilleController;
}



    @Override
    public void update() {

    }

}