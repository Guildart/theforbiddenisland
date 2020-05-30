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

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/vue.fxml"));


        Parent root = loader.load();
        primaryStage.setTitle("The Forbidden Island JavaFX");
        primaryStage.setScene(new Scene(root, 1000, 800));
       /* Island modele = new Island();
        Controller controller = new Controller(modele);
        loader.setController(controller);*/

       // System.out.println(canvas);
        ObservableList<Node> n = root.getChildrenUnmodifiable();
        CVue FXVue= loader.getController(); // accès à l'instance controlleur
        System.out.println("ca affiche "+ FXVue.maVAR);
        Island modele = new Island();
        FXVue.Controller1(modele);
        //FXVue.update();

        //l.getCVueIslandController().setStage(primaryStage);


        for(Node t: n){
            System.out.println("noeuds "+ t);
            if(t.getId().equals("grille")){ // acces a la grille ( mais pas à l'instance)
                System.out.println("match "+ t);
            }
            if(t.getId().equals("anch")){ // acces a la grille ( mais pas à l'instance)
                //CVueIsland g = t.
            }
        }
        /*Canvas canvas = new Canvas(300, 100);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setLineWidth(2.0);
        gc.setFill(Color.RED);

        gc.strokeRoundRect(10, 10, 50, 50, 10, 10);

        gc.fillOval(70, 10, 50, 20);

        gc.strokeText("Hello Canvas", 150, 20);
        Pane root1 = new Pane();
        root1.getChildren().add(canvas);
        Scene scene = new Scene(root1);
        primaryStage.setScene(scene);*/
        //l.getCVueIslandController().show();

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
