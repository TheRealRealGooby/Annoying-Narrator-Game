package com.example.granddevoir2;

enum Quality{common, rare, epic};
enum Type{grain,wood,stone,armour,enhacement};

public class GatherableObject {
    public int quantity;
    public Quality quality;
    public Type type;

    public GatherableObject(int quantity, Quality quality, Type type) {
        this.quantity = quantity;
        this.quality = quality;
        this.type=type;
    }

    //abstract void Gatherable();

    public int qualityMultiplier() {
        return switch(quality){
            case common -> 1;
            case rare -> 2;
            case epic -> 3;

        };
    }

    @Override
    public String toString() {
        return "GatherableObject{" +
                "quantity=" + quantity +
                ", quality=" + quality +
                ", type=" + type +
                '}';
    }
}

