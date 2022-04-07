package GUI;

import Components.KontrolerRozpocznij;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class GameWindow {



    private Pane pane;
    private Scene scene;
    private String kierunek ="right";
    private int sec = 0;
    private int min = 0;
    private int hrs = 0;
    private Label timeLabel;
    private Label pointsLabel;
    private int points = 0;
    private GraphicsContext graphic;
    private Stage gameStage;
    private Stage menu;
    private KontrolerRozpocznij kr;




public GameWindow(int wys, int szer){

    pane = new Pane();
    Rectangle top = new Rectangle();
    top.setWidth(szer*50); top.setHeight(50);
    top.setX(0); top.setY(0);
    Color topColor = Color.rgb(46,46,46);
    top.setFill(topColor);

    Rectangle mainBackground = new Rectangle();
    mainBackground.setWidth(szer*50); mainBackground.setHeight(wys*50);
    mainBackground.setX(0); mainBackground.setY(50);
    Color mainColor = Color.rgb(124,124,124);
    mainBackground.setFill(mainColor);


    timeLabel = new Label("Time:  0:0:0");
    timeLabel.setFont(Font.font("", FontWeight.BOLD,17));
    timeLabel.setTextFill(Color.WHITE);
    timeLabel.setLayoutX(szer*50 - 130); timeLabel.setLayoutY(10);

    pointsLabel = new Label("Points: 0");
    pointsLabel.setFont(Font.font("",FontWeight.BOLD,17));
    pointsLabel.setTextFill(Color.WHITE);
    pointsLabel.setLayoutX(5); pointsLabel.setLayoutY(10);


    Canvas canvas = new Canvas(szer*50,wys*50);
    canvas.setLayoutX(0); canvas.setLayoutY(50);
    graphic = canvas.getGraphicsContext2D();
    graphic.setFill(Color.WHITE);
    graphic.fillRect(0,0,50,50);


    pane.getChildren().addAll(top,mainBackground,timeLabel,pointsLabel,canvas);
    scene = new Scene(pane,szer*50,wys*50+50);


    KeyCombination combination = new KeyCodeCombination(KeyCode.Q,KeyCodeCombination.SHIFT_DOWN,KeyCodeCombination.CONTROL_DOWN);
    scene.setOnKeyPressed(keyEvent -> {

        if(combination.match(keyEvent)){
            menu.show();
            kr.setWorking(false);
            gameStage.close();
        }

        if(keyEvent.getCode()== KeyCode.LEFT){
            if(!this.kierunek.equals("right")) {
                this.kierunek = "left";
            }
        }
        else
            if(keyEvent.getCode()==KeyCode.RIGHT){
                if(!this.kierunek.equals("left")) {
                    this.kierunek = "right";
                }
            }
            else
                if(keyEvent.getCode()==KeyCode.UP){
                    if(!this.kierunek.equals("down")) {
                        this.kierunek = "up";
                    }
                }
                else
                    if(keyEvent.getCode()==KeyCode.DOWN){
                        if(!this.kierunek.equals("up")) {
                            this.kierunek = "down";
                        }
                    }

    });

    }


    public Scene getScene() {
        return scene;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setStages(Stage game, Stage menu, KontrolerRozpocznij kr){
    this.gameStage = game;
    this.menu = menu;
    this.kr = kr;
    }

    public void changeTime(){
        if(min == 59 && sec == 59){
            hrs+=1;
            min = 0;
            sec = 0;
        }else{
            if(sec == 59){
                min += 1;
                sec = 0;
            }else
                sec+=1;
        }
        Platform.runLater(()->{
            timeLabel.setText("Time:  "+hrs+":"+min+":"+sec);
        });
    }

    public void addPoints(){
    this.points +=100;
    Platform.runLater(()->{
        pointsLabel.setText("Points: "+points);
    });
    }

    public void drawRect(int X, int Y){
    graphic.setFill(Color.WHITE);
    graphic.fillRect(X,Y,49,49);
    }

    public void clearRect(int X, int Y){
    graphic.clearRect(X,Y,50,50);
    }

    public void drawNormalPoint(int X, int Y){
    graphic.setFill(Color.GREEN);
    graphic.fillOval(X+10,Y+10,30,30);
    }
    public void drawSuperPoint(int X, int Y){
    graphic.setFill(Color.GOLD);
    graphic.fillOval(X+10,Y+10,30,30);
    }

    public void drawHitRect(int X, int Y){
    graphic.setFill(Color.RED);
    graphic.fillRect(X,Y,49,49);
    }

    public int getTime(){
    return hrs*3600+min*60+sec;
    }

    public int getPoints() {
        return points;
    }
}
