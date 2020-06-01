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
            canFly = false;
            return modele.getSafeZones();
        }else
            return super.zonesReachable();
    }

    @Override
    public void searchKey(ArrayList<TresorCard> tas, ArrayList<TresorCard> defausse, Island island) {
        super.searchKey(tas, defausse, island);
        this.canFly = true;
    }
}
