package Vue;

import Card.Card;
import IleInterdite.Player;
import IleInterdite.Observer;
import Enumeration.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VuePlayer extends JPanel implements Observer, MouseListener{

    private Player player;
    private final static int HAUTEUR = 150;
    private final static int LARGEUR = 395;

    public VuePlayer(Player player){
        super();
        this.player = player;
        player.addObserver(this);
        addMouseListener(this); //pour pouvoir cliquer sur un joueur ou sur ses cartes
        Dimension dim = new Dimension(LARGEUR,
                HAUTEUR);
        this.setPreferredSize(dim);
    }


    @Override
    public void update() { repaint();}

    public void paintComponent(Graphics g) {
        super.repaint();
        paintCard(g);
        paintPlayer(g,player.getColor());
        Color c = Color.YELLOW;
        g.setColor(c);
        /** Coloration d'un rectangle. */
        Graphics2D g2 = (Graphics2D) g;
        Stroke s = g2.getStroke();
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(0, 0, LARGEUR, HAUTEUR);
        g2.setStroke(s);


    }

    private void paintPlayer(Graphics g, Color c) {
        /** Sélection d'une couleur. */
        g.setColor(c);
        /** Coloration d'un rectangle. */
        g.fillOval(LARGEUR/2, 2, 20, 20);
    }

    private void paintCard(Graphics g){
        ArrayList<Card<TresorCard>> listecard = player.getCards();
        for(int i = 0; i < 6; i++){
            if(listecard.size() > i) {
                Card<TresorCard> card = listecard.get(i);
                g.setColor(card.getType().getColor());
            }else{
                g.setColor(Color.white);
            }

            g.fillRect(5+i*(60+3), 30, 60, 90);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
