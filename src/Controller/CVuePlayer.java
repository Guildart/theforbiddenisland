package Controller;

import Card.Card;
import Enumeration.TresorCard;
import IleInterdite.Island;
import IleInterdite.Observer;
import IleInterdite.Player;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

public class CVuePlayer implements Initializable, Observer {

    public Canvas viewplayer;
    private Island modele;



    private int indicePlayer;
    private final static int HAUTEUR = 200; //200
    private final static int LARGEUR = 400; //ou 400
    private GraphicsContext gcF;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dimension dim = new Dimension(LARGEUR, HAUTEUR);
        gcF = this.viewplayer.getGraphicsContext2D();

    }

    @Override
    public void update() {
        this.repaint();
    }

    public void repaint(){

        Color c = Color.PINK;
        gcF.setFill(c);
       // gcF.strokeLineJoin(new BasicStroke(4));
        //gcF.fillRect(0, 0, viewplayer.getWidth(), viewplayer.getHeight());
        gcF.fillRect(0, 0, LARGEUR,HAUTEUR);
        //gcF.setStroke(s);
        paintCard();
        paintPlayer(getPlayer().getColor());

    }
    private void paintPlayer(Color c) {
        /** Sélection d'une couleur. */
        gcF.setFill(c);
        /** Coloration d'un rectangle. */
        //*gcF.fillOval(LARGEUR/2, 20, 20, 20);
        gcF.fillOval(40/2, 150, 40, 40);

    }



    private void paintCard(){
        ArrayList<Card<TresorCard>> listecard = getPlayer().getCards();
        for(int i = 0; i < 6; i++){
            if(listecard.size() > i) {
                Card<TresorCard> card = listecard.get(i);
                gcF.setFill(card.getType().getColor());
            }else{
                gcF.setFill(Color.WHITE);
            }

            gcF.fillRect(5+i*(60+3), 30, 60, 90);
        }
    }


    public void setModel(Island modele){
        this.modele = modele;
        repaint();
        modele.notifyObservers();
    }

    public void setIndicePlayer(int indicePlayer) {
        this.indicePlayer = indicePlayer;
        repaint();
        modele.addObserver(this);
    }



    public Island getModele(){
        return this.modele;

    }


    public Player getPlayer(){
        //System.out.println("player: "+  this.modele.getListPlayers().get(this.indicePlayer));
        return this.modele.getListPlayers().get(this.indicePlayer);
    }


}
