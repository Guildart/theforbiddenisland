package Card;

import Enumeration.TresorCard;
import IleInterdite.Zone;

public class SandbagCard extends Card<TresorCard>{

    public SandbagCard(){
        super(TresorCard.sandbag);
    }

    @Override
    public void doAction(Zone zones) {
    }
}
