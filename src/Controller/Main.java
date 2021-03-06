package Controller;

import IleInterdite.Island;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



/**Avec Java Fx on est obligé d'avoir une classe de ce type pour lancé notre interface graphique**/
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/VueMenu.fxml")); // on charge la première page fxml

        Parent root = loader.load(); // on charge le parent
        primaryStage.getIcons().add(new Image("/image/fondBateau.jpg"));

        primaryStage.setTitle("The Forbidden Island JavaFX");
        primaryStage.setScene(new Scene(root, 1000, 800));// on set la sec à la fenêtre
        primaryStage.show(); // on affiche la taille

    }


    public static void main(String[] args) {
        launch(args);
    }


}
