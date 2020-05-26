package JVFCVue;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CharacMove extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Group root = new Group();
        Scene scene = new Scene(root);

        scene.setFill(Color.BLACK);
        stage.setWidth(600);
        stage.setHeight(600);
        stage.setTitle("BBMAN");
        stage.setScene(scene);

        Charac c1 = new Charac();
        ImageView ivChar = new ImageView();
        ivChar.setImage(c1.getImage());
        ivChar.setX(c1.getX());
        ivChar.setY(c1.getY());

        root.setFocusTraversable(true);
        root.requestFocus();
        root.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent e)
            {
                switch(e.getCode())
                {
                    case UP:
                        c1.goUp();
                        break;
                    case DOWN:
                        c1.goDown();
                        break;
                    case LEFT:
                        c1.goLeft();
                        break;
                    case RIGHT:
                        c1.goRight();
                        break;
                    default:
                        break;
                }
                ivChar.setImage(c1.getImage());
                ivChar.setX(c1.getX());
                ivChar.setY(c1.getY());
            }
        });

        root.getChildren().add(ivChar);
        stage.show();
    }

    class Charac
    {
        private Image[] img;
        private double xLoc, yLoc;
        private int currentImg;

        public Image getImage() { return img[currentImg]; }
        public double getX() { return xLoc; }
        public double getY() { return yLoc; }

        public Charac()
        {
            img = new Image[4];
            img[0] = new Image(getClass().getResource("Images/bbright.png").toString());
            img[1] = new Image(getClass().getResource("Images/bbleft.png").toString());
            img[2] = new Image(getClass().getResource("Images/bbup.png").toString());
            img[3] = new Image(getClass().getResource("Images/bbfront.png").toString());
        }

        public void goRight()
        {
            xLoc += 1;
            currentImg = 0;
        }

        public void goLeft()
        {
            xLoc -= 1;
            currentImg = 1;
        }

        public void goUp()
        {
            yLoc -= 1;
            currentImg = 2;
        }

        public void goDown()
        {
            yLoc += 1;
            currentImg = 3;
        }
    }
}
