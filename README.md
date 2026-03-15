Annoying Narrator Game

A strategic 2D grid-based survival game built with JavaFX and Maven. The game features a persistent, "annoying" narrator that provides sarcastic feedback on player actions.
# Execution Instructions

To run this project on Ubuntu (or any system with Maven and Java 21+), follow these steps:

    Clean the project build:
    Bash

    mvn clean

    Run the application:
    Bash

    mvn javafx:run

Note: Ensure you have the necessary GStreamer codecs installed on Ubuntu for the background music to function.
# The Concept: Why "Annoying Narrator"?

The game is titled Annoying Narrator Game because every action the player takes is met with snarky, sarcastic, or insulting commentary in the message log. Whether you are building, fighting, or simply failing to have enough resources, the narrator is there to remind you of your "inadequacy".
# Gameplay Mechanics
# The Grid World

The game world is a 5x5 matrix represented by colored tiles:

    Grey: Empty void space (safe for movement and building).

    Green: Gatherable objects (Resources).

    Red: Enemy encounters (Combat trigger).

    YellowGreen/Violet: Player-built structures.

# Controls

    W, A, S, D: Move the player (Blue Square) across the grid.

    H: Build a Well of Life (on empty tiles).

    G: Build a Monument of Death (on empty tiles).

# Building & Resources

You collect resources by stepping on green tiles. Resource yield is affected by the item's Quality (Common, Rare, or Epic).

    Well of Life: Costs 2 Wood, 2 Stone, 5 Grain. Restores Full Health.

    Monument of Death: Costs 2 Wood, 2 Stone, 3 Enhancement. Grants +1 Permanent Attack at the cost of 2 Life.

# Combat System

When stepping on a red tile, a combat window opens:

    Turn-based: Player attacks first, followed by the enemy.

    Consumables: During a fight, you can spend resources from your inventory to boost Defense, Life, or Attack temporarily.

    Loot: Defeated enemies drop random resource packages.

# Technical Details

    Language: Java 21

    Framework: JavaFX (with FXML for UI)

    Build Tool: Maven

    Audio: Background MP3 playback via MediaPlayer

    State Management: Dynamic UI updates using IntegerProperty binding

# Project Structure

    HelloApplication.java: Main entry point.

    MenuController.java: Core game logic and event handling.

    Character.java / Player.java / Enemy.java: Inheritance-based character system.

    GatherableObject.java: Resource and quality multiplier logic.
