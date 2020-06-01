package Controller;

import Enumeration.TresorCard;
import IleInterdite.Island;
import IleInterdite.Observer;
import IleInterdite.Player;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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

    private ArrayList<TresorCard> toDiscard = new ArrayList();

    private int nbCardTodrop;

    private int cardH = 100;
    private int cardV = 160;
    private int gap = 20;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void display(Island modele, Player player){
        stage = new Stage();
        vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        container = new FlowPane();
        scrollPane = new ScrollPane();
        continueButton = new Button("Continue");
        continueButton.setCancelButton(true);
        continueButton.setOnMouseClicked(handleClickButton);
        this.modele = modele;
        this.player = player;
        canvas = new Canvas(10 * (gap+cardH) + 50,200);

        canvas.setOnMouseClicked(handleClickOnCanvas);
        ArrayList<TresorCard> cards = player.getCards();


        GraphicsContext gcF = canvas.getGraphicsContext2D();

        container.getChildren().add(canvas);
        nbCardTodrop = cards.size() - 5;
        label = new Label("Vous devez encore deffauser " + nbCardTodrop + "cartes");
        for(int i = 0; i < cards.size(); i++){
            TresorCard card = cards.get(i);
            gcF.setFill(card.getColor());
            gcF.fillRect(gap + i * (gap+cardH), 10, cardH, cardV);
        }

        scrollPane.setMinHeight(210);
        scrollPane.setContent(container);
        vbox.getChildren().add(label);
        vbox.getChildren().add(continueButton);
        vbox.getChildren().add(scrollPane);
        stage.setScene(new Scene(vbox, 500, 400));
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
            if(!continueButton.isCancelButton()){
                player.discardCard(toDiscard);
                modele.notifyObservers();
                Stage s = (Stage) continueButton.getScene().getWindow();
                s.close();
            }
        }
    };

    EventHandler<MouseEvent> handleClickOnCanvas = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    //double x = ((gap+cardH)+25)/mouseEvent.getX();
                    int x = (int)(mouseEvent.getX())/(gap+cardH);
                    System.out.println("pos x :" + x);
                    GraphicsContext gcF = canvas.getGraphicsContext2D();
                    TresorCard card = player.getCards().get(x);
                    if(toDiscard.contains(card)){
                        gcF.setFill(card.getColor());
                        gcF.fillRect(20 + x * (gap+cardH), 10, cardH, cardV);
                        toDiscard.remove(card);
                    }else{
                        gcF.setLineWidth(2);
                        gcF.strokeRect((20 + x * (gap+cardH))+2, 10+2, cardH-4, cardV-4);
                        toDiscard.add(card);
                    }

                    if(player.getCards().size() - toDiscard.size() < 6) {
                        continueButton.setCancelButton(false);
                        label.setText("Vous devez encore deffauser " + 0 + "cartes");
                    }else
                        label.setText("Vous devez encore deffauser " + nbCardTodrop + "cartes");
                }
            };

}
