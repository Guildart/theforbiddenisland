package IleInterdite;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Explorateur extends Player {
    public Explorateur(Zone zone, Color colo, Island modele) {
        super(zone, colo, modele);
    }

    /**
     * @return une liste de zone
     */
    @Override
    public ArrayList<Zone> zonesReachable(){
        Position pos = super.zone.getPosition();
        ArrayList<Zone> zonesSafe = new ArrayList<>();
        Zone [][] zones = modele.getGrille();
        /*if (pos.y-1>=0)
            if(zones[pos.x][pos.y-1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y-1]);
                if(pos.x-1>=0){
                    zonesSafe.add(zones[pos.x-1][pos.y-1]);
                }
                if(pos.x+1<Island.LARGEUR){
                    zonesSafe.add(zones[pos.x+1][pos.y-1]);
                }
            }
        if(pos.x-1>=0)
            if(zones[pos.x-1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x-1][pos.y]);
            }

        if(pos.y+1<Island.HAUTEUR)
            if(zones[pos.x][pos.y+1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y+1]);
                if(pos.x-1>=0){
                    zonesSafe.add(zones[pos.x-1][pos.y+1]);
                }
                if(pos.x+1<Island.LARGEUR){
                    zonesSafe.add(zones[pos.x+1][pos.y+1]);
                }
            }

        if(pos.x+1<Island.LARGEUR)
            if(zones[pos.x+1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x+1][pos.y]);
            }
        */

        int xMin ;
        int yMin ;
        int yMax ;
        int xMax ;

        xMin = getMin(pos.x);
        yMin = getMin(pos.y);
        yMax = getMax(pos.y, Island.HAUTEUR);
        xMax = getMax(pos.x,Island.LARGEUR);

        for(int i = xMin; i<=xMax; i++ )
            for(int j = yMin; j<=yMax; j++)
                if(zones[i][j].isSafe())
                zonesSafe.add(zones[i][j]);

        if(zone.isSafe())
            zonesSafe.add(zone);

        return zonesSafe;
    }

    @Override
    public boolean isReachable(ArrayList<Zone> listZone, Zone zp){
        for( Zone z : listZone){
            if(z.getPosition().equals( zp.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public static int getMin(int d){
        int res;

        if(d-1>=0){
            res=d-1;

        }
        else
            res=d;

        return res;
    }

    public static int getMax(int d, int MAX){
        int res;
        if(d+1<MAX){
            res=d+1;
        }
        else
            res=d;
        return res;
    }
}
