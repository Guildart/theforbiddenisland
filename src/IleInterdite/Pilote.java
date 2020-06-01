package IleInterdite;

import Enumeration.TresorCard;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Pilote extends Player{

    public boolean canFly = true;

    public Pilote(Zone zone, Color color, Island modele) {
        super(zone, color, modele);
    }

    @Override
    public ArrayList<Zone> zonesReachable() {
        if(canFly){
            return modele.getSafeZones();
        }else
            return super.zonesReachable();
    }

    //On regard si le pilote utilise sa competence
    public boolean isFlying(Zone zone){
        ArrayList<Zone> zones = modele.getSafeZones();
        zones.removeAll(super.zonesReachable());
        if(zones.contains(zone))
            return true;
        else
            return false;
    }

    @Override
    public void movePlayer(Zone z) {
        if(isFlying(z))
            this.canFly = false; //Si le mouvement est distant on empeche d'utiliser une seconde fois le vol
        super.movePlayer(z);
    }

    @Override
    public void searchKey(ArrayList<TresorCard> tas, ArrayList<TresorCard> defausse, Island island) {
        super.searchKey(tas, defausse, island);
        this.canFly = true;  //On remet canFly à jour
    }
}
