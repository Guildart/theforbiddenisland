package com.company;
import java.util.Random;

public enum Etat {
    none{
        @Override
        public String toString() {
            return "none";
        }
    },

    normale{
        @Override
        public String toString() {
            return "normale";
        }
    }, inondee{
        @Override
        public String toString() {
            return "inondee";
        }
    }, submergee{
        @Override
        public String toString() {
            return "submergee";
        }
    };

    public static Etat randomEtat()  {
        return Etat.values()[new Random().nextInt(Etat.values().length)];
    }

    public static void main(String[] args) {
        Etat a = Etat.inondee;
        System.out.print(a.toString());
    }

}

