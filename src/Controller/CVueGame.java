package Controller;

import Enumeration.Artefacts;
import IleInterdite.Island;
import IleInterdite.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import IleInterdite.Observer;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


public class CVueGame implements Observer{

    @FXML
    public Canvas viewGame;
    private Island modele;
    private GraphicsContext gcF;
   /* public HashMap<Artefacts, Image> artefactsGris = new HashMap<Artefacts, Image>();
    public HashMap<Artefacts, Image> artefactsCouleur = new HashMap<Artefacts, Image>();*/
    public ArrayList<Artefacts> artefactsList = new ArrayList();


    @Override
    public void update() {
        repaint();
    }

    public void initialize() {

        /*artefactsGris.put(Artefacts.eau, new Image(getClass().getResource("/image/eau_g.png").toExternalForm()));
        artefactsGris.put(Artefacts.feu, new Image(getClass().getResource("/image/feu_g.png").toExternalForm()));
        artefactsGris.put(Artefacts.air, new Image(getClass().getResource("/image/vent_g.png").toExternalForm()));
        artefactsGris.put(Artefacts.terre, new Image(getClass().getResource("/image/terre_g.png").toExternalForm()));

        artefactsGris.put(Artefacts.eau, new Image(getClass().getResource("/image/eau.png").toExternalForm()));
        artefactsGris.put(Artefacts.feu, new Image(getClass().getResource("/image/feu.png").toExternalForm()));
        artefactsGris.put(Artefacts.air, new Image(getClass().getResource("/image/vent.png").toExternalForm()));
        artefactsGris.put(Artefacts.terre, new Image(getClass().getResource("/image/terre.png").toExternalForm()));*/

        artefactsList.add(Artefacts.eau);
        artefactsList.add(Artefacts.feu);
        artefactsList.add(Artefacts.air);
        artefactsList.add(Artefacts.terre);

    }

    public void setModele(Island modele){
        this.modele = modele;
        modele.addObserver(this);
        modele.notifyObservers();
    }

    public void repaint() {
        /** Pour chaque cellule... */

        //initialize();
        gcF = this.viewGame.getGraphicsContext2D();

        gcF.clearRect(0, 0, viewGame.getWidth(), viewGame.getHeight());

        URL imageURL = getClass().getResource("/image/jauge.png");
        Image image = new Image(imageURL.toExternalForm());
        gcF.drawImage(image, 250,30);
        int seaLevel = modele.getSeaLevel();

        Image pointeur = new Image(getClass().getResource("/image/pointer.png").toExternalForm());
        gcF.drawImage(pointeur, 250-5+(30*(seaLevel-1)),30-20);

        int compteur = 0;
        for( Artefacts a : artefactsList){
            Image image1;
            if(modele.getListArtefacts().contains(a))
                image1 = a.getImageOrgi();
            else
                image1 = a.getImageGris();

            ImageView img =  new ImageView(image1);
            gcF.drawImage(image1,200+100*compteur,100);
            compteur++;
        }


    }


}
