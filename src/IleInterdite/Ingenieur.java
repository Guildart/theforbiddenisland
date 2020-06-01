package IleInterdite;

import javafx.scene.paint.Color;

public class Ingenieur extends Player{
    private int nbDrain = 0;

    public Ingenieur(Zone zone, Color colo, Island modele) {
        super(zone, colo, modele);
    }

    @Override
    public void drainWaterZone(Zone z) {
        this.nbDrain++;
        super.drainWaterZone(z);
        if(nbDrain%2 == 0)
            Ingenieur.nbActionsRestant--;
    }
}
