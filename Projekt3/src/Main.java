import GUI.Menu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {


        Menu menu = new Menu(primaryStage);
        primaryStage.setScene(menu.getScene());
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Snake");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());

    }


    public static void main(String[] args) {
        launch(args);
    }
}
