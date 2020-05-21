package com.company;

public class Position {
    public final int x;
    public final int y;

    public Position(int absc, int ord) {
        this.x = absc;
        this.y = ord;
    }

    public String toSrring(){
        return "x: " + x + " y: " + y;
    }
}
