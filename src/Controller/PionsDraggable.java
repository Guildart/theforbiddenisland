package Controller;

import IleInterdite.Island;
import IleInterdite.Navigateur;
import IleInterdite.Player;
import IleInterdite.Zone;
import com.sun.javafx.image.BytePixelSetter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

class PionsDraggable extends Pane {

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
    private Player playerPion;
    private Color c;
    private Island modele;

    public PionsDraggable(Node view) {

        this.view = view;
        getChildren().add(view);
        init();
    }


    public void setColor(Color c) {
        this.c = c;
    }


    PionsDraggable(Player playerPion, Island modele){
        this.playerPion = playerPion;
        this.modele = modele;
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
                if(modele.getRoundOf().equals(playerPion) || modele.getRoundOf() instanceof Navigateur) {
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
            }
        });

        onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked");
                System.out.println(modele.getRoundOf() + " " + playerPion);

               if(modele.getRoundOf().equals(playerPion)){ // on set le joueur si c'est son tour
                    Player p = modele.getRoundOf();
                    int x = (int) event.getSceneX() /TAILLE;
                    int y = (int) event.getSceneY()/TAILLE;
                    System.out.println("pos x : " + x + "pos y : " + y);
                    System.out.println("vuePion click");
                    if (x>=0 && x<Island.LARGEUR && y>=0 && y<Island.HAUTEUR ){

                        Zone z = modele.getZone(x, y);

                        ArrayList<Zone> listZones = p.zonesReachable();

                        if(p.isReachable(listZones,z) && p.canAct() && z != playerPion.getZone()) {
                            p.movePlayer(modele.getZone(x, y));
                            p.addAction();
                            //this.translate( (int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY());
                            modele.notifyObservers();

                        }
                        else
                            System.out.println("Mouvement interdit");
                    }
                    else
                    {
                        System.out.println("Mouvement en dehors du tableau");

                    }
                   modele.notifyObservers();
               }
               else if(modele.getRoundOf() instanceof Navigateur){ // on déplace un autre joueur quand c'est au tour du navigateur
                   Player p = modele.getRoundOf();
                   int x = (int) event.getSceneX() /TAILLE;
                   int y = (int) event.getSceneY()/TAILLE;
                   if (x>=0 && x<Island.LARGEUR && y>=0 && y<Island.HAUTEUR ){ // on check qu'on est bien dans la grille
                       Zone z = modele.getZone(x, y);
                       ArrayList<Zone> listZones = Navigateur.zonesReachableNavigateur(modele, playerPion.getZone().getPosition());


                       if(playerPion.isReachable(listZones,z) && p.canAct() && z != playerPion.getZone()) {
                           playerPion.movePlayer(modele.getZone(x, y)); // on bouge l'autre joueur
                           p.addAction(); // on incremente l'action du navigateur

                           modele.notifyObservers();

                       }
                       else
                           System.out.println("Mouvement interdit");
                   }
                   else
                   {
                       System.out.println("Mouvement en dehors du tableau");

                   }
                   modele.notifyObservers();

               }

                modele.notifyObservers();

                dragging = false;
            }
        });


    }

    public void setPlayerPion(Player playerPion) {
        this.playerPion = playerPion;
    }


    public void setModele(Island modele) {
        this.modele = modele;
    }

    public PionsDraggable() {
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


}