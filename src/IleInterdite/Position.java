package IleInterdite;

public class Position {
    public final int x;
    public final int y;

    public Position(int absc, int ord) {
        this.x = absc;
        this.y = ord;
    }

    public String toString(){
        return "x: " + x + " y: " + y;
    }

    public boolean equals(Position p){
        return this.x == p.x && this.y == p.y;
    }
}
