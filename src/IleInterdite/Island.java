package IleInterdite; /**
 * @auteur: MOULOUA Ramdane
 * Classe qui s'occupe de du de gérer le Modèle et conteint notamment la IleInterdite.Zone
 *
 * Update of Mai 20 :
 *     - Croix achevée : fonction init adaptée + constructeur
 *     - zonesNonSubmergee retirée
 *     - nextRound() modifée
 *     - getRandomZone() renvoie une zone à inonder
 */


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
 * Le modèle : le coeur de l'application.
 *
 * Le modèle étend la classe [IleInterdite.Observable] : il va posséder un certain nombre
 * d'observateurs (ici, un : la partie de la vue responsable de l'affichage)
 * et devra les prévenir avec [notifyObservers] lors des modifications.
 * Voir la méthode [avance()] pour cela.
 */
public class Island extends Observable {
    /** On fixe la taille de la grille. */
    public static final int HAUTEUR=6, LARGEUR=6;
    /** On stocke un tableau de cellules. */
    public Zone[][] zones;
    private Player RoundOf;
    public final Random randomGen = new Random();
    private ArrayList<Player> listPlayers = new ArrayList<>();
    private ArrayList<Artefacts> listArtefacts = new ArrayList<>();
    private ArrayList<TresorCard> tasCarteTresor = new ArrayList<>();
    private ArrayList<TresorCard> defausseCarteTresor = new ArrayList<>();
    private ArrayList<Zone> tasCarteInnondation = new ArrayList<>();
    private ArrayList<Zone> defausseCarteInnondation = new ArrayList<>();
    private int seaLevel = 1;
    private int numberCardToPick = 2;
    private boolean endOfGame = false;
    private boolean testEndOfGame = false;

    FXMLLoader loader ;
    Stage stage;

    /** Construction : on initialise un tableau de cellules. */
    public Island() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une
         * colonne de chaque côté, dont les cellules n'évolueront pas.
         */
        zones = new Zone[LARGEUR][HAUTEUR];
        for(int i=0; i<LARGEUR; i++) {
            for(int j=0; j<HAUTEUR; j++) {
                zones[i][j] = new Zone(Etat.none, new Position(i,j), Artefacts.none);
            }
        }
        init();
        listArtefacts.add(Artefacts.air);
        listArtefacts.add(Artefacts.eau);
        listArtefacts.add(Artefacts.feu);
        listArtefacts.add(Artefacts.terre);
    }

    /**
     * Initialisation aléatoire des cellules, exceptées celle des bords qui
     * ont été ajoutés.
     */

    public void init() {

        initTasCarteInnondation();
        initTasCarteTresor();

        for(int j=0; j<=HAUTEUR; j++) {
            int j_p;
            if (j >= HAUTEUR/2)
                j_p = HAUTEUR -1 - j;
            else
                j_p = j;
            for(int i= LARGEUR/2   - j_p%(HAUTEUR/2) - 1; i<=LARGEUR/2 + j_p%(HAUTEUR/2) ; i++) {
                Zone z = tasCarteInnondation.get(0);
                z.setPosition(new Position(i,j));
                zones[i][j] = z;
                defausseCarteInnondation.add(z); //Todo: Une classe qui géré les tas de carte (une classe = un tas + une defausse)
                tasCarteInnondation.remove(z);
            }
        }

        /***On retransfert toutes les cartes**/
        Collections.shuffle(defausseCarteInnondation); //on melange avant
        tasCarteInnondation.addAll(defausseCarteInnondation);
        defausseCarteInnondation.clear();

        /*Color c1 = Color.RED;
        //addPlayer(c1);
        int[] tab = getRandomPoint();
        Player p = new Explorateur(zones[tab[0]][tab[1]],c1, this);
        this.listPlayers.add(p);

        Color c2 = Color.BLACK;
        //addPlayer(c2);
        int[] tab2 = getRandomPoint();
        Player p2 = new Navigateur(zones[tab2[0]][tab2[1]],c2, this);
        this.listPlayers.add(p2);


        Color c3 = Color.PURPLE;
        //addPlayer(c3);
        int[] tab3 = getRandomPoint();
        Player p3 = new Plongeur(zones[tab3[0]][tab3[1]],c3, this);
        this.listPlayers.add(p3);

        Color c4 = Color.GREY;
        addPlayer(c4);
        this.setRoundOf(listPlayers.get(0));*/

    }

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

    public void enableTestEndOfGame(){
        testEndOfGame=true;
    }

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
    * Fonction qui renvoie les coordonnées dans un tab
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
     * Inondation de trois zones tirées au hsard selon les règles
     */
    public void nextRound() {
        /**
         * On procède en deux étapes.
         *  - D'abord, pour chaque cellule on évalue ce que sera son état à la
         *    prochaine génération.
         *  - Ensuite, on applique les évolutions qui ont été calculées.
         */

        for(int i = 0; i < numberCardToPick; i++){
            if(tasCarteInnondation.size() + defausseCarteInnondation.size() > 0) { //Todo : rempalcer par test fin de partie ?
                if (tasCarteInnondation.size() == 0) { //si tas vide on remet la defausse dans le tas
                    Collections.shuffle(defausseCarteInnondation); //on melange avant
                    tasCarteInnondation.addAll(defausseCarteInnondation);
                    defausseCarteInnondation.clear();
                }
                Zone z = tasCarteInnondation.get(0);
                Etat etat = z.getEtat();
                z.setEtat(Etat.nextEtat(etat));
                if (z.getEtat() != Etat.submergee)
                    defausseCarteInnondation.add(z);
                tasCarteInnondation.remove(z);
            }
        }
        displayLose();


        RoundOf.searchKey(this.tasCarteTresor, this.defausseCarteTresor, this);
        RoundOf.searchKey(this.tasCarteTresor, this.defausseCarteTresor, this);

        System.out.println(tasCarteTresor.size());
        //Round du prochain joueur
        ArrayList<Player> players = this.listPlayers;
        this.setRoundOf(players.get( (players.indexOf(this.getRoundOf())+1)%players.size()));
        notifyObservers();
    }

    public boolean getEndOfGame(){
        return this.endOfGame;
    }

    public void setLoader(FXMLLoader loader){
        this.loader = loader;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * Méthode auxiliaire : compte le nombre de voisichnes vivantes d'une
     * cellule désignée par ses coordonnées.
     */
    protected int compteVoisines(int x, int y) {
        int res=0;
        /**
         * Stratégie simple à écrire : on compte les cellules vivantes dans le
         * carré 3x3 centré autour des coordonnées (x, y), puis on retire 1
         * si la cellule centrale est elle-même vivante.
         * On n'a pas besoin de traiter à part les bords du tableau de cellules
         * grâce aux lignes et colonnes supplémentaires qui ont été ajoutées
         * de chaque côté (dont les cellules sont mortes et n'évolueront pas).
         */
        for(int i=x-1; i<=x+1; i++) {
            for(int j=y-1; j<=y+1; j++) {
                //if (zones[i][j].etat) { res++; }
            }
        }
        //return (res - ((zones[x][y].etat)?1:0));
        return 0;
        /**
         * L'expression [(c)?e1:e2] prend la valeur de [e1] si [c] vaut [true]
         * et celle de [e2] si [c] vaut [gfalse].
         * Cette dernière ligne est donc équivalente à
         *     int v;
         *     if (cellules[x][y].etat) { v = res - 1; }
         *     else { v = res - 0; }
         *     return v;
         */
    }

    /**
     * Une méthode pour renvoyer la zobne aux coordonnées choisies (sera
     * utilisée par la vue).
     */
    public Zone getZone(int x, int y) {
        return zones[x][y];
    }

    public void displayLose(){
        if(this.Lose()){// pour perdre faut aussi tester que les zones artefactrs sont submergées


            notifyObservers();
            EndOfGame dv = new EndOfGame();
            dv.display(this, "perdu", loader,stage);
            endOfGame=true;
        }
    }

    public void displayWin(){
         if(this.Win()) { // tester autre chose
             notifyObservers();
             EndOfGame dv = new EndOfGame();
             dv.display(this, "gagné", loader, stage);
             endOfGame = true;
         }
    }

    /**
     * Une méthode pour tester l'état de victoire
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
     * Une méthode pour tester l'état de jeu perdu
     */
    public boolean Lose(){
        for(Player p: listPlayers) { // test si un joueur est noyé
            if(!p.getZone().isSafe())
                return true;
        }
        int[][] counterElmts = new int [4][1];
        for(int i=0; i<LARGEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                if(!zones[i][j].isSafe() && zones[i][j].isHeliport()) // test si l'heliport est inondé{

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
            if(counterElmts[i][0]>=2)
                return true;
        }

        return false;
    }

    public void setRoundOf(Player p){
        this.RoundOf = p;
        p.resetNbActionRestant();
    }

    /**
     * Une méthode pour récuperer le joueur qui joue actuellement
     */
    public Player getRoundOf(){
        return this.RoundOf;
    }

    /**
     * Une méthode pour récuperer la liste des joueurs
     */
    public ArrayList<Player> getListPlayers(){
        return listPlayers;
    }

    public ArrayList<Artefacts> getListArtefacts(){
        return listArtefacts;
    }

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

    public int getSeaLevel(){
        return this.seaLevel;
    }

    public void addToDefausseCarteTresor(TresorCard card){
        this.defausseCarteTresor.add(card);
    }

    public Zone[][] getGrille(){
        return this.zones;
    }

    public ArrayList<Zone> getSafeZones(){
        ArrayList<Zone> safeZones = new ArrayList();
        safeZones.addAll(tasCarteInnondation);
        safeZones.addAll(defausseCarteInnondation);
        return safeZones;
    }

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

}