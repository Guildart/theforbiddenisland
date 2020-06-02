package IleInterdite;

import Enumeration.TresorCard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;

public class Messager extends Player{

    public Messager(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    @Override
    public void giveCard(TresorCard card, Player player) {
        this.removeCard(card);
        modele.addToDefausseCarteTresor(card);
        this.addAction();
        player.setCard(card);
    }
}
