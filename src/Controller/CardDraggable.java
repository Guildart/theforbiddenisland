package Controller;

import Enumeration.Etat;
import Enumeration.TresorCard;
import IleInterdite.Island;
import IleInterdite.Player;
import IleInterdite.Zone;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class CardDraggable extends PionsDraggable {



    /*
    * Class qui hérite de PionsDraggable
    * */
    private TresorCard card;

    // origine position
    private double safeX = 0;
    private double safeY = 0;
    //lien de l'image de la carte
    private String imageURL;


    CardDraggable(Player player, Island modele, TresorCard card){
        super(player,modele);
        this.card = card;
        this.init();
        released = false;
    }

    private void init() {

        onMousePressedProperty().set(event -> {

            mousex = event.getSceneX();
            mousey = event.getSceneY();

            x = getLayoutX();
            y = getLayoutY();

            if (isMoveToFront()) {
                toFront();
            }
        });

        onMouseDraggedProperty().set(new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                if(modele.getRoundOf()==player){ // on bouge les cartes que si c notre tour

                    double offsetX = event.getSceneX() - mousex;
                    double offsetY = event.getSceneY() - mousey;


                    x += offsetX;
                    y += offsetY;

                    double scaledX = x;
                    double scaledY = y;

                    setLayoutX(scaledX);
                    setLayoutY(scaledY);

                    dragging = true;

                    mousex = event.getSceneX();
                    mousey = event.getSceneY();


                    if (x >= 600 && y >= 400 || (x <= 600 && y >= 600)) {
                        dragging = false;
                        modele.notifyObservers();
                    }
                    event.consume();
                }

            }

        });

        onMouseClickedProperty().set(event -> {
            //System.out.println("clicked");

            if (x<600 && modele.getRoundOf()==player){
                //System.out.println("grille");
                //System.out.println("x"+ (int)x/CVueIsland.TAILLE + "y " + (int)y/CVueIsland.TAILLE);
                // appeler fonction pour soit assecher soit poser lhelicopter sur la case donne par la formule d'avant
                Zone z = modele.getZone((int)x/CVueIsland.TAILLE, (int)y/CVueIsland.TAILLE);

                if(card == TresorCard.sandbag){
                    if(z.getEtat()== Etat.inondee){
                        z.setEtat(Etat.normale);
                    }
                    player.defausseCard(card);
                    modele.addToDefausseCarteTresor(card);

                }
                else if(card == TresorCard.helicopter){
                    System.out.println("HELICO ");
                    if(modele.getZone((int)x/CVueIsland.TAILLE,(int)y/CVueIsland.TAILLE).isHeliport()){ // cas de fin du jeu
                        modele.enableTestEndOfGame(); // on rend le test possible de la fin du jeu possible sur l'action de la carte helicoptere sur l'heliport
                        modele.displayWin(); // on affiche l'ecran de fin
                    }
                    player.movePlayer(modele.getZone((int)x/CVueIsland.TAILLE,(int)y/CVueIsland.TAILLE)); // je le teleporte quand meme si ca échoue
                    player.defausseCard(card); // dans tous les cas on retire la carte
                    modele.addToDefausseCarteTresor(card); // puis on la défausse
                    // si c'est bien la fin de partie,
                }

            }else if(modele.getRoundOf()==player) { // echange de carte ici
                if(y<480){
                    if(card != TresorCard.empty && card != TresorCard.helicopter && card != TresorCard.sandbag){

                        Player toPlayer = modele.getListPlayers().get((int)(y)/120);
                        if(toPlayer!=player){
                            System.out.println("panel numero "+ ((int)(y)/120));
                            player.giveCard(card,toPlayer);

                        }
                    }
                }

            }
            modele.notifyObservers();

            dragging = false;

        });

        onMouseDragReleasedProperty().set((EventHandler<MouseEvent>) event -> {
            modele.notifyObservers();
            dragging = false;
        });


    }

    /**
     * @Description setteurs et getteurs
     */



    public void setCard(TresorCard card) {
        this.card = card;
    }

    public void setPlayer(Player player){
        this.player = player;
    }


    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSafeX(double safeX) {
        this.safeX = safeX;

    }

    public void setSafeY(double safeY) {
        this.safeY = safeY;

    }

    public void translateSafeX() {
        setLayoutX(safeX);
    }

    public void translateSafeY() {
        setLayoutY(safeY);
    }




}
