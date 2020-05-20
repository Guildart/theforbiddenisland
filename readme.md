# The Forbidden Island
## MOULOUA Ramdane & MUSSARD Romain


UML : https://app.genmymodel.com/api/projects/_0W4BgJnWEeqEM7mFKilpXw/diagrams/_0W4Bg5nWEeqEM7mFKilpXw/png

La classe cellule = cases

Class Island:

    - Attributs:
        - Grille de zone
        - Hauteur de la grille 6
        - Largeur de la grille 6 
        - Players 
    
    - Méthodes:
        - init() pour initiliaser la grille
        - getZone()
        - Win
        - Lose
        - nextRound()
                * Doit vérifier l'état du jeu (Win, Lose)
                * Actions joueurs (appelle une Action de joueur)
                * Recherche de clé (dans player)
                * Immersion de 3 zones aléatoires
                
Class Zone:

    -Attributs:
        - pos x et y
        - Boolean Heliport 
        - Etat enum (none, normale,inondée ou submergée)
        - Artefacts enum (air, eau, feu, vent, none)

    -Méthodes:
        - setEtat
        - removeArtefacts
        - getPosition
        - getArtefacts
        - getEtat
        - isHeliport 
        
        
Class Player:

    - Attributs:
        - Zone
        - ArrayList de clés
        - ArrayList d'artefacts
        
    - Méthodes:
        - Set Player (lui passer une zone)
        - Actions (une des trois actions )
        - RechercheClé (tirage de carte )
        
        
Update Romain 20/05/2020:

    - Suppression de l'attribut modele dans Zone car inutile (pour l'instant)
    - Modification VueIsland paintComponent pour afficher à partir de la case (0,0)
    - Modification dimension de zones dans Island (10*8 --> 6*6)
    - Creation (private) zonesNonSubmergee dans Island : renvoie la liste des zones non submergée
    - Modification NextRound: Innondation de 3 zones non inondés
    - Creation de la methode nextEtat dans zones, une classe Etat serait peut être
    plus approprié ?
    
Update Ramdane 20/05/2020:
    
    - Modifications de Island:
    - Croix achevée : fonction init adaptée + constructeur
    - zonesNonSubmergee retirées
    - nextRound() modifée
    - getRandomZone() renvoie une zone à inonder
    - utilisation de getZone partout dans le code
    - Modification de enum
    - Déplacement de nextEtat de la classe Zone à la classe Etat