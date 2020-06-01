package Controller;

import Enumeration.Etat;
import Enumeration.TresorCard;
import IleInterdite.*;
import IleInterdite.Observer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class CVueIsland implements Initializable, Observer {



    @FXML
    public Canvas canvas;

    private static final String IMAGE_LOC = "http://icons.iconarchive.com/icons/uiconstock/flat-halloween/128/Halloween-Bat-icon.png";
    private static final String IMAGE_LOC1 = "https://as1.ftcdn.net/jpg/02/12/43/28/500_F_212432820_Zf6CaVMwOXFIylDOEDqNqzURaYa7CHHc.jpg";
    final Image image = new Image(IMAGE_LOC);
    final Image image1 = new Image(IMAGE_LOC1);
    public AnchorPane anch;

    private ArrayList<PionsDraggable> arrayPion = new ArrayList<>();
    private ArrayList<CardDraggable> arrayCards = new ArrayList<>();

    public static int TAILLE = 100;
    private GraphicsContext gcF;
    private Island modele;

    GraphicsContext gc1;
    double orgSceneX, orgSceneY , orgTranslateX , orgTranslateY;
    final int numNodes   =  4; // nombre de Joueurs


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void repaint() {
        /** Pour chaque cellule... */

        gcF = this.canvas.getGraphicsContext2D();


        for(int i = 0; i< Island.LARGEUR; i++) {
            for(int j = 0; j< Island.HAUTEUR; j++) {
                /**
                 * ... Appeler une fonction d'affichage auxiliaire.
                 * On lui fournit les informations de dessin [g] et les
                 * coordonnées du coin en haut à gauche.
                 */
                paintZone(gcF, modele.getZone(i, j), (i)*TAILLE, (j)*TAILLE);
            }
        }

        // on dessine les cases autour
        Player p = modele.getRoundOf();

        ArrayList<Zone> listZones = p.zonesReachable();

        for(Zone z: listZones){
            Position pos = z.getPosition();
            paintSafeZone(gcF, Color.AQUA, pos.x*TAILLE, pos.y*TAILLE);
        }

        translatePionPlayer();



    }

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

    private void paintZone(GraphicsContext g, Zone c, int x, int y) {
        /** Sélection d'une couleur. TODO: Fonction qui renvoie couleur */

        if (c.getEtat() == Etat.none) {
            g.setFill(Color.DARKGRAY);
        } else if (c.getEtat() == Etat.normale){
            g.setFill(Color.GREEN);
        } else if (c.getEtat() == Etat.inondee){
            g.setFill(Color.CYAN);
        } else{
            g.setFill(Color.BLUE);
        }
        /** Coloration d'un rectangle. */
        g.fillRect(x, y, TAILLE, TAILLE);
    }

    private void paintPlayer(GraphicsContext g, Color c, int x, int y) {
        /** Sélection d'une couleur. */
        g.setFill(c);
        /** Coloration d'un rectangle. */
        g.fillOval(x, y, TAILLE/2, TAILLE/2);

    }

    private void paintSafeZone(GraphicsContext g, Color c, int x, int y) {
        /** Sélection d'une couleur. */
        double old = g.getLineWidth();
        g.setLineWidth(4.0);
        g.setFill(c);
        /** Coloration d'un rectangle. */
        g.strokeRect(x, y, TAILLE, TAILLE);
        g.setLineWidth(old);
    }

    public void handleOnMouseClicked(MouseEvent mouseEvent) {
        Player p = modele.getRoundOf();
        int x = (int) mouseEvent.getX()/TAILLE;
        int y = (int) mouseEvent.getY()/TAILLE;
        System.out.println("CvueIsland click");

        System.out.println("pos x : " + x + "pos y : " + y);
        Zone z = modele.getZone(x, y);
        //Todo: Can be done on isReachable
        ArrayList<Zone> listZones = p.zonesReachable();
        if(p.isReachable(listZones,z) && p.canAct() && z.isFlooded()){ // on fait le drain water ici
            p.drainWaterZone(modele.getZone(x, y));
            p.addAction();
        }
        else
            System.out.println("Mouvement interdit");

        modele.notifyObservers();
    }


    public void setModel(Island modele){
        this.modele = modele;
        modele.addObserver(this); //l'instance de GRILLE existe avnat le reste, on set le modèle après

        ArrayList<Player> liste = modele.getListPlayers();

        for (int i = 0; i < numNodes; i++) {
            PionsDraggable node = new PionsDraggable(liste.get(i), modele);
            node.setPrefSize(TAILLE/2, TAILLE/2);
            node.setStyle(colorToStylePion(liste.get(i).getColor()));

            // TODO gérer les images ici
            /*ImageView iv1 = new ImageView(new Image("http://icons.iconarchive.com/icons/kidaubis-design/cool-heroes/128/Ironman-icon.png"));
            iv1.relocate(0, 0);
            node.getChildren().addAll(iv1);
            */

            //node.setLayoutX(spacing*(i+1) + node.getPrefWidth()*i);
            //node.setLayoutY(spacing);
            Color c = liste.get(i).getColor();
            Position pos = liste.get(i).getZone().getPosition();
            System.out.println(c.toString());
            node.setModel(this.modele);
            node.setColor(c);

            node.setLayoutX(pos.x*TAILLE);
            node.setLayoutY(pos.y*TAILLE);

            arrayPion.add(node);
            anch.getChildren().add(node);
        }

        ArrayList<TresorCard> listecard = modele.getRoundOf().getCards();

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
                node.setStyle(colorToStyleCard(Color.GRAY));
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

        System.out.println("grille modele: "+ arrayPion.size());
        System.out.println("grille modele: "+ arrayCards.size());


        System.out.println("grille modele: "+ modele);
        this.update(); //TODO : c'est un choix ? ou pas ?

    }

    public Island getModele(){
        return this.modele;
    }

    @Override
    public void update() {
        repaint();
    }


    public static String colorToStylePion(Color c){
        return
                "-fx-background-color:"+toRGBCode(c) +";"
                + "-fx-border-radius: 50%; "
                + "-fx-border-color: black;";
    }

    public static String colorToStyleCard(Color c){
        return "-fx-background-color:"+toRGBCode(c) +"; "
                + "-fx-text-fill: black; "
                + "-fx-border-color: black;";
    }

    public static String toRGBCode( Color color ) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
