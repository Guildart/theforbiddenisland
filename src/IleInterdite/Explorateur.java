package IleInterdite;

import java.net.URL;
import java.util.ArrayList;

/**L'Explorateur peut se depalcer et assécher des tuiles diagonalement**/
public class Explorateur extends Player {
    public Explorateur(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    /**
     * @return une liste de zone où le joueur peut se deplacer
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

    /**
     * Renvoie le role sous forme de chaine de caractère
     * @return
     */
    @Override
    public String toString() {
        return "Explorateur";
    }
}
