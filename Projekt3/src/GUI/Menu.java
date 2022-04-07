package GUI;

import GUI.HighScoresWindow;
import GUI.NickWindow;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Menu {


    private Scene scene;
    private Stage nickStage;
    private Stage primaryStage;

    public Menu(Stage primaryStage){


        NickWindow nickWindow = new NickWindow(primaryStage);
        nickStage = new Stage();
        nickStage.setScene(nickWindow.getScene());
        nickStage.setResizable(false);
        nickStage.initModality(Modality.APPLICATION_MODAL);
        nickStage.setTitle("Snake");
        nickStage.setOnCloseRequest(e->{
            nickWindow.clearText();
            nickStage.hide();
        });
        nickWindow.setNickWin(nickStage);

        HighScoresWindow hsw = new HighScoresWindow();
        Stage scoresStage = new Stage();
        scoresStage.setResizable(false);
        scoresStage.setScene(hsw.getScene());
        scoresStage.setTitle("Snake");
        scoresStage.initModality(Modality.APPLICATION_MODAL);
        scoresStage.setOnCloseRequest(e->{
            scoresStage.hide();
        });
        hsw.setScorseStage(scoresStage);


        this.primaryStage = primaryStage;
        Pane pane = new Pane();

        Button newGameButton = new Button();
        newGameButton.setGraphic(new ImageView(new Image("images/NewGameButton.png")));
        newGameButton.setPadding(Insets.EMPTY);
        newGameButton.setLayoutX(80);
        newGameButton.setLayoutY(300);
        newGameButton.setOnAction(actionEvent -> {
            nickStage.setY(this.primaryStage.getY() + this.primaryStage.getScene().getWidth()/2 - nickStage.getScene().getWidth()/2);
            nickStage.setX(this.primaryStage.getX() + this.primaryStage.getScene().getHeight()/2 - nickStage.getScene().getHeight()/2);
            nickStage.show();
        });


        Button highScoresButton = new Button();
        highScoresButton.setGraphic(new ImageView(new Image("images/HighScoresButton.png")));
        highScoresButton.setPadding(Insets.EMPTY);
        highScoresButton.setLayoutX(260);
        highScoresButton.setLayoutY(300);
        highScoresButton.setOnAction(actionEvent -> {
            scoresStage.setY(this.primaryStage.getY() + this.primaryStage.getScene().getWidth()/2 - scoresStage.getScene().getWidth()/2);
            scoresStage.setX(this.primaryStage.getX() + this.primaryStage.getScene().getHeight()/2 - scoresStage.getScene().getHeight()/2);
            hsw.setListView();
            scoresStage.show();
        });


        Button exitButton = new Button();
        exitButton.setGraphic(new ImageView(new Image("images/ExitButton.png")));
        exitButton.setPadding(Insets.EMPTY);
        exitButton.setLayoutX(450);
        exitButton.setLayoutY(300);
        exitButton.setOnAction(actionEvent -> Platform.exit());


        pane.getChildren().addAll(newGameButton,highScoresButton,exitButton);
        Image backgroundMenu = new Image("images/SnakeBackground.png");
        BackgroundImage bi = new BackgroundImage(backgroundMenu, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        pane.setBackground(bg);
        scene = new Scene(pane,backgroundMenu.getWidth(),backgroundMenu.getHeight());

    }

    public Scene getScene() {
        return scene;
    }
}
