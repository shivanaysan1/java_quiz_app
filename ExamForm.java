package examform;

import java.io.*;
import javafx.util.Duration;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ExamForm extends Application {

    private File myf = new File("./data", "question_list.txt");
    private File test = new File("./data", "test_result.txt");
    private int totQues = 0;
    private int activeQ = 1;
    private Label labQuesNo, labQues, labName, labAge, labGender;
    private ImageView imgFlag, imgQues, imgChoice1, imgChoice2, imgChoice3, imgChoice4;
    private Label labA, labB, labC, labD;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private ToggleGroup grpChoices;
    private Button btnPrev, btnNext, btnSubmit;
    private Pane mainPane;
    private Pane paneC;
    private Pane paneStrip;
    private Scene mainScene;
    private CandidateForm winGreeting;
    private EndPage winFarewell;
    private LinkedList<Question> quesList = new LinkedList<Question>();
    private long min, sec, totalSec = 0;
    private Label labTimer;
    private MediaPlayer mdPlayer1;
    private Timer timer;

    public void start(Stage mainStage) {
        
        mainStage.setTitle("Candidate Form");
        mainStage.initStyle(StageStyle.UNDECORATED);
        
        labName = new Label("");
        labName.setLayoutX(120);
        labName.setLayoutY(60);
        labName.setStyle("-fx-font-family:roboto; -fx-font-size: 12pt; -fx-font-weight:bold;");
        
        labAge = new Label("");
        labAge.setLayoutX(120);
        labAge.setLayoutY(80);
        labAge.setStyle("-fx-font-family:roboto; -fx-font-size: 12pt; -fx-font-weight:bold;");
        
        imgFlag = new ImageView();
        imgFlag.setLayoutX(25);
        imgFlag.setLayoutY(65);
        imgFlag.setFitHeight(55);
        imgFlag.setFitWidth(75);
        
        labGender = new Label("");
        labGender.setLayoutX(120);
        labGender.setLayoutY(100);
        labGender.setStyle("-fx-font-family:roboto; -fx-font-size: 12pt; -fx-font-weight:bold;");
        
        labQuesNo = new Label("");
        labQuesNo.setAlignment(Pos.CENTER);
        labQuesNo.setLayoutX(310);
        labQuesNo.setLayoutY(80);
        labQuesNo.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight:bold;");
        
        labQues = new Label("");
        labQues.setAlignment(Pos.CENTER);
        labQues.setTextAlignment(TextAlignment.CENTER);
        labQues.setStyle("-fx-font-family:roboto; -fx-font-size: 14pt; -fx-font-weight:bold;");

        labTimer = new Label();
        labTimer.setLayoutX(700);
        labTimer.setLayoutY(80);
        labTimer.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight:bold;");
        
        imgQues = new ImageView();
        imgQues.setLayoutX(150);
        imgQues.setLayoutY(115);
        //imgQues.setAlignment(Pos.CENTER);
        imgQues.setFitHeight(150);
        imgQues.setFitWidth(267);

        imgChoice1 = new ImageView();
        imgChoice1.setLayoutX(25);
        imgChoice1.setLayoutY(120);
        imgChoice1.setFitHeight(90);
        imgChoice1.setFitWidth(125);
        
        imgChoice2 = new ImageView();
        imgChoice2.setLayoutX(175);
        imgChoice2.setLayoutY(120);
        imgChoice2.setFitHeight(90);
        imgChoice2.setFitWidth(125);
        
        imgChoice3 = new ImageView();
        imgChoice3.setLayoutX(25);
        imgChoice3.setLayoutY(250);
        imgChoice3.setFitHeight(90);
        imgChoice3.setFitWidth(125);
        
        imgChoice4 = new ImageView();
        imgChoice4.setLayoutX(175);
        imgChoice4.setLayoutY(250);
        imgChoice4.setFitHeight(90);
        imgChoice4.setFitWidth(125);

        labA = new Label("A");
        labA.setLayoutX(100);
        labA.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");
        radChoice1 = new RadioButton("");
        radChoice1.setLayoutX(125);
        radChoice1.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");

        labB = new Label("B");
        labB.setLayoutX(100);
        labB.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");
        radChoice2 = new RadioButton("");
        radChoice2.setLayoutX(125);
        radChoice2.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");

        labC = new Label("C");
        labC.setLayoutX(100);
        labC.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");
        radChoice3 = new RadioButton("");
        radChoice3.setLayoutX(125);
        radChoice3.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");

        labD = new Label("D");
        labD.setLayoutX(100);
        labD.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");
        radChoice4 = new RadioButton("");
        radChoice4.setLayoutX(125);
        radChoice4.setStyle("-fx-font-family:roboto; -fx-font-size: 13pt;");
        
        grpChoices = new ToggleGroup();

        radChoice1.setToggleGroup(grpChoices);
        radChoice2.setToggleGroup(grpChoices);
        radChoice3.setToggleGroup(grpChoices);
        radChoice4.setToggleGroup(grpChoices);
        
        paneC = new Pane();
        paneC.setLayoutX(100);
        paneC.setLayoutY(75);
        paneC.getChildren().add(imgQues);
        paneC.getChildren().add(imgChoice1);
        paneC.getChildren().add(imgChoice2);
        paneC.getChildren().add(imgChoice3);
        paneC.getChildren().add(imgChoice4);
        paneC.getChildren().add(labA);
        paneC.getChildren().add(radChoice1);
        paneC.getChildren().add(labB);
        paneC.getChildren().add(radChoice2);
        paneC.getChildren().add(labC);
        paneC.getChildren().add(radChoice3);
        paneC.getChildren().add(labD);
        paneC.getChildren().add(radChoice4);
        
        paneStrip = new Pane();
        paneStrip.setLayoutX(0);
        paneStrip.setLayoutY(0);
        paneStrip.setPrefSize(800,40);
        paneStrip.setStyle("-fx-border-color: black; -fx-background-color: #6895C5;"); 
        
        btnPrev = new Button("←");
        btnPrev.setLayoutX(50);
        btnPrev.setLayoutY(500);
        btnPrev.setStyle("-fx-pref-width: 60px; -fx-font-size: 16pt; -fx-background-radius: 10px; -fx-background-color: #6895C5; -fx-text-fill: white; -fx-font-family:calibri; -fx-font-weight:bold;");
        btnPrev.setDisable(true);
        btnNext = new Button("→");
        btnNext.setLayoutX(140);
        btnNext.setLayoutY(500);
        btnNext.setStyle("-fx-pref-width: 60px; -fx-font-size: 16pt; -fx-background-radius: 10px; -fx-background-color: #6895C5; -fx-text-fill: white; -fx-font-family:calibri; -fx-font-weight:bold;");
        btnSubmit = new Button("END");
        btnSubmit.setLayoutX(650);
        btnSubmit.setLayoutY(510);
        btnSubmit.setStyle("-fx-pref-width: 100px; -fx-pref-length: 70px; -fx-background-radius: 10px; -fx-background-color: #6895C5; -fx-text-fill: white; -fx-font-family:roboto; -fx-font-weight:bold;");

        btnSubmit.setOnAction(e -> {
            saveToFile();
        });
        
        readFromFile();
        radChoice1.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, true);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, false);
        });
        
        radChoice2.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, true);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, false);
        });
        
        radChoice3.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, true);
            quesList.get(activeQ-1).setSelected(3, false);
        });
        
        radChoice4.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false);
            quesList.get(activeQ-1).setSelected(1, false);
            quesList.get(activeQ-1).setSelected(2, false);
            quesList.get(activeQ-1).setSelected(3, true);
        });
        
        if (totQues == 1)
            btnNext.setDisable(true);
        
        btnNext.setOnAction(e -> {
            activeQ++;
            btnPrev.setDisable(false);
            if (activeQ == totQues)
                btnNext.setDisable(true);
            reloadQues();
        });
        
        btnPrev.setOnAction(e -> {
            activeQ--;
            btnNext.setDisable(false);
            if (activeQ == 1)
                btnPrev.setDisable(true);
            reloadQues();
        });
        
        btnSubmit.setOnAction(e -> {
            mainStage.hide();
            saveToFile();
            winFarewell.showStage();
            stopTimer();
        });

        PauseTransition delay = new PauseTransition(Duration.seconds(181));
        delay.setOnFinished(e -> {
                mainStage.hide();
                saveToFile();
                winFarewell.showStage();
                stopTimer();
        });

        File bgg = new File("./data/coatofarms2.png");
        Image im = new Image(bgg.toURI().toString());
        BackgroundImage bi = new BackgroundImage(im,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(600, 350, false, false, true, true));
        Background bg = new Background(bi);

        HBox hbox = new HBox(labQues);
        hbox.setAlignment(Pos.CENTER);
        hbox.setLayoutX(150);
        hbox.setLayoutY(150);
        
        mainPane = new Pane();
        mainPane.setBackground(bg);
        mainPane.getChildren().add(paneStrip);
        mainPane.getChildren().add(labName);
        mainPane.getChildren().add(labAge);
        mainPane.getChildren().add(imgFlag);
        mainPane.getChildren().add(labGender);
        mainPane.getChildren().add(labQuesNo);
        mainPane.getChildren().add(labTimer);
        mainPane.getChildren().add(paneC);
        mainPane.getChildren().add(hbox);
        mainPane.getChildren().add(btnNext);
        mainPane.getChildren().add(btnPrev);
        mainPane.getChildren().add(btnSubmit);
        mainPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

        
        mainScene = new Scene(mainPane, 800, 600);
        mainStage.setScene(mainScene);
        reloadQues();
        winGreeting = new CandidateForm();
        winGreeting.setOnHiding(e -> {
            labName.setText(MyParam.getName());
            labAge.setText(MyParam.getAge());
            flags();
            labGender.setText(MyParam.getGender());
            mainStage.show();
            setTimer();
            delay.play();
        });
        winFarewell = new EndPage();
        
        Platform.setImplicitExit(false);
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    }
 
    public void reloadQues() {
        
        labQuesNo.setText("Question " + Integer.toString(activeQ)+ " of 25");
        labQues.setText(quesList.get(activeQ-1).getTheQues());
        
        radChoice1.setText(quesList.get(activeQ-1).getChoice(0));
        radChoice2.setText(quesList.get(activeQ-1).getChoice(1));
        radChoice3.setText(quesList.get(activeQ-1).getChoice(2));
        radChoice4.setText(quesList.get(activeQ-1).getChoice(3));
        imgQues.setImage(null);
        imgChoice1.setImage(null);
        imgChoice2.setImage(null);
        imgChoice3.setImage(null);
        imgChoice4.setImage(null);
        
        if (quesList.get(activeQ-1).getType() == 1) {
            
            labA.setLayoutY(125);
            radChoice1.setLayoutY(125);
            labB.setLayoutY(160);
            radChoice2.setLayoutY(160);
            labC.setLayoutY(195);
            radChoice3.setLayoutY(195);
            labD.setLayoutY(230);
            radChoice4.setLayoutY(230);
        }
        if (quesList.get(activeQ-1).getType() == 2) {
            File pFile = new File("data/" + quesList.get(activeQ-1).getQuesPic());
            Image img = new Image(pFile.toURI().toString());
            
            imgQues.setImage(img);
            labA.setLayoutX(50);
            labA.setLayoutY(275);
            radChoice1.setLayoutX(75);
            radChoice1.setLayoutY(275);
            
            labB.setLayoutX(50);
            labB.setLayoutY(310);
            radChoice2.setLayoutX(75);
            radChoice2.setLayoutY(310);
            
            labC.setLayoutX(50);
            labC.setLayoutY(345);
            radChoice3.setLayoutX(75);
            radChoice3.setLayoutY(345);
            
            labD.setLayoutX(50);
            labD.setLayoutY(380);
            radChoice4.setLayoutX(75);
            radChoice4.setLayoutY(380);
        }
        if (quesList.get(activeQ-1).getType() == 3) {
            File pFile1 = new File("data/" + quesList.get(activeQ-1).getAnsPic1());
            File pFile2 = new File("data/" + quesList.get(activeQ-1).getAnsPic2());
            File pFile3 = new File("data/" + quesList.get(activeQ-1).getAnsPic3());
            File pFile4 = new File("data/" + quesList.get(activeQ-1).getAnsPic4());
            Image img1 = new Image(pFile1.toURI().toString());
            Image img2 = new Image(pFile2.toURI().toString());
            Image img3 = new Image(pFile3.toURI().toString());
            Image img4 = new Image(pFile4.toURI().toString());
            imgChoice1.setImage(img1);
            imgChoice2.setImage(img2);
            imgChoice3.setImage(img3);
            imgChoice4.setImage(img4);
           
            imgChoice1.setImage(img1);
            imgChoice2.setImage(img2);
            imgChoice3.setImage(img3);
            imgChoice4.setImage(img4);

            imgChoice1.setLayoutX(20);
            radChoice1.setLayoutX(65);
            labA.setLayoutX(67);
            imgChoice1.setLayoutY(140);
            radChoice1.setLayoutY(250);
            labA.setLayoutY(280);

            imgChoice2.setLayoutX(150);
            radChoice2.setLayoutX(195);
            labB.setLayoutX(197);
            imgChoice2.setLayoutY(140);
            radChoice2.setLayoutY(250);
            labB.setLayoutY(280);

            imgChoice3.setLayoutX(280);
            radChoice3.setLayoutX(325);
            labC.setLayoutX(327);
            imgChoice3.setLayoutY(140);
            radChoice3.setLayoutY(250);
            labC.setLayoutY(280);

            imgChoice4.setLayoutX(410);
            radChoice4.setLayoutX(455);
            labD.setLayoutX(457);
            imgChoice4.setLayoutY(140);
            radChoice4.setLayoutY(250);
            labD.setLayoutY(280);
        }
        
        radChoice1.setSelected(quesList.get(activeQ-1).getSelected(0));
        radChoice2.setSelected(quesList.get(activeQ-1).getSelected(1));
        radChoice3.setSelected(quesList.get(activeQ-1).getSelected(2));
        radChoice4.setSelected(quesList.get(activeQ-1).getSelected(3));
    }

    public void readFromFile() {
        Scanner sfile;
        int type;
        char answer;
        String theQues;
        String choices[] = new String[4];
        String quesPic,quesPic1,quesPic2,quesPic3, quesPic4 ;
        Question ques;
        try {
            sfile = new Scanner(myf);
            String aLine = sfile.nextLine();
            Scanner sline = new Scanner(aLine);
            totQues = Integer.parseInt(sline.next());
            for (int k = 1; k <= totQues; k++) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                type = Integer.parseInt(sline.next());
                answer = sline.next().charAt(0);
                theQues = sline.next();
                quesPic = "";
                quesPic1 = "";
                quesPic2 = "";
                quesPic3 = "";
                quesPic4 = "";
               
                if (type == 3) { 
                    quesPic1 = sline.next();
                    quesPic2 = sline.next();
                    quesPic3 = sline.next();
                    quesPic4 = sline.next();
                                
                    ques = new Question(type, answer, theQues,  quesPic1,quesPic2, quesPic3, quesPic4);
                } else {
                    choices[0] = sline.next();
                    choices[1] = sline.next();
                    choices[2] = sline.next();
                    choices[3] = sline.next();

                    if (type == 2)
                        quesPic = sline.next();

                    ques = new Question(type, answer, theQues, choices, quesPic);
                }
               
                sline.close();
                
                quesList.add(ques);
            }
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File to read " + myf + " not found!");
        }
    }
    
   public void saveToFile() {
        
        try {
            PrintWriter pd = new PrintWriter(new FileWriter(test, true));
            
            for (int x = 0; x<25; x++) {
                if (quesList.get(x).getSelected(0)){
                    pd.print("A:");
                } else if (quesList.get(x).getSelected(1)){
                    pd.print("B:");
                }else if (quesList.get(x).getSelected(2)){
                    pd.print("C:");
                }else if (quesList.get(x).getSelected(3)){
                    pd.print("D:");
                }else {
                    pd.print("Z:");
                }
            }
            pd.print(labName.getText());
            pd.println();
            pd.close();
        }
        catch(IOException e) {
            System.out.println("MY ERROR : " + e);
        }
    }

    private String format(long value){
        if(value < 10){
            return 0 + "" + value;
        }
        return value + "";
    }
    
    public void convertTime(){
        min = TimeUnit.SECONDS.toMinutes(totalSec);
        sec = totalSec - (min * 60);
    }
    
    public void setTimer() {
        totalSec = 180;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            
            public void run() {
                convertTime();
                Platform.setImplicitExit(false);

                String timeLimitNear = "./data/timelimit_near.wav";
                Media sound1 = new Media(new File(timeLimitNear).toURI().toString());
                mdPlayer1 = new MediaPlayer(sound1);

                    if(totalSec > 0){
                        Platform.runLater(() -> labTimer.setText(format(min) + ":" + format(sec)));
                        totalSec--;
                        
                        if(totalSec == 59){
                                mdPlayer1.play();
                                labTimer.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight:bold; -fx-text-fill: red;");
                        }
                        else if(totalSec < 10){
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> labTimer.setVisible(false)),
                                new KeyFrame(Duration.seconds(0.1), evt -> labTimer.setVisible(true)));
                                timeline.setCycleCount(Animation.INDEFINITE);
                                timeline.play();
                                mdPlayer1.play();
                        }
                        else {
                        }
                    }
                    else{  
                    }
            }
            
        }, 0,1000);
    }
    
    private void stopTimer() {
        timer.cancel();
    }
    
    public void flags(){
        
        File pFileN = new File("./data/flagN.png");
        File pFileE = new File("./data/flagE.png");
        File pFileI = new File("./data/flagI.png");
        File pFileB = new File("./data/flagBF.png");
        File pFileM = new File("./data/flagM.png");

        Image imgN = new Image(pFileN.toURI().toString());
        Image imgE = new Image(pFileE.toURI().toString());
        Image imgI = new Image(pFileI.toURI().toString());
        Image imgB = new Image(pFileB.toURI().toString());
        Image imgM = new Image(pFileM.toURI().toString());

        switch (MyParam.getNationality()) {
            case "NEPAL":
                labName.setLayoutX(100);
                labName.setLayoutY(60);
                labAge.setLayoutX(100);
                labAge.setLayoutY(80);
                imgFlag.setImage(imgN);
                imgFlag.setFitHeight(50);
                imgFlag.setFitWidth(50);
                labGender.setLayoutX(100);
                labGender.setLayoutY(100);
                break;
            case "ESTONIA":
                imgFlag.setImage(imgE);
                break;
            case "ISRAEL":
                imgFlag.setImage(imgI);
                break;
            case "BURKINA FASO":
                imgFlag.setImage(imgB);
                break;
            case "MEXICO":
                imgFlag.setImage(imgM);
                break;
            default:  
                break;
        }
        
    }
    
    public static void main(String args[]) {
        Application.launch(args);
    }
 
}