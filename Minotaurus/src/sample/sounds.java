package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class sounds {

    String fileName;
    public MediaPlayer player;
/*    file:///" + System.getProperty("user.dir").replace('\\', '/') + "/*/
    sounds(String fileName){
        this.fileName = fileName;
        Media m = new Media(new File(fileName).toURI().toString());
        player = new MediaPlayer(m);
    }
    public void play(){

        player.play();
    }
    public void stop(){
        player.stop();
    }
}
