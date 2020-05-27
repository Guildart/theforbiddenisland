package Enumeration;

import javafx.scene.paint.Color;

//Todo: Modif TresorCard
public enum TresorCard {
    air, eau, feu, terre, helicopter, sandbag, water;

    public Color getColor(){
        switch (this){
            case air:
                return  Color.rgb(56, 255, 30);
            case eau:
                return  Color.rgb(78, 173, 255);
            case feu:
                return  Color.rgb(255, 7, 22);
            case terre:
                return  Color.rgb(134, 39, 13);
            case sandbag:
                return Color.rgb(134, 129, 7);
            case helicopter:
                return  Color.rgb(23, 11, 8);
        }
        return  Color.rgb(7, 10, 134);
    }

    public String toString(){
        switch (this){
            case air:
                return "air";
            case eau:
                return "eau";
            case feu:
                return "feu";
            case terre:
                return "terre";
            case sandbag:
                return "sable";
            case helicopter:
                return "helico";
        }
        return "inconu";
    }
}
