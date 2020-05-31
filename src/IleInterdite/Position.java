package IleInterdite;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;


    public Position(int absc, int ord) {
        this.x = absc;
        this.y = ord;
    }

    @Override
    public String toString(){
        return "x: " + x + " y: " + y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    public boolean equals(Position p){
        return this.x == p.x && this.y == p.y;
    }

}
