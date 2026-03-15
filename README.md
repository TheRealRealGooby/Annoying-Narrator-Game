Annoying Narrator Game

A strategic 2D grid-based survival game built with JavaFX and Maven. The game features a persistent, "annoying" narrator that provides sarcastic feedback on player actions.

# How to Run

The game is cross-platform and can be executed on Windows, macOS, or Linux.
Run the game using the Terminal (All Platforms):

    Navigate to project root folder:
    cd Annoying-Narrator-Game

    Clean build artifacts:
    mvn clean

    Launch application:
    mvn javafx:run

OS Specific Prerequisites

    Windows: No extra steps required if JDK 21 is installed.

    macOS: No extra steps required.

    Linux (Ubuntu/Debian):

        Install GStreamer plugins for MP3 support

        Run: sudo apt install libgstreamer1.0-dev libgstreamer-plugins-base1.0-dev libgstreamer-plugins-good1.0-dev libgstreamer-plugins-ugly1.0-dev

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
