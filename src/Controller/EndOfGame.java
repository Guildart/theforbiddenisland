package Controller;

        import IleInterdite.Island;
        import javafx.event.EventHandler;
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
        import javafx.scene.layout.*;
        import javafx.scene.text.Font;
        import javafx.stage.Modality;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class EndOfGame implements Initializable {

    /*
    * Classe qui pop - up la fin du jeu et qui renvoie vers le menu principal
     */

    public VBox vbox;
    public ScrollPane scrollPane;
    public Button continueButton;
    public Label label;
    public Canvas canvas;
    public FlowPane container;
    private Stage stage;
    private Island modele;
    private FXMLLoader loader;
    private Stage stageP;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * @Description on crée un pop-up pour afficher un msg de fin et pour renvoyer dans le menu principal dans la fenêtre stageP
     * @param modele le modèle
     * @param msg le message
     * @param loader le loader
     * @param stageP fenêtre principal
     */
    public void display(Island modele, String msg, FXMLLoader loader,Stage stageP){
        this.stageP= stageP;
        stage = new Stage();
        this.loader = loader;
        Font font = Font.loadFont(getClass().getResourceAsStream("/image/treamd.ttf"),30);
        System.out.println(font);
        vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        container = new FlowPane();
        scrollPane = new ScrollPane();
        continueButton = new Button("Retour au menu principal");
        continueButton.setCancelButton(true);
        continueButton.setOnMouseClicked(handleClickButton);
        this.modele = modele;


        canvas = new Canvas(0,200);
        //canvas.setOnMouseClicked(handleClickOnCanvas);

        GraphicsContext gcF = canvas.getGraphicsContext2D();

        container.getChildren().add(canvas);

        label = new Label("Vous avez "+ msg);
        label.setFont(font);
        vbox.setSpacing(30);


        scrollPane.setMinHeight(220);
        scrollPane.setContent(container);
        vbox.getChildren().add(label);
        vbox.getChildren().add(continueButton);
        vbox.getChildren().add(scrollPane);

        stage.setScene(new Scene(vbox, 500, 345));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(0.90);
        stage.toFront();
        stage.showAndWait();

    }

    public Stage getStage(){
        return this.stage;
    }

    EventHandler<MouseEvent> handleClickButton = new EventHandler<>() {
        public void handle(MouseEvent mouseEvent) {
            Stage stage;
            Parent root;
            Stage s = (Stage) continueButton.getScene().getWindow();
            s.close();

            try {
                root = loader.load();
                Scene scene = new Scene(root);

                stageP.setScene(scene);
                stageP.show();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    };

    //inutilisée pour l'instant
  /*  EventHandler<MouseEvent> handleClickOnCanvas = mouseEvent -> {
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

        if(player.nombreCarte() - toDiscard.size() < 6) {
            continueButton.setCancelButton(false);
            label.setText("Vous devez encore deffauser " + 0 + " cartes");
        }else
            label.setText("Vous devez encore deffauser " + nbCardTodrop + " cartes");
    };*/

}
