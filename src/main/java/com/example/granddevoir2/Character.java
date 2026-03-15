package com.example.granddevoir2;
// Base abstract class for all characters in game (Player,Enemy)
// Defines all all attributes and methods of all future characters
public abstract class Character {
    public String name;
    public int  attack; // Attack power (how much damage they deal)
    public boolean status = true; // Character state:alive or dead (true or false)
    public int defense; //Armour value
    public int life; // Health value
//Constructor
    public Character(String name, int attack, boolean status, int defense, int life) {
        this.name = name;
        this.attack = attack;
        this.status = status;
        this.defense = defense;
        this.life = life;
    }
//Abstract methods
    abstract int damage(); // Returns damage character deals in attack
    abstract void takedamage(int d); // Take damage method (life-=d)
    abstract void die(); // Change 'status' to false => die

    // Utility method to display character stats as text (debugging)
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", status=" + status +
                ", defense=" + defense +
                ", life=" + life +
                '}';
    }
}
