package GUI;

import Components.Rozpocznij;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class NickWindow {

    private Scene scene;
    private TextField nickfield;
    private TextField szerokoscField;
    private TextField wysokoscField;
    private Stage menu;
    private Stage nickWin;

    public NickWindow(Stage menu){

        this.menu = menu;
        Pane pane = new Pane();

        Label nicklabel = new Label("Podaj Nick:");
        nicklabel.setFont(Font.font("S", FontWeight.BOLD,20));
        nicklabel.setTextFill(Color.WHITE);
        nicklabel.setLayoutX(10);
        nicklabel.setLayoutY(25);

        nickfield = new TextField();
        nickfield.setPromptText("Nick");
        nickfield.setPrefSize(150,20);
        nickfield.setLayoutY(25);
        nickfield.setLayoutX(140);
        nickfield.setFont(Font.font("S", FontWeight.BOLD,15));

        Label szerokosclabel = new Label("Podaj szerokość:");
        szerokosclabel.setFont(Font.font("",FontWeight.BOLD,20));
        szerokosclabel.setTextFill(Color.WHITE);
        szerokosclabel.setLayoutY(70);
        szerokosclabel.setLayoutX(25);
        szerokoscField = new TextField();
        szerokoscField.setPrefSize(50,12);
        szerokoscField.setFont(Font.font("",FontWeight.BOLD,12));
        szerokoscField.setLayoutY(105);
        szerokoscField.setLayoutX(70);
        Label minSzer = new Label("Min: 6    Max: 17");
        minSzer.setWrapText(true);
        minSzer.setFont(Font.font("a",FontWeight.BOLD,12));
        minSzer.setTextFill(Color.RED);
        minSzer.setPrefSize(50,50);
        minSzer.setLayoutY(90);
        minSzer.setLayoutX(130);


        Label wysokosclabel = new Label("Podaj wysokość:");
        wysokosclabel.setFont(Font.font("",FontWeight.BOLD,20));
        wysokosclabel.setTextFill(Color.WHITE);
        wysokosclabel.setLayoutX(250);
        wysokosclabel.setLayoutY(70);
        wysokoscField = new TextField();
        wysokoscField.setPrefSize(50,12);
        wysokoscField.setFont(Font.font("",FontWeight.BOLD,12));
        wysokoscField.setLayoutY(105);
        wysokoscField.setLayoutX(300);
        Label minWys = new Label("Min: 6    Max: 17");
        minWys.setWrapText(true);
        minWys.setFont(Font.font("a",FontWeight.BOLD,12));
        minWys.setTextFill(Color.RED);
        minWys.setPrefSize(50,50);
        minWys.setLayoutY(90);
        minWys.setLayoutX(360);


        Button okButton = new Button();
        okButton.setGraphic(new ImageView(new Image("images/OkButton.png")));
        okButton.setPadding(Insets.EMPTY);
        okButton.setLayoutX(185);
        okButton.setLayoutY(140);
        okButton.setOnAction(actionEvent -> {
            try {
                int wysokosc = Integer.parseInt(wysokoscField.getText());
                int szerokosc = Integer.parseInt(szerokoscField.getText());
                String nick = nickfield.getText();
                if(nick.compareTo("")==0)
                    throw new NumberFormatException("a");
                if(wysokosc<6 || wysokosc>17 || szerokosc<6 || szerokosc>17)
                    throw new NumberFormatException("a");

                nickfield.clear(); wysokoscField.clear(); szerokoscField.clear();

                Rozpocznij rozpocznij = new Rozpocznij(menu, nickWin,nick,wysokosc,szerokosc);

            }catch(NumberFormatException e){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Błąd");
                alert1.setHeaderText("Wprowadzono błędne dane lub nie podano nicku!");
                alert1.show();
            }
        });

        pane.getChildren().addAll(nicklabel, nickfield,szerokosclabel,szerokoscField,wysokosclabel,wysokoscField,okButton, minSzer, minWys);
        Image background = new Image("images/NickBackground.png");
        BackgroundImage bi = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        pane.setBackground(bg);
        scene = new Scene(pane,background.getWidth(),background.getHeight());

        KeyCombination combination = new KeyCodeCombination(KeyCode.Q,KeyCodeCombination.SHIFT_DOWN,KeyCodeCombination.CONTROL_DOWN);
        scene.setOnKeyPressed(keyEvent -> {
            if(combination.match(keyEvent)){
                menu.show();
                nickWin.hide();
            }
        });

    }

    public Scene getScene() {
        return scene;
    }

    public void clearText(){
        this.nickfield.setText("");
        this.szerokoscField.setText("");
        this.wysokoscField.setText("");
    }

    public void setNickWin(Stage nickWin) {
        this.nickWin = nickWin;
    }
}
