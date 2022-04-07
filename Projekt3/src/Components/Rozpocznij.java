package Components;

import Components.KontrolerRozpocznij;
import Components.ModelRozpocznij;
import Components.Point;
import GUI.GameWindow;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Rozpocznij {

    private String nick;
    private Stage menu;
    private Stage nickWin;
    private GameWindow gameWindow;
    private Stage gameStage;

    private KontrolerRozpocznij kontrolerRozpocznij;

// Wątek czasu//////////////////////////////////////////////////
    Thread timeThread = new Thread(()->{

        while(kontrolerRozpocznij.isWorking()){

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            gameWindow.changeTime();
        }
    });

//Ruch węża///////////////////////////////////////////////////////////////

    Thread snakeThread = new Thread(()->{

        while(kontrolerRozpocznij.isWorking()){
            Point pointToAdd = null;

            try {
                Thread.sleep(kontrolerRozpocznij.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(gameWindow.getKierunek().equals("right")){

                    if(kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getX() == kontrolerRozpocznij.getSzer()*50 - 50) {
                        kontrolerRozpocznij.setNotWall(false);
                    }
                    else
                        pointToAdd = findPoint(kontrolerRozpocznij.getOgolem(),kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getX()+50,kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getY());

            }

            if(gameWindow.getKierunek().equals("left")){

                    if(kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getX() == 0) {
                        kontrolerRozpocznij.setNotWall(false);
                    }
                    else
                        pointToAdd = findPoint(kontrolerRozpocznij.getOgolem(),kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getX()-50,kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getY());
            }

            if(gameWindow.getKierunek().equals("up")){

                if(kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getY() == 0) {
                    kontrolerRozpocznij.setNotWall(false);
                }
                else
                    pointToAdd = findPoint(kontrolerRozpocznij.getOgolem(),kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getX(),kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getY()-50);
            }

            if(gameWindow.getKierunek().equals("down")){

                if(kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getY() == kontrolerRozpocznij.getWys()*50-50) {
                    kontrolerRozpocznij.setNotWall(false);
                }
                else
                    pointToAdd = findPoint(kontrolerRozpocznij.getOgolem(),kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getX(),kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getY()+50);
            }

            if(kontrolerRozpocznij.isNotWall() && checkPoint(kontrolerRozpocznij.getZajete(),pointToAdd.getX(),pointToAdd.getY())){

                gameWindow.drawRect(pointToAdd.getX(),pointToAdd.getY());
                kontrolerRozpocznij.getZajete().add(pointToAdd);
                removePoint(kontrolerRozpocznij.getWolne(),pointToAdd.getX(),pointToAdd.getY());

                if(pointToAdd.getX()==kontrolerRozpocznij.getPoint().getX() && pointToAdd.getY() == kontrolerRozpocznij.getPoint().getY()){

                    gameWindow.addPoints();
                    if(kontrolerRozpocznij.isSuperPoint()){
                        if(kontrolerRozpocznij.getZajete().size()>1){
                            gameWindow.clearRect(kontrolerRozpocznij.getZajete().get(0).getX(),kontrolerRozpocznij.getZajete().get(0).getY());
                            kontrolerRozpocznij.getWolne().add(findPoint(kontrolerRozpocznij.getZajete(),kontrolerRozpocznij.getZajete().get(0).getX(),kontrolerRozpocznij.getZajete().get(0).getY()));
                            kontrolerRozpocznij.getZajete().remove(0);
                        }
                        gameWindow.clearRect(kontrolerRozpocznij.getZajete().get(0).getX(),kontrolerRozpocznij.getZajete().get(0).getY());
                        kontrolerRozpocznij.getWolne().add(findPoint(kontrolerRozpocznij.getZajete(),kontrolerRozpocznij.getZajete().get(0).getX(),kontrolerRozpocznij.getZajete().get(0).getY()));
                        kontrolerRozpocznij.getZajete().remove(0);
                        kontrolerRozpocznij.setSuperPoint(false);
                    }


                    int random = (int)(Math.random()*kontrolerRozpocznij.getWolne().size());
                    kontrolerRozpocznij.setPoint(kontrolerRozpocznij.getWolne().get(random));
                    kontrolerRozpocznij.setCounter(kontrolerRozpocznij.getCounter()+1);
                    if(kontrolerRozpocznij.getCounter()>=10){
                        int spr = (int)(Math.random()*100);
                        if(spr%2 == 0){
                            kontrolerRozpocznij.setCounter(0);
                            gameWindow.drawSuperPoint(kontrolerRozpocznij.getWolne().get(random).getX(),kontrolerRozpocznij.getWolne().get(random).getY());
                            kontrolerRozpocznij.setSuperPoint(true);
                        }else {
                            gameWindow.drawNormalPoint(kontrolerRozpocznij.getWolne().get(random).getX(), kontrolerRozpocznij.getWolne().get(random).getY());
                        }
                    }else{
                        gameWindow.drawNormalPoint(kontrolerRozpocznij.getWolne().get(random).getX(),kontrolerRozpocznij.getWolne().get(random).getY());
                    }

                    if(kontrolerRozpocznij.getSpeed() > 100)
                        kontrolerRozpocznij.setSpeed(kontrolerRozpocznij.getSpeed()-2);
                }else {
                    gameWindow.clearRect(kontrolerRozpocznij.getZajete().get(0).getX(),kontrolerRozpocznij.getZajete().get(0).getY());
                    kontrolerRozpocznij.getWolne().add(findPoint(kontrolerRozpocznij.getZajete(),kontrolerRozpocznij.getZajete().get(0).getX(),kontrolerRozpocznij.getZajete().get(0).getY()));
                    kontrolerRozpocznij.getZajete().remove(0);
                }

            }else{
                gameWindow.drawHitRect(kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getX(),kontrolerRozpocznij.getZajete().get(kontrolerRozpocznij.getZajete().size()-1).getY());
                kontrolerRozpocznij.setWorking(false);


                int a = gameWindow.getTime();
                a += kontrolerRozpocznij.getSzer()*kontrolerRozpocznij.getWys();
                int resA = gameWindow.getPoints()*100/a;
                String str = nick+":  "+resA+"   pkt";

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("src\\Scores.txt",true));
                    bw.newLine();
                    bw.write(str);
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Platform.runLater(()-> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Koniec Gry");
                    alert.setHeaderText("Koniec Gry");
                    alert.show();
                    alert.setOnHiding(dialogEvent -> {
                        menu.show();
                        this.gameStage.hide();
                    });
                });
            }

        }
    });



    public Rozpocznij(Stage menu, Stage nickWin, String nick, int wys, int szer) {
        this.menu = menu;
        this.nickWin = nickWin;
        this.nick = nick;

        ModelRozpocznij md = new ModelRozpocznij(wys,szer);
        kontrolerRozpocznij = new KontrolerRozpocznij(md);



        gameWindow = new GameWindow(wys,szer);
        gameStage = new Stage();
        gameStage.setScene(gameWindow.getScene());
        gameStage.centerOnScreen();
        gameStage.setTitle("Snake");
        gameStage.setResizable(false);
        kontrolerRozpocznij.setPoint(new Point(150,150));
        gameWindow.drawNormalPoint(150,150);
        gameStage.show();
        gameStage.setOnCloseRequest(windowEvent -> {
            menu.show();
            kontrolerRozpocznij.setWorking(false);
            gameStage.close();
        });
        gameWindow.setStages(gameStage, menu,kontrolerRozpocznij);

        timeThread.start();
        snakeThread.start();


        menu.hide();
        nickWin.hide();

    }


    public Point findPoint(List<Point> list,int X, int Y){
        Point result = null;
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getX() == X && list.get(i).getY() == Y){
                result = list.get(i);
                break;
            }
        }
        return result;
    }


    public void removePoint(List<Point> list,int X, int Y){
        int index = 0;
        for (int i = 0; i < list.size() ; i++) {
            if(list.get(i).getY() == Y && list.get(i).getX() == X) {
                index = i;
                break;
            }
        }
        list.remove(index);
    }

    public boolean checkPoint(List<Point> list, int X, int Y){
        boolean res = true;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getY() == Y && list.get(i).getX()==X){
                res = false;
                break;
            }
        }
        return res;
    }

}
