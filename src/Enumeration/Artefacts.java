package Enumeration;

import javafx.scene.image.Image;
import java.net.URL;


public enum Artefacts {
    air, eau, feu, terre, none;

    URL imgUrlAir = getClass().getResource("/image/vent.png") ;
    URL imgUrlFeu = getClass().getResource("/image/feu.png") ;
    URL imgUrlEau = getClass().getResource("/image/eau.png") ;
    URL imgUrlTerre = getClass().getResource("/image/terre2.png") ;
    URL imgUrlNone = getClass().getResource("/image/fondBateau.jpg") ;

    Image imageAir =  new Image(imgUrlAir.toExternalForm(),20,20,true,true);
    Image imageFeu =  new Image(imgUrlFeu.toExternalForm(),20,20,true,true);
    Image imageEau =  new Image(imgUrlEau.toExternalForm(),20,20,true,true);
    Image imageTerre =  new Image(imgUrlTerre.toExternalForm(),20,20,true,true);
    Image imageNone =  new Image(imgUrlNone.toExternalForm(),20,20,true,true);



    public TresorCard getKeyAssociated(){
        switch(this) {
            case air:
                return TresorCard.clef_air;
            case eau:
                return TresorCard.clef_eau;
            case feu:
                return TresorCard.clef_feu;
            case terre:
                return TresorCard.clef_terre;
        }
        return TresorCard.empty;
    }


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

    public Image getImageGris(){
        switch (this){
            case air:
                return  new Image(getClass().getResource("/image/vent_g.png").toExternalForm());
            case eau:
                return new Image(getClass().getResource("/image/eau_g.png").toExternalForm());
            case feu:
                return  new Image(getClass().getResource("/image/feu_g.png").toExternalForm());
            case terre:
                return  new Image(getClass().getResource("/image/terre_g2.png").toExternalForm());
        }
        return imageNone;

    }

    public Image getImageOrgi(){
        switch (this){
            case air:
                return  new Image(getClass().getResource("/image/vent.png").toExternalForm());
            case eau:
                return new Image(getClass().getResource("/image/eau.png").toExternalForm());
            case feu:
                return  new Image(getClass().getResource("/image/feu.png").toExternalForm());
            case terre:
                return  new Image(getClass().getResource("/image/terre2.png").toExternalForm());
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
