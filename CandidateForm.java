package examform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class CandidateForm extends Stage {

    private Label candidateNationality, candidateAge, candidateGender, error;
    private TextField candidateName, candidateA;
    private ChoiceBox choiceBoxN, choiceBoxG;
    final private PasswordField password;
    private Label labResult, errorA;
    private File myf = new File("./data", "candidate.txt");
    private String name, passwd;
    private LinkedList<String> combolist = new LinkedList<String>();
    private Pane p1 = new Pane();

    public CandidateForm(){

        this.initStyle(StageStyle.UNDECORATED);
        
        Label login = new Label("LOG IN");
        login.setLayoutX(255);
        login.setLayoutY(60);
        login.setStyle("-fx-font-family:roboto; -fx-font-size: 20pt; -fx-font-weight: bold;");
        
        Label user = new Label("USERNAME");
        user.setLayoutX(100);
        user.setLayoutY(140);
        user.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight: bold;");
        
        final TextField username = new TextField() {
            @Override 
            public void replaceText(int start, int end, String text) {
                super.replaceText(start, end, text.toUpperCase());
            }      
        };
        username.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));
        username.setPromptText("Enter your username");
        username.setLayoutX(300);
        username.setLayoutY(140);
        username.setStyle("-fx-border-style: solid inside; -fx-background-color: white; -fx-font-family: roboto; -fx-border-color: #6895C5; -fx-font-weight: bold;");
        
        Label pass = new Label("PASSWORD");
        pass.setLayoutX(100);
        pass.setLayoutY(190);
        pass.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight: bold;");

        password = new PasswordField();
        password.setPromptText("Enter your password");
        password.setLayoutX(300);
        password.setLayoutY(190);
        password.setStyle("-fx-border-style: solid inside; -fx-background-color: white; -fx-font-family: roboto; -fx-border-color: #6895C5; -fx-font-weight: bold;");
        
        errorA = new Label("INVALID USERNAME OR PASSWORD ENTERED");
        errorA.setLayoutX(80);
        errorA.setLayoutY(240);
        errorA.setStyle("-fx-font-family:roboto; -fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: red;");
        
        Button btnLogin = new Button("VERIFY");
        btnLogin.setLayoutX(250);
        btnLogin.setLayoutY(280);
        btnLogin.setStyle("-fx-pref-width: 100px; -fx-background-radius: 10px; -fx-background-color: #6895C5; -fx-text-fill: white; -fx-font-family: roboto; -fx-font-weight: bold;");
        btnLogin.setOnAction(e -> {
            try{
                if (username.getText().isEmpty()) { 
                    p1.getChildren().add(errorA);
                } 
                else {
                    name = username.getText();
                    check(); 
                    if (password.getText().equals(passwd)) { 
                        MyParam.setName(name);
                        CandidateDetails();
                    } 
                    else { 
                        p1.getChildren().add(errorA);
                    }
                }
            }
            catch(Exception expt){
                this.show();
            }
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

        p1.setBackground(bg);
        p1.getChildren().add(paneStrip);
        p1.getChildren().add(login);
        p1.getChildren().add(user);
        p1.getChildren().add(username);
        p1.getChildren().add(pass);
        p1.getChildren().add(password);
        p1.getChildren().add(btnLogin);
        p1.setStyle("-fx-border-color: black;");
        
        Scene cScene = new Scene(p1, 600, 350);
        this.setScene(cScene);
        this.show(); 
        
        Platform.setImplicitExit(false);
        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    }

    public void read() {
        Scanner sfile;
        String rname;
        String pass;
        try {
            sfile = new Scanner(myf);
            String allData = "";
            while (sfile.hasNextLine()) {
                String aLine = sfile.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(":");
                while (sline.hasNext()) {
                    rname = sline.next();
                    pass = sline.next();
                    combolist.add(rname);
                }
                sline.close();
            }
            sfile.close();

        } catch (FileNotFoundException e) {
            labResult.setText("File to read " + myf + " not found!");
        }
    }

    public void check() {
        Scanner sfile;
        String rname;
        String pass;

        try {
            sfile = new Scanner(myf);
            String allData = "";
            while (sfile.hasNextLine()) {
                String aLine = sfile.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(":");
                while (sline.hasNext()) {
                    rname = sline.next();
                    pass = sline.next();
                    if (rname.equals(name)) {
                        passwd = pass;
                    }
                }
                sline.close();
            }
            sfile.close();
        } 
        catch (FileNotFoundException e) {
            labResult.setText("File to read " + myf + " not found!");
        }
    }

    public String getName() {
        return candidateName.getText();
    } 
    
    public void CandidateDetails() {
        
        candidateNationality = new Label("NATIONALITY ");
        candidateNationality.setLayoutX(100);
        candidateNationality.setLayoutY(70);
        candidateNationality.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight: bold;");
        
        choiceBoxN = new ChoiceBox();
        choiceBoxN.setLayoutX(300);
        choiceBoxN.setLayoutY(70);
        choiceBoxN.setStyle("-fx-border-style: solid inside; -fx-background-color: white; -fx-border-color: #6895C5; -fx-font-family: roboto; -fx-font-weight: bold;");
        choiceBoxN.getItems().add(null);
        choiceBoxN.getItems().add("NEPAL");
        choiceBoxN.getItems().add("ISRAEL");
        choiceBoxN.getItems().add("ESTONIA");
        choiceBoxN.getItems().add("MEXICO");
        choiceBoxN.getItems().add("BURKINA FASO");
        choiceBoxN.setTooltip(new Tooltip("Select your nationality"));
        
        candidateAge = new Label("AGE ");
        candidateAge.setLayoutX(100);
        candidateAge.setLayoutY(120);
        candidateAge.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight: bold;");
        
        candidateA = new TextField();
        candidateA.setPromptText("Enter your age");
        candidateA.setLayoutX(300);
        candidateA.setLayoutY(120);
        candidateA.setStyle("-fx-border-style: solid inside; -fx-background-color: white; -fx-font-family: roboto; -fx-border-color: #6895C5; -fx-font-weight: bold;");
        candidateA.textProperty().addListener(new ChangeListener <String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if (!newValue.matches("\\d*")) {
                    candidateA.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        final int maxLength = 3;
        candidateA.textProperty().addListener(new ChangeListener <String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (candidateA.getText().length() > maxLength) {
                    String s = candidateA.getText().substring(0, maxLength);
                    candidateA.setText(s);
                }
            }
        });

        candidateGender = new Label("GENDER ");
        candidateGender.setLayoutX(100);
        candidateGender.setLayoutY(170);
        candidateGender.setStyle("-fx-font-family:roboto; -fx-font-size: 16pt; -fx-font-weight: bold;");
        
        choiceBoxG = new ChoiceBox();
        choiceBoxG.setLayoutX(300);
        choiceBoxG.setLayoutY(170);
        choiceBoxG.setStyle("-fx-border-style: solid inside; -fx-background-color: white; -fx-border-color: #6895C5; -fx-font-family: roboto; -fx-font-weight: bold;");
        choiceBoxG.getItems().add(null);
        choiceBoxG.getItems().add("MALE");
        choiceBoxG.getItems().add("FEMALE");
        choiceBoxG.setTooltip(new Tooltip("Select your gender"));

        error = new Label("PLEASE ENTER IN ALL FIELDS");
        error.setLayoutX(170);
        error.setLayoutY(230);
        error.setStyle("-fx-font-family:roboto; -fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: red;");
        
        Button btnNext = new Button("NEXT");
        btnNext.setLayoutX(245);
        btnNext.setLayoutY(280);
        btnNext.setStyle("-fx-pref-width: 100px; -fx-background-radius: 10px; -fx-background-color: #6895C5; -fx-text-fill: white; -fx-font-family: roboto; -fx-font-weight: bold;");

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

        Pane cfPane = new Pane();
        cfPane.setBackground(bg);
        cfPane.getChildren().add(paneStrip);
        cfPane.getChildren().add(candidateNationality);
        cfPane.getChildren().add(choiceBoxN);
        cfPane.getChildren().add(candidateAge);
        cfPane.getChildren().add(candidateA);
        cfPane.getChildren().add(candidateGender);
        cfPane.getChildren().add(choiceBoxG);
        cfPane.getChildren().add(btnNext);
        cfPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
    
        btnNext.setOnAction(e -> {
            try{
                if(choiceBoxN.getValue() == null || candidateA.getText().isEmpty() || candidateA.getText().trim().isEmpty() || choiceBoxG.getValue() == null){
                    cfPane.getChildren().add(error);
                }
                else
                    MyParam.setNationality((String) choiceBoxN.getValue());
                    MyParam.setAge(candidateA.getText());
                    MyParam.setGender((String) choiceBoxG.getValue());
                    this.close();
                }
            catch(Exception ex){
                    this.show();
                }
        });

        Scene cfScene = new Scene(cfPane, 600, 350);
        this.setScene(cfScene);
        
        Platform.setImplicitExit(false);
        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    }
    
    
}