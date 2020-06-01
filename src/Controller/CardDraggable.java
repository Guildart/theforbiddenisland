package Controller;

import Enumeration.Etat;
import Enumeration.TresorCard;
import IleInterdite.Island;
import IleInterdite.Player;
import IleInterdite.Position;
import IleInterdite.Zone;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CardDraggable extends Pane {

    // node position
    private double x = 0;
    private double y = 0;
    // mouse position
    private double mousex = 0;
    private double mousey = 0;
    private Node view;
    private boolean dragging = false;
    private boolean moveToFront = true;
    private int TAILLE = 100;

    public boolean released;
    private Player player;



    private Color c;
    private Island modele;
    private TresorCard card;


    // origine position
    private double safeX = 0;



    private double safeY = 0;



    public CardDraggable(Node view) {

        this.view = view;
        getChildren().add(view);
        init();
    }

    public void setSafeX(double safeX) {
        this.safeX = safeX;

    }

    public void setSafeY(double safeY) {
        this.safeY = safeY;

    }

    public void translateSafeX() {
        setLayoutX(safeX);
    }

    public void translateSafeY() {
        setLayoutY(safeY);
    }


    public void setColor(Color c) {
        this.c = c;
    }

    public Color getColor() {
        return c;
    }

    CardDraggable(Player player, Island modele, TresorCard card){
        this.player = player;
        this.modele = modele;
        this.card = card;
        this.init();
        released = false;
    }

    private void init() {

        onMousePressedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                // record the current mouse X and Y position on Node
                mousex = event.getSceneX();
                mousey = event.getSceneY();

                x = getLayoutX();
                y = getLayoutY();

                if (isMoveToFront()) {
                    toFront();
                }
            }
        });

        //Event Listener for MouseDragged
        onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                    // Get the exact moved X and Y
                //System.out.println(x+" "+y);
                    double offsetX = event.getSceneX() - mousex;
                    double offsetY = event.getSceneY() - mousey;


                    x += offsetX;
                    y += offsetY;

                    double scaledX = x;
                    double scaledY = y;

                    setLayoutX(scaledX);
                    setLayoutY(scaledY);

                    dragging = true;

                    // again set current Mouse x AND y position
                    mousex = event.getSceneX();
                    mousey = event.getSceneY();

                    //test(mousex, mousey);

                    event.consume();
                }

        });

        onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked");

                if (x<600){
                    System.out.println("grille");
                    System.out.println("x"+ (int)x/CVueIsland.TAILLE + "y " + (int)y/CVueIsland.TAILLE);
                    // appeler fonction pour soit assecher soit poser lhelicopter sur la case donne par la formule d'avant
                    Zone z = modele.getZone((int)x/CVueIsland.TAILLE, (int)y/CVueIsland.TAILLE);

                    if(card == TresorCard.sandbag){
                        if(z.getEtat()== Etat.inondee){
                            z.setEtat(Etat.normale);
                        }
                        player.removeCard(card);
                        modele.addToDefausseCarteTresor(card);

                    }
                    else if(card == TresorCard.helicopter){ // déplace le joueur ou on veut en glissant la carte sur une case de la grille
                        player.movePlayer(modele.getZone((int)x/CVueIsland.TAILLE,(int)y/CVueIsland.TAILLE));
                        player.removeCard(card);
                        modele.addToDefausseCarteTresor(card);
                    }

                }else
                {
                    if(y<480){
                        if(card != TresorCard.empty){

                            Player toPlayer = modele.getListPlayers().get((int)(y)/120);
                            System.out.println("panel numero "+ ((int)(y)/120));
                            player.removeCard(card);
                            modele.addToDefausseCarteTresor(card);
                            toPlayer.setCard(card);
                        }

                    }



                }

                modele.notifyObservers();

                dragging = false;

            }




        });

        onMouseDragReleasedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("released"+ " " + playerPion.getZone().toString());
                modele.notifyObservers();



                dragging = false;

            }




        });


    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void setModele(Island modele) {
        this.modele = modele;
    }

    public CardDraggable() {
        init();
    }


    protected boolean isDragging() {
        return dragging;
    }



    public Node getView() {
        return view;
    }

    public void setMoveToFront(boolean moveToFront) {
        this.moveToFront = moveToFront;
    }


    public boolean isMoveToFront() {
        return moveToFront;
    }

    public void removeNode(Node n) {
        getChildren().remove(n);
    }

    public void setX(int x){
        x = x;
        setLayoutX(x);
    }
    public void setY(int y){
        y = y;
        setLayoutY(y);
    }

    public void setModel(Island modele) {
        this.modele = modele;
    }

    /*public void test(float x1, float x2){
        if(modele.getRoundOf().equals(playerPion)){
            Player p = modele.getRoundOf();
            int x = (int) x1 /TAILLE;
            int y = (int) x2 /TAILLE;
            System.out.println("pos x : " + x + "pos y : " + y);
            System.out.println("vuePion click");

            Zone z = modele.getZone(x, y);

            ArrayList<Zone> listZones = modele.zonesReachable(modele.getRoundOf().getZone());
            if(modele.isReachable(listZones, z) && p.canAct() ){
                p.movePlayer(modele.getZone(x, y));
                p.addAction();
                //this.translate( (int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY());
                modele.notifyObservers();

            } else if(modele.isReachable(listZones, z) && p.canAct()){
                p.drainWaterZone(modele.getZone(x, y));
                p.addAction();
            }
            else
                System.out.println("Mouvement interdit");
            modele.notifyObservers();
        }
    }*/

    public TresorCard getCard() {
        return card;
    }

    public void setCard(TresorCard card) {
        this.card = card;
    }



}
