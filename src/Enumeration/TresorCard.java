package Enumeration;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;

//Todo: Modif TresorCard
public enum TresorCard {
    clef_air, clef_eau, clef_feu, clef_terre, helicopter, sandbag, rising_water, empty;


    public Artefacts getArtefactsAssociated(){
        switch(this) {
             case clef_air:
                 return Artefacts.air;
             case clef_eau:
                 return Artefacts.eau;
             case clef_feu:
                 return Artefacts.feu;
             case clef_terre:
                 return Artefacts.terre;
        }
        return Artefacts.none;
    }

    public String toString(){
        switch (this){
            case clef_air:
                return "clef air";
            case clef_eau:
                return "clef eau";
            case clef_feu:
                return "clef feu";
            case clef_terre:
                return "clef terre";
            case sandbag:
                return "clef sable";
            case helicopter:
                return "clef helico";
            case rising_water:
                return "rising water";
        }
        return "inconu";

    }

    public String getURLForPlayersCards(){
        switch (this){
            case clef_air:
                return "/image/clef_vent2.png";
            case clef_eau:
                return "/image/clef_eau2.png";
            case clef_feu:
                return "/image/clef_feu2.png";
            case clef_terre:
                return "/image/clef_terre2.png";
            case sandbag:
                return "/image/sandbag2.png";
            case helicopter:
                return "/image/helico2.png";
            case empty:
                return "/image/carte_vide2.png";
        }
        return "inconu";
    }

    public String getURLForPlayersDiscard(){
        switch (this){
            case clef_air:
                return "/image/clef_vent.png";
            case clef_eau:
                return "/image/clef_eau.png";
            case clef_feu:
                return "/image/clef_feu.png";
            case clef_terre:
                return "/image/clef_terre.png";
            case sandbag:
                return "/image/sandbag.png";
            case helicopter:
                return "/image/helico.png";
            case empty:
                return "/image/carte_vide.png";
        }
        return "inconu";
    }
}
