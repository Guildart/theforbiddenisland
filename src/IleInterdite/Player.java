package IleInterdite;

import Card.Card;
import Enumeration.SpecialZone;
import Enumeration.Etat;
import Enumeration.TresorCard;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player{

    private Zone zone;
    private Color color;
    private ArrayList<Card<TresorCard>> playerCards = new ArrayList<>(); //Todo : Instancier un tas de carte
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
    public void getArtefact( SpecialZone art){

    }

    /**
     * change la quantite de clé d'un joueur
     **/

    /**
     * rechercher une clé avec proba de 0.5 d'en trouver
     **/
    public void searchKey(ArrayList<Card<TresorCard>> tas, ArrayList<Card<TresorCard>> defausse){
        if(tas.size() == 0){
            Collections.shuffle(defausse);
            tas.addAll(defausse);
            defausse.clear();
        }

        Card<TresorCard> card = tas.get(0);

        if(card.getType() == TresorCard.water) {
            //card.doAction(this.zone); //Todo : à implemnter, pas besoin d'attribut normalement
            defausse.add(card);
            tas.remove(card);
        }else{
            this.playerCards.add(card);
            System.out.println(card.getType().toString());
            tas.remove(card);
        }

        if(this.playerCards.size() > 6){
            card = playerCards.get(0);
            defausse.add(card);
            playerCards.remove(card);
        }
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

    public ArrayList<Card<TresorCard>> getCards(){
        return this.playerCards;
    }

}
