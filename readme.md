# The Forbidden Island,
## MOULOUA Ramdane & MUSSARD Romain

Ce git est celui de notre projet de POGL de DL Math-Info du S4.

But du Porjet : 
    
       Implémenter le jeu L'Ile Interdite de Matt Leacock en respectant au mieux la structure MVC
       pour une meilleurs gestion de projet.

Notre Objectif Personnel : 

    Faire une version la plus fidèle possible du jeu de plateau avec Java FX.

Originalité :

    - Nous avons utilisé Java FX pour la partie graphique en alliant le plus possible le
    code java, FXML et l'utilisation de scene builder.
    
    - Notre gameplay plutôt intuitif et facile d'accès.
    
Comment ça se joue :

    - Le joueur peut se deplacer (ou deplacer les autres dans le cas du navigateur) en faisant un
    Drag and Drop, il suffit donc de faire glisser les pions.
    
    - Un joueur peut donner une carte à un autre joueur pendant sont tour (quand les conditions sont
    réunit) en faisant un Drag and Drop de sa carte vers la main de son coéquipier.
    
    - De même le joueurs peut utiliser une carte spéciale (sans coût d'action) en glissant sa carte
    vers la zone de l'île où il veut se rendre.
    
    - Nous avons implémenté les différents rôles du jeux, le joueur devra au lancement du jeux
    sélectionner 2 à 4 rôles (selon le nombre de joueurs voulut) pour lancer une partie.
    
    - Nous avons implementer les packets de cartes ainsi que la fonctionnalitée montée des eaux, quand une
    carte montée des eaux est tirée ont mélange la defausse des cartes innondation au tas de carte
    innondation (Attention des zones vont commencer à sombrer!!!). Selon la zone ou est le curseur,
    sur la règle montée des eaux (panneaux du bas dans la fênetre), 2 à 5 cartes inondation seront tirées.
    
    - De même les Cartes tresors sont implémentés, quand le tas est vide on mélange la défausse qui
    devient la nouvelle main.
    
    - Pour coller au mieux aux règles officiels, un joueur ne peut avoir plus de 5 cartes en mains,
    aussi à chaque debut de tours si un joueurs à trop cartes une fenetre pop up s'ouvrira 
    et le joueur sera obligé de défausser un nombre minimale de carte.
    
    - Pour récupérer un Tresor/Artefacts le joueurs devra se trouver sur la case de l'artefacts voulu,
    avoir en sa possession 4 cartes tresors et cliquer sur le bouton "Take Tresor"
    
    - Pour finir son tours le jouer devra cliquer sur Next Round, il
    tirera alors 2 cartes tresors dans la pioche et ce sera au joueur suivant
    
    - Le joueur dont c'est le tour verra le fond derrière sa main (panneau de droite) devenir vert,
    de plus il est indiqué sous les boutons ("Next Round" et "Take Tresor") le role du joeur dont c'est
    le tour et le nombre d'action qu'il lui reste
    
    - Un joueur peut arrêter sont tour et passer au prochain joueur quand il le veut en cliquant
    sur "Next Round", aucune obligation de réaliser des actions.
    
    - Sur la grille sont représenter par des logo eau, vent, air, feu les zones contenant des artefacts

    - Quand un joueur prend un artefacts celui ci disparait de la zone où elle se trouvait et
    l'artefacts correspondant sur le panneau en bas de l'île se colorisera
    
    - Pour gagner après avoir obtenu tous les artefacts, tous les joueurs devront être sur la case
    Heliport (la case de l'île avec un sybole H) et utiliser une carte Helicopter pour décoller de
    l'île
    
    - Les joueurs perdent si toutes les tuiles contenant un artefacts sombres avant qu'il l'ai 
    récupéré, si un joueur se noie ou si l'héliport sombre
    
    
Un mots sur les graphismes :

    - Merci a Marius qui a dessiné pour nous les Cartes représentant les différents rôles et les pions.
    - Une quantité non negligeable des resources graphique provienne du site freepik.com
    
    
    
Ci-Dessous la liste des updates fait au jour le jour, qui nous a permis une meilleur communication
entre nous dans la réalisation du projet:        
        
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
    - ajout CvuePlayer
    - modification de CVueFX
    - modification des proportions (dans le main, cvuefxml etc )
    - erreur player Observable ??? => à modifier
    - ajout de Cvueplayer et cvueplayer.fxml
    - mdofication de TresorCard
     
Update Romain 27/05/2020:

    - Preparation de la fusion de la branches principale avec la branche JavaFX     
    - Merge des 2 branches


Update Ramdane 30/05/2020:

    - Ajout de drag and drop
    - cliquer sur un case submerge pour retirer l'eeau
    - glisser et déposer un pion pour le déplacer
    - cration dans la classe VueIsland de noeuds pour gérer les pions
    - creation d'une classe PionsDraggable pour le drag and drop
    - Island modification de la fonction qui gères les cliques sur la grille
    - et d'autres changements mineurs, dont des modifications de fxml


Update Romain 30/05/2020:

    - Creation CPlayerPanel et VuePlayerPanel.fxml
    - CVuePlayer dessine un joueur sur un pane et CPlayerPanel contient les
      panes de tout les joueurs
    - Implementation Take Tresor + Association GUI
    - Supression de type action, modification des methodes qui l'utilisait + 
      correction de bug mineur pour respecter le Drag and Drop

Update Ramdane 31/05/2020:

    - Ajout d'un menu principal
    - Ajout d'un bouton quitter ( qui efface donc la partie en cours)
    - Ajout VueMenu.fxml avec des boutons
    - Modification du CSS
    - Ajout du bouton quitter dans vue commande qui renvoie au menu principal


Update Romain 30/05/2020:

    - Implementation de la montée des eaux et affichage graphique sur panneau du bas
    

Update Romain 01/05/2020:

    - Creation du Pop up pour la gestion de la defausse
    - Correctif en tout genre de bug liée à la defausse
    - Graphisme affichage des images de cartes (jusque là représenter simplement par rectangle colorée)
    - Réalisation des rôles Messager, Pilote, Plongeur et Ingenieur
    - Affichage des noms des joueurs à coté de leurs logo
    - Mise en place du systeme de coloration de la main du joueur dont c'est le tour
    - Commentaire de code et clean up

    
Update Ramdane 01/06/2020:

    - Finalisation du Drag And Drop des cartes
    - Graphisme : Gestion des images représentant les joueurs (jusque là représenter simplement par rond colorée)
    - Réalisation du pop up Menu et de la Fin de partie
    - Réalisation des rôles Navigateur et Explorateur
    - Gestion affichage Artefacts dans grilles et panneau du bas
    - Affcihage du compteur d'action 
    - Commentaire de code et clean up
    
