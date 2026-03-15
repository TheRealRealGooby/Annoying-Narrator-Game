package com.example.granddevoir2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;

import java.net.URL;
import java.util.Random;

// Defines possible spaces on map matrix
// o = object, e = enemy, v = void
enum MapSpaces {o, e, v};

// Controls main menu and game logic execution
public class MenuController {
    // UI elements linked directly from FXML layout
    public Button New_Game;
    public Button Options;
    public Button Help;
    // Stores main menu window reference
    public Stage currentStage; 
    // Plays background audio track
    private MediaPlayer mediaPlayer; 
    // Counts temporary attack boosts used during active fight
    int at=0; 
    // Counts total enemies on map to determine win condition
    int enemies=0; 

    // Executes on new game button press event
    @FXML
    private void onNewGameClick() {

        Random random = new Random();
        // Initializes 5x5 grid representing game world matrix
        String [][] map = new String[5][5]; 
        MapSpaces [] ms = MapSpaces.values();
        
        // Iterates through map to populate grid randomly with objects enemies or void spaces
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                map[i][j]= String.valueOf(ms[random.nextInt(ms.length)]);
            }
        }
        // Forces starting position at top-left to be empty void
        map[0][0]="v"; 

        // Initializes base JavaFX UI layouts for game window
        VBox messageBox = new VBox();
        messageBox.setPadding(new Insets(10));
        messageBox.setSpacing(5);
        messageBox.setAlignment(Pos.CENTER_LEFT);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(messageBox);

        // Sets up scene parameters and opens active game window
        Scene scene = new Scene(root, 500, 500);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.show();

        // Hides main menu window
        Button Back = new Button();
        currentStage = (Stage) New_Game.getScene().getWindow();
        currentStage.close();

        // Configures back button layout inside game grid
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setMargin(Back, new Insets(15));
        newWindow.setScene(scene);
        newWindow.show();
        Back.setText("<-");
        grid.add(Back, 0,0 );

        // Iterates through logical map matrix to draw visual colored squares
        for(int i =0;i<5;i++){
            for (int j=0;j<5;j++){
                switch (map[i][j]){
                    case "o" -> {  
                        // Draws object square in green
                        Label s = new Label();
                        s.setMinSize(50, 50);
                        s.setPrefSize(50, 50);
                        s.setMaxSize(50, 50);
                        s.setStyle("-fx-background-color: green;");
                        grid.add(s,i+1,j+1);
                    }
                    case "v" -> {  
                        // Draws void empty square in grey
                        Label s = new Label();
                        s.setMinSize(50, 50);
                        s.setPrefSize(50, 50);
                        s.setMaxSize(50, 50);
                        s.setStyle("-fx-background-color: grey;");
                        grid.add(s,i+1,j+1);
                    }
                    case "e" -> {  
                        // Draws enemy square in red
                        Label s = new Label();
                        s.setMinSize(50, 50);
                        s.setPrefSize(50, 50);
                        s.setMaxSize(50, 50);
                        s.setStyle("-fx-background-color: red;");
                        grid.add(s,i+1,j+1);
                        // Increments enemy counter variable for win condition evaluation
                        enemies++; 
                    }
                }
            }
        }

        // Sets up static visual labels for player stats
        Label defense = new Label("Defense");
        defense.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white;");
        Label life = new Label("Life");
        life.setStyle("-fx-background-color: magenta;" + "-fx-text-fill: white;");
        Label attack = new Label("Attack");
        attack.setStyle("-fx-background-color: indigo;" + "-fx-text-fill: white;");

        // Creates observable integer properties for dynamic UI updates
        IntegerProperty d = new SimpleIntegerProperty(1); 
        IntegerProperty l = new SimpleIntegerProperty(2); 
        IntegerProperty a = new SimpleIntegerProperty(3); 

        // Binds dynamic integer properties to visual text labels
        Label Defense = new Label();
        Defense.setStyle("-fx-background-color: white;" + "-fx-text-fill: blue;");
        Defense.textProperty().bind(d.asString());
        Label Life = new Label();
        Life.setStyle("-fx-background-color: white;" + "-fx-text-fill: magenta;");
        Life.textProperty().bind(l.asString());
        Label Attack = new Label();
        Attack.setStyle("-fx-background-color: white;" + "-fx-text-fill: indigo;");
        Attack.textProperty().bind(a.asString());

        // Adds stats labels to specific right side grid coordinates
        grid.add(defense, 7, 1);
        grid.add(life, 7, 2);
        grid.add(attack, 7, 3);
        grid.add(Defense, 8, 1);
        grid.add(Life, 8, 2);
        grid.add(Attack, 8, 3);

        // Sets up static visual labels for inventory categories
        Label grain = new Label("Grain");
        grain.setStyle("-fx-background-color: mediumseagreen;" + "-fx-text-fill: white;");
        Label wood = new Label("Wood");
        wood.setStyle("-fx-background-color: chocolate;" + "-fx-text-fill: white;");
        Label stone = new Label("Stone");
        stone.setStyle("-fx-background-color: darkgray;" + "-fx-text-fill: white;");
        Label armour = new Label("Armour");
        armour.setStyle("-fx-background-color: darksalmon;" + "-fx-text-fill: white;");
        Label enhacement = new Label("Enhacement");
        enhacement.setStyle("-fx-background-color: gold;" + "-fx-text-fill: white;");

        // Creates observable integer properties for resource quantities
        IntegerProperty g = new SimpleIntegerProperty(0); 
        IntegerProperty w = new SimpleIntegerProperty(0); 
        IntegerProperty s = new SimpleIntegerProperty(0); 
        IntegerProperty ar = new SimpleIntegerProperty(0); 
        IntegerProperty e = new SimpleIntegerProperty(0); 

        // Binds inventory integer properties to dynamic text labels
        Label Grain = new Label();
        Grain.setStyle("-fx-background-color: white;" + "-fx-text-fill: mediumseagreen;");
        Grain.textProperty().bind(g.asString());

        Label Wood = new Label();
        Wood.setStyle("-fx-background-color: white;" + "-fx-text-fill: chocolate;");
        Wood.textProperty().bind(w.asString());

        Label Stone = new Label();
        Stone.setStyle("-fx-background-color: white;" + "-fx-text-fill: darkgray;");
        Stone.textProperty().bind(s.asString());

        Label Armour = new Label();
        Armour.setStyle("-fx-background-color: white;" + "-fx-text-fill: darksalmon;");
        Armour.textProperty().bind(ar.asString());

        Label Enhacement = new Label();
        Enhacement.setStyle("-fx-background-color: white;" + "-fx-text-fill: gold;");
        Enhacement.textProperty().bind(e.asString());

        // Adds static inventory labels to bottom row of visual grid
        grid.add(grain, 1, 7);
        grid.add(wood, 2, 7);
        grid.add(stone, 3, 7);
        grid.add(armour, 4, 7);
        grid.add(enhacement, 5, 7);

        // Adds dynamic inventory quantity labels directly below static categories
        grid.add(Grain, 1, 8);
        grid.add(Wood, 2, 8);
        grid.add(Stone, 3, 8);
        grid.add(Armour, 4, 8);
        grid.add(Enhacement, 5, 8);

        // Instantiates visual representation of player as blue square
        Label playerView = new Label();
        playerView.setMinSize(50, 50);
        playerView.setPrefSize(50, 50);
        playerView.setMaxSize(50, 50);
        playerView.setStyle("-fx-background-color: blue;");
        
        // Sets initial player coordinates on visual grid mapping
        int[] position = {1, 1};
        grid.add(playerView, position[0], position[1]);

        // Creates logical player object holding base stats and logic
        Player p = new Player("lilman", 2, true, 0, 5);
        
        // Synchronizes UI property values with initial logical player stats
        d.set(p.defense);
        l.set(p.life);
        a.set(p.attack);
        
        // Sets initial player coordinates for logical map matrix
        int[] positionr = {0,0};

        // Synchronizes UI property values with initial logical inventory amounts
        g.set(p.inventory[0]);
        w.set(p.inventory[1]);
        s.set(p.inventory[2]);
        ar.set(p.inventory[3]);
        e.set(p.inventory[4]);

        // Requests window focus to capture keyboard events immediately
        root.requestFocus();

        // Registers event listener to process keyboard input actions
        scene.setOnKeyPressed(event -> {
            // Blocks input registration if player status is dead
            if(!p.status) return;
            
            // Evaluates pressed key code to determine action
            switch (event.getCode()) {
                case W -> {
                    // Moves player up on grid if not at top boundary
                    if (position[1] > 1) {
                        grid.getChildren().remove(playerView);
                        position[1]--;
                        grid.add(playerView, position[0], position[1]);
                        positionr[1]--;
                    }
                }
                case S -> {
                    // Moves player down on grid if not at bottom boundary
                    if (position[1] < 5) {
                        grid.getChildren().remove(playerView);
                        position[1]++;
                        grid.add(playerView, position[0], position[1]);
                        positionr[1]++;
                    }
                }
                case A -> {
                    // Moves player left on grid if not at left boundary
                    if (position[0] > 1) {
                        grid.getChildren().remove(playerView);
                        position[0]--;
                        grid.add(playerView, position[0], position[1]);
                        positionr[0]--;
                    }
                }
                case D -> {
                    // Moves player right on grid if not at right boundary
                    if (position[0] < 5) {
                        grid.getChildren().remove(playerView);
                        position[0]++;
                        grid.add(playerView, position[0], position[1]);
                        positionr[0]++;
                    }
                }
                case H ->{
                    // Attempts to build health well on empty void space
                    if(map[positionr[0]][positionr[1]]=="v") {
                        // Checks if inventory contains required materials for build
                        if(p.build("health")==null) {
                            Text t = new Text("You don't have enough resources to build you silly numbnut!");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                        }
                        else{
                            // Consumes resources to restore life completely
                            Text t = new Text("Wowzer, sissy-boy! You just drank from the Well of Life and your life is full now.");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                            
                            // Synchronizes UI with restored life and depleted resources
                            l.set(p.life);
                            g.set(p.inventory[0]);
                            w.set(p.inventory[1]);
                            s.set(p.inventory[2]);
                            
                            // Updates logical map space to mark presence of health building
                            map[positionr[0]][positionr[1]]="h";
                            
                            // Redraws visual grid square to show health building in yellowgreen
                            grid.getChildren().remove(playerView);
                            Label label = new Label();
                            label.setMinSize(50, 50);
                            label.setPrefSize(50, 50);
                            label.setMaxSize(50, 50);
                            label.setStyle("-fx-background-color: yellowgreen;");
                            grid.add(label,position[0],position[1]);
                            grid.add(playerView, position[0], position[1]);
                        }
                    }
                }
                case G ->{
                    // Attempts to build damage monument on empty void space
                    if(map[positionr[0]][positionr[1]]=="v") {
                        // Checks if inventory contains required materials for build
                        if(p.build("damage")==null) {
                            Text t = new Text("You don't have enough resources to build you silly numbnut!");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                        }
                        else{
                            // Consumes resources and life to increase attack permanently
                            Text t = new Text("Wowzer, big-bad-boy! You just drank from the Well of Death and you gained 1 attack permanently, but you lost 2 life.");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                            
                            // Synchronizes UI with new attack value life cost and depleted resources
                            l.set(p.life);
                            e.set(p.inventory[4]);
                            w.set(p.inventory[1]);
                            s.set(p.inventory[2]);
                            
                            // Updates logical map space to mark presence of damage building
                            map[positionr[0]][positionr[1]]="d";
                            
                            // Redraws visual grid square to show damage building in violet
                            grid.getChildren().remove(playerView);
                            Label label = new Label();
                            label.setMinSize(50, 50);
                            label.setPrefSize(50, 50);
                            label.setMaxSize(50, 50);
                            label.setStyle("-fx-background-color: violet;");
                            grid.add(label,position[0],position[1]);
                            grid.add(playerView, position[0], position[1]);
                        }
                    }
                }
            }
            
            // Retrieves current tile type from logical map matrix
            String field=map[positionr[0]][positionr[1]];

            // Evaluates tile type to trigger specific interactions
            switch (field) {
                case "o"->{
                    // Handles interaction with gatherable object tile
                    Quality [] q = Quality.values();
                    Type [] t = Type.values();
                    
                    // Generates random gatherable object with random quantity quality and type
                    GatherableObject go = new GatherableObject(random.nextInt(1,3), q[random.nextInt(0,3)], t[random.nextInt(0,5)]);
                    
                    // Adds generated object to player inventory
                    p.AddToInventory(go);
                    
                    // Updates specific UI inventory label based on collected object type
                    switch (go.type){
                        case grain -> g.set(p.inventory[0]);
                        case wood -> w.set(p.inventory[1]);
                        case stone -> s.set(p.inventory[2]);
                        case armour -> ar.set(p.inventory[3]);
                        case enhacement -> e.set(p.inventory[4]);
                    }
                    
                    // Replaces visual green square with grey void square
                    grid.getChildren().remove(playerView);
                    Label label = new Label();
                    label.setMinSize(50, 50);
                    label.setPrefSize(50, 50);
                    label.setMaxSize(50, 50);
                    label.setStyle("-fx-background-color: grey;");
                    grid.add(label,position[0],position[1]);
                    grid.add(playerView, position[0], position[1]);
                    
                    // Updates logical map to reflect empty void space
                    map[positionr[0]][positionr[1]]="v";
                }
                case "h"->{
                    // Restores player life to maximum on health well tile
                    p.life=5;
                    l.set(p.life);
                }
                case "d" ->{
                    // Refreshes UI labels for life and attack on damage monument tile
                    l.set(p.life);
                    a.set(p.attack);
                }
                case "e"->{
                    // Triggers combat event on enemy tile
                    Stage enemyWindow = new Stage();
                    GridPane layout = new GridPane();
                    Scene enemyScene = new Scene(layout, 400, 400);
                    
                    // Sets up combat window layout
                    enemyWindow.setScene(enemyScene);
                    layout.setVgap(10);
                    layout.setHgap(10);
                    layout.setPadding(new Insets(20));
                    
                    // Generates enemy with random attack defense and life stats
                    Enemy enemy = new Enemy("mostrous monster", random.nextInt(1,5), true, random.nextInt(0,4), random.nextInt(2,7));

                    // Initializes static UI labels for enemy combat stats
                    Label Enemy_Defense = new Label("Enemy Defense");
                    Enemy_Defense.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white;");
                    Label Enemy_Life = new Label("Enemy Life");
                    Enemy_Life.setStyle("-fx-background-color: magenta;" + "-fx-text-fill: white;");
                    Label Enemy_Attack = new Label("Enemy Attack");
                    Enemy_Attack.setStyle("-fx-background-color: indigo;" + "-fx-text-fill: white;");

                    // Creates observable integer properties for dynamic enemy stats
                    IntegerProperty ED = new SimpleIntegerProperty(1);
                    IntegerProperty EL = new SimpleIntegerProperty(2);
                    IntegerProperty EA = new SimpleIntegerProperty(3);

                    // Binds dynamic properties to enemy UI text labels
                    Label EDefense = new Label();
                    EDefense.setStyle("-fx-background-color: white;" + "-fx-text-fill: blue;");
                    EDefense.textProperty().bind(ED.asString());
                    Label ELife = new Label();
                    ELife.setStyle("-fx-background-color: white;" + "-fx-text-fill: magenta;");
                    ELife.textProperty().bind(EL.asString());
                    Label EAttack = new Label();
                    EAttack.setStyle("-fx-background-color: white;" + "-fx-text-fill: indigo;");
                    EAttack.textProperty().bind(EA.asString());
                    
                    // Displays combat window
                    enemyWindow.show();
                    
                    // Synchronizes UI property values with initial logical enemy stats
                    ED.set(enemy.defense);
                    EL.set(enemy.life);
                    EA.set(enemy.attack);
                    
                    // Adds enemy stats labels to combat layout
                    layout.add(Enemy_Defense, 1,1);
                    layout.add(Enemy_Life,2,1);
                    layout.add(Enemy_Attack,3,1);
                    layout.add(EDefense,1,2);
                    layout.add(ELife,2,2);
                    layout.add(EAttack,3,2);
                    
                    // Reuses and updates player stats labels for combat window
                    defense.setText("Your_Defense");
                    life.setText("Your_Life");
                    attack.setText("Your_Attack");
                    
                    // Adds player stats labels to combat layout
                    layout.add(defense, 1, 3);
                    layout.add(life, 2, 3);
                    layout.add(attack, 3, 3);
                    layout.add(Defense, 1, 4);
                    layout.add(Life, 2, 4);
                    layout.add(Attack, 3, 4);

                    // Creates action buttons for consuming items during combat
                    Button a_d = new Button("^");
                    Button g_l = new Button("^");
                    Button e_a = new Button("^");

                    // Adds action buttons to combat layout
                    layout.add(a_d,1,5);
                    layout.add(g_l,2,5);
                    layout.add(e_a,3,5);

                    // Adds static inventory labels below action buttons
                    layout.add(armour, 1, 6);
                    layout.add(grain, 2, 6);
                    layout.add(enhacement, 3, 6);

                    // Adds dynamic inventory labels to combat layout
                    layout.add(Armour, 1, 7);
                    layout.add(Grain, 2, 7);
                    layout.add(Enhacement, 3, 7);

                    // Creates button to initiate combat calculation
                    Button fight = new Button("FIGHT!");

                    // Adds fight button to combat layout
                    layout.add(fight,3,8);

                    // Handles armour boost button click event
                    a_d.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            // Checks for available armour items in inventory
                            if (p.inventory[3]>0){
                                // Increases defense stat and consumes armour item
                                p.defense++;
                                p.inventory[3]--;
                                // Synchronizes UI with updated defense and inventory values
                                ar.set(p.inventory[3]);
                                d.set(p.defense);
                            }
                        }
                    });

                    // Handles health restore button click event
                    g_l.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            // Checks for available grain items in inventory
                            if (p.inventory[0]>0){
                                // Increases life stat and consumes grain item
                                p.life++;
                                p.inventory[0]--;
                                // Synchronizes UI with updated life and inventory values
                                g.set(p.inventory[0]);
                                l.set(p.life);
                            }
                        }
                    });

                    // Handles attack boost button click event
                    e_a.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            // Checks for available enhancement items in inventory
                            if (p.inventory[4]>0){
                                // Increases attack stat and consumes enhancement item
                                p.attack++;
                                p.inventory[4]--;
                                // Synchronizes UI with updated attack and inventory values
                                e.set(p.inventory[4]);
                                a.set(p.attack);
                                // Increments temporary attack boost counter
                                at++;
                            }
                        }
                    });

                    // Handles combat resolution when fight button is pressed
                    fight.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            // Executes combat turns until either player or enemy dies
                            while(p.life>0 && enemy.life>0){
                                enemy.takedamage(p.damage());
                                p.takedamage(enemy.damage());
                            }
                            
                            // Processes enemy defeat event
                            if (enemy.life<=0) {
                                // Decrements total enemy counter
                                enemies--;
                                
                                // Replaces visual red square with grey void square
                                grid.getChildren().remove(playerView);
                                Label label = new Label();
                                label.setMinSize(50, 50);
                                label.setPrefSize(50, 50);
                                label.setMaxSize(50, 50);
                                label.setStyle("-fx-background-color: grey;");
                                grid.add(label,position[0],position[1]);
                                grid.add(playerView, position[0], position[1]);
                                
                                // Updates logical map to reflect empty void space
                                map[positionr[0]][positionr[1]]="v";
                                
                                // Adds enemy loot drop to player inventory
                                p.AddToInventory(enemy.drop());
                                
                                // Resets temporary attack boosts used during fight
                                p.attack-=at;
                                at=0;
                            }
                            
                            // Triggers game win condition if all enemies are defeated
                            if (enemies==0){
                                // Closes combat window and restores main menu
                                Stage fightstage = (Stage) fight.getScene().getWindow();
                                fightstage.close();
                                currentStage.show();
                                
                                // Creates and displays game won window
                                Stage gamewin = new Stage();
                                GridPane l = new GridPane();
                                Scene gw = new Scene(l, 200, 100);
                                gamewin.setScene(gw);
                                Text t = new Text("GAME WON, DUMMY!");
                                l.add(t,0,0);
                                gamewin.show();
                            }
                            
                            // Triggers game over condition if player dies
                            if (p.life<=0){
                                // Closes combat window and restores main menu
                                Stage fightstage = (Stage) fight.getScene().getWindow();
                                fightstage.close();
                                currentStage.show();
                                
                                // Creates and displays game over window
                                Stage gameover = new Stage();
                                GridPane l = new GridPane();
                                Scene go = new Scene(l, 200, 100);
                                gameover.setScene(go);
                                Text t = new Text("GAME OVER");
                                l.add(t,0,0);
                                gameover.show();
                            }
                            else {
                                // Restores main game window if player survives combat
                                Stage fightstage = (Stage) fight.getScene().getWindow();
                                fightstage.close();

                                // Re-attaches inventory labels back to main game layout
                                grid.add(grain, 1, 7);
                                grid.add(armour, 4, 7);
                                grid.add(enhacement, 5, 7);

                                grid.add(Grain, 1, 8);
                                grid.add(Armour, 4, 8);
                                grid.add(Enhacement, 5, 8);

                                // Re-attaches stats labels back to main game layout
                                grid.add(defense, 7, 1);
                                grid.add(life, 7, 2);
                                grid.add(attack, 7, 3);
                                grid.add(Defense, 8, 1);
                                grid.add(Life, 8, 2);
                                grid.add(Attack, 8, 3);

                                // Synchronizes UI with post-combat stats
                                l.set(p.life);
                                d.set(p.defense);
                                a.set(p.attack);

                                // Synchronizes UI with post-combat inventory
                                g.set(p.inventory[0]);
                                w.set(p.inventory[1]);
                                s.set(p.inventory[2]);
                                ar.set(p.inventory[3]);
                                e.set(p.inventory[4]);
                            }
                        }
                    });

                }
            }
        });

        // Configures back button logic to save game state and return to menu
        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Closes active game window and restores main menu stage
                Stage BackStage = (Stage) Back.getScene().getWindow();
                BackStage.close();
                currentStage.show();
            }
        });
    }

    // Executes on options button click event
    @FXML
    private void onOptionsClick() {
        // Checks if background music is currently stopped
        if (mediaPlayer == null) {
            // Retrieves audio file from local resources
            URL musicFile = getClass().getResource("/com/example/granddevoir2/lalala.mp3");
            Media sound = new Media(musicFile.toString());

            // Initializes media player with loaded audio track
            mediaPlayer = new MediaPlayer(sound);
            // Sets playback loop to infinite cycle
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
            // Starts audio playback
            mediaPlayer.play();  
        } else {
            // Stops currently playing audio track
            mediaPlayer.stop();
            // Resets media player object to null state
            mediaPlayer = null;
        }

        // Configures UI layout for options window
        Button Back = new Button();
        currentStage = (Stage) Options.getScene().getWindow();
        currentStage.close();
        Stage newWindow = new Stage();
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid,320,240);
        Text succes = new Text("Options");
        newWindow.setScene(scene);
        grid.add(succes,0,0);
        newWindow.show();
        
        // Configures back button logic to return to menu
        Back.setText("<-");
        grid.add(Back, 1, 1);
        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Closes options window and restores main menu stage
                Stage BackStage = (Stage) Back.getScene().getWindow();
                BackStage.close();
                currentStage.show();
            }
        });
    }

    // Executes on help button click event
    @FXML
    private void onHelpClick() {
        // Configures UI layout for help window
        Button Back = new Button();
        currentStage = (Stage) Help.getScene().getWindow();
        currentStage.close();
        Stage newWindow = new Stage();
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid,320,240);
        newWindow.setScene(scene);
        newWindow.show();
        
        // Adds back button to grid layout
        Back.setText("<-");
        grid.add(Back, 1, 1);
        
        // Defines technical gameplay instructions for player
Label text = new Label("CONTROLS:\n" +
        "- Use W, A, S, D keys to move player across 5x5 grid matrix\n\n" +
        "MAP TILES:\n" +
        "- Grey: Empty void space\n" +
        "- Green: Gatherable object (grain, wood, stone, armour, enhancement)\n" +
        "- Red: Enemy encounter\n\n" +
        "GATHERING & QUALITY:\n" +
        "- Stepping on green tile adds items to inventory\n" +
        "- Quality (Common, Rare, Epic) acts as multiplier for resource quantity\n\n" +
        "BUILDING SYSTEM:\n" +
        "- Press H on empty tile: Build 'Well of Life' (costs 2 wood, 2 stone, 5 grain) to restore full health\n" +
        "- Press G on empty tile: Build 'Well of Death' (costs 2 wood, 2 stone, 3 enhancement) to gain +1 permanent attack at cost of 2 life\n\n" +
        "COMBAT:\n" +
        "- Combat triggers on red tiles\n" +
        "- Player attacks first; fight continues until player or enemy life reaches 0\n" +
        "- Use Armour/Grain/Enhancement during fight to boost stats temporarily\n" +
        "- Defeated enemies drop random loot packages\n\n" +
        "WIN CONDITION:\n" +
        "- Clear all red enemy tiles from map to win game\n" +
        "- Progress is saved to 'PreviousGame.txt' upon exiting to menu");
        
        // Wraps text block in scrollable pane
        ScrollPane scrollPane = new ScrollPane(text);
        scrollPane.setPrefSize(330, 230);
        // Forces vertical scrollbar to remain visible always
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); 
        grid.add(scrollPane, 3, 3);

        // Configures back button logic to return to menu
        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Closes help window and restores main menu stage
                Stage BackStage = (Stage) Back.getScene().getWindow();
                BackStage.close();
                currentStage.show();
            }
        });

    }

    // Executes on exit button click event
    @FXML
    private void onExitClick() {
        // Terminates application process immediately
        System.exit(0);
    }
}
