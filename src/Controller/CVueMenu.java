package Controller;

import IleInterdite.*;
import com.sun.prism.PresentableState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

public class CVueMenu implements Initializable, Observer {

    @FXML
    public AnchorPane ancre;

    @FXML
    public Canvas canvas;

    @FXML
    public Button btn1;

    @FXML
    public RadioButton Explorateur;
    public RadioButton Ingénireur;
    public RadioButton Plongeur;
    public RadioButton Pilote;
    public RadioButton Navigateur;
    public RadioButton Messager;
    public Text text;

    HashMap<String, Player> hashMap1 = new HashMap<String, Player>();

    @FXML
    public ToggleGroup toggleGroup;
    private Island modele = new Island();
    private ArrayList<Player> listPlayers;
    private int nbJoueurs = 0;



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

        if(actionEvent.getSource()==Explorateur) {

            if(Explorateur.isSelected()){
                int[] tab = modele.getRandomPoint();
                //ImageView c = new Image("/image/jauge.png");
                URL imageURL = getClass().getResource("/image/explorateur.png");
                Player p = new Explorateur(modele.getGrille()[tab[0]][tab[1]],imageURL, modele);
                hashMap1.put("Explorateur",p);
                nbJoueurs++;

            }else{
                if(hashMap1.containsKey("Explorateur"))
                    hashMap1.remove("Explorateur");
                nbJoueurs--;
            }

        }

        if(actionEvent.getSource()==Ingénireur) {
            if(Ingénireur.isSelected()){
                int[] tab = modele.getRandomPoint();
                URL imageURL = getClass().getResource("/image/ingenieur.png");
                Player p = new Ingenieur(modele.getGrille()[tab[0]][tab[1]],imageURL, modele);
                hashMap1.put("Ingénireur",p);
                nbJoueurs++;

            }else{
                if(hashMap1.containsKey("Ingénireur"))
                    hashMap1.remove("Ingénireur");
                nbJoueurs--;

            }

        }

        if(actionEvent.getSource()==Plongeur) {
            if(Plongeur.isSelected()){
                int[] tab = modele.getRandomPoint();
                URL imageURL = getClass().getResource("/image/plongeur.png");
                Player p = new Plongeur(modele.getGrille()[tab[0]][tab[1]],imageURL, modele);
                hashMap1.put("Plongeur",p);
                nbJoueurs++;

            }else{
                if(hashMap1.containsKey("Plongeur"))
                    hashMap1.remove("Plongeur");
                nbJoueurs--;

            }

        }
        if(actionEvent.getSource()==Pilote) {
            if(Pilote.isSelected()){
                int[] tab = modele.getRandomPoint();
                URL imageURL = getClass().getResource("/image/pilote.png");
                Player p = new Pilote(modele.getGrille()[tab[0]][tab[1]],imageURL, modele);
                hashMap1.put("Pilote",p);
                nbJoueurs++;
            }else{
                if(hashMap1.containsKey("Pilote"))
                    hashMap1.remove("Pilote");
                nbJoueurs--;

            }

        }
        if(actionEvent.getSource()==Navigateur) {
            if(Navigateur.isSelected()){
                int[] tab = modele.getRandomPoint();
                URL imageURL = getClass().getResource("/image/plongeur.png");
                Player p = new Navigateur(modele.getGrille()[tab[0]][tab[1]],imageURL, modele);
                hashMap1.put("Navigateur",p);
                nbJoueurs++;

            }else{
                if(hashMap1.containsKey("Navigateur"))
                    hashMap1.remove("Navigateur");
                nbJoueurs--;

            }

        }
        if(actionEvent.getSource()==Messager) {
            if(Messager.isSelected()){
                int[] tab = modele.getRandomPoint();
                URL imageURL = getClass().getResource("/image/messager.png");
                Player p = new Messager(modele.getGrille()[tab[0]][tab[1]],imageURL, modele);
                hashMap1.put("Messager",p);
                nbJoueurs++;

            }else{
                if(hashMap1.containsKey("Messager"))
                    hashMap1.remove("Messager");
                nbJoueurs--;

            }

        }

        Stage stage;
        Parent root;
            if(actionEvent.getSource()==btn1){
                if (nbJoueurs >= 2) {

                    for(Map.Entry<String, Player> e : hashMap1.entrySet()){
                        modele.getListPlayers().add(e.getValue());
                    }

                    stage = (Stage) btn1.getScene().getWindow();
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/vue.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/Vue/vue.fxml"));

            CVue FXVue = (CVue) loader.getController();
                System.out.println("ca affiche "+ FXVue.maVAR);

            FXVue.setModele(modele);
            root =  loader.load();
            loader.setController(FXVue);*/ // bug chelou ?? ordre qui importe ??

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vue/vue.fxml"));
                    root = loader.load();

                    CVue FXVue= loader.getController(); // accès à l'instance controlleur
                    System.out.println("ca affiche "+ FXVue.maVAR);
                    System.out.println("ca affiche "+ modele.getListPlayers().size());
                    modele.setRoundOf(modele.getListPlayers().get(0));
                    FXVue.setModele(modele);

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }else
                    text.setFill(Color.RED);


            }


    }
}
