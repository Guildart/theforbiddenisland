# The Forbidden JAVAFX version,
## MOULOUA Ramdane & MUSSARD Romain


UML : https://app.genmymodel.com/api/projects/_0W4BgJnWEeqEM7mFKilpXw/diagrams/_0W4Bg5nWEeqEM7mFKilpXw/png

La classe cellule = cases

Class IleInterdite.Island:

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
                
Class IleInterdite.Zone:

    -Attributs:
        - pos x et y
        - Boolean Heliport 
        - Enumeration.Etat enum (none, normale,inondée ou submergée)
        - Enumeration.SpecialZone enum (air, eau, feu, vent, none)

    -Méthodes:
        - setEtat
        - removeArtefacts
        - getPosition
        - getArtefacts
        - getEtat
        - getHeliport 


class VueIsland:
    
    -    




Class IleInterdite.Player:

    - Attributs:
        - Couleur (https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html)
        - IleInterdite.Zone
        - ArrayList de clés
        - ArrayList d'specialZone
        (-ArrayList mainCarte)
        (-ArrayList défausse) (commune aux joueurs un static, partagés par tous les joueurs ou dans IleInterdite.Island))
        
    - Méthodes:
        - Set IleInterdite.Player (lui passer une zone)
        - deplaceJoueur
        - assecheZone
        - recupArtefacts
        - donnerCle ( avec un autre joueur)
        (- addArtefacts)
        - addCle
        - 
        - RechercheClé (tirage de carte )
        
        
Update Romain 20/05/2020:

    - Suppression de l'attribut modele dans IleInterdite.Zone car inutile (pour l'instant)
    - Modification VueIsland paintComponent pour afficher à partir de la case (0,0)
    - Modification dimension de zones dans IleInterdite.Island (10*8 --> 6*6)
    - Creation (private) zonesNonSubmergee dans IleInterdite.Island : renvoie la liste des zones non submergée
    - Modification NextRound: Innondation de 3 zones non inondés
    - Creation de la methode nextEtat dans zones, une classe Enumeration.Etat serait peut être
    plus approprié ?
    
Update Ramdane 20/05/2020:
    
    - Modifications de IleInterdite.Island:
    - Croix achevée : fonction init adaptée + constructeur
    - zonesNonSubmergee retirées
    - nextRound() modifée
    - getRandomZone() renvoie une zone à inonder
    - utilisation de getZone partout dans le code
    - Modification de enum
    - Déplacement de nextEtat de la classe IleInterdite.Zone à la classe Enumeration.Etat
    - retour à la version précédente de la méthode nextRound() dans IleInterdite.Island de
    - toString dans IleInterdite.Zone(=) et print dans VueIsland à l'aide de toString()
   
Update Ramdane 21/05/2020:

    - Ajout class IleInterdite.Player
    - Class IleInterdite.Zone ajout isSafe
    - Class position ajout des méthodes toString et equals
    - Class Vueisland ajout de paintPlayer
    - Class Vueisland  ajout de mouseClicked qui permet de cliquer sur une case adjacente "safe"
    - Class IleInterdite.Island ajout de zoneSafeToMove, getListPlayers 
    - Class IleInterdite.Island ajout de l'attribut listPlayers
    - Class IleInterdite.Island dans init instanciation de deux players
    - Ajout d'autres méthodes minimes non détaillées
    - Possibilité de 3 actions (uniquement le mouvemnt pour le moment) puis obligation de passer le tour
    - Class IleInterdite.Island ajout de l'attribut actions qui compte le nombre d'action + des méthdoes
    - modification donc de VueIsland
    - ajout d'une croix jaune autour du joueur pour le déplcament
    
Update Romain 21/05/2020:

    - Ajout Enum Enumeration.TresorCard
    - Modif dans player de artefactsKey (ArrayList -> Tableau)
    - Implementation de searchKey dans IleInterdite.Player
    - Utilisation de searchKey dans nextRound (IleInterdite.Island)
    - Joueur représenter par rond
    - Case avec mouve posible entouré en jaune
    - renomage attribut actions en nbActionsRestant dans IleInterdite.Island
    - Ajout de l'attribut int typeAction qui indique quelles actions le joueurs à choisit
      (depalcement = 1, assechement d'une zone = 2 ...)
    - rajout methode zonesDrainable qui renvoie les zones qui peuvent être assécher par un joueur
    - rajout de methode zonesReachable = zones sur lesquelles le joueurs peut agir selon sont mode d'action
      (elle sera appelé dans les calsse externes à la place de zoneSafeToMove pour généraliser
      les actions du joueurs)
    - renommage methode rightToMove en isReachable (colle plus à l'action) + qlq modif pour généraliser
      la methode selon le type d'action pour renvoyer les cases ou peut s'appliquer l'action
    - Mise en place de group button dans VueCommandes pour avoir une liste "à puce" d'action possible
    - Rajout de nouvelle methode dans Controleur selon le type d'action sélectionné par player
    
 Update Ramdane 22/05/2020:
 
     - séléctionner un artefact
     - Class IleInterdite.Island fonctions win et lose
     - deboggage de fonctions
     - début de recherche JavaFX
     
Update Romain 23/05/2020:

    - IleInterdite.Player herite de observable
    - Creation VuePlayer pour afficher les info d'un joueur
    - debut implementation vrai jeu de carte
        - Creation d'un package card
        - Creation classe abstraite Card
        - Creation Classe fille pour représenter les cartes Tresors
        - Creation enum Tresor Card pour enumerer les cartes tresor que
        le joueur peut tirer (remplace enum Key)
        - Creation enum SpecialZone pour lsiter les zones spécial contenant
        éliport ou Trésors (= artefacts)
        - Supression du booleen isHelicopter car enum dans SpecialZone
        - Supression ArrayList de Key pour le remplacer par une ArrayList de
        Card<TresorCard>
        - Ajout à Island d'un tas de carte innondation (=array de zones normal ou
        innonée) et de la defausse associer
        - Ajout d'un tas de carte Card<TresorCard> et de la defausse associé
        - Ajout de deux methode pour initialiser les 2 tas de carte et leur defausse
        initTasCarteInnondation et initTasCarteTresor
        - Modif init pour initialiser la grille selon le tas de carte innondation
        (avant c'était l'inverse)
        - Rajout de setPosition dans Zone pour les besoin ci dessus
        - Affichage du tas de carte joueur en temps réelle
        - Limite de la main du joueurs à 6 cartes, on remplace les plus anciennes
        cartes de la main si le joueur à trop de carte
        - Modification des methodes NextRound et Searchkey pour utiliser le system
        de carte
   
          
Update Ramdane 26/05/2020:

    - Nouvelle Branche
    - Ajout de JavaFX
    - structure MVC 
    
  

Update Romain 27/05/2020:

    - Debut d'implementation de l'interface avec java fx : Grille + action deplacement
    et assechement de zone.
        - Creation de la classe CButton qui est le controleur des Buttons et du
        fichier FXML associé

Update Ramdane 27/05/2020:

    - Ajout de code css pour les boutons
          
TODO LIST :

    **Priorité : High**
    - Clean le code (effacer ancien, renommé les nouveau)
    - Impelementer echanges de carte (modele et interface)
    - Faire en sorte que le joueurs puisse choisir quelles cartes défaussé si il a
    plus de 6 cartes
    - Retravailler le package carte qui n'est pas optimal et implementer 
    les cartes special WaterRise, Sandbag et Helico
    - Implémenter Take Tresor
    
    **Priorité : Medium**
    - Faire un classe qui gére un tas de cartes et sa défausse ???
    - Utiliser l'héritage pour faire des Zones spécial plutot que de les enuméré
    dans SpecialZone ?
    
    **Priorité : Low**
    - Ceation de personnage (hérite de Joueurs), comme chaque joueurs à des mouv
    particuliers attribuer cetaines de méthodes de Island dans Joueurs
    Notamment zonesReachable (et donc toutes les sous méthodes associé zonesDrainable,
    zoneSafeToMove ....)
        