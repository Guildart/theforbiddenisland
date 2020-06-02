package IleInterdite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;

/**L'ingénieur peut assécher 2 tuiles pour 1 action**/

public class Ingenieur extends Player{
    private int nbDrain = 0; //On garde en mémoire le nombre de zones asséché puis on le soustrait modulo 2 au nombre d'action réalisé


    public Ingenieur(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    /**
     *
     * @param z la zone a assécher
     */
    @Override
    public void drainWaterZone(Zone z) {
        this.nbDrain++;
        super.drainWaterZone(z);
        if(nbDrain%2 == 0)
            Ingenieur.nbActionsRestant--;
    }

    /**
     * Renvoie le role sous forme de chaine de caractère
     * @return
     */
    @Override
    public String toString() {
        return "Ingenieur";
    }
}
