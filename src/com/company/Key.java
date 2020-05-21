package com.company;

public enum Key {
    air(0), eau(0), feu(0), vent(0);

    private int Qte;

    Key(int Qte){
        this.Qte = Qte;
    }

    public int getQuantity(){
        return this.Qte;
    }

    public void setQuantity(int Qte){
        this.Qte = Qte;
    }

}