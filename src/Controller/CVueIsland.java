package Controller;

import Enumeration.Etat;
import Enumeration.TresorCard;
import IleInterdite.*;
import IleInterdite.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.*;
import Enumeration.Artefacts;

public class CVueIsland implements Observer {


    /**
     * @FX:ID des controlleurs
     * */
    @FXML public Canvas canvas;
    @FXML public AnchorPane anch;

    //Array list de mes pions, j'en aurais besoin pour les translate
    private ArrayList<PionsDraggable> arrayPion = new ArrayList<>();
    //Array list de mes cartes, j'en aurais besoin pour les translate elles aussi
    private ArrayList<CardDraggable> arrayCards = new ArrayList<>();

    public static int TAILLE = 100; // taille d'une case
    private GraphicsContext gcF; // contexte graphique pour dessiner
    private Island modele; // notre modele


    // path
    private final URL imageURLHeliport = getClass().getResource("/image/heliport.png");
    // l'image heliport à afficher
    private Image imageHeliport =  new Image(imageURLHeliport.toExternalForm(),TAILLE,TAILLE,true,true);

    //implement observer..
    @Override
    public void update() {
        repaint();
    }

    /**
    * @Description fonction qui repaint la grille
     */
    public void repaint() {


        gcF = this.canvas.getGraphicsContext2D();


        for(int i = 0; i< Island.LARGEUR; i++) {
            for(int j = 0; j< Island.HAUTEUR; j++) {
                paintZone(gcF, modele.getZone(i, j), (i)*TAILLE, (j)*TAILLE); // affichage auxiliaire
                setArtefactToZone(gcF, modele.getZone(i, j), (i)*TAILLE, (j)*TAILLE);
            }
        }

        // on peint les zones ou le joueur peut se déplacer.
        Player p = modele.getRoundOf();
        ArrayList<Zone> listZones = p.zonesSafeToMove();
        for(Zone z: listZones){
            Position pos = z.getPosition();
            paintSafeZone(gcF, Color.AQUA, pos.x*TAILLE, pos.y*TAILLE);
        }

        translatePionPlayer();
    }

    /**
    * @Description On translate les pions ici,
    * on fait en sorte d'organiser les pions sur une même grille
     */
    private void translatePionPlayer(){
        ArrayList<Player> liste = modele.getListPlayers();
        ArrayList<Position> arrayPos = new ArrayList<>();

        for(int i = 0; i<liste.size(); i++){
            Position pos = liste.get(i).getZone().getPosition();

            if(arrayPos.contains(pos)){
                int a = Collections.frequency(arrayPos, pos);
                if(a == 1 ){
                    arrayPion.get(i).setY(pos.y*TAILLE);
                    arrayPion.get(i).setX(TAILLE/2+pos.x*TAILLE);
                }
                else if(a==2){
                    arrayPion.get(i).setY(TAILLE/2+pos.y*TAILLE);
                    arrayPion.get(i).setX(pos.x*TAILLE);
                }
                else{
                    arrayPion.get(i).setY(pos.y*TAILLE+TAILLE/2);
                    arrayPion.get(i).setX(TAILLE/2+pos.x*TAILLE);
                }

            }else{
                arrayPion.get(i).setY(pos.y*TAILLE);
                arrayPion.get(i).setX(pos.x*TAILLE);

            }

            arrayPos.add(pos);

        }
    }

    /**
     * @Description On dessine une grille, et si c'est un l'heliport, on lui met son image
     */
    private void paintZone(GraphicsContext g, Zone c, int x, int y) {

        if (c.getEtat() == Etat.none) {
            g.setFill(Color.DARKGRAY);
        } else if (c.getEtat() == Etat.normale){
            g.setFill(Color.GREEN);
        } else if (c.getEtat() == Etat.inondee){
            g.setFill(Color.CYAN);
        }
        else{
            g.setFill(Color.BLUE);
        }
        /** Coloration d'un rectangle. */ // il faut dessiner les cartes ici
        g.fillRect(x, y, TAILLE, TAILLE);


        if(c.isHeliport()){ // on dessine l'heliport ici
            g.drawImage(imageHeliport,c.getPosition().x*TAILLE,c.getPosition().y*TAILLE); // imageHeliport constant
        }

    }


    /**
     * @Description On dessine les zones ou le joueur peut se déplacer
     * @param g là ou on dessine
     * @param c la couleur
     * @param x position x
     * @param y position y
     */
    private void paintSafeZone(GraphicsContext g, Color c, int x, int y) {
        /** Sélection d'une couleur. */
        double old = g.getLineWidth();
        g.setLineWidth(4.0);
        g.setFill(c);
        /** Coloration d'un rectangle. */
        g.strokeRect(x, y, TAILLE, TAILLE);
        g.setLineWidth(old);
    }

    /**
     * @Description On dessine les objets artefacts d'une zone
     * @param g là ou on dessine
     * @param c la couleur
     * @param x position x
     * @param y position y
     */
    public void setArtefactToZone(GraphicsContext g, Zone c, int x, int y){
        Artefacts a = c.getArtefacts();
        if(!a.equals(Artefacts.none)){
            Image image = a.getImage();
            ImageView img =  new ImageView(image);
            g.drawImage(image,c.getPosition().x*TAILLE+TAILLE-20,c.getPosition().y*TAILLE+TAILLE -25);
        }

    }

    /**
     * @Description On gère le clique ici sur la grille,  pour retirer l'eau ds'une case
     * @param mouseEvent l'évenement cliqué dans ce ca
     */
    public void handleOnMouseClicked(MouseEvent mouseEvent) {
        Player p = modele.getRoundOf();
        int x = (int) mouseEvent.getX()/TAILLE; // pos x dans la grille
        int y = (int) mouseEvent.getY()/TAILLE;

        Zone z = modele.getZone(x, y);

        ArrayList<Zone> listZones = p.zonesDrainable();
        if(p.isReachable(listZones,z) && p.canAct() && z.isFlooded()){ // on fait le drain water ici
            p.drainWaterZone(modele.getZone(x, y));
            p.addAction();
        }
        else
            System.out.println("Mouvement interdit");

        modele.notifyObservers();
    }

    /**
     * @Description set le modele
     */
    public void setModel(Island modele){
        this.modele = modele;
        modele.addObserver(this);
        ArrayList<Player> liste = modele.getListPlayers();
        initPionsDraggable(liste);
        initTresorCardDisplay(liste);
        this.update();

    }

    /**
     * @Description on ajoute le pion ici et on ititialise les pions des joueurs ici
     * @param liste c'est la liste des joueurs en,tre 0 et 2
     */
    public void initPionsDraggable(ArrayList<Player> liste){
        for (int i = 0; i < liste.size(); i++) {
            PionsDraggable node = new PionsDraggable(liste.get(i), modele);
            node.setPrefSize(TAILLE/2, TAILLE/2);
            Image image = new Image(liste.get(i).getImage().toExternalForm(),100,100,true,true);
            ImageView img =  new ImageView(image);
            img.setX(-30);
            node.getChildren().addAll( img);
            Position pos = liste.get(i).getZone().getPosition();
            node.setModel(this.modele);
            node.setLayoutX(pos.x*TAILLE);
            node.setLayoutY(pos.y*TAILLE);

            arrayPion.add(node);
            anch.getChildren().add(node);
        }
    }

    /**
     * @Description on ajoute le cardsDraggable ici
     * @param liste c'est la liste des joueurs en,tre 0 et 2
     */
    public void initTresorCardDisplay(ArrayList<Player> liste){
        for(int i = 0; i < modele.getListPlayers().size(); i++){
            Player p = modele.getListPlayers().get(i);
            for(int k = 0; k<5 ;k++)
                p.setCard(TresorCard.empty);

            for(int j = 0; j<p.getCards().size(); j++){
                TresorCard card = p.getCards().get(j);
                Color c = modele.getRoundOf().getColor();
                CardDraggable node = new CardDraggable(p, modele, card);
                Shape circle = new Circle(12,12,12);
                node.setPrefSize(TAILLE/2, 8*TAILLE/10);
                node.setModel(this.modele);
                node.setColor(c);
                node.setCard(TresorCard.empty);
                node.setLayoutX(600+10+j*(60));
                node.setLayoutY(50+120*i);
                node.setSafeX(600+80+30+j*(50+5));
                node.setSafeY(35+125*i);
                node.setPlayer(p);
                arrayCards.add(node);
                anch.getChildren().add(node);
            }
            p.setPlayerCardsDragtgable(arrayCards);
            arrayCards = new ArrayList<>();


        }
    }

    /**
     * @Description getteur modele
     */
    public Island getModele(){
        return this.modele;
    }



    /**
     * @Description permet de se passer d'image en renvoyant un string utilisé" comme style  pour les pions
     */
    public static String colorToStylePion(Color c){
        return
                "-fx-background-color:"+toRGBCode(c) +";"
                + "-fx-border-radius: 50%; "
                + "-fx-border-color: black;";
    }

    /**
     * @Description permet de se passer d'image en renvoyant un string utilisé" comme style pour les cartes
     */
    public static String colorToStyleCard(String s){
        return "-fx-background-image: url(\"" + s + "\");";
    }
    /**
     * @Description transforme couleur rgb en hexa
     * @param color rgb
     */
    public static String toRGBCode( Color color ) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
