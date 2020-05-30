package Controller;

import IleInterdite.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class vuePion implements Initializable, Observer {


    public Canvas pion;
    private Island modele;



    private Player playerPion;
    private int TAILLE = 100;
    private Color c;
    GraphicsContext gP;

    double orgSceneX, orgSceneY , orgTranslateX , orgTranslateY;

    public void setP(Player p) {
        this.playerPion = p;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // pion.setTranslateX(-50); // on translate en x = 0 de la grille
        //pion.setTranslateY(-50);// on translate en  y = 0 de la grille

        gP = pion.getGraphicsContext2D();
        pion.setOnMousePressed(canvasOnMousePressedEventHandler);
        pion.setOnMouseDragged(canvasOnMouseDraggedEventHandler);
        pion.setOnMouseDragged(canvasOnMouseDraggedEventHandler);

    }

    public void translate(int x, int y){
        pion.setTranslateX(x); // on translate en x = 0 de la grille
        pion.setTranslateY(y);// on translate en  y = 0 de la grille

    }

    public void setColor(Color color){
        c = color;
    }

    @Override
    public void update() {
        repaint();
    }

    public void repaint(){
        gP.setFill(c);
        gP.fillRect(0, 0, pion.getWidth(),pion.getHeight());
    }

    private void paintPlayer(GraphicsContext g, Color c, int x, int y) {


    }

    public void handleOnMouseClicked(MouseEvent mouseEvent) {
        if(modele.getRoundOf().equals(playerPion)){
            Player p = modele.getRoundOf();
            int x = (int) mouseEvent.getSceneX() /TAILLE;
            int y = (int) mouseEvent.getSceneY()/TAILLE;
            System.out.println("pos x : " + x + "pos y : " + y);
            System.out.println("vuePion click");

            Zone z = modele.getZone(x, y);

            ArrayList<Zone> listZones = modele.zonesReachable(modele.getRoundOf().getZone());
            if(modele.isReachable(listZones, z) && p.canAct() && modele.getTypeAction() == 0){
                p.movePlayer(modele.getZone(x, y));
                p.addAction();
                this.translate( (int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY());

            } else if(modele.isReachable(listZones, z) && p.canAct() && modele.getTypeAction() == 1){
                p.drainWaterZone(modele.getZone(x, y));
                p.addAction();
            }
            else
                System.out.println("Mouvement interdit");
            modele.notifyObservers();
        }



    }

    @FXML
    public void handleOnMouseMoved(MouseEvent mouseEvent) {


    }



    public void setModel(Island model){
        this.modele = model;
        this.modele.addObserver(this);
        this.update();
    }
    public void handleOnMousePressed(MouseEvent mouseEvent) {

    }


    EventHandler<MouseEvent> canvasOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if(modele.getRoundOf().equals(playerPion))
                    {
                        System.out.println("pressed");

                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();
                        orgTranslateX = ((Canvas)(t.getSource())).getTranslateX();
                        orgTranslateY = ((Canvas)(t.getSource())).getTranslateY();
                        update();
                    }

                }
            };

    EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {


                @Override
                public void handle(MouseEvent t) {
                    if(modele.getRoundOf().equals(playerPion) ){
                        System.out.println("dragged"+t.getSceneX() +" "+  t.getSceneY());
                        System.out.println("dragged"+t.getX() +" "+  t.getY());
                        double offsetX = t.getSceneX() - orgSceneX;
                        double offsetY = t.getSceneY() - orgSceneY;
                        double newTranslateX = orgTranslateX + offsetX;
                        double newTranslateY = orgTranslateY + offsetY;

                        ((Canvas)(t.getSource())).setTranslateX(newTranslateX);
                        ((Canvas)(t.getSource())).setTranslateY(newTranslateY);
                        update();

                    }


                }
            };



}
