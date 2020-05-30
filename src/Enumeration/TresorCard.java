package Enumeration;

import javafx.scene.paint.Color;

//Todo: Modif TresorCard
public enum TresorCard {
    clef_air, clef_eau, clef_feu, clef_terre, helicopter, sandbag, rising_water;

    public Color getColor(){
        switch (this){
            case clef_air:
                return  Color.rgb(56, 255, 30);
            case clef_eau:
                return  Color.rgb(78, 173, 255);
            case clef_feu:
                return  Color.rgb(255, 7, 22);
            case clef_terre:
                return  Color.rgb(134, 39, 13);
            case sandbag:
                return Color.rgb(134, 129, 7);
            case helicopter:
                return  Color.rgb(23, 11, 8);
        }
        return  Color.rgb(7, 10, 134);
    }

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
}
