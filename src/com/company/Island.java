package com.company;
/**
 * @auteur: MOULOUA Ramdane
 * Classe qui s'occupe de du de gérer le Modèle et conteint notamment la Zone
 *
 * Update of Mai 20 :
 *     - Mise à jour de la fonction init.
 *
 */


import java.util.ArrayList;
import java.util.Random;

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
    private Zone[][] zones;

    public final Random randomGen = new Random();

    /** Construction : on initialise un tableau de cellules. */
    public Island() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une
         * colonne de chaque côté, dont les cellules n'évolueront pas.
         */
        zones = new Zone[LARGEUR][HAUTEUR];
        for(int i=0; i<LARGEUR; i++) {
            for(int j=0; j<HAUTEUR; j++) {
                zones[i][j] = new Zone(Etat.normale, Artefacts.none, new Position(i,j), false);
            }
        }
        init();
    }

    /**
     * Initialisation aléatoire des cellules, exceptées celle des bords qui
     * ont été ajoutés.
     */
    public void init() {
        zones[0][0].setEtat(Etat.none);
        zones[0][1].setEtat(Etat.none);
        zones[1][0].setEtat(Etat.none);

        zones[0][HAUTEUR-1].setEtat(Etat.none);
        zones[1][HAUTEUR-1].setEtat(Etat.none);
        zones[0][HAUTEUR-2].setEtat(Etat.none);

        zones[LARGEUR-1][HAUTEUR-1].setEtat(Etat.none);
        zones[LARGEUR-1][HAUTEUR-2].setEtat(Etat.none);
        zones[LARGEUR-2][HAUTEUR-1].setEtat(Etat.none);

        zones[LARGEUR-1][0].setEtat(Etat.none);
        zones[LARGEUR-1][1].setEtat(Etat.none);
        zones[LARGEUR-2][0].setEtat(Etat.none);
    }

    /**Renvoie la liste des zones non submergée**/
    private ArrayList<Zone> zonesNonSubmergee(){
        ArrayList<Zone> listZone = new ArrayList<>();
        for(int i=0; i<LARGEUR; i++)
            for(int j=0; j<HAUTEUR; j++)
                if (zones[i][j].getEtat() != Etat.none && zones[i][j].getEtat() != Etat.submergee)
                    listZone.add(zones[i][j]);
        return listZone;
    }

    /**
     * Calcul de la génération suivante.
     */
    public void nextRound() {
        /**
         * On procède en deux étapes.
         *  - D'abord, pour chaque cellule on évalue ce que sera son état à la
         *    prochaine génération.
         *  - Ensuite, on applique les évolutions qui ont été calculées.
         */
        ArrayList<Zone> listZone = zonesNonSubmergee();
        for(int i = 0; i < 3; i++){
            if(listZone.size() != 0){
                int rd = randomGen.nextInt(listZone.size());
                Zone newZone = listZone.get(rd);
                Etat etat = newZone.getEtat();
                newZone.setEtat(Zone.nextEtat(etat));
                listZone.remove(newZone);
            }
        }
    }

    /**
     * Méthode auxiliaire : compte le nombre de voisines vivantes d'une
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
     * Une méthode pour renvoyer la cellule aux coordonnées choisies (sera
     * utilisée par la vue).
     */
    public Zone getCellule(int x, int y) {
        return zones[x][y];
    }

    /**
     * Une méthode pour tester l'état de victoire
     */
    public boolean Win(){
        return true;
    }

    /**
     * Une méthode pour tester l'état de jeu perdu
     */
    public boolean Lose(){
        return false;
    }

}