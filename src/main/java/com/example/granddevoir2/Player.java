package com.example.granddevoir2;

public class Player extends Character {
    // Array to store the quantity of 5 different resource types:
    // Index mapping: 0=grain, 1=wood, 2=stone, 3=armour, 4=enhancement
    int [] inventory = new int[5];

    // Constructor: initializes player and gives starting resources
    public Player(String name, int attack, boolean status, int defense, int life) {
        super(name, attack, status, defense, life);
        inventory[0]=5;
        inventory[1]=2;
        inventory[2]=2;
        inventory[4]=3;
    }

    // Adds gatherable object to player inventory
    // Amount added depends on the object quantity and quality(view GatherableObject.java)
    public void AddToInventory(GatherableObject o){
        switch (o.type){
            case grain -> inventory[0]+=o.quantity*o.qualityMultiplier();
            case wood -> inventory[1]+=o.quantity*o.qualityMultiplier();
            case stone -> inventory[2]+=o.quantity*o.qualityMultiplier();
            case armour -> inventory[3]+=o.quantity*o.qualityMultiplier();
            case enhacement -> inventory[4]+=o.quantity*o.qualityMultiplier();
        }
    }

    // Consumes items from inventory to boost player stats.
    // Can only use grain, enhancement and armour (wood and stone only for buildings)
    // i parameter = index of item type
    // q parameter = quantity to use
    void useObject(int i, int q){
        if(i<0 || i>4 || i==1 || i==2) return; // If item index not in inventory or points to wood or stone exit function
        else {
            switch (i){
                case 0 -> {  // Grain is consumed
                    if(inventory[0]-q*3>=0)
                    {inventory[0]-=q*3;
                        life+=q;} // Use 3 grain to gain 1 life
                    else {
                        System.out.println("You don't have enough grain, bitch!");
                        return;
                    }
                }
                case 3 -> { // Armour is consumed
                    if(inventory[3]-q>=0)
                    {inventory[3]-=q;
                        defense+=q;} // Use 1 armour to gain  1 defense
                    else {
                        System.out.println("You don't have enough armour, bitch!");
                        return;
                    }
                }
                case 4 -> { // Enhancement is consumed
                    if(inventory[4]-q>=0)
                    {inventory[4]-=q;
                        attack+=q;} // Use 1 enhancement to get 1 attack
                    else {
                        System.out.println("You don't have enough enhancement, bitch!");
                        return;
                    }
                } // After end of fight, attack returns to initial state (view MenuController.java - fight.setOnAction())
            }
        }
    }

    // Consumes items from inventory to create building
    // Receives "health"/"damage" as parameter and returns created Building object or null if resources are insufficient
    public Building build(String s){
        // For building that restores all health: 2wood, 2stone and 5grain
        if(s=="health" && inventory[1]>=2 && inventory[2]>=2 && inventory[0]>=5){
            Building b = new Building(buildType.health);
            inventory[1]-=2;
            inventory[2]-=2;
            inventory[0]-=5;
            life=5; // Reset health to 5
            return b;
        // For building that permanently adds 1 attack: 2wood, 2stone and 3enhancement
        } else if (s=="damage" && inventory[1]>=2 && inventory[2]>=2 && inventory[4]>=3) {
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

    // Utility method to print current inventory to console
    public void showInventory(){
        System.out.println(inventory[0] + "grain " + inventory[1] + "wood " + inventory[2] + "stone " + inventory[3] +"armour " + inventory[4] + "enhancement");
    }

    // Implement logic to abstract methods (view Character.java)
    
    @Override
    int damage() {
        // Deals damage equal to their current attack stat
        return this.attack;
    } 

    @Override
    void takedamage(int d) {
        // Armour blocks damage first. If damage exceeds defense, subtract from life.
        if((this.defense-d)<0) {
            this.life += this.defense - d;
            this.defense = 0; // Defense becomes 0 when armour is destroyed
        }
        else
            this.defense-=d;
    }

    @Override
    void die() {
        // Player is marked as dead
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
