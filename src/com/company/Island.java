package com.company;
/**
 * @auteur: MOULOUA Ramdane
 * Classe qui s'occupe de du de gérer le Modèle et conteint notamment la Zone
 *
 * Update of Mai 20 :
 *     - Croix achevée : fonction init adaptée + constructeur
 *     - zonesNonSubmergee retirée
 *     - nextRound() modifée
 *     - getRandomZone() renvoie une zone à inonder
 */


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static com.company.Etat.inondee;
import static com.company.Etat.none;

/**
 * Le modèle : le coeur de l'application.
 *
 * Le modèle étend la classe [Observable] : il va posséder un certain nombre
 * d'observateurs (ici, un : la partie de la vue responsable de l'affichage)
 * et devra les prévenir avec [notifyObservers] lors des modifications.
 * Voir la méthode [avance()] pour cela.
 */
public class Island extends Observable {
    /** On fixe la taille de la grille. */
    public static final int HAUTEUR=6, LARGEUR=6;
    /** On stocke un tableau de cellules. */
    public Zone[][] zones;
    private ArrayList<Zone> listZone;
    private Player RoundOf;
    public final Random randomGen = new Random();
    private ArrayList<Player> listPlayers;
    private int typeAction = 1; // Move Player:1, Drain Water: 2, Take Artfc: 3, Swap cards: 4 TODO : creation d'un type ?
    private ArrayList<Artefacts> listArtefacts;

    /** Construction : on initialise un tableau de cellules. */
    public Island() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une
         * colonne de chaque côté, dont les cellules n'évolueront pas.
         */
        zones = new Zone[LARGEUR][HAUTEUR];
        for(int i=0; i<LARGEUR; i++) {
            for(int j=0; j<HAUTEUR; j++) {
                zones[i][j] = new Zone(none, Artefacts.none, new Position(i,j), false);
            }
        }

        listZone = new ArrayList<>();
        listPlayers = new ArrayList<>();
        listArtefacts = new ArrayList<>();
        init();
    }

    /**
     * Initialisation aléatoire des cellules, exceptées celle des bords qui
     * ont été ajoutés.
     */
    public void init() {
        for(int j=0; j<=HAUTEUR; j++) {
            int j_p;
            if (j >= HAUTEUR/2)
                j_p = HAUTEUR -1 - j;
            else
                j_p = j;
            for(int i= LARGEUR/2   - j_p%(HAUTEUR/2) - 1; i<=LARGEUR/2 + j_p%(HAUTEUR/2) ; i++) {
                zones[i][j].setEtat(Etat.normale);
                listZone.add(zones[i][j]);
            }
        }
        Color c1 = new Color(17, 51, 236, 232);
        addPlayer(c1);
        this.setRoundOf(listPlayers.get(0));
        Color c2 = new Color(168, 0, 53, 232);
        addPlayer(c2);
        Color c3 = new Color(255, 230, 9, 232);
        addPlayer(c3);
    }

    private void addPlayer(Color c){
        int[] tab = getRandomPoint();
        Player p = new Player(zones[tab[0]][tab[1]],c);
        listPlayers.add(p);
    }


    /**
    * Fonction qui renvoie les coordonnées dans un tab
    * de trois zones à modifier se situant dans la croix
    */
     private int[] getRandomPoint(){
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

        for(int i = 0; i < 3; i++){
            if(listZone.size() != 0){
                int rd = randomGen.nextInt(listZone.size());
                Zone newZone = listZone.get(rd);
                Etat etat = newZone.getEtat();
                newZone.setEtat(Etat.nextEtat(etat));
                if (newZone.getEtat()==Etat.submergee)
                    listZone.remove(newZone);
            }
        }
        RoundOf.searchKey();

        //Round du prochain joueur
        ArrayList<Player> players = this.listPlayers;
        this.setRoundOf(players.get( (players.indexOf(this.getRoundOf())+1)%players.size()));


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
         * et celle de [e2] si [c] vaut [false].
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

    /**
     * Une méthode pour tester l'état de victoire
     */
    public boolean Win(){
        Player p1 = listPlayers.get(0);
        if(!p1.getZone().isHeliport())
            return false;

        for(Player p: listPlayers){ // les joueurs doivent être sur la même zone
            if(!p1.getZone().equals(p.getZone()))
                return false;
            p1=p;
        }
        return true;
    }

    /**
     * Une méthode pour tester l'état de jeu perdu
     */
    public boolean Lose(){
        for(Player p: listPlayers) { // test si un joueur est noyé
            if(!p.getZone().isSafe())
                return true;
        }

        for(int i=0; i<LARGEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                if(!zones[i][j].isSafe() && zones[i][j].isHeliport()) // test si l'heliport est inondé
                    return true;
            }
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

    /**
     * @param zP zone ou se trouve le joueur
     * @return une liste de zone
     */
    public ArrayList<Zone>  zoneSafeToMove(Zone zP){
        Position pos = zP.getPosition();
        ArrayList<Zone> zonesSafe = new ArrayList<>();

        if (pos.y-1>=0)
            if(zones[pos.x][pos.y-1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y-1]);
             }
        if(pos.x-1>=0)
            if(zones[pos.x-1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x-1][pos.y]);
            }

        if(pos.y+1<HAUTEUR)
        if(zones[pos.x][pos.y+1].isSafe()){
            zonesSafe.add(zones[pos.x][pos.y+1]);
        }

        if(pos.x+1<LARGEUR)
        if(zones[pos.x+1][pos.y].isSafe()){
            zonesSafe.add(zones[pos.x+1][pos.y]);
        }

        return zonesSafe;
    }


    /**Renvoie une liste des zones que le joueurs peut assécher = zones voisines et inondée**/
    public ArrayList<Zone> zonesDrainable(Zone zp){
        ArrayList<Zone> zonesSafe = zoneSafeToMove(zp);
        ArrayList<Zone> zonesDrainable = new ArrayList<>(); //ne SURTOUT pas modifier directement zonesSafes car crée une ERREUR
        if(RoundOf.getZone().getEtat() == inondee)
            zonesDrainable.add(RoundOf.getZone()); //on ajoute la case ou se trouve le joueurs
        for(Zone z : zonesSafe)
            if(z.getEtat() == Etat.inondee)
                zonesDrainable.add(z);
        return zonesDrainable;
    }

    /**Renvoie une liste des zones sur lesquels le joueurs peut agir selon le type d'action qui est activée**/
    public ArrayList<Zone> zonesReachable(Zone zp){
        if(this.typeAction == 1)
            return zoneSafeToMove(zp);
        else if(this.typeAction == 2)
            return zonesDrainable(zp);
        else if(this.typeAction == 3)
            return zonesDrainable(zp);
        else
            return zoneSafeToMove(zp); //à modifer quand rajout d'action
    }


    public boolean isReachable(ArrayList<Zone> listZone, Zone zM){
        for( Zone z : listZone){
            if(z.getPosition().equals( zM.getPosition())) {
                return true;
            }
        }

        return false;
    }


    public void setTypeAction(int action){
        if(action < 1 || action > 2)
            this.typeAction = 1; //TODO : Creer une exceptions, peut causer des bug graphique (ne sera normalement jamais le cas)
        this.typeAction = action;
    }

    public int getTypeAction(){
        return this.typeAction;
    }

    public void addArtefact(Artefacts art){
        listArtefacts.add(art);
    }

    public ArrayList<Artefacts> getListArtefacts(){
        return listArtefacts;
    }


}