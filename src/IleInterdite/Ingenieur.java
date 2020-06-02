package IleInterdite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;

public class Ingenieur extends Player{
    private int nbDrain = 0;

    public Ingenieur(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    @Override
    public void drainWaterZone(Zone z) {
        this.nbDrain++;
        super.drainWaterZone(z);
        if(nbDrain%2 == 0)
            Ingenieur.nbActionsRestant--;
    }
}
