package Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Draggable node sample.
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class DraggableNode01 extends Application {

    @Override
    public void start(Stage primaryStage) {

        // we use a default pane without layout such as HBox, VBox etc.
        final Pane root = new Pane();

        final Scene scene = new Scene(root, 800, 600, Color.rgb(160, 160, 160));

        final int numNodes   =  6; // number of nodes to add
        final double spacing = 30; // spacing between nodes

        // add numNodes instances of DraggableNode to the root pane
        for (int i = 0; i < numNodes; i++) {
            PionsDraggable node = new PionsDraggable();
            node.setPrefSize(98, 80);
            // define the style via css
            node.setStyle(
                    "-fx-background-color: #334488; "
                            + "-fx-text-fill: black; "
                            + "-fx-border-color: black;");
            // position the node
            node.setLayoutX(spacing*(i+1) + node.getPrefWidth()*i);
            node.setLayoutY(spacing);
            // add the node to the root pane
            root.getChildren().add(node);
        }

        // finally, show the stage
        primaryStage.setTitle("Draggable Node 01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * Simple draggable node.
 *
 * Dragging code based on {@link http://blog.ngopal.com.np/2011/06/09/draggable-node-in-javafx-2-0/}
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */

