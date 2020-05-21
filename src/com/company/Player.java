package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    Zone zone;
    Color color;
    Key[] artefactsCles = {Key.air, Key.eau, Key.feu, Key.vent};

    public Player(Zone zone, Color color){
        zone = zone;
        color = color;
    }
    /**
    * Deplacer le joueur
    **/
    public void movePlayer(Zone z){
        zone = z;
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
     * rechercher une clé
     **/
    public void searchKery(){

    }


}
