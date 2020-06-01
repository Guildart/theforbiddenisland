package IleInterdite;

import Enumeration.Artefacts;
import Enumeration.Etat;
import Enumeration.TresorCard;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Player{

    private Zone zone;
    private Color color;
    private ArrayList<TresorCard> playerCards = new ArrayList<>(); //Todo : Instancier un tas de carte
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
    public void takeArtefact(ArrayList<Artefacts> listArtefacts){
        int compteur = 0;
        Artefacts artefacts = zone.getArtefacts();
        for(TresorCard t : playerCards)
            if(t.getArtefactsAssociated() == artefacts && artefacts != Artefacts.none)
                compteur++;
        if(compteur >= 4){
            zone.setArtefacts(Artefacts.none);
            listArtefacts.add(artefacts);
            for (int i = 0; i < 4; i++)
                this.playerCards.remove(artefacts);
        }else{
            System.out.println("Not allow here !");
        }
    }

    /**
     * change la quantite de clé d'un joueur
     **/

    /**
     * rechercher une clé avec proba de 0.5 d'en trouver
     **/
    public void searchKey(ArrayList<TresorCard> tas, ArrayList<TresorCard> defausse, Island island){
        if(tas.size() == 0){
            Collections.shuffle(defausse);
            tas.addAll(defausse);
            defausse.clear();
        }

        TresorCard card = tas.get(0);

        if(card == TresorCard.rising_water) {
            System.out.println(card.toString());
            island.risingWater();
            defausse.add(card);
            tas.remove(card);
        }else{
            this.playerCards.add(card);
            System.out.println(card.toString());
            tas.remove(card);
        }

        if(this.playerCards.size() > 8){
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

    public ArrayList<TresorCard> getCards(){
        return this.playerCards;
    }

    public void discardCard(ArrayList<TresorCard> toDiscard){
        this.getCards().removeAll(toDiscard);
    }

}
