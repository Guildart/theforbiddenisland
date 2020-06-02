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

    @Override
    public ArrayList<Zone> zonesSafeToMove() {
        if(canFly){
            return modele.getSafeZones();
        }else
            return super.zonesSafeToMove();
    }



    //On regard si le pilote utilise sa competence
    public boolean isFlying(Zone zone){
        ArrayList<Zone> zones = modele.getSafeZones();
        zones.removeAll(super.zonesSafeToMove());
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
