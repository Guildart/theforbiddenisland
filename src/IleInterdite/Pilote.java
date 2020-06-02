package IleInterdite;

import Enumeration.TresorCard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;

public class Pilote extends Player{

    public boolean canFly = true;

    public Pilote(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    /**
     * Si la capacité spéciale du joueur est actif les zones où il peut se depalcer différents
     * @return zones où peut se rendre le joueur
     */
    @Override
    public ArrayList<Zone> zonesSafeToMove() {
        if(canFly){
            return modele.getSafeZones();
        }else
            return super.zonesSafeToMove();
    }


    /**
     * On regarde si le mouvement effectuer par le joueur à necessiter sa capacitée spéciale
     * @param zone zone ou veut aller le joueur
     * @return renvoie true si le joueur utilise sont atout false si non
     */
    public boolean isFlying(Zone zone){
        ArrayList<Zone> zones = modele.getSafeZones();
        zones.removeAll(super.zonesSafeToMove());
        if(zones.contains(zone))
            return true;
        else
            return false;
    }

    /**
     * @param z la zone ou le joueur
     */
    @Override
    public void movePlayer(Zone z) {
        if(isFlying(z))
            this.canFly = false; //Si le mouvement est distant on empeche d'utiliser une seconde fois le vol
        super.movePlayer(z);
    }

    /**On le réécrit pour remettre l'attribut canFly à 0 pour le prochain tour**/
    @Override
    public void searchKey(ArrayList<TresorCard> tas, ArrayList<TresorCard> defausse, Island island) {
        super.searchKey(tas, defausse, island);
        this.canFly = true;  //On remet canFly à jour
    }



    /**
     * Renvoie le role sous forme de chaine de caractère
     * @return
     */
    @Override
    public String toString() {
        return "Pilote";
    }


}
