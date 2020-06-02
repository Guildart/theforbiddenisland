package IleInterdite;

import Controller.CVueDefausse;
import Controller.EndOfGame;
import Enumeration.Artefacts;
import Enumeration.Etat;
import Enumeration.TresorCard;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;

import java.util.ArrayList;
import java.util.Random;

/**
 * Le modèle Island : C'est dans cette classe que nous gérons le jeu
 */
public class Island extends Observable {

    public final Random randomGen = new Random();


    /** On fixe la taille de la grille. */
    public static final int HAUTEUR=6, LARGEUR=6;
    /** On stocke un tableau de cellules. */
    public Zone[][] zones;
    /** C'est un pointeur vers player qui a un tour **/
    private Player RoundOf;
    /*Liste des Players*/
    private final ArrayList<Player> listPlayers = new ArrayList<>();
    /*Liste des Artefacts*/
    private final ArrayList<Artefacts> listArtefacts = new ArrayList<>();
    /*Liste de la pioche*/
    private final ArrayList<TresorCard> tasCarteTresor = new ArrayList<>();
    /*Liste de la défausse*/
    private ArrayList<TresorCard> defausseCarteTresor = new ArrayList<>();
    /*Liste de la pioche Inondation*/
    private ArrayList<Zone> tasCarteInnondation = new ArrayList<>();
    /*Liste de la défausse inondation*/
    private ArrayList<Zone> defausseCarteInnondation = new ArrayList<>();
    /*Indicateur du niveau de la montée des eaux*/
    private int seaLevel = 1;

    /*Indicateur du nombre de carte à piocher*/
    private int numberCardToPick = 2;
    /*boolean qui nous permet de savoir qui la fin du jeu est actée, permettant d'afficher l'écran de fin*/
    private boolean endOfGame = false;
    /*boolean qui permet de tester la fin du jeu à l'aide d'une carte helicoptere*/
    private boolean testEndOfGame = false;

    /*Chemin de la fenêtre principal contenant les composant du jeu*/
    FXMLLoader loader ;
    /*Fenetre enêtre racine*/
    Stage stage;

    /** Construction : on initialise notre grille */
    public Island() {
        /**
         * On initialise la grille, on met tous les état à None
         *
         */
        zones = new Zone[LARGEUR][HAUTEUR];
        for(int i=0; i<LARGEUR; i++) {
            for(int j=0; j<HAUTEUR; j++) {
                zones[i][j] = new Zone(Etat.none, new Position(i,j), Artefacts.none);
            }
        }
        init();

    }

    /**
     * @Initialisation aléatoire de la grille
     * on calcule la croix de la grille
     */

    public void init() {

        initTasCarteInnondation();
        initTasCarteTresor();

        for(int j=0; j<=HAUTEUR; j++) { // on calcule les coordonées en augmentant et diminuant les i et j
            int j_p;
            if (j >= HAUTEUR/2)
                j_p = HAUTEUR -1 - j;
            else
                j_p = j;
            for(int i= LARGEUR/2   - j_p%(HAUTEUR/2) - 1; i<=LARGEUR/2 + j_p%(HAUTEUR/2) ; i++) {
                Zone z = tasCarteInnondation.get(0);
                z.setPosition(new Position(i,j));
                zones[i][j] = z;
                defausseCarteInnondation.add(z);
                tasCarteInnondation.remove(z);
            }
        }

        /***On retransfert toutes les cartes**/
        Collections.shuffle(defausseCarteInnondation); //on melange avant
        tasCarteInnondation.addAll(defausseCarteInnondation);
        defausseCarteInnondation.clear();


    }
    /**
    * @Description On initialise le tas Inondation
     */
    private void initTasCarteInnondation(){
        tasCarteInnondation.add(new Zone(Etat.normale, new Position(0,0), Artefacts.none,true));
        for(int i = 0; i < 2; i++) {
            tasCarteInnondation.add(new Zone(Etat.normale, new Position(0,0), Artefacts.feu));
            tasCarteInnondation.add(new Zone(Etat.normale, new Position(0,0), Artefacts.eau));
            tasCarteInnondation.add(new Zone(Etat.normale, new Position(0,0), Artefacts.terre));
            tasCarteInnondation.add(new Zone(Etat.normale, new Position(0,0), Artefacts.air));
        }

        for(int i = 0; i < 15; i++){
            tasCarteInnondation.add(new Zone(Etat.normale, new Position(0,0), Artefacts.none));
        }
        Collections.shuffle(tasCarteInnondation); //Pour mélanger
    }

    /**
     * @Description active le test de la fin du jeu à l'aide d'une carte helicoptere,
     * méthode utilisé par un controller.
     */
    public void enableTestEndOfGame(){
        testEndOfGame=true;
    }

    /**
     * @Description On initialise le tas Inondation CarteTresor
     */
    private void initTasCarteTresor(){

        //3 * Chaque Cartes spéciale
        for(int i = 0; i < 3; i++){
            tasCarteTresor.add(TresorCard.helicopter);
            tasCarteTresor.add(TresorCard.sandbag);
            tasCarteTresor.add(TresorCard.rising_water);
        }

        //5 * Clé pour chaque artefacts
        for(int i = 0; i < 5; i++){
            tasCarteTresor.add(TresorCard.clef_air);
            tasCarteTresor.add(TresorCard.clef_eau);
            tasCarteTresor.add(TresorCard.clef_feu);
            tasCarteTresor.add(TresorCard.clef_terre);
        }
        Collections.shuffle(tasCarteTresor);
    }

    private void addPlayer(URL c){
        int[] tab = getRandomPoint();
        Player p = new Player(zones[tab[0]][tab[1]],c, this);
        this.listPlayers.add(p);
    }


    /**
    * @Description qui renvoie les coordonnées dans un tab
    * de trois zones à modifier se situant dans la croix
    */
    public int[] getRandomPoint(){
        int[] tab= new int[2];
        int j = randomGen.nextInt(HAUTEUR);
        int j_p;
        if (j >= HAUTEUR/2)
            j_p = HAUTEUR -1 - j;
        else
            j_p = j;

        int indic = LARGEUR/2 + j_p%(HAUTEUR/2) + 1 - (LARGEUR/2   - j_p%(HAUTEUR/2) - 1);
        int i = LARGEUR/2   - j_p%(HAUTEUR/2) - 1 + randomGen.nextInt( indic);
        tab[0] = i;
        tab[1] = j;
        return tab;
    }

    /**
     * @Description est appellée par un controlleur pour changer de round
     * On procède en deux étapes.
     *  - D'abord, pour chaque cellule on évalue ce que sera son état à la
     *    prochaine génération.
     *  - Ensuite, on applique les évolutions qui ont été calculées.
     */
    public void nextRound() {


        for(int i = 0; i < numberCardToPick; i++){ // on pioche un certain nombre de carte
            if(tasCarteInnondation.size() + defausseCarteInnondation.size() > 0) {
                if (tasCarteInnondation.size() == 0) { //si tas vide on remet la defausse dans le tas
                    Collections.shuffle(defausseCarteInnondation); //on melange avant
                    tasCarteInnondation.addAll(defausseCarteInnondation);
                    defausseCarteInnondation.clear();
                }
                Zone z = tasCarteInnondation.get(0); // on pioche la carte ( mise à jour de la zone )
                Etat etat = z.getEtat();
                z.setEtat(Etat.nextEtat(etat));
                if (z.getEtat() != Etat.submergee)
                    defausseCarteInnondation.add(z); // defausse la carte
                tasCarteInnondation.remove(z); // on retire la carte
            }
        }

        displayLose(); // appelle cette méthode qui va tester l'état de défaite
        // l'état de victoire est testé par un controlleur


        RoundOf.searchKey(this.tasCarteTresor, this.defausseCarteTresor, this);

        //mise à jour du prochain joueur
        ArrayList<Player> players = this.listPlayers;
        this.setRoundOf(players.get( (players.indexOf(this.getRoundOf())+1)%players.size()));
        notifyObservers();
    }

    /**
     * @Description: getteur endOfGame
     */
    public boolean getEndOfGame(){
        return this.endOfGame;
    }

    /**
     * @Description: Set le loader
     */
    public void setLoader(FXMLLoader loader){
        this.loader = loader;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }


    /**
     * @Description Une méthode pour renvoyer la zobne aux coordonnées choisies (sera
     * utilisée par la vue).
     * @param x coordonées x
     * @param y coordonnées y
     * @return une Zone
     */
    public Zone getZone(int x, int y) {
        return zones[x][y];
    }

    /**
     * @Description teste l'état dee défaire selon  Lose()
     * si on perd, alors on affiche un pop up qui renvoie au menu principal.
     */
    public void displayLose(){
        if(this.Lose()){ // on teste la défait
            notifyObservers();
            EndOfGame dv = new EndOfGame();
            dv.display(this, "perdu", loader,stage);
            endOfGame=true; // on set à true ici
        }
    }

    /**
     * @Description teste l'état de victoire selon Win()
     * si on perd, alors on affiche un pop up qui renvoie au menu principal.
     */
    public void displayWin(){
         if(this.Win()) { // on teste la victoire
             notifyObservers();
             EndOfGame dv = new EndOfGame();
             dv.display(this, "gagné", loader, stage);
             endOfGame = true;// on set à true ici
         }
    }

    /**
     * @Description teste l'état de victoire selon certains conditions
     * il faut d'abord que que l'utilisateur utilise une carte helicopter
     * en settant testEndOfGame à true
     */
    public boolean Win() {
        if (testEndOfGame) { // test si on utilisé la carte heliport
            Player p1 = listPlayers.get(0);
            if (!p1.getZone().isHeliport())
                return false;


            for (Player p : listPlayers) { // les joueurs doivent être sur la même zone
                if (!p1.getZone().equals(p.getZone()))
                    return false;
                p1 = p;
            }
            if(haveFourElements())
                return true;

            testEndOfGame = false; // si le test échoue on retourne à false

        }

            return false;
    }

        public boolean haveFourElements(){

            return listArtefacts.contains(Artefacts.air) &&
                    listArtefacts.contains(Artefacts.terre) &&
                    listArtefacts.contains(Artefacts.eau) &&
                    listArtefacts.contains(Artefacts.feu);
        }


    /**
     * @Description Une méthode pour tester l'état de jeu perdu
     */
    public boolean Lose(){
        for(Player p: listPlayers) { // test si un joueur est noyé
            if(!p.getZone().isSafe())
                return true;
        }
        int[][] counterElmts = new int [4][1];
        for(int i=0; i<LARGEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                if(!zones[i][j].isSafe() && zones[i][j].isHeliport()) // test si l'heliport est inondé
                        return true;

                // on ccompte le nombre d'artefact d'un même élement qu'on ne peut plus récuperer
                if(zones[i][j].getArtefacts()==Artefacts.air && !zones[i][j].isSafe() )
                    counterElmts[0][0]+=1;
                else if(zones[i][j].getArtefacts()==Artefacts.eau && !zones[i][j].isSafe() )
                    counterElmts[1][0]+=1;
                else if(zones[i][j].getArtefacts()==Artefacts.feu && !zones[i][j].isSafe() )
                    counterElmts[2][0]+=1;
                else if(zones[i][j].getArtefacts()==Artefacts.terre && !zones[i][j].isSafe() )
                    counterElmts[3][0]+=1;
            }
        }

        for(int i = 0; i<4; i++){
            if(counterElmts[i][0]>=2) // si un tous les artefcts récupérable d'un même élément sont noyés alors on perd
                return true;
        }

        return false;
    }
    /**
     * @Description setteur du joueur qui joue actuellement
     */
    public void setRoundOf(Player p){
        this.RoundOf = p;
        p.resetNbActionRestant();
    }

    /**
     * @Description pour récuperer le joueur qui joue actuellement
     */
    public Player getRoundOf(){
        return this.RoundOf;
    }

    /**
     *@Description pour récuperer la liste des joueurs
     */
    public ArrayList<Player> getListPlayers(){
        return listPlayers;
    }

    /**
     *@Description pour récuperer la liste des Artefacts
     */
    public ArrayList<Artefacts> getListArtefacts(){
        return listArtefacts;
    }

    /**
     *@Description Cette méthode gère la montée des eaux:
     * elle incrémenter le niveau de l'eau
     * ainsi que le nombre de carte à piocher
     */
    public void risingWater(){
        this.seaLevel= 1+(this.seaLevel)%11;
        if(this.seaLevel > 7)
            this.numberCardToPick = 5;
        else if(this.seaLevel > 5)
            this.numberCardToPick = 4;
        else if(this.seaLevel > 2)
            this.numberCardToPick = 3;
        else
            this.numberCardToPick = 2;
        System.out.println(this.numberCardToPick + " card to pick");
    }

    /**
     *@Description pour récuperer le niveau de l'eau
     */
    public int getSeaLevel(){
        return this.seaLevel;
    }

    /**
     *@Description méthode pour ajouter des cartes à la défausse
     * @param card une carte tresor à ajouter dans la défausse
     */
    public void addToDefausseCarteTresor(TresorCard card){
        this.defausseCarteTresor.add(card);
    }

    /**
     *@Description getteur de la grille zones
     * @return permet de récupérer la grille, utilisée par les joueurs pour les déplacements
     */
    public Zone[][] getGrille(){
        return this.zones;
    }

    /**
    * @Description on cherche les zones safes dans la grille
     * cette fonction sert autirage
     */
    public ArrayList<Zone> getSafeZones(){
        ArrayList<Zone> safeZones = new ArrayList();
        safeZones.addAll(tasCarteInnondation);
        safeZones.addAll(defausseCarteInnondation);
        return safeZones;
    }


    /**
     * @Description on cherche les zones haut bas gauche droite dans la grille
     * @param zone une zone dans la grille zones
     * @return une liste de Zone
     */
    public ArrayList<Zone> getZoneArround(Zone zone){
        ArrayList<Zone> voisins = new ArrayList<>();
        Position pos = zone.getPosition();
        if (pos.y-1>=0)
            voisins.add(zones[pos.x][pos.y-1]);
        if(pos.x-1>=0)
            voisins.add(zones[pos.x-1][pos.y]);

        if(pos.y+1<Island.HAUTEUR)
            voisins.add(zones[pos.x][pos.y+1]);

        if(pos.x+1<Island.LARGEUR)
            voisins.add(zones[pos.x+1][pos.y]);

        voisins.add(zone);
        return voisins;
    }

    /**
     * @Description retourne les zones autour sûrs
     * @param zone une zone dans la grille zones
     * @return une liste de Zone
     */
    public ArrayList<Zone> getSafeZoneArround(Zone zone){
        ArrayList<Zone> voisins = new ArrayList<>();
        Position pos = zone.getPosition();
        if (pos.y-1>=0)
            if(zones[pos.x][pos.y-1].isSafe())
            voisins.add(zones[pos.x][pos.y-1]);
        if(pos.x-1>=0)
            if(zones[pos.x-1][pos.y].isSafe())
            voisins.add(zones[pos.x-1][pos.y]);

        if(pos.y+1<Island.HAUTEUR)
            if(zones[pos.x][pos.y+1].isSafe())
            voisins.add(zones[pos.x][pos.y+1]);

        if(pos.x+1<Island.LARGEUR)
            if(zones[pos.x+1][pos.y].isSafe())
            voisins.add(zones[pos.x+1][pos.y]);

        voisins.add(zone);
        return voisins;
    }

    /**
     * @Description gfetteur de defausseTresorCard
     */
    public ArrayList<TresorCard> getDefausseTresorCard(){
        return this.defausseCarteTresor;
    }

}