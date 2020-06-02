package IleInterdite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;

public class Explorateur extends Player {
    public Explorateur(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    /**
     * @return une liste de zone
     */
    @Override
    public ArrayList<Zone> zonesSafeToMove(){
        Position pos = super.zone.getPosition();
        ArrayList<Zone> zonesSafe = new ArrayList<>();
        Zone [][] zones = modele.getGrille();

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

    @Override
    public String toString() {
        return "Explorateur";
    }
}
