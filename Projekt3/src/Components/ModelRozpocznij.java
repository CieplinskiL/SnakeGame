package Components;

import java.util.LinkedList;
import java.util.List;

public class ModelRozpocznij {

    private int wys;
    private int szer;
    private boolean working = true;
    private List<Point> ogolem = new LinkedList<>();
    private List<Point> wolne = new LinkedList();
    private List<Point> zajete = new LinkedList<>();
    private Point point;
    private boolean superPoint = false;
    private boolean isNotWall = true;
    private int counter=0;
    private int speed = 200;


    public  ModelRozpocznij(int wys, int szer){
        this.wys = wys;
        this.szer = szer;

        for (int i = 0; i < wys; i++) {
            for (int j = 0; j < szer; j++) {
                Point tmp = new Point(j*50,i*50);
                wolne.add(tmp);
                ogolem.add(tmp);
            }
        }

        for (int i = 0; i < wolne.size() ; i++) {
            if(wolne.get(i).checkCoordinates(0,0)) {
                zajete.add(wolne.get(i));
                wolne.remove(i);
            }
        }

        }



    public int getWys() {
        return wys;
    }


    public int getSzer() {
        return szer;
    }


    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public List<Point> getOgolem() {
        return ogolem;
    }

    public List<Point> getWolne() {
        return wolne;
    }

    public void addWolne(Point p) {
        wolne.add(p);
    }


    public List<Point> getZajete() {
        return zajete;
    }

    public void addZajete(Point zajete) {
        this.zajete.add(zajete);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isSuperPoint() {
        return superPoint;
    }

    public void setSuperPoint(boolean superPoint) {
        this.superPoint = superPoint;
    }

    public boolean isNotWall() {
        return isNotWall;
    }

    public void setNotWall(boolean notWall) {
        isNotWall = notWall;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
