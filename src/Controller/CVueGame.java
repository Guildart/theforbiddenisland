package Controller;

import Enumeration.Artefacts;
import IleInterdite.Island;
import IleInterdite.Player;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import IleInterdite.Observer;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;


public class CVueGame implements Observer{

    @FXML public Canvas viewGame;
    @FXML public Text text;
    private Island modele;
    private GraphicsContext gcF; // là ou l'on dessine

    /**
    * Exemplaire d'array list de tous les artefacts pour display les éléments que les joueurs ont récupérés en bas
    * */
    public ArrayList<Artefacts> artefactsList = new ArrayList();
    Font font;

    @Override
    public void update() {
        repaint();
    }

    // on initialise la font et on met les 4 artefacts dans artefactsList
    public void initialize() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/image/treamd.ttf"),30);
        text.setFont(font);


        artefactsList.add(Artefacts.eau);
        artefactsList.add(Artefacts.feu);
        artefactsList.add(Artefacts.air);
        artefactsList.add(Artefacts.terre);

    }

    /**
     * @Description set le modele
     */
    public void setModele(Island modele){
        this.modele = modele;
        modele.addObserver(this);
        modele.notifyObservers();
    }

    /**
     * @Description redessine en bas les 4 artefacts et la jauge de l'eau qui monte
     */
    public void repaint() {

        gcF = this.viewGame.getGraphicsContext2D();

        gcF.clearRect(0, 0, viewGame.getWidth(), viewGame.getHeight());

        URL imageURL = getClass().getResource("/image/jauge.png");
        Image image = new Image(imageURL.toExternalForm());
        gcF.drawImage(image, 320,30);
        int seaLevel = modele.getSeaLevel();
        //text.setX(350);
        text.setText(modele.getRoundOf().toString() + ": " + (3-Player.nbActionsRestant) + " actions restantes");
        Image pointeur = new Image(getClass().getResource("/image/pointer.png").toExternalForm());
        gcF.drawImage(pointeur, 320-5+(30*(seaLevel-1)),30-20);

        int compteur = 0;
        for( Artefacts a : artefactsList){
            Image image1;
            if(modele.getListArtefacts().contains(a))
                image1 = a.getImageOrgi();
            else
                image1 = a.getImageGris();

            ImageView img =  new ImageView(image1);
            gcF.drawImage(image1,300+100*compteur,100);
            compteur++;
        }


    }


}
