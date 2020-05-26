package IleInterdite;

import Enumeration.SpecialZone;
import Enumeration.Etat;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Zone {
    /** On conserve un pointeur vers la classe principale du modèle. */
    private Island modele;

    /** L'état d'une cellule est donné par un booléen. */
    private Etat etat;
    private SpecialZone specialZone;
    private Position position;
    public BooleanProperty test;
    public final IntegerProperty age = new SimpleIntegerProperty();

    /**
     * On stocke les coordonnées pour pouvoir les passer au modèle lors
     * de l'appel à [compteVoisines].
     */

    public Zone(Etat etat, Position position,  SpecialZone specialZone) {
        this.etat = etat;
        this.specialZone = specialZone;
        this.position = position;
        test = new SimpleBooleanProperty();

        test.setValue(true);
    }



    public void setPosition(Position p){
        this.position = p;
    }
    public void setEtat(Etat etat){
        this.etat = etat;
    }

    public void removeArtefacts(){
        this.specialZone = SpecialZone.none;
    }

    public Position getPosition(){
        return this.position;
    }

    public SpecialZone getSpecialZone(){
        return this.specialZone;
    }

    public Etat getEtat(){
        return this.etat;
    }

    public boolean isHeliport(){
        return this.specialZone == SpecialZone.heliport;
    }

    public String toString(){
        return etat.toString();
    }

    public boolean isSafe(){
        return this.etat==Etat.normale || this.etat==Etat.inondee;
    }

}
/** Fin de la classe Cellule, et du modèle en général. */