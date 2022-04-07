package GUI;

import Components.ScoresComparator;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HighScoresWindow {

private ListView listView;
private Scene scene;
private Stage scorseStage;

public HighScoresWindow(){


    Pane pane = new Pane();
    listView = new ListView();
    listView.setEditable(false);

    listView.setPrefSize(300,150);

    pane.getChildren().addAll(listView);
    scene = new Scene(pane,300,150);

    KeyCombination combination = new KeyCodeCombination(KeyCode.Q,KeyCodeCombination.SHIFT_DOWN,KeyCodeCombination.CONTROL_DOWN);
    scene.setOnKeyPressed(keyEvent -> {
        if(combination.match(keyEvent)){
           scorseStage.hide();
        }
    });

}


public Scene getScene(){
    return scene;
}

public void setListView(){

    List<String> list = new ArrayList<>();
    try {
        BufferedReader br = new BufferedReader(new FileReader("src\\Scores.txt"));

        String tmpLine;
        while ((tmpLine = br.readLine()) != null){
                list.add(tmpLine);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    Collections.sort(list,new ScoresComparator());
    listView.getItems().clear();
    listView.getItems().addAll(list);

}

public void setScorseStage(Stage scorseStage){
    this.scorseStage = scorseStage;
}

}
