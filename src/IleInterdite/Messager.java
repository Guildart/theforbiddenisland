package IleInterdite;

import Enumeration.TresorCard;

import java.net.URL;

public class Messager extends Player{

    public Messager(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    @Override
    public void giveCard(TresorCard card, Player player) {
        this.removeCard(card);
        this.addAction();
        player.setCard(card);
    }

    @Override
    public String toString() {
        return "Messager";
    }
}
