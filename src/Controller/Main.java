package Controller;

import IleInterdite.Island;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    Stage windows;
    Scene sceneMenu, scenePlaying;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Vue/vue.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/VueMenu.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("The Forbidden Island JavaFX");
        primaryStage.setScene(new Scene(root, 1000, 800));

        ObservableList<Node> n = root.getChildrenUnmodifiable();

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
