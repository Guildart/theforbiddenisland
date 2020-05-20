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
        - getHeliport 
        
        
Class Player:

    - Attributs:
        - Zone
        - ArrayList de clés
        - ArrayList d'artefacts
        
    - Méthodes:
        - Set Player (lui passer une zone)
        - Actions (une des trois actions )
        - RechercheClé (tirage de carte )