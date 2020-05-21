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
        (-Arraylist piocheJoueur)
        (-Arraylist piocheEnvironnement)
        (-Arraylist défausseEnvironnement)
    
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


class VueIsland:
    
    -    




Class Player:

    - Attributs:
        - Couleur (https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html)
        - Zone
        - ArrayList de clés
        - ArrayList d'artefacts
        (-ArrayList mainCarte)
        (-ArrayList défausse) (commune aux joueurs un static, partagés par tous les joueurs ou dans Island))
        
    - Méthodes:
        - Set Player (lui passer une zone)
        - deplaceJoueur
        - assecheZone
        - recupArtefacts
        - donnerCle ( avec un autre joueur)
        (- addArtefacts)
        - addCle
        - 
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
    - retour à la version précédente de la méthode nextRound() dans Island de
    - toString dans Zone(=) et print dans VueIsland à l'aide de toString()
   
Update Ramdane 21/05/2020:

    - Ajout class Player
    - Class Zone ajout isSafe
    - Class position ajout des méthodes toString et equals
    - Class Vueisland ajout de paintPlayer
    - Class Vueisland  ajout de mouseClicked qui permet de cliquer sur une case adjacente "safe"
    - Class Island ajout de zoneSafeToMove, getListPlayers 
    - Class Island ajout de l'attribut listPlayers
    - Class Island dans init instanciation de deux players
    - Ajout d'autres méthodes minimes non détaillées
    - Possibilité de 3 actions (uniquement le mouvemnt pour le moment) puis obligation de passer le tour
    - Class Island ajout de l'attribut actions qui compte le nombre d'action + des méthdoes
    - modification donc de VueIsland
    - ajout d'une croix jaune autour du joueur pour le déplcament
    
Update Romain 21/05/2020:

    - Ajout Enum Key
    - Modif dans player de artefactsKey (ArrayList -> Tableau)
    - Implementation de searchKey dans Player
    - Utilisation de searchKey dans nextRound (Island)