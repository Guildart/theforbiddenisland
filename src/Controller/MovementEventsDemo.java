import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MovementEventsDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene sc = new Scene(root, 600, 600);
        stage.setScene(sc);
        stage.show();

        HBox hb = new HBox();
        VBox imageBox = new VBox();
        Node node1 = buildNode("red");
        Node node2 = buildNode("yellow");
        imageBox.getChildren().addAll(node1,node2);

        StackPane displayBox = new StackPane();
        displayBox.setStyle("-fx-border-width:2px;-fx-border-color:black;");
        HBox.setHgrow(displayBox,Priority.ALWAYS);
        hb.getChildren().addAll(imageBox,displayBox);
        root.getChildren().add(hb);

        displayBox.setOnDragOver(event -> {
            if (event.getGestureSource() != displayBox &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        displayBox.setOnDragEntered(event -> {
            if (event.getGestureSource() != displayBox && event.getDragboard().hasString()) {
                displayBox.setStyle("-fx-border-width:2px;-fx-border-color:black;-fx-opacity:.4;-fx-background-color:"+event.getDragboard().getString());
            }
            event.consume();
        });

        displayBox.setOnDragExited(event -> {
            if(!event.isAccepted()) {
                displayBox.setStyle("-fx-border-width:2px;-fx-border-color:black;");
                event.consume();
            }
        });

        displayBox.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                displayBox.setStyle("-fx-border-width:2px;-fx-border-color:black;-fx-background-color: "+db.getString());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

    }
    private Node buildNode(String color){
        StackPane node = new StackPane();
        node.setPrefSize(200,200);
        node.setStyle("-fx-background-color:"+color);
        node.setOnDragDetected(event -> {
            Dragboard db = node.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(node.snapshot(new SnapshotParameters(),null));
            content.putString(color);
            db.setContent(content);
            event.consume();
        });
        return node;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
