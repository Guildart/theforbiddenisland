package Card;

import IleInterdite.Zone;

import java.util.ArrayList;

public abstract class Card<T>{
    private T type;

    public Card(T type){
        this.type = type;
    }
    public T getType(){
        return this.type;
    }

    public abstract void doAction(Zone zones);

}
