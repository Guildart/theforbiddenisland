package Enumeration;

import javafx.scene.image.Image;
import java.net.URL;


public enum Artefacts {
    air, eau, feu, terre, none;

    URL imgUrlAir = getClass().getResource("/image/vent.png") ;
    URL imgUrlFeu = getClass().getResource("/image/feu.png") ;
    URL imgUrlEau = getClass().getResource("/image/eau.png") ;
    URL imgUrlTerre = getClass().getResource("/image/terre.png") ;
    URL imgUrlNone = getClass().getResource("/image/fondBateau.jpg") ;

    Image imageAir =  new Image(imgUrlAir.toExternalForm(),20,20,true,true);
    Image imageFeu =  new Image(imgUrlFeu.toExternalForm(),20,20,true,true);
    Image imageEau =  new Image(imgUrlEau.toExternalForm(),20,20,true,true);
    Image imageTerre =  new Image(imgUrlTerre.toExternalForm(),20,20,true,true);
    Image imageNone =  new Image(imgUrlNone.toExternalForm(),20,20,true,true);

    public Image getImage(){
        switch (this){
            case air:
                return imageAir;
            case eau:
                return imageEau;
            case feu:
                return imageFeu;
            case terre:
                return imageTerre;
        }
        return imageNone;

    }

    public Image getImagResized(int x, int y){
        switch (this){
            case air:
                return new Image(imgUrlAir.toExternalForm(),x,y,true,true);
            case eau:
                return new Image(imgUrlFeu.toExternalForm(),x,y,true,true);
            case feu:
                return new Image(imgUrlEau.toExternalForm(),x,y,true,true);
            case terre:
                return new Image(imgUrlTerre.toExternalForm(),x,y,true,true);
        }
        return new Image(imgUrlNone.toExternalForm(),x,y,true,true);


    }


}
