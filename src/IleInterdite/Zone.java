package IleInterdite;

import Enumeration.Artefacts;
import Enumeration.Etat;
import javafx.beans.property.*;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Zone {
    /** On conserve un pointeur vers la classe principale du modèle. */

    /** L'état d'une cellule est donné par un booléen. */
    private Etat etat;
    /** Artéfact caché dnas la zone*/
    private Artefacts artefacts;
    /** Bool pour savoir si la zone est la zone spéciale héliport*/
    private boolean heliport;
    /** Position de la zone */
    private Position position;

    public Zone(Etat etat, Position position,  Artefacts artefacts, boolean heliport) {
        this.etat = etat;
        this.artefacts = artefacts;
        this.position = position;
        this.heliport = heliport;
    }

    // second constructeur pour set autre chose que l'heliort.
    public Zone(Etat etat, Position position,  Artefacts artefacts){
        this(etat, position, artefacts, false);
    }


    /**
     * @Description, on change la position de la Zone
     * */
    public void setPosition(Position p){
        this.position = p;
    }

    /**
     * @Description, on change l'etat de la zone
     * */
    public void setEtat(Etat etat){
        this.etat = etat;
    }

    /*
    public void removeArtefacts(){
        this.artefacts = Artefacts.none;
    }*/
    /**
     * @Description geteur de la position
     * */
    public Position getPosition(){
        return this.position;
    }

    /**
     * @Description geteur l'artefact
     * */
    public Artefacts getArtefacts(){
        return this.artefacts;
    }
    /**
     * @Description geteur de l'etat
     * */
    public Etat getEtat(){
        return this.etat;
    }

    /**
     * @Description geteur de l'artefact pour l'update
     * */
    public void setArtefacts(Artefacts artefacts) {
        this.artefacts = artefacts;
    }

    /**
     * @Description geteur de Heliport
     * */
    public boolean isHeliport(){
        return this.heliport;
    }

    /**
     * @Description pour efficher le type de zone
     * */
    @Override
    public String toString(){
        return etat.toString();
    }

    /**
     * @Description test que c'est une zone non mortelle pour une joueur
     * @return une zone safe
     * */
    public boolean isSafe(){
        return this.etat==Etat.normale || this.etat==Etat.inondee;
    }
    /**
     * @Description que c'est une zone inondee
     * @return true si la zone est inondee
     * */
    public boolean isFlooded(){
        return this.etat == Etat.inondee;
    }

    /**
     * @Description getteur de la position x
     * */
    public int getX(){
        return position.x;
    }
    /**
     * @Description getteur de la position y
     * */
    public int getY(){
        return position.y;
    }

}
