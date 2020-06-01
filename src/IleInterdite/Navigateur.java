package IleInterdite;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Navigateur extends Player {
    public Navigateur(Zone zone, Color colo, Island modele) {
        super(zone, colo, modele);
    }


    public static ArrayList<Zone> zonesReachableNavigateur(Island modele, Position pos ){
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
            if(zones[i][pos.y].isSafe())
                zonesSafe.add(zones[i][pos.y]);

        for(int j = yMin; j<=yMax; j++)
            if(zones[pos.x][j].isSafe())
                zonesSafe.add(zones[pos.x][j]);

        return zonesSafe;
    }

    public static int getMin(int d){
        int res;
        if(d-2>=0){
            res=d-2;
        }
        else if(d-1>=0){
            res=d-1;

        }
        else
            res=d;

        return res;
    }

    public static int getMax(int d, int MAX){
        int res;
        if(d+2<MAX){
            res=d+2;
        }
        else if(d+1<MAX){
            res=d+1;

        }
        else
            res=d;
        return res;
    }


}
