package IleInterdite;

import Enumeration.Etat;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;

/**Le Plongeur peut se déplacer au travers d'une ou plusieurs tuiles adjacentes manquantes et/ou inondées pour 1 action (pas forcément en ligne droite)**/
 public class Plongeur extends Player{
    public Plongeur(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    /**
     * @return zone ou le joueur peut se depalcer
     */
    @Override
    public ArrayList<Zone> zonesSafeToMove() {
        ArrayList<Zone> zonesReachable = new ArrayList();
        searchRoad(zonesReachable, this.zone);
        zonesReachable.addAll(super.zonesSafeToMove());
        return zonesReachable;
    }


    /**
     * Calcul recursif les tuiles où le joueurs peut avancer grâce à sa capacité spéciale
     * @param zonesReachable list des zones à updates
     * @param zone zone à partir de laquelle ont regarde les zones accessibles
     */
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


    /**
     * Renvoie le role sous forme de chaine de caractère
     * @return
     */
    @Override
    public String toString() {
        return "Plongeur";
    }
}
