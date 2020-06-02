package IleInterdite;

import Controller.CardDraggable;
import Enumeration.Artefacts;
import Enumeration.Etat;
import Enumeration.TresorCard;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;

public class Player{

    protected Zone zone;
    protected ArrayList<TresorCard> playerCards = new ArrayList<>();
    protected ArrayList<CardDraggable> playerCardsDragtgable = new ArrayList<>();
    public static int nbActionsRestant;
    protected Island modele;
    URL image ;

    public Player(Zone zone, URL image, Island modele){
        this.zone = zone;
        this.image = image;
        this.modele = modele;
    }

    /**
     * Deplace le joueur
     * @param z la zone ou le joueur
     */
    public void movePlayer(Zone z){
        this.zone = z;
    }

    /**
     * Depalce le joeur
     * @param z la zone a assécher
     */
    public void drainWaterZone(Zone z){
        z.setEtat(Etat.normale);
    }

    /**
     * Prendre un artefact de l'ile
     */
    public void takeArtefact(){
        int compteur = 0;
        Artefacts artefacts = zone.getArtefacts();
        for(TresorCard t : playerCards)
            if(t.getArtefactsAssociated() == artefacts && artefacts != Artefacts.none)
                compteur++;
        if(compteur >= 4){
            zone.setArtefacts(Artefacts.none);
            modele.getListArtefacts().add(artefacts);
            for (int i = 0; i < 4; i++)
                this.playerCards.remove(artefacts);
        }else{
            System.out.println("Not allow here !");
        }
    }

    /**
     *
     * @param tas le tas de carte tresors du modele
     * @param defausse defausse carte tresor du modele
     * @param island modele
     */
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

        /**Situation où il est facile de prendre un artefact feu**/
        /*this.playerCards.add(0,TresorCard.clef_feu);
        this.playerCards.add(0,TresorCard.clef_feu);
        this.playerCards.add(0,TresorCard.clef_feu);
        this.playerCards.add(0,TresorCard.clef_feu);*/
    }


    /**
     * Rajoute une action au compte d'action du joueur
     */
    public void addAction(){
        if(nbActionsRestant <3){
            nbActionsRestant +=1;
        }
        else{
            System.out.println("PLUS DE DEPLACEMENT POSSIBLE");
        }
    }

    /**Regarde si le joueur peut agir**/
    public boolean canAct(){
        return nbActionsRestant <3;
    }

    /**Réinitialise le compte action pour le prochain joueur**/
    public void resetNbActionRestant(){
        nbActionsRestant = 0;
    }


    /**
     * Gestion de l'action de defausser son surplus de cartes au debut
     * de chque tour car selon les règles officiel un joueur ne peut
     * voir plus de 5 cartes dans sa mains
     * @param toDiscard Liste des indices des cartes à defausser dans la main du joueur
     */
    public void discardCard(ArrayList<Integer> toDiscard){
        ArrayList<TresorCard> toRemove = new ArrayList();
        for(int i : toDiscard){
            toRemove.add(playerCards.get(i));
        }

        for(int i = 0; i < toRemove.size(); i++){
            defausseCard(toRemove.get(i));
        }

        modele.getDefausseTresorCard().addAll(toRemove);
    }


    /**
     * Action de defausser une carte
     * @param card carte à defausser
     */
    public void defausseCard(TresorCard card){
        if(this.removeCard(card))
            modele.addToDefausseCarteTresor(card);
    }

    /**
     * Supprime une carte de la main du joueur
     * @param card card à supprimer
     * @return true si une carte est supprimer false si non
     */
    public boolean removeCard(TresorCard card){
        for(int i = 0; i < this.playerCards.size(); i++){
            if(card == playerCards.get(i)){
                playerCards.remove(i);
                return  true;
            }
        }
        return false;
    }

    /**Renvoie le nombre de vrai carte donc sans les cartes empty
     * ces cartes sont necessaire pour le drag and drop des cartes
     * @return
     */
    public int nombreCarte(){
        int compteur = 0;
        for(TresorCard card : this.playerCards)
            if(card != TresorCard.empty)
                compteur++;
        return compteur;
    }

    /**
     * @return une liste de zone sure où le joueur peut se deplacer
     */
    public ArrayList<Zone> zonesSafeToMove(){
        Position pos = zone.getPosition();
        ArrayList<Zone> zonesSafe = modele.getSafeZoneArround(this.zone);
        zonesSafe.remove(this.zone);
        return  zonesSafe;
    }


    /**
     * @return une liste de zone innondé que le joueur peut assecher
     */
    public ArrayList<Zone> zonesDrainable(){
        ArrayList<Zone> zones = modele.getSafeZoneArround(this.zone);
        if(this.zone.isSafe())
            zones.add(this.zone);
        return zones;
    }


    /**
     * Action de donner une carte à un autre joueurs
     * @param card carte à donner
     * @param player joueur qui reçoit la carte
     */
    public void giveCard(TresorCard card, Player player){
        if(player.getZone() == this.zone) {
            this.removeCard(card);
            this.addAction();
            player.setCard(card);
        }
    }


/********* Setter Et Getter ********/


    /**
     * Gettter renvoyant le chemin d'accès à l'image representant le joueur
     * @return
     */
    public URL getImage(){
        return this.image;
    }

    /**
     * @return la zone où se trouve le joueurs
     */
    public Zone getZone(){
        return this.zone;
    }


    /**
     * Getter
     * @return ArrayList des cartes du joueurs
     */
    public ArrayList<TresorCard> getCards(){
        return this.playerCards;
    }

    /**
     * Setter
     * @param c carte a rajouter à la main du joueur
     */
    public void setCard(TresorCard c){
        playerCards.add(0,c);
    }


    /**Renvoie les cartes draggable, soit un noeaud de notre graphisme
     * qui rend le Drag and Drop possible
     * @return playerCardsDraggable
     */
    public ArrayList<CardDraggable> getPlayerCardsDragtgable() {
        return playerCardsDragtgable;
    }

    /**
     * Setter
     * @param playerCardsDragtgable l'array qui sera affecter à l'attribut du joueur
     */
    public void setPlayerCardsDragtgable(ArrayList<CardDraggable> playerCardsDragtgable) {
        this.playerCardsDragtgable = playerCardsDragtgable;
    }

    /**
     * Renvoie le role sous forme de chaine de caractère
     * @return
     */
    public String toString(){
        return "Player";
    }

}
