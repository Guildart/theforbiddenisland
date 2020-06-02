package Controller;

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

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class CVuePlayer implements Initializable, Observer {


    public AnchorPane anch;
    public Canvas viewplayer;
    private Island modele;



    private int indicePlayer;
    private final static int HAUTEUR = 120;
    private final static int LARGEUR = 400;
    private GraphicsContext gcF;
    private boolean popupBool = true;
    private ArrayList<CardDraggable> arrayCards = new ArrayList<>();


    /**Hérité de Initializable, sera notre classe sera initialiser graphiquement/visuellement ici**/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gcF = viewplayer.getGraphicsContext2D();
    }

    /**Hérité de Observer, sera appelé ç chaque notification du modèle pour redessiner notre pane selon les nouvelles infos**/
    @Override
    public void update() {
        this.repaint();
        if(getPlayer().nombreCarte() > 5 && modele.getRoundOf() == getPlayer() && !modele.getEndOfGame()){
            CVueDefausse dv = new CVueDefausse();
            dv.display(modele, getPlayer());
        }
    }

    /**On généralise ici ce qui sera redessiner à chaque fois**/
    public void repaint(){
        updateCard();
        Color c = Color.PINK; //Couleurs standard que nous avons choisit
        gcF.setFill(c);
        if(getPlayer() == modele.getRoundOf())
            gcF.setFill(Color.MEDIUMSEAGREEN); //Si est au tour du joueur associer à l'instance de cette classe, le fond sera vert si non il sera rouge
        gcF.fillRoundRect(0, 0, LARGEUR,HAUTEUR,50,50); // Si non le fond sera de la couleurs Standard (derrière chaque interface joueurs)
        paintCard();

        for(int i = 0; i<arrayCards.size(); i++){
            arrayCards.get(i).translateSafeX();
            arrayCards.get(i).translateSafeY();
        }


    }


    /**On associe les Cartes du joueurs avec des CardDraggable qu'on pourra Drag and Drop**/
    public void updateCard(){
        ArrayList<TresorCard> listecard = getPlayer().getCards();
        for(int i =0; i< getPlayer().getPlayerCardsDragtgable().size(); i++){

            TresorCard c = listecard.get(i);
            CardDraggable ere = arrayCards.get(i);
            ere.setCard(c);
            ere.setImageURL(c.getURLForPlayersCards());

        }
    }



    private void paintCard(){
        ArrayList<TresorCard> listecard = getPlayer().getCards();
        for(int i = 0; i < 5; i++){
            if(listecard.size() > i) {
                TresorCard card = listecard.get(i);
                arrayCards.get(i).setStyle(CVueIsland.colorToStyleCard(card.getURLForPlayersCards()));
            }else{
                gcF.setFill(Color.WHITE);
                arrayCards.get(i).setStyle(CVueIsland.colorToStyleCard( TresorCard.empty.getURLForPlayersCards()));
            }

            gcF.fillRect(80+30+i*(50+5), 35, 50, 80);
        }


    }


    public void setModel(Island modele){
        this.modele = modele;
        modele.notifyObservers();
    }

    public void setIndicePlayer(int indicePlayer) {
        this.indicePlayer = indicePlayer;
        arrayCards = this.getPlayer().getPlayerCardsDragtgable();
        Image image = new Image(modele.getListPlayers().get(indicePlayer).getImage().toExternalForm(),200,200,true,true);
        ImageView img =  new ImageView(image);
        img.setX(-50);
        anch.getChildren().addAll(img);
        Label label = new Label(getPlayer().toString());
        Font font = Font.loadFont(getClass().getResourceAsStream("/image/treamd.ttf"),30);
        label.setFont(font);
        label.setTextFill(Color.BLACK);
        label.setLayoutX(100);
        label.setLayoutY(-10);
        anch.getChildren().addAll(label);
        repaint();
        modele.addObserver(this);
    }



    public Island getModele(){
        return this.modele;
    }


    public Player getPlayer(){
        return this.modele.getListPlayers().get(this.indicePlayer);
    }


}
