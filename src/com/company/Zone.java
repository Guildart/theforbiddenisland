package com.company;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Zone {
    /** On conserve un pointeur vers la classe principale du modèle. */
    private Island modele;

    /** L'état d'une cellule est donné par un booléen. */
    protected boolean etat;
    /**
     * On stocke les coordonnées pour pouvoir les passer au modèle lors
     * de l'appel à [compteVoisines].
     */
    private final int x, y;
    public Zone(Island modele, int x, int y) {
        this.modele = modele;
        this.etat = false;
        this.x = x; this.y = y;
    }
    /**
     * Le passage à la génération suivante se fait en deux étapes :
     *  - D'abord on calcule pour chaque cellule ce que sera sont état à la
     *    génération suivante (méthode [evalue]). On stocke le résultat
     *    dans un attribut supplémentaire [prochainEtat].
     *  - Ensuite on met à jour l'ensemble des cellules (méthode [evolue]).
     * Objectif : éviter qu'une évolution immédiate d'une cellule pollue
     * la décision prise pour une cellule voisine.
     */
    private boolean prochainEtat;
    protected void evalue() {
        switch (this.modele.compteVoisines(x, y)) {
            case 2: prochainEtat=etat; break;
            case 3: prochainEtat=true; break;
            default: prochainEtat=false;
        }
    }
    protected void evolue() {
        etat = prochainEtat;
    }

    /** Un test à l'usage des autres classes (sera utilisé par la vue). */
    public boolean estVivante() {
        return etat;
    }

    public String toString(){
        return "C";
    }
}
/** Fin de la classe Cellule, et du modèle en général. */