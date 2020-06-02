package IleInterdite;

import Enumeration.TresorCard;

import java.net.URL;

/**Le Messager peut donner des cartes Trésor sans être sur la même tuile.**/

public class Messager extends Player{

    public Messager(Zone zone, URL image, Island modele) {
        super(zone, image, modele);
    }

    /**
     * On modifie pour que le messager ne soit limiter spacialement pour donner ses cartes
     * @param card carte à donner
     * @param player joueur qui reçoit la carte
     */
    @Override
    public void giveCard(TresorCard card, Player player) {
        this.removeCard(card);
        this.addAction();
        player.setCard(card);
    }


    /**
     * Renvoie le role sous forme de chaine de caractère
     * @return
     */
    @Override
    public String toString() {
        return "Messager";
    }
}
