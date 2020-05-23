package Card;

import Enumeration.Etat;
import Enumeration.TresorCard;
import IleInterdite.Zone;

public class HelicoCard extends Card<TresorCard>{
    public HelicoCard() {
        super(TresorCard.helicopter);
    }


    @Override
    public void doAction(Zone zone) {

    }
}
