package com.company.Vue;


import com.company.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Une classe pour représenter la zone d'affichage des cellules.
 *
 * JPanel est une classe d'éléments graphiques, pouvant comme JFrame contenir
 * d'autres éléments graphiques.
 *
 * Cette vue va être un observateur du modèle et sera mise à jour à chaque
 * nouvelle génération des cellules.
 */
public class VueIsland extends JPanel implements com.company.Observer, MouseListener {
    /** On maintient une référence vers le modèle. */
    private Island modele;
    /** Définition d'une taille (en pixels) pour l'affichage des cellules. */
    private final static int TAILLE = 50;

    /** Constructeur. */
    public VueIsland(Island modele) {
        this.modele = modele;
        /** On enregistre la vue [this] en tant qu'observateur de [modele]. */
        modele.addObserver(this);

        //J'ajoute au MouseListener notre objet
        addMouseListener(this);
        /**
         * Définition et application d'une taille fixe pour cette zone de
         * l'interface, calculée en fonction du nombre de cellules et de la
         * taille d'affichage.
         */
        Dimension dim = new Dimension(TAILLE* Island.LARGEUR,
                TAILLE* Island.HAUTEUR);
        this.setPreferredSize(dim);
    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'un changement dans le
     * modèle. Ici on se content de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update() { repaint(); }

    /**
     * Les éléments graphiques comme [JPanel] possèdent une méthode
     * [paintComponent] qui définit l'action à accomplir pour afficher cet
     * élément. On la redéfinit ici pour lui confier l'affichage des cellules.
     *
     * La classe [Graphics] regroupe les éléments de style sur le dessin,
     * comme la couleur actuelle.
     */
    public void paintComponent(Graphics g) {
        super.repaint();
        /** Pour chaque cellule... */
        for(int i = 0; i< Island.LARGEUR; i++) {
            for(int j = 0; j< Island.HAUTEUR; j++) {
                /**
                 * ... Appeler une fonction d'affichage auxiliaire.
                 * On lui fournit les informations de dessin [g] et les
                 * coordonnées du coin en haut à gauche.
                 */
                paint(g, modele.getZone(i, j), (i)*TAILLE, (j)*TAILLE);
            }
        }

        ArrayList<Player> liste = modele.getListPlayers();
        for(Player p: liste){
            Position pos = p.getZone().getPosition();
            paint(g, p.getColor(), pos.x*TAILLE, pos.y*TAILLE);
        }


        //System.out.println(this.toString()); // code utilisé pour afficher les éléments
    }
    /**
     * Fonction auxiliaire de dessin d'une cellule.
     * Ici, la classe [Cellule] ne peut être désignée que par l'intermédiaire
     * de la classe [CModele] à laquelle elle est interne, d'où le type
     * [CModele.Cellule].
     * Ceci serait impossible si [Cellule] était déclarée privée dans [CModele].
     */
    private void paint(Graphics g, Zone c, int x, int y) {
        /** Sélection d'une couleur. */
        if (c.getEtat() == Etat.none) {
            g.setColor(Color.BLACK);
        } else if (c.getEtat() == Etat.normale){
            g.setColor(Color.GREEN);
        } else if (c.getEtat() == Etat.inondee){
            g.setColor(Color.CYAN);
        } else{
            g.setColor(Color.BLUE);
        }
        /** Coloration d'un rectangle. */
        g.fillRect(x, y, TAILLE, TAILLE);
    }

    private void paint(Graphics g, Color c, int x, int y) {
        /** Sélection d'une couleur. */
        g.setColor(c);
        /** Coloration d'un rectangle. */
        g.fillRect(x, y, TAILLE, TAILLE);
    }

    public String toString(){
        String islandString = "";
        for(int i = 0; i< Island.LARGEUR; i++) {
            for(int j = 0; j< Island.HAUTEUR; j++) {
                islandString =islandString +" "+ modele.getZone(i, j).toString();
            }
            islandString = islandString + "\n";
        }
        islandString = islandString + "\n \n \n";
        return islandString;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX()/TAILLE + " " + mouseEvent.getY()/TAILLE);
        Player p = modele.getRoundOf();
        p.movePlayer(modele.getZone(mouseEvent.getX()/TAILLE, mouseEvent.getY()/TAILLE));
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}