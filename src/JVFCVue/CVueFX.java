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
    private Parent grille;

    @FXML
    private Grille grilleController = new Grille();

    @FXML
    private CButton buttonController = new CButton();

    public int maVAR;

    private Island modele;

    public int TAILLE = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(grille);
        System.out.println(grilleController);
        System.out.println("gridTest"+gridTest);
        System.out.println(buttonController);
        maVAR=213;
        modele = new Island();
        modele.addObserver(this);
        System.out.println("test");
        buttonController.setModele(this.modele);
        grilleController.setModel(this.modele); // ici je set le model dans la grille

        Island mod = buttonController.getModele();
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