package IleInterdite;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Plongeur extends Player {
    public Plongeur(Zone zone, Color colo, Island modele) {
        super(zone, colo, modele);
    }

    @Override
    /*
     * @return une liste de zone
     */
    public ArrayList<Zone> zonesReachable(){
        Position pos = zone.getPosition();
        ArrayList<Zone> zonesSafe = new ArrayList<>();
        Zone [][] zones = modele.getGrille();
        zonesSafe = getZoneSousMarines(zone,0);
       /*if (pos.y-1>=0)
            if(zones[pos.x][pos.y-1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y-1]);
            }
        if(pos.x-1>=0)
            if(zones[pos.x-1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x-1][pos.y]);
            }

        if(pos.y+1<Island.HAUTEUR)
            if(zones[pos.x][pos.y+1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y+1]);
            }

        if(pos.x+1<Island.LARGEUR)
            if(zones[pos.x+1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x+1][pos.y]);
            }

        if(zone.isSafe())
            zonesSafe.add(zone);*/
        return zonesSafe;
    }

    public ArrayList<Zone> getZoneSousMarines(Zone z, int depth){
        System.out.println(z.getPosition().toString());
        Position pos = z.getPosition();
        ArrayList<Zone> zonesSafe = new ArrayList<>();
        Zone [][] zones = modele.getGrille();

        if (depth <16) {

            if(pos.x-1>=0){
                if(zones[pos.x-1][pos.y].isSafe())
                    zonesSafe.add(zones[pos.x-1][pos.y]);
                else{

                    ArrayList<Zone> a = getZoneSousMarines(zones[pos.x-1][pos.y],depth+1);
                    if (a.size()>0)
                        for (Zone zone1 : a)
                            if (!zonesSafe.contains(z) )
                                zonesSafe.add(zone1);
                }

            }


            if(pos.y-1>=0){
                if(zones[pos.x][pos.y-1].isSafe())
                    zonesSafe.add(zones[pos.x][pos.y-1]);
                else{
                    ArrayList<Zone> a = getZoneSousMarines(zones[pos.x][pos.y-1],depth+1);
                    if (a.size() > 0)
                        for (Zone zone1 : a)
                            if (!zonesSafe.contains(z))
                                zonesSafe.add(zone1);
                }
            }

            if(pos.x+1<Island.LARGEUR){
                if(zones[pos.x+1][pos.y].isSafe())
                    zonesSafe.add(zones[pos.x+1][pos.y]);
                else {
                    ArrayList<Zone> a = getZoneSousMarines(zones[pos.x+1][pos.y],depth+1);
                    if (a.size() > 0)
                        for (Zone zone1 :a)
                            if (!zonesSafe.contains(z))
                                zonesSafe.add(zone1);
                }
            }

            if(pos.y+1<Island.HAUTEUR){
                if(zones[pos.x][pos.y+1].isSafe())
                    zonesSafe.add(zones[pos.x][pos.y+1]);
                else {
                    ArrayList<Zone> a = getZoneSousMarines(zones[pos.x][pos.y+1],depth+1);
                    if (a.size() > 0)
                        for (Zone zone1 : a)
                            if (!zonesSafe.contains(z))
                                zonesSafe.add(zone1);
                }
            }
        }


        return zonesSafe;

    }



}

