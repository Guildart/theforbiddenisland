package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    public final Random randomGen = new Random();

    private Zone zone;
    private Color color;
    private Key[] artefactsCles = {Key.air, Key.eau, Key.feu, Key.vent};
    private static int nbActionsRestant;

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
        z.setEtat(Etat.normale); //TODO: Vérifier si la zone est bien innondée et rien d'autres ? Normalment verifier avant utilisation donc est ce nécessaire ?
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
        System.out.println(k);
        System.out.println(quantite);
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
        if(rd < 100)
            setQuantiteKey(artefactsCles[randomGen.nextInt(4)],1);
    }

    public Color getColor(){return color;}

    public Zone getZone(){
        return this.zone;
    }

    public void addAction(){
        if(nbActionsRestant <3){
            nbActionsRestant +=1;
        }
        else{
            System.out.println("PLUS DE DEPLACEMENT POSSIBLE");
        }
    }

    public boolean canAct(){
        return nbActionsRestant <3;
    }

    public void resetNbActionRestant(){
        nbActionsRestant = 0;
    }

}
