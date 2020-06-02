package IleInterdite;

import Enumeration.Etat;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;

public class Plongeur extends Player{
    public Plongeur(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    @Override
    public ArrayList<Zone> zonesSafeToMove() {
        ArrayList<Zone> zonesReachable = new ArrayList();
        searchRoad(zonesReachable, this.zone);
        zonesReachable.addAll(super.zonesSafeToMove());
        return zonesReachable;
    }

    /*@Override
    public ArrayList<Zone> zonesDrainable() {
        ArrayList<Zone> zones = super.zonesSafeToMove();
        zones.add(this.zone);
        return zones;
    }*/

    private void searchRoad(ArrayList<Zone> zonesReachable, Zone zone){
        for(Zone z : modele.getZoneArround(zone)){
            if(!zonesReachable.contains(z)){
                if (zone.getEtat() == Etat.inondee || zone.getEtat() == Etat.normale) {
                    zonesReachable.add(zone);
                    if (zone.getEtat() == Etat.inondee || zone.getEtat() == Etat.submergee) {
                        searchRoad(zonesReachable, z);
                    }
                }

            }
        }
    }


    @Override
    public String toString() {
        return "Plongeur";
    }
}
