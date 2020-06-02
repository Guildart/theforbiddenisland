package Controller;

import Enumeration.TresorCard;
import IleInterdite.Island;
import IleInterdite.Player;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CVueDefausse implements Initializable {


    public VBox vbox;

    public ScrollPane scrollPane;

    public Button continueButton;

    public Label label;

    public Canvas canvas;

    public FlowPane container;

    private Stage stage;

    private Player player;

    private Island modele;

    private ArrayList<Integer> indiceToDiscard = new ArrayList();

    private int nbCardTodrop;

    private int nbCardEmpty;

    private int cardH = 100;
    private int cardV = 160;
    private int gap = 20;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void display(Island modele, Player player){
        stage = new Stage();

        //Parent root = FXMLLoader.load(getClass().getResource("/Vue/VueDiscard.fxml"));

        Font font = Font.loadFont(getClass().getResourceAsStream("/image/treamd.ttf"),25);
        System.out.println(font);
        vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        container = new FlowPane();
        scrollPane = new ScrollPane();
        continueButton = new Button("Continue");
        continueButton.setCancelButton(true);
        continueButton.setOnMouseClicked(handleClickButton);
        this.modele = modele;
        this.player = player;



        ArrayList<TresorCard> cards = player.getCards();

        nbCardTodrop = player.nombreCarte() - 5;
        nbCardEmpty = player.getPlayerCardsDragtgable().size();
        canvas = new Canvas((cards.size()-nbCardEmpty) * (gap+cardH) + 50,200);
        canvas.setOnMouseClicked(handleClickOnCanvas);


        GraphicsContext gcF = canvas.getGraphicsContext2D();

        container.getChildren().add(canvas);

        label = new Label(player.toString() + ": Vous devez defausser " + nbCardTodrop + " cartes");
        label.setFont(font);
        vbox.setSpacing(30);

        for(int i = 0; i < cards.size()-nbCardEmpty; i++){
            TresorCard card = cards.get(i);
            gcF.setFill(card.getColor());
            URL url = getClass().getResource(card.getURLForPlayersDiscard());
            gcF.drawImage(new Image(url.toExternalForm()), i * (gap+cardH),10);
        }

        scrollPane.setMinHeight(220);
        scrollPane.setContent(container);
        vbox.getChildren().add(label);
        vbox.getChildren().add(continueButton);
        vbox.getChildren().add(scrollPane);
        //vbox.setPrefHeight();
        stage.setScene(new Scene(vbox, 500, 345));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(0.95);
        stage.toFront();
        stage.showAndWait();

    }

    public Stage getStage(){
        return this.stage;
    }

    EventHandler<MouseEvent> handleClickButton = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent mouseEvent)
        {
            ArrayList<TresorCard> toDiscard = new ArrayList();
            if(!continueButton.isCancelButton()){
                player.discardCard(indiceToDiscard);
                modele.notifyObservers();
                Stage s = (Stage) continueButton.getScene().getWindow();
                s.close();
            }
        }
    };

    EventHandler<MouseEvent> handleClickOnCanvas = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    int x = (int)(mouseEvent.getX())/(gap+cardH);
                    System.out.println("pos x :" + x);
                    GraphicsContext gcF = canvas.getGraphicsContext2D();
                    TresorCard card = player.getCards().get(x);
                    if(indiceToDiscard.contains(x)){
                        URL url = getClass().getResource(card.getURLForPlayersDiscard());
                        gcF.drawImage(new Image(url.toExternalForm()), x * (gap+cardH),10);
                        int indice = indiceToDiscard.indexOf(x);
                        indiceToDiscard.remove(indice);
                        nbCardTodrop++;
                    }else{
                        gcF.setLineWidth(4);
                        gcF.setFill(Color.RED);
                        gcF.setStroke(Color.RED);
                        gcF.strokeRect((x * (gap+cardH))+4, 10+4, cardH-7, cardV-7);
                        indiceToDiscard.add(x);
                        nbCardTodrop--;
                    }

                    if(player.nombreCarte() - indiceToDiscard.size() < 6) {
                        continueButton.setCancelButton(false);
                        label.setText(player.toString() + ": Vous devez encore defausser " + 0 + " cartes");
                    }else {
                        continueButton.setCancelButton(true);
                        label.setText(player.toString() + ": Vous devez encore defausser " + nbCardTodrop + " cartes");
                    }
                    System.out.println("discard size : " + indiceToDiscard.size());
                }
            };

}
