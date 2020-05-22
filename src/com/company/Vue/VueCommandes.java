package com.company.Vue;

import com.company.*;

import javax.swing.*;
import java.awt.*;


/**
 * Une classe pour représenter la zone contenant le bouton.
 *
 * Cette zone n'aura pas à être mise à jour et ne sera donc pas un observateur.
 * En revanche, comme la zone précédente, celle-ci est un panneau [JPanel].
 */
class VueCommandes extends JPanel {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une
     * référence au modèle.
     */
    private Island modele;
    private final static int TAILLE = 50;

    /** Constructeur. */
    public VueCommandes(Island modele) {
        this.modele = modele;
        /**
         * On crée un nouveau bouton, de classe [JButton], en précisant le
         * texte qui doit l'étiqueter.
         * Puis on ajoute ce bouton au panneau [this].
         */
        JButton boutonNextRound = new JButton("Fin de Tour");
        this.add(boutonNextRound);

        /**JRadioButton est un bouton de type selet/Unselect, on les ajoute directement au Jpanel comme un JButton**/
        JRadioButton boutonMovePlayer = new JRadioButton("Move");
        this.add(boutonMovePlayer);
        JRadioButton boutonDrainWater = new JRadioButton("Drain Water");
        this.add(boutonDrainWater);
        JRadioButton boutonTakeArtefact = new JRadioButton("Take Artefact");
        this.add(boutonTakeArtefact);
        JRadioButton boutonSwapCard = new JRadioButton("Swap Card");
        this.add(boutonSwapCard);

        /**On crée un ButtonGroup pour réunir les bouton qui ne doivent jamais être sélectionné en même temps
         * On obtiens une liste à puce avec un unique élément sélectionné
         */
        ButtonGroup bg = new ButtonGroup();
        bg.add(boutonMovePlayer);
        bg.add(boutonDrainWater);
        bg.add(boutonTakeArtefact);
        bg.add(boutonSwapCard);


        Dimension dim = new Dimension(TAILLE* Island.LARGEUR,
                TAILLE* Island.HAUTEUR);
        this.setPreferredSize(dim);
        /**
         * Le bouton, lorsqu'il est cliqué par l'utilisateur, produit un
         * événement, de classe [ActionEvent].
         *
         * On a ici une variante du schéma observateur/observé : un objet
         * implémentant une interface [ActionListener] va s'inscrire pour
         * "écouter" les événements produits par le bouton, et recevoir
         * automatiquements des notifications.
         * D'autres variantes d'auditeurs pour des événements particuliers :
         * [MouseListener], [KeyboardListener], [WindowListener].
         *
         * Cet observateur va enrichir notre schéma Modèle-Vue d'une couche
         * intermédiaire Contrôleur, dont l'objectif est de récupérer les
         * événements produits par la vue et de les traduire en instructions
         * pour le modèle.
         * Cette strate intermédiaire est potentiellement riche, et peut
         * notamment traduire les mêmes événements de différentes façons en
         * fonction d'un état de l'application.
         * Ici nous avons un seul bouton réalisant une seule action, notre
         * contrôleur sera donc particulièrement simple. Cela nécessite
         * néanmoins la création d'une classe dédiée.
         */
        Controleur ctrl = new Controleur(modele);
        /** Enregistrement du contrôleur comme auditeur du bouton. */
        boutonNextRound.addActionListener(ctrl);
        boutonMovePlayer.addActionListener(ctrl::actionMovePlayer);
        boutonDrainWater.addActionListener(ctrl::actionDrainWater);
        boutonTakeArtefact.addActionListener(ctrl::actionTakeArtefact); //Remplacer actionDrainWater
        boutonSwapCard.addActionListener(ctrl::actionDrainWater); //Remplacer actionDrainWater


        /**
         * Variante : une lambda-expression qui évite de créer une classe
         * spécifique pour un contrôleur simplissime.
         *
         JButton boutonNextRound = new JButton(">");
         this.add(boutonNextRound);
         boutonNextRound.addActionListener(e -> { modele.avance(); });
         *
         */



    }

    public void paintComponent(Graphics g) {
       // paint(g, new Color(255,255,255), 20,30);

    }

    private void paint(Graphics g, Color c, int x, int y) {

       // g.fillRect(x, y, 100, 100);
    }

}
/** Fin de la vue. */

