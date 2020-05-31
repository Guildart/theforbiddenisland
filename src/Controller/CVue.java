package Controller;

import IleInterdite.Island;
import IleInterdite.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CVue implements Initializable, Observer {
    @FXML
    public Parent vueIsland;

    @FXML
    public Parent vueButton;

    @FXML
    public Parent vuePlayers;

    @FXML
    public Parent vueGame;

    @FXML
    public GridPane gridTest;

    @FXML
    public AnchorPane ancreParent;

    @FXML
    private CVueIsland vueIslandController;

    @FXML
    private CButton vueButtonController;


    @FXML
    private CPlayerPanel vuePlayersController;

    @FXML
    private CVueGame vueGameController;


    public int maVAR;

    private Island modele;

    public int TAILLE = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* System.out.println(butVBOX);
        System.out.println(grille);
        System.out.println(CVueIslandController);
        System.out.println("gridTest "+gridTest);
        System.out.println("griplayer0ControllerdTest "+player0Controller );*/
        maVAR=213;
        modele = new Island();
        modele.addObserver(this);
        System.out.println("test");
        vueButtonController.setModele(this.modele);
        vueIslandController.setModel(this.modele); // ici je set le model dans la grille
        vuePlayersController.setModel(this.modele);
        vueGameController.setModele(this.modele);
        //vueMenu.toFront();
        //vuePlayersController.setModel(this.modele);

        /*
        player3Controller.setModel(this.modele); // ici je set le model dans la grille
        player3Controller.setIndicePlayer(3);
        */

        Island mod = vueButtonController.getModele();
        Island mod2 = vueIslandController.getModele();
        System.out.println(mod);
        System.out.println(mod2);
        ancreParent.getParent();
    }


    @Override
    public void update() {

    }

}