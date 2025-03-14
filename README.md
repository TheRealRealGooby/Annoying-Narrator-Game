# Annoying-Narrator-Game
A JavaFX-based survival strategy game where you explore, gather resources, build structures, and battle enemies, all while being guided by an annoyingly persistent narrator.

Features
Exploration: Move around a 5x5 tile map using W, A, S, D keys.
Resource Gathering: Collect grain, wood, stone, armor, and enhancement items.
Building: Construct helpful structures like the "Well of Life" and "Sword Monument" to boost your stats.
Combat System: Engage in turn-based fights with enemies. Win to collect loot, lose and it's game over!
Inventory Management: Track and use resources strategically for survival and upgrades.
Persistent Game State: Save and resume your progress.
Dynamic UI: Interactive JavaFX user interface with real-time updates on player stats and resources.
Sound: Optional background music toggle in the Options menu.

Technologies Used
Java 17
JavaFX
FXML
CSS for styling
Multimedia (Audio with MediaPlayer)

How to Run the Game
Prerequisites

Java 17 or newer.
JavaFX SDK properly configured.
Running the Application

Clone or download the project.
Run the HelloApplication.java from your IDE (IntelliJ IDEA recommended) or use command line tools.
swift
Copy
Edit
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d out src/com/example/granddevoir2/*.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp out com.example.granddevoir2.HelloApplication
Controls

W, A, S, D: Move around the map.
H: Build a "Well of Life" on empty land to restore health.
G: Build a "Sword Monument" on empty land to gain permanent attack power.
Combat and resource gathering are triggered by movement onto relevant tiles.
Use inventory items to improve stats before fighting enemies.
Menus

New Game: Start a new game with a randomly generated map.
Options: Toggle background music.
Help: View instructions and gameplay tips.
Exit: Quit the game.

Gameplay Description
You are a survivor in a perilous world, navigating a tile-based map full of dangers and opportunities. Explore, gather, build, and battle to survive. Defeat all enemies to win the game, or fall in battle and face defeat. Every choice matters!
Resources: Collect and manage grain, wood, stone, armor, and enhancement materials.
Enemies: Randomized with different stats and abilities.
Building System:
Well of Life: Costs grain, wood, and stone. Fully restores health.
Sword Monument: Costs wood, stone, and enhancements. Permanently increases attack, but reduces life.

Project Structure
cpp
Copy
Edit
src/
├── com.example.granddevoir2/
│   ├── HelloApplication.java        // Main application entry point
│   ├── MenuController.java          // Controller for main menu and game logic
│   ├── Character.java               // Abstract class for shared character logic
│   ├── Player.java                  // Player character implementation
│   ├── Enemy.java                   // Enemy character implementation
│   ├── Building.java                // Building structure logic
│   ├── GatherableObject.java        // Resources and item logic
│   └── Resources/                   // Images, sounds, and FXML files

Assets
hello-view.fxml: Defines the UI layout of the main menu.
menu.css: Styles the game UI.
player2.png: Player icon used in-game.
lalala.mp3: Optional background music.
