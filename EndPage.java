package examform;

import java.io.File;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class EndPage extends Stage {

    private Label labName, labName2;
    private MediaPlayer mdPlayer2;
    private Pane p1;

    public EndPage() {
        
        this.initStyle(StageStyle.UNDECORATED);
        
        Label tq = new Label("THANK YOU");
        tq.setLayoutX(150);
        tq.setLayoutY(130);
        tq.setStyle("-fx-font-family:roboto; -fx-font-size: 40pt; -fx-font-weight: bold;");
        
        Button btnExit = new Button("EXIT");
        btnExit.setLayoutX(250);
        btnExit.setLayoutY(280);
        btnExit.setStyle("-fx-pref-width: 100px; -fx-background-radius: 10px; -fx-background-color: #6895C5; -fx-text-fill: white; -fx-font-family: roboto; -fx-font-weight: bold;");
        btnExit.setOnAction(e -> {
            this.close();
            System.exit(0);
        });
        
        Pane paneStrip = new Pane();
        paneStrip.setLayoutX(0);
        paneStrip.setLayoutY(0);
        paneStrip.setPrefSize(600,40);
        paneStrip.setStyle("-fx-border-color: black; -fx-background-color: #6895C5;");
        
        File bgg = new File("./data/coatofarms2.png");
        Image im = new Image(bgg.toURI().toString());
        BackgroundImage bi = new BackgroundImage(im,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(600, 350, false, false, true, true));
        Background bg = new Background(bi);

        p1 = new Pane();
        p1.setBackground(bg);
        p1.getChildren().add(paneStrip);
        p1.getChildren().add(tq);
        p1.getChildren().add(btnExit);
        p1.setStyle("-fx-border-color: black;");
        
        Scene byeScene = new Scene(p1, 600, 350);
        this.setScene(byeScene);

        String timesUp = "./data/timesup.wav";
        Media sound2 = new Media(new File(timesUp).toURI().toString());
        mdPlayer2 = new MediaPlayer(sound2); 
    }

    public void showStage() {
        mdPlayer2.play();
        this.show();
    }
   
}