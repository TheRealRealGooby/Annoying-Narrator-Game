package com.example.granddevoir2;

import java.util.Random;

public class Enemy extends Character{
    private GatherableObject droppedItem;

    public Enemy(String name, int attack, boolean status, int defense, int life) {
        super(name, attack, status, defense, life);
    }

    public GatherableObject drop(){
        Random random=new Random();
        Quality [] q = Quality.values();
        Type [] t = Type.values();
        GatherableObject go = new GatherableObject(random.nextInt(1,3), q[random.nextInt(0,3)], t[random.nextInt(0,5)]);
        return go;
    }

    @Override
    int damage() {
        return this.attack;
    }

    @Override
    void takedamage(int d) {
        if((this.defense-d)<0)
            this.life+=this.defense-d;
        else
            this.defense-=d;
    }

    @Override
    void die() {
        this.status = false;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                ", life=" + life +
                '}';
    }
}