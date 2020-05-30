package IleInterdite;

import Enumeration.Artefacts;
import Enumeration.Etat;
import javafx.beans.property.*;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Zone {
    /** On conserve un pointeur vers la classe principale du modèle. */

    /** L'état d'une cellule est donné par un booléen. */
    private Etat etat;
    private Artefacts artefacts;
    private boolean heliport;
    private Position position;
    public BooleanProperty test;
    public final IntegerProperty age = new SimpleIntegerProperty();

    /**
     * On stocke les coordonnées pour pouvoir les passer au modèle lors
     * de l'appel à [compteVoisines].
     */


    public Zone(Etat etat, Position position,  Artefacts artefacts, boolean heliport) {
        this.etat = etat;
        this.artefacts = artefacts;
        this.position = position;
        test = new SimpleBooleanProperty();
        test.setValue(true);
        this.heliport = heliport;
    }

    public Zone(Etat etat, Position position,  Artefacts artefacts){
        this(etat, position, artefacts, false);
    }



    public void setPosition(Position p){
        this.position = p;
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

    public void setArtefacts(Artefacts artefacts) {
        this.artefacts = artefacts;
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