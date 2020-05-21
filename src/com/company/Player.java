package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    public final Random randomGen = new Random();

    private Zone zone;
    private Color color;
    private Key[] artefactsCles = {Key.air, Key.eau, Key.feu, Key.vent};

    public Player(Zone zone, Color color){
        this.zone = zone;
        this.color = color;
    }
    /**
    * Deplacer le joueur
    **/
    public void movePlayer(Zone z){
        this.zone = z;
    }

    /**
     * assecher zone zone
     **/
    public void drainWaterZone(Zone z){

    }

    /**
     * récuperer un Artefact
     **/
    public void getArtefact( Artefacts art){

    }

    /**
     * change la quantite de clé d'un joueur
     **/
    private void setQuantiteKey(Key k, int quantite){
        if(k == Key.air)
            this.artefactsCles[0].setQuantity(this.artefactsCles[0].getQuantity() + quantite);
        else if (k == Key.eau)
            this.artefactsCles[1].setQuantity(this.artefactsCles[1].getQuantity() + quantite);
        else if (k == Key.feu)
            this.artefactsCles[2].setQuantity(this.artefactsCles[2].getQuantity() + quantite);
       else
           this.artefactsCles[3].setQuantity(this.artefactsCles[3].getQuantity() + quantite);


    }

    /**
     * rechercher une clé avec proba de 0.5 d'en trouver
     **/
    public void searchKey(){
        float rd = randomGen.nextFloat();
        if(rd < 0.5)
            setQuantiteKey(artefactsCles[randomGen.nextInt(4)],1);
    }

    public Color getColor(){return color;}

    public Zone getZone(){
        return zone;
    }


}
