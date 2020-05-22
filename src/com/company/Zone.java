package com.company;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Zone {
    /** On conserve un pointeur vers la classe principale du modèle. */
    private Island modele;

    /** L'état d'une cellule est donné par un booléen. */
    private Etat etat;
    private Artefacts artefacts;
    private boolean heliport;
    private Position position;
    /**
     * On stocke les coordonnées pour pouvoir les passer au modèle lors
     * de l'appel à [compteVoisines].
     */

    public Zone(Etat etat, Artefacts artefacts, Position position, boolean heliport) {
        this.etat = etat;
        this.artefacts = artefacts;
        this.heliport = heliport;
        this.position = position;
    }

    public Zone(Island modele, Etat etat, Artefacts artefacts, Position position) {
        this(etat, artefacts, position, false);
    }

    public void setEtat(Etat etat){
        this.etat = etat;
    }

    public void removeArtefacts(){
        this.artefacts = Artefacts.none;
    }

    public Position getPosition(){
        return this.position;
    }

    public Artefacts getArtefacts(){
        return this.artefacts;
    }

    public Etat getEtat(){
        return this.etat;
    }

    public boolean isHeliport(){
        return this.heliport;
    }

    public String toString(){
        return etat.toString();
    }

    public boolean isSafe(){
        return this.etat==Etat.normale || this.etat==Etat.inondee;
    }

}
/** Fin de la classe Cellule, et du modèle en général. */