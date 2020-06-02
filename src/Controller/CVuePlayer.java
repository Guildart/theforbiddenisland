package Controller;

import Enumeration.TresorCard;
import IleInterdite.Island;
import IleInterdite.Observer;
import IleInterdite.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class CVuePlayer implements Initializable, Observer {


    public AnchorPane anch;
    public Canvas viewplayer;
    private Island modele;



    private int indicePlayer;
    private final static int HAUTEUR = 120; //200
    private final static int LARGEUR = 400; //ou 400
    private GraphicsContext gcF;
    private boolean popupBool = true;
    private ArrayList<CardDraggable> arrayCards = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dimension dim = new Dimension(LARGEUR*10, HAUTEUR);
        gcF = viewplayer.getGraphicsContext2D();
        /*gcF.setFill(Color.RED);
        gcF.fillRect(0, 0, LARGEUR,HAUTEUR);*/



    }

    @Override
    public void update() {
        this.repaint();
        if(getPlayer().nombreCarte() > 5 && modele.getRoundOf() == getPlayer()){
            CVueDefausse dv = new CVueDefausse();
            dv.display(modele, getPlayer());
        }
    }

    public void repaint(){
        updateCard();
        Color c = Color.PINK;
        gcF.setFill(c);
       // gcF.strokeLineJoin(new BasicStroke(4));
        //gcF.fillRect(0, 0, viewplayer.getWidth(), viewplayer.getHeight());
        gcF.fillRect(0, 0, LARGEUR,HAUTEUR);
        //gcF.setStroke(s);
        paintCard();
        paintPlayer(getPlayer().getColor());

        for(int i = 0; i<arrayCards.size(); i++){
            arrayCards.get(i).translateSafeX();
            arrayCards.get(i).translateSafeY();
        }


    }
    private void paintPlayer(Color c) {
        /** Sélection d'une couleur. */
        gcF.setFill(c);
        /** Coloration d'un rectangle. */
        //*gcF.fillOval(LARGEUR/2, 20, 20, 20);
        //gcF.fillOval(10, 30, 80, 80);

    }

    public void updateCard(){
        ArrayList<TresorCard> listecard = getPlayer().getCards();
        for(int i =0; i< getPlayer().getPlayerCardsDragtgable().size(); i++){
            //System.out.println(listecard.size());
            //System.out.println(arrayCards.size());

            TresorCard c = listecard.get(i);
            //arrayCards.get(i).setCard(listecard.get(i));
            CardDraggable ere = arrayCards.get(i);
            ere.setCard(c);
            ere.setColor(c.getColor());
            ere.setImageURL(c.getImageForCard());

        }
    }



    private void paintCard(){
        ArrayList<TresorCard> listecard = getPlayer().getCards();
        for(int i = 0; i < 5; i++){
            if(listecard.size() > i) {
                TresorCard card = listecard.get(i);
                gcF.setFill(card.getColor());
                //TresorCard a = arrayCards.get(i).getCard();
                //gcF.setFill(a .getColor());
                arrayCards.get(i).setStyle(CVueIsland.colorToStyleCard(card.getImageForCard()));
            }else{
                gcF.setFill(Color.WHITE);
                arrayCards.get(i).setStyle(CVueIsland.colorToStyleCard( TresorCard.empty.getImageForCard()));
            }

            gcF.fillRect(80+30+i*(50+5), 35, 50, 80);
        }


    }


    public void setModel(Island modele){
        this.modele = modele;
        //repaint();



        modele.notifyObservers();
    }

    public void setIndicePlayer(int indicePlayer) {
        this.indicePlayer = indicePlayer;
        arrayCards = this.getPlayer().getPlayerCardsDragtgable();
        Image image = new Image(modele.getListPlayers().get(indicePlayer).getImage().toExternalForm(),200,200,true,true);

        ImageView img =  new ImageView(image);
        img.setX(-50);
        anch.getChildren().addAll(img);
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
