package Controller;

import IleInterdite.Island;
import IleInterdite.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class CVueMenu implements Initializable, Observer {

    @FXML
    public AnchorPane ancre;

    @FXML
    public Canvas canvas;

    @FXML
    public Button btn1;

    private Island modele;


    public void setModele(Island modele){
        this.modele = modele;
        modele.addObserver(this);
        modele.notifyObservers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLUE);
        g.fillRect(0,0,ancre.getWidth(),ancre.getHeight());
        repaint();*/
        //repaint();

    }

    @Override
    public void update() {
        repaint();
    }

    public void repaint(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLUE);
        g.fillRect(0,0,ancre.getWidth(),ancre.getHeight());
    }

    public void handleOnMouseClicked(MouseEvent mouseEvent) {

    }

    public void handleButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;

        if(actionEvent.getSource()==btn1){
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/Vue/vue.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


    }
}
