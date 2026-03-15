package com.example.granddevoir2;

// Determines resource yield based on item quality
enum Quality{common, rare, epic};
// Types of collectible items
enum Type{grain,wood,stone,armour,enhacement};

// Item picked up from map or dropped by enemy
public class GatherableObject {
    public int quantity;
    public Quality quality;
    public Type type;

    // Constructor
    public GatherableObject(int quantity, Quality quality, Type type) {
        this.quantity = quantity;
        this.quality = quality;
        this.type=type;
    }

    // Returns multiplier based on item quality
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