package sample;



import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import static javafx.application.Platform.exit;

public class Main extends Application {

    sounds click;
    sounds menuMusic;
    @Override

    public void start(Stage stage) throws Exception{


        Parent faggot = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 48);


        //System.out.println("Working Directory = " + System.getProperty("user.dir"));

        String playClick = System.getProperty("user.dir") + "/src/sounds/select.wav";
        String srcMenuMusic = System.getProperty("user.dir") + "/src/sounds/menu.mp3";

        String picture = System.getProperty("user.dir") + "/src/minotaur.png";


        click = new sounds("src/sample/sounds/select.wav");
        menuMusic = new sounds("src/sample/sounds/menu.mp3");



        Image image = new Image("/sample/minotaur.png");
        ImageView view = new ImageView(image);
        ImageView view1 = new ImageView(image);
        ImageView view2 = new ImageView(image);
        ImageView view3 = new ImageView(image);


        //Buttons for menu
        int x = 0;
        Button bStart = new Button("Start");
        Button bSettings = new Button("Settings");
        Button bAbout = new Button("About");
        Button bExit = new Button("Exit");

        Button bBack = new Button("Back");
        Button bBack2 = new Button("Back");
        Button bSound = new Button("Sound: ON");
        Button bMusic = new Button("Music: ON");

        bStart.setPrefSize(200, 100);
        bExit.setPrefSize(200, 100);
        bSettings.setPrefSize(200, 100);
        bAbout.setPrefSize(200, 100);

        bStart.setLayoutY(x+10);
        x+=120;
        bSettings.setLayoutY(x);
        x+=110;
        bAbout.setLayoutY(x);
        x+=110;
        bExit.setLayoutY(x);


        bBack.setPrefSize(200, 100);
        bBack.setLayoutY(500);
        bBack.setLayoutX(200);

        bBack2.setPrefSize(200, 100);
        bBack2.setLayoutY(500);
        bBack2.setLayoutX(200);


        bSound.setPrefSize(200, 100);
        bSound.setLayoutY(200);
        bSound.setLayoutX(200);
        bMusic.setPrefSize(200, 100);
        bMusic.setLayoutY(200);
        bMusic.setLayoutX(500);


        String strAbout = "Minotaurus - is a not so funny game about mazes and running away from " +
                "minotaur. So far there is no minotaur yet. But I will probably add him on finals. " +
                "For it is a simple maze with almost no difficulties. Enjoy your short  time wasting!";
        Label lAbout = new Label(strAbout);
        //lAbout.setPrefSize(10, 20);
        lAbout.autosize();
        //lAbout.setRotate(270);
        lAbout.setFont(Font.font("Cambria", 32));
        lAbout.setWrapText(true);
        lAbout.setMaxWidth(400);
        lAbout.setLayoutX(20);

        lAbout.setTextFill(Color.web("#ffffff", 0.8));

        Group root = new Group(view,bStart, bExit, bSettings, bAbout);
        Group gRoot = new Group(view1);
        Group sRoot = new Group(view3, bBack2, bSound, bMusic);
        Group aRoot = new Group(view2, bBack, lAbout);
        Scene scene = new Scene(root, 1280, 720, Color.BEIGE);


        scene.getStylesheets().add("/sample/CSS/style.css");


        menuMusic.player.play();
        menuMusic.player.setVolume(0.4);



        Scene sGame = new Scene(gRoot, 1280, 720, Color.BEIGE);
        Scene sAbout = new Scene(aRoot, 1280, 720, Color.BEIGE);
        Scene sSettings = new Scene(sRoot, 1280, 720, Color.BEIGE);

        sAbout.getStylesheets().add("/sample/CSS/style.css");
        sSettings.getStylesheets().add("/sample/CSS/style.css");

        stage.setTitle("Minotaurus");
        stage.setScene(scene);
        stage.show();

        Game game = new Game();


        bStart.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(click::stop);

            stage.setScene(Game.geemu);
            game.gaming();
            Thread runner = new Thread(String.valueOf(this));
            runner.setDaemon(true);  // won't stop program from ending
            runner.start();
            stage.setTitle("Game");

        });

        bSettings.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(click::stop);
            stage.setScene(sSettings);

        });

        bAbout.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(click::stop);
            stage.setScene(sAbout);
        });

        bExit.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(() -> {
                exit();
            });
        });

        //aux buttons
        bBack.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(click::stop);
            stage.setScene(scene);
        });

        bBack2.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(click::stop);
            stage.setScene(scene);
        });

        //Checking whether sound on or off

        AtomicBoolean isPlayingSound = new AtomicBoolean(true);
        
        bSound.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(() -> {
                click.stop();
            });

            if(!isPlayingSound.get()){
                isPlayingSound.set(true);
                bSound.setText("Sound: ON");
            }else{
                isPlayingSound.set(false);
                bSound.setText("Sound: OFF");
            }

        });
        //Checking whether menu music is playing or not

        AtomicBoolean isPlayingMenu = new AtomicBoolean(true);
        bMusic.setOnAction(actionEvent ->  {
            click.play();
            click.player.setOnEndOfMedia(() -> {
                click.stop();
            });




            if(!isPlayingMenu.get()){
                isPlayingMenu.set(true);
                menuMusic.player.play();
                bMusic.setText("Music: ON");
            }else{
                isPlayingMenu.set(false);
                menuMusic.player.pause();
                bMusic.setText("Music: OFF");
            }


        });


    }


    public static void main(String[] args) {
        launch(args);
    }
}
