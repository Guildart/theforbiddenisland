package IleInterdite;

import Controller.CardDraggable;
import Enumeration.Artefacts;
import Enumeration.Etat;
import Enumeration.TresorCard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;

public class Player{

    protected Zone zone;
    protected Color color;
    protected ArrayList<TresorCard> playerCards = new ArrayList<>(); //Todo : Instancier un tas de carte
    protected ArrayList<CardDraggable> playerCardsDragtgable = new ArrayList<>(); //Todo : Instancier un tas de carte
    protected static int nbActionsRestant;
    protected Island modele;
    URL image ; //= new ImageView(new Image("http://icons.iconarchive.com/icons/kidaubis-design/cool-heroes/128/Ironman-icon.png"));

    public Player(Zone zone, URL image, Island modele){
        this.zone = zone;
        this.image = image;
        this.modele = modele;
    }

    public URL getImage(){
        return this.image;
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
            this.playerCards.add(0,card);
            System.out.println(card.toString());
            tas.remove(card);
        }

        /*if(this.playerCards.size() > 8){
            card = playerCards.get(0);
            defausse.add(card);
            playerCards.remove(card);
        }*/
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
    public void setCard(TresorCard c){
        playerCards.add(0,c);
    }

    public void discardCard(ArrayList<TresorCard> toDiscard){
        this.getCards().removeAll(toDiscard);
    }


    public ArrayList<CardDraggable> getPlayerCardsDragtgable() {
        return playerCardsDragtgable;
    }

    public void setPlayerCardsDragtgable(ArrayList<CardDraggable> playerCardsDragtgable) {
        this.playerCardsDragtgable = playerCardsDragtgable;
    }

    public void removeCard(TresorCard card){
        playerCards.remove(card);
    }

    public int nombreCarte(){
        int compteur = 0;
        for(TresorCard card : this.playerCards)
            if(card != TresorCard.empty)
                compteur++;
        return compteur;
    }

    /**
     * @return une liste de zone
     */
    public ArrayList<Zone> zonesSafeToMove(){
        Position pos = zone.getPosition();
        ArrayList<Zone> zonesSafe = modele.getZoneArround(this.zone);
        zonesSafe.remove(this.zone);
        return  zonesSafe;
    }


    public ArrayList<Zone> zonesDrainable(){
        ArrayList<Zone> zones = modele.getZoneArround(this.zone);
        zones.add(this.zone);
        return zones;
    }

    public boolean isReachable(ArrayList<Zone> listZone, Zone zp){
        for( Zone z : listZone){
            if(z.getPosition().equals(zp.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public void giveCard(TresorCard card, Player player){
        if(player.getZone() == this.zone) {
            this.removeCard(card);
            modele.addToDefausseCarteTresor(card);
            this.addAction();
            player.setCard(card);
        }
    }
}
