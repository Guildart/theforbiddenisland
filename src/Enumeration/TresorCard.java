package Enumeration;

import java.awt.*;

//Todo: Modif TresorCard
public enum TresorCard {
    air, eau, feu, terre, helicopter, sandbag, water;

    public Color getColor(){
        switch (this){
            case air:
                return new Color(56, 255, 30);
            case eau:
                return new Color(78, 173, 255);
            case feu:
                return new Color(255, 7, 22);
            case terre:
                return new Color(134, 39, 13);
            case sandbag:
                return new Color(134, 129, 7);
            case helicopter:
                return new Color(23, 11, 8);
        }
        return new Color(7, 10, 134);
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
