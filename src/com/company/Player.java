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
     * récuperer une clée donnée par un joueur
     **/
    private void addKey(){

    }

    /**
     * rechercher une clé
     **/
    public void searchKery(){

    }


}
