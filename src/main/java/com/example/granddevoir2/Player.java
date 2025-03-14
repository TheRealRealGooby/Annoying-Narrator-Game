package com.example.granddevoir2;

import java.util.Arrays;

public class Player extends Character {
    int [] inventory = new int[5];

    public Player(String name, int attack, boolean status, int defense, int life) {
        super(name, attack, status, defense, life);
        inventory[0]=5;
        inventory[1]=2;
        inventory[2]=2;
        inventory[4]=3;
    }

    public void AddToInventory(GatherableObject o){
        switch (o.type){
            case grain -> inventory[0]+=o.quantity*o.qualityMultiplier();
            case wood -> inventory[1]+=o.quantity*o.qualityMultiplier();
            case stone -> inventory[2]+=o.quantity*o.qualityMultiplier();
            case armour -> inventory[3]+=o.quantity*o.qualityMultiplier();
            case enhacement -> inventory[4]+=o.quantity*o.qualityMultiplier();
        }
    }

    void useObject(int i, int q){
        if(i<0 || i>4 || i==1 || i==2) return; // daca nu folosim un obiect din inventar sau daca incercam sa construim, suntem scosi din functie.
        else {
            switch (i){
                case 0 -> {
                    if(inventory[0]-q*3>=0)
                    {inventory[0]-=q*3;
                        life+=q;}
                    else {
                        System.out.println("You don't have enough grain, bitch!");
                        return;
                    }
                } // pentru 1 viata este nevoie de 3 grain
                case 3 -> {
                    if(inventory[3]-q>=0)
                    {inventory[3]-=q;
                        defense+=q;}
                    else {
                        System.out.println("You don't have enough armour, bitch!");
                        return;
                    }
                }
                case 4 -> {
                    if(inventory[4]-q>=0)
                    {inventory[4]-=q;
                        attack+=q;}
                    else {
                        System.out.println("You don't have enough enhancement, bitch!");
                        return;
                    }
                } // DUPA TERMINAREA UNEI LUPTE ATACUL REVINE LA VALOAREA INITIALA!!!!!!
            }
        }
    }

    public Building build(String s){
        if(s=="health" && inventory[1]>=2 && inventory[2]>=2 && inventory[0]>=5){ // pentru o cladire care restaureaza integral viata, este nevoie de 2wood, 2stone, 5grain.
            Building b = new Building(buildType.health);
            inventory[1]-=2;
            inventory[2]-=2;
            inventory[0]-=5;
            life=5; //??????cat va fi viata???????
            return b;
        } else if (s=="damage" && inventory[1]>=2 && inventory[2]>=2 && inventory[4]>=3) { // pentru o cladire care adauga permanent 1 la atac, este nevoie de 2wood, 2stone, 3enhancement.
            Building b = new Building(buildType.damage);
            inventory[1]-=2;
            inventory[2]-=2;
            inventory[4]-=3;
            life-=2;
            attack++;
            return b;
        }
        else return null;
    }

    public void showInventory(){
        System.out.println(inventory[0] + "grain " + inventory[1] + "wood " + inventory[2] + "stone " + inventory[3] +"armour " + inventory[4] + "enhancement");
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
        status=false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "defense=" + defense +
                ", life=" + life +
                ", attack=" + attack +
                ", status=" + status +
                '}';
    }
}
