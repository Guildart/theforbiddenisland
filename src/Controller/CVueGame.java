package Controller;

import IleInterdite.Island;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import IleInterdite.Observer;
import java.net.URL;
import java.util.ResourceBundle;


public class CVueGame implements Initializable, Observer{

    @FXML
    public Canvas viewGame;
    private Island modele;
    private GraphicsContext gcF;

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setModele(Island modele){
        this.modele = modele;
        modele.addObserver(this);
        modele.notifyObservers();
    }

    public void repaint() {
        /** Pour chaque cellule... */

        gcF = this.viewGame.getGraphicsContext2D();

        gcF.clearRect(0, 0, viewGame.getWidth(), viewGame.getHeight());

        URL imageURL = getClass().getResource("/image/jauge.png");
        Image image = new Image(imageURL.toExternalForm());
        gcF.drawImage(image, 150,50);
        int seaLevel = modele.getSeaLevel();

        Image pointeur = new Image(getClass().getResource("/image/pointer.png").toExternalForm());
        gcF.drawImage(pointeur, 150-5+(30*(seaLevel-1)),50-20);
    }


}
