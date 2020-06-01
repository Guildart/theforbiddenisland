package IleInterdite;

import Enumeration.TresorCard;
import javafx.scene.paint.Color;

public class Messager extends Player{

    public Messager(Zone zone, Color colo, Island modele) {
        super(zone, colo, modele);
    }

    @Override
    public void giveCard(TresorCard card, Player player) {
        this.removeCard(card);
        modele.addToDefausseCarteTresor(card);
        this.addAction();
        player.setCard(card);
    }
}
