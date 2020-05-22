package com.company.Vue;

import com.company.Island;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe pour notre contrôleur rudimentaire.
 *
 * Le contrôleur implémente l'interface [ActionListener] qui demande
 * uniquement de fournir une méthode [actionPerformed] indiquant la
 * réponse du contrôleur à la réception d'un événement.
 */
public class Controleur implements ActionListener {
    /**
     * On garde un pointeur vers le modèle, car le contrôleur doit
     * provoquer un appel de méthode du modèle.
     * Remarque : comme cette classe est interne, cette inscription
     * explicite du modèle est inutile. On pourrait se contenter de
     * faire directement référence au modèle enregistré pour la classe
     * englobante [VueCommandes].
     */
    Island modele;
    VueIsland vueIsland;
    public Controleur(Island modele) {
        this.modele = modele;

    }
    /**
     * Action effectuée à réception d'un événement : appeler la
     * méthode [avance] du modèle.
     */
    public void actionPerformed(ActionEvent e) {
        modele.nextRound();
    }

    public void actionMovePlayer(ActionEvent e){
        modele.setTypeAction(1);

    }

    public void actionDrainWater(ActionEvent e){
        modele.setTypeAction(2);

    }


}
/** Fin du contrôleur. */