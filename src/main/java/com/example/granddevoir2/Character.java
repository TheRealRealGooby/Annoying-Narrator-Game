package com.example.granddevoir2;

public abstract class Character {
    public String name;
    public int  attack;
    public boolean status = true;
    public int defense;
    public int life;

    public Character(String name, int attack, boolean status, int defense, int life) {
        this.name = name;
        this.attack = attack;
        this.status = status;
        this.defense = defense;
        this.life = life;
    }

    abstract int damage();
    abstract void takedamage(int d);
    abstract void die();

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
