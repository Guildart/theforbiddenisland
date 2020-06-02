package Controller;

import IleInterdite.Island;
import IleInterdite.Navigateur;
import IleInterdite.Player;
import IleInterdite.Zone;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

class PionsDraggable extends Pane {

    /**
     * Classe qui nous permet de drag des pions
     * */

    //  positions réelles
    protected double x = 0;
    protected double y = 0;

    //  positions de la souris
    protected double mousex = 0;
    protected double mousey = 0;

    //bool pour savoir si on fait bouger le pions
    protected boolean dragging = false;
    // second bool pour le mouvement du pion
    protected boolean moveToFront = true;
    // taille du pane
    protected int TAILLE = 100;

    //bool pour savoir si on a relaché le pion
    protected boolean released;

    //bool pour savoir si on a relaché le pion
    protected Player player;
    protected Color c;
    protected Island modele;




    PionsDraggable(Player player, Island modele){
        this.player = player;
        this.modele = modele;
        this.init();
        released = false;
    }

    private void init() {
        // on récupérer les positions au clic, on se sert d'une lambda expression
        onMousePressedProperty().set(event -> {

            // record the current mouse X and Y position on Node
            mousex = event.getSceneX();
            mousey = event.getSceneY();

            x = getLayoutX();
            y = getLayoutY();

            if (isMoveToFront()) {
                toFront();
            }
        });

        //Event Listener pour MouseDragged, on fait déplacer notre pion ici
        onMouseDraggedProperty().set(event -> {
            if(modele.getRoundOf().equals(player) || modele.getRoundOf() instanceof Navigateur) {
                // permettre ici qu'on puisse déplacer d'autre joueur quand on utilise le navigateur
                System.out.println(x+" "+y);

                // Get the exact moved X and Y
                System.out.println("draged");
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

                if(x>600 || y>600){
                    dragging=false;
                    modele.notifyObservers();

                }

                event.consume();
            }
        });
        //Event Listener à l'aide d'une lambda expression pour le clic, on fait déplacer notre pion ici

        onMouseClickedProperty().set(event -> {
            System.out.println("clicked");
            System.out.println(modele.getRoundOf() + " " + player);

            if (modele.getRoundOf().equals(player)) { // on set le joueur si c'est son tour
                Player p = modele.getRoundOf();
                int x = (int) event.getSceneX() / TAILLE;
                int y = (int) event.getSceneY() / TAILLE;
                System.out.println("pos x : " + x + "pos y : " + y);
                System.out.println("vuePion click");
                if (x >= 0 && x < Island.LARGEUR && y >= 0 && y < Island.HAUTEUR) {

                    Zone z = modele.getZone(x, y);

                    ArrayList<Zone> listZones = p.zonesSafeToMove();

                    if (p.isReachable(listZones, z) && p.canAct() && z != player.getZone()) {
                        p.movePlayer(modele.getZone(x, y));
                        p.addAction();
                        modele.notifyObservers();

                    } else
                        System.out.println("Mouvement interdit");
                } else {
                    System.out.println("Mouvement en dehors du tableau");

                }
                modele.notifyObservers();
            } else if (modele.getRoundOf() instanceof Navigateur) { // on déplace un autre joueur quand c'est au tour du navigateur
                Player p = modele.getRoundOf();
                int x = (int) event.getSceneX() / TAILLE;
                int y = (int) event.getSceneY() / TAILLE;
                if (x >= 0 && x < Island.LARGEUR && y >= 0 && y < Island.HAUTEUR) { // on check qu'on est bien dans la grille
                    Zone z = modele.getZone(x, y);
                    ArrayList<Zone> listZones = Navigateur.zonesReachableNavigateur(modele, player.getZone().getPosition());


                    if (player.isReachable(listZones, z) && p.canAct() && z != player.getZone()) {
                        player.movePlayer(modele.getZone(x, y)); // on bouge l'autre joueur
                        p.addAction(); // on incremente l'action du navigateur

                        modele.notifyObservers();

                    } else
                        System.out.println("Mouvement interdit");
                } else {
                    System.out.println("Mouvement en dehors du tableau");

                }
                modele.notifyObservers();

            }

            modele.notifyObservers();

            dragging = false;
        });


    }




    public void setModele(Island modele) {
        this.modele = modele;
    }


    /**
    * @Description test si on va dans une direction
    * */
    public boolean isMoveToFront() {
        return moveToFront;
    }

    /**
     * @Descripton, translate en x
     * @param x
     */
    public void setX(int x){
        this.x = x;
        setLayoutX(this.x);
    }

    /**
     * @Description, translate en Y
     * @param y
     */
    public void setY(int y){
        this.y = y;
        setLayoutY( this.y);
    }

    /**
     * @Description set le modele
     * @param modele
     */
    public void setModel(Island modele) {
        this.modele = modele;
    }
    /**
     * @Description set la couleurS
     * @param c la couleur
     */
    public void setColor(Color c) {
        this.c = c;
    }


}