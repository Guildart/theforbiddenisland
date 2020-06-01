package IleInterdite;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Explorateur extends Player {
    public Explorateur(Zone zone, Color colo, Island modele) {
        super(zone, colo, modele);
    }

    /**
     * @param zP zone ou se trouve le joueur
     * @return une liste de zone
     */
    @Override
    public ArrayList<Zone> zonesReachable(){
        Position pos = super.zone.getPosition();
        ArrayList<Zone> zonesSafe = new ArrayList<>();
        Zone [][] zones = modele.getGrille();
        if (pos.y-1>=0)
            if(zones[pos.x][pos.y-1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y-1]);
            }
        if(pos.x-1>=0)
            if(zones[pos.x-1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x-1][pos.y]);
            }

        if(pos.y+1<modele.HAUTEUR)
            if(zones[pos.x][pos.y+1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y+1]);
            }

        if(pos.x+1<modele.LARGEUR)
            if(zones[pos.x+1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x+1][pos.y]);
            }

        if(zone.isSafe())
            zonesSafe.add(zone);

        return zonesSafe;
    }

    @Override
    public boolean isReachable(ArrayList<Zone> listZone){
        for( Zone z : listZone){
            if(z.getPosition().equals( zone.getPosition())) {
                return true;
            }
        }
        return false;
    }
}
