package com.example.granddevoir2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;

import java.net.URL;
import java.util.Random;


enum MapSpaces {o, e, v};

public class MenuController {

    public Button Resume;
    public Button New_Game;
    public Button Options;
    public Button Help;
    public Stage currentStage;
    private MediaPlayer mediaPlayer;
    int at=0;
    int enemies=0;

    @FXML
    private void onResumeClick() {
        Button Back = new Button();
        currentStage = (Stage) Resume.getScene().getWindow();
        currentStage.close();
        Stage newWindow = new Stage();
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid,320,240);
        Text succes = new Text("Resume");
        newWindow.setScene(scene);
        grid.add(succes,0,0);
        newWindow.show();
        Back.setText("<-");
        grid.add(Back, 1, 1);
        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage BackStage = (Stage) Back.getScene().getWindow();
                BackStage.close();
                currentStage.show();
            }
        });
    }

    @FXML
    private void onNewGameClick() {

        Random random = new Random();
        String [][] map = new String[5][5];
        MapSpaces [] ms = MapSpaces.values();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                map[i][j]= String.valueOf(ms[random.nextInt(ms.length)]);
            }
        }
        map[0][0]="v";


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

        Scene scene = new Scene(root, 500, 500);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.show();

        Button Back = new Button();
        currentStage = (Stage) New_Game.getScene().getWindow();
        currentStage.close();

        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setMargin(Back, new Insets(15));
        newWindow.setScene(scene);
        newWindow.show();
        Back.setText("<-");
        grid.add(Back, 0,0 );
        for(int i =0;i<5;i++){
            for (int j=0;j<5;j++){
                switch (map[i][j]){
                    case "o" -> {
                        Label s = new Label();
                        s.setMinSize(50, 50);
                        s.setPrefSize(50, 50);
                        s.setMaxSize(50, 50);
                        s.setStyle("-fx-background-color: green;");
                        grid.add(s,i+1,j+1);
                    }
                    case "v" -> {
                        Label s = new Label();
                        s.setMinSize(50, 50);
                        s.setPrefSize(50, 50);
                        s.setMaxSize(50, 50);
                        s.setStyle("-fx-background-color: grey;");
                        grid.add(s,i+1,j+1);
                    }
                    case "e" -> {
                        Label s = new Label();
                        s.setMinSize(50, 50);
                        s.setPrefSize(50, 50);
                        s.setMaxSize(50, 50);
                        s.setStyle("-fx-background-color: red;");
                        grid.add(s,i+1,j+1);
                        enemies++;
                    }
                }
            }
        }


        Label defense = new Label("Defense");
        defense.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white;");
        Label life = new Label("Life");
        life.setStyle("-fx-background-color: magenta;" + "-fx-text-fill: white;");
        Label attack = new Label("Attack");
        attack.setStyle("-fx-background-color: indigo;" + "-fx-text-fill: white;");

        IntegerProperty d = new SimpleIntegerProperty(1);
        IntegerProperty l = new SimpleIntegerProperty(2);
        IntegerProperty a = new SimpleIntegerProperty(3);

        Label Defense = new Label();
        Defense.setStyle("-fx-background-color: white;" + "-fx-text-fill: blue;");
        Defense.textProperty().bind(d.asString());
        Label Life = new Label();
        Life.setStyle("-fx-background-color: white;" + "-fx-text-fill: magenta;");
        Life.textProperty().bind(l.asString());
        Label Attack = new Label();
        Attack.setStyle("-fx-background-color: white;" + "-fx-text-fill: indigo;");
        Attack.textProperty().bind(a.asString());

        grid.add(defense, 7, 1);
        grid.add(life, 7, 2);
        grid.add(attack, 7, 3);
        grid.add(Defense, 8, 1);
        grid.add(Life, 8, 2);
        grid.add(Attack, 8, 3);

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

        IntegerProperty g = new SimpleIntegerProperty(0);
        IntegerProperty w = new SimpleIntegerProperty(0);
        IntegerProperty s = new SimpleIntegerProperty(0);
        IntegerProperty ar = new SimpleIntegerProperty(0);
        IntegerProperty e = new SimpleIntegerProperty(0);

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

        grid.add(grain, 1, 7);
        grid.add(wood, 2, 7);
        grid.add(stone, 3, 7);
        grid.add(armour, 4, 7);
        grid.add(enhacement, 5, 7);

        grid.add(Grain, 1, 8);
        grid.add(Wood, 2, 8);
        grid.add(Stone, 3, 8);
        grid.add(Armour, 4, 8);
        grid.add(Enhacement, 5, 8);

        Image playerImage = new Image("C:\\Users\\Administrator\\IdeaProjects\\GrandDevoir2\\src\\main\\resources\\com\\example\\granddevoir2\\player2.png");
        ImageView playerView = new ImageView(playerImage);
        playerView.setFitWidth(50);
        playerView.setFitHeight(50);
        int[] position = {1, 1};
        grid.add(playerView, position[0], position[1]);

        Player p = new Player("lilman", 2, true, 0, 5);
        d.set(p.defense);
        l.set(p.life);
        a.set(p.attack);
        int[] positionr = {0,0};

        g.set(p.inventory[0]);
        w.set(p.inventory[1]);
        s.set(p.inventory[2]);
        ar.set(p.inventory[3]);
        e.set(p.inventory[4]);

        scene.setOnKeyPressed(event -> {
            if(!p.status) return;
            switch (event.getCode()) {
                case W -> {
                    if (position[1] > 1) {
                        grid.getChildren().remove(playerView);
                        position[1]--;
                        grid.add(playerView, position[0], position[1]);
                        positionr[1]--;
                    }
                }
                case S -> {
                    if (position[1] < 5) {
                        grid.getChildren().remove(playerView);
                        position[1]++;
                        grid.add(playerView, position[0], position[1]);
                        positionr[1]++;
                    }
                }
                case A -> {
                    if (position[0] > 1) {
                        grid.getChildren().remove(playerView);
                        position[0]--;
                        grid.add(playerView, position[0], position[1]);
                        positionr[0]--;
                    }
                }
                case D -> {
                    if (position[0] < 5) {
                        grid.getChildren().remove(playerView);
                        position[0]++;
                        grid.add(playerView, position[0], position[1]);
                        positionr[0]++;
                    }
                }
                case H ->{
                    if(map[positionr[0]][positionr[1]]=="v") {
                        if(p.build("health")==null) {
                            Text t = new Text("You don't have enough resources to build you silly numbnut!");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                        }
                        else{
                            Text t = new Text("Wowzer, sissy-boy! You just drank from the Well of Life and your life is full now.");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                            l.set(p.life);
                            g.set(p.inventory[0]);
                            w.set(p.inventory[1]);
                            s.set(p.inventory[2]);
                            map[positionr[0]][positionr[1]]="h";
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
                    if(map[positionr[0]][positionr[1]]=="v") {
                        if(p.build("damage")==null) {
                            Text t = new Text("You don't have enough resources to build you silly numbnut!");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                        }
                        else{
                            Text t = new Text("Wowzer, big-bad-boy! You just drank from the Well of Death and you gained 1 attack permanently, but you lost 2 life.");
                            messageBox.getChildren().clear();
                            messageBox.getChildren().add(t);
                            l.set(p.life);
                            e.set(p.inventory[4]);
                            w.set(p.inventory[1]);
                            s.set(p.inventory[2]);
                            map[positionr[0]][positionr[1]]="d";
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
            String field=map[positionr[0]][positionr[1]];

            switch (field) {
                case "o"->{
                    Quality [] q = Quality.values();
                    Type [] t = Type.values();
                    GatherableObject go = new GatherableObject(random.nextInt(1,3), q[random.nextInt(0,3)], t[random.nextInt(0,5)]);
                    p.AddToInventory(go);
                    switch (go.type){
                        case grain -> g.set(p.inventory[0]);
                        case wood -> w.set(p.inventory[1]);
                        case stone -> s.set(p.inventory[2]);
                        case armour -> ar.set(p.inventory[3]);
                        case enhacement -> e.set(p.inventory[4]);
                    }
                    grid.getChildren().remove(playerView);
                    Label label = new Label();
                    label.setMinSize(50, 50);
                    label.setPrefSize(50, 50);
                    label.setMaxSize(50, 50);
                    label.setStyle("-fx-background-color: grey;");
                    grid.add(label,position[0],position[1]);
                    grid.add(playerView, position[0], position[1]);
                    map[positionr[0]][positionr[1]]="v";
                }
                case "h"->{p.life=5;
                           l.set(p.life);}
                case "d" ->{l.set(p.life);
                            a.set(p.attack);}
                case "e"->{

                    Stage enemyWindow = new Stage();
                    GridPane layout = new GridPane();
                    Scene enemyScene = new Scene(layout, 400, 400);
                    enemyWindow.setScene(enemyScene);
                    layout.setVgap(10);
                    layout.setHgap(10);
                    layout.setPadding(new Insets(20));
                    Enemy enemy = new Enemy("mostrous monster", random.nextInt(1,5), true, random.nextInt(0,4), random.nextInt(2,7));

                    Label Enemy_Defense = new Label("Enemy Defense");
                    Enemy_Defense.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white;");
                    Label Enemy_Life = new Label("Enemy Life");
                    Enemy_Life.setStyle("-fx-background-color: magenta;" + "-fx-text-fill: white;");
                    Label Enemy_Attack = new Label("Enemy Attack");
                    Enemy_Attack.setStyle("-fx-background-color: indigo;" + "-fx-text-fill: white;");

                    IntegerProperty ED = new SimpleIntegerProperty(1);
                    IntegerProperty EL = new SimpleIntegerProperty(2);
                    IntegerProperty EA = new SimpleIntegerProperty(3);

                    Label EDefense = new Label();
                    EDefense.setStyle("-fx-background-color: white;" + "-fx-text-fill: blue;");
                    EDefense.textProperty().bind(ED.asString());
                    Label ELife = new Label();
                    ELife.setStyle("-fx-background-color: white;" + "-fx-text-fill: magenta;");
                    ELife.textProperty().bind(EL.asString());
                    Label EAttack = new Label();
                    EAttack.setStyle("-fx-background-color: white;" + "-fx-text-fill: indigo;");
                    EAttack.textProperty().bind(EA.asString());
                    enemyWindow.show();
                    ED.set(enemy.defense);
                    EL.set(enemy.life);
                    EA.set(enemy.attack);
                    layout.add(Enemy_Defense, 1,1);
                    layout.add(Enemy_Life,2,1);
                    layout.add(Enemy_Attack,3,1);
                    layout.add(EDefense,1,2);
                    layout.add(ELife,2,2);
                    layout.add(EAttack,3,2);
                    defense.setText("Your_Defense");
                    life.setText("Your_Life");
                    attack.setText("Your_Attack");
                    layout.add(defense, 1, 3);
                    layout.add(life, 2, 3);
                    layout.add(attack, 3, 3);
                    layout.add(Defense, 1, 4);
                    layout.add(Life, 2, 4);
                    layout.add(Attack, 3, 4);

                    Button a_d = new Button("^");
                    Button g_l = new Button("^");
                    Button e_a = new Button("^");

                    layout.add(a_d,1,5);
                    layout.add(g_l,2,5);
                    layout.add(e_a,3,5);

                    layout.add(armour, 1, 6);
                    layout.add(grain, 2, 6);
                    layout.add(enhacement, 3, 6);

                    layout.add(Armour, 1, 7);
                    layout.add(Grain, 2, 7);
                    layout.add(Enhacement, 3, 7);

                    Button fight = new Button("FIGHT!");

                    layout.add(fight,3,8);

                    a_d.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (p.inventory[3]>0){
                                p.defense++;
                                p.inventory[3]--;
                                ar.set(p.inventory[3]);
                                d.set(p.defense);
                            }
                        }
                    });

                    g_l.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (p.inventory[0]>0){
                                p.life++;
                                p.inventory[0]--;
                                g.set(p.inventory[0]);
                                l.set(p.life);
                            }
                        }
                    });

                    e_a.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (p.inventory[4]>0){
                                p.attack++;
                                p.inventory[4]--;
                                e.set(p.inventory[4]);
                                a.set(p.attack);
                                at++;
                            }
                        }
                    });

                    fight.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            while(p.life>0 && enemy.life>0){
                                enemy.takedamage(p.damage());
                                p.takedamage(enemy.damage());
                            }
                            if (enemy.life<=0) {
                                enemies--;
                                grid.getChildren().remove(playerView);
                                Label label = new Label();
                                label.setMinSize(50, 50);
                                label.setPrefSize(50, 50);
                                label.setMaxSize(50, 50);
                                label.setStyle("-fx-background-color: grey;");
                                grid.add(label,position[0],position[1]);
                                grid.add(playerView, position[0], position[1]);
                                map[positionr[0]][positionr[1]]="v";
                                p.AddToInventory(enemy.drop());
                                p.attack-=at;
                                at=0;
                            }
                            if (enemies==0){
                                Stage fightstage = (Stage) fight.getScene().getWindow();
                                fightstage.close();
                                currentStage.show();
                                Stage gamewin = new Stage();
                                GridPane l = new GridPane();
                                Scene gw = new Scene(l, 200, 100);
                                gamewin.setScene(gw);
                                Text t = new Text("GAME WON, DUMMY!");
                                l.add(t,0,0);
                                gamewin.show();
                            }
                            if (p.life<=0){
                                Stage fightstage = (Stage) fight.getScene().getWindow();
                                fightstage.close();
                                currentStage.show();
                                Stage gameover = new Stage();
                                GridPane l = new GridPane();
                                Scene go = new Scene(l, 200, 100);
                                gameover.setScene(go);
                                Text t = new Text("GAME OVER");
                                l.add(t,0,0);
                                gameover.show();
                            }
                            else {
                                Stage fightstage = (Stage) fight.getScene().getWindow();
                                fightstage.close();

                                grid.add(grain, 1, 7);
                                grid.add(armour, 4, 7);
                                grid.add(enhacement, 5, 7);

                                grid.add(Grain, 1, 8);
                                grid.add(Armour, 4, 8);
                                grid.add(Enhacement, 5, 8);

                                grid.add(defense, 7, 1);
                                grid.add(life, 7, 2);
                                grid.add(attack, 7, 3);
                                grid.add(Defense, 8, 1);
                                grid.add(Life, 8, 2);
                                grid.add(Attack, 8, 3);

                                l.set(p.life);
                                d.set(p.defense);
                                a.set(p.attack);

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

        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try (PrintWriter writer = new PrintWriter("C:\\Users\\Administrator\\IdeaProjects\\GrandDevoir2\\src\\main\\java\\com\\example\\granddevoir2\\PreviousGame.txt")) {
                    for(int i=0;i<5;i++){
                        for(int j=0;j<5;j++) writer.print(map[i][j]+" ");
                        writer.println();
                    }
                } catch (IOException e) {
                    System.err.println("A apărut o eroare: " + e.getMessage());
                }
                Stage BackStage = (Stage) Back.getScene().getWindow();
                BackStage.close();
                currentStage.show();
            }
        });
    }

    @FXML
    private void onOptionsClick() {
        if (mediaPlayer == null) {
            URL musicFile = getClass().getResource("/com/example/granddevoir2/lalala.mp3");
            Media sound = new Media(musicFile.toString());

            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Muzica va repeta la infinit
            mediaPlayer.play();  // Redă muzica
        } else {
            // Dacă muzica e deja redată, o oprește
            mediaPlayer.stop();
            mediaPlayer = null;
        }

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
        Back.setText("<-");
        grid.add(Back, 1, 1);
        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage BackStage = (Stage) Back.getScene().getWindow();
                BackStage.close();
                currentStage.show();
            }
        });
    }

    @FXML
    private void onHelpClick() {
        Button Back = new Button();
        currentStage = (Stage) Help.getScene().getWindow();
        currentStage.close();
        Stage newWindow = new Stage();
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid,320,240);
        //Text succes = new Text("Help");
        newWindow.setScene(scene);
        //grid.add(succes,0,0);
        newWindow.show();
        Back.setText("<-");
        grid.add(Back, 1, 1);
        Label text=new Label("Ești un supraviețuitor aruncat într-o lume plină de pericole și resurse. Harta pe care te deplasezi este o matrice pătrată, iar tu controlezi totul folosind tastele W, A, S, D. La fiecare pas, vei descoperi locuri goale, obiecte valoroase sau inamici de temut. Fiecare decizie contează.\n" +
                "\n" +
                "Pe spațiile goale poți construi clădiri sau obiecte care te vor ajuta în lupta pentru supraviețuire. Dacă dai peste un obiect, îl poți aduna și folosi mai târziu. Fii atent la calitatea lor: comună, rară sau epică. Calitatea influențează cât de multe resurse primești dintr-un copac, o rocă sau un lan de cereale.\n" +
                "\n" +
                "Dar ai grijă! Dacă întâlnești un inamic, începe o luptă. Tu lovești primul, dar și inamicul va riposta. Lupta continuă până când unul dintre voi moare. Învingătorul ia totul: inamicul înfrânt poate lăsa arme, armuri sau alte obiecte prețioase.\n" +
                "\n" +
                "Resursele tale - lemn, piatră și hrană - sunt cheia pentru a construi clădiri utile. Poți crea o „Fântână a Vieții” care să-ți refacă sănătatea sau un „Monument al Sabiei” pentru a-ți crește atacul. Dar toate acestea costă resurse, așa că planifică-ți bine fiecare mișcare.\n" +
                "\n" +
                "Pe măsură ce joci, progresul tău este salvat. Poți relua aventura oricând și să continui să explorezi, să construiești și să lupți. Supraviețuirea depinde de cum îți folosești resursele și de alegerile tale. Lumea este periculoasă, dar și plină de oportunități. Ești pregătit să faci față provocării?");
        grid.add(text,3,3);
        ScrollPane scrollPane = new ScrollPane(text);
        scrollPane.setPrefSize(330, 230);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        grid.add(scrollPane, 3, 3);

                Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage BackStage = (Stage) Back.getScene().getWindow();
                BackStage.close();
                currentStage.show();
            }
        });

    }

    @FXML
    private void onExitClick() {
        System.exit(0);
    }
}
