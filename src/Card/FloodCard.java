package Card;

import Enumeration.Etat;
import IleInterdite.Zone;

public class FloodCard {

    //Todo : Pour l'isntant on utilise zone à la place de floodCard, on laisse comme ça ?

    private Zone zone;

    public FloodCard(Zone zone) {
        this.zone = zone;
    }

    public void doAction() {
        Etat etat = this.zone.getEtat();
        this.zone.setEtat(Etat.nextEtat(etat));
    }

}
