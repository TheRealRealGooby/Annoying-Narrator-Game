package com.example.granddevoir2;

// Defines building types player can construct
// health restores life, damage increases attack power at life cost
enum buildType{health, damage};

// Structure built by player on map
public class Building {
    buildType bT;

    public Building(buildType bT) {
        this.bT = bT;
    }

}