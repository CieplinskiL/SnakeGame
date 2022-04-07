package Components;

import java.util.List;

public class KontrolerRozpocznij {

    ModelRozpocznij md;

    public KontrolerRozpocznij(ModelRozpocznij md){
        this.md = md;
    }

    public int getWys() {
        return md.getWys();
    }


    public int getSzer() {
       return  md.getSzer();
    }


    public boolean isWorking() {
        return md.isWorking();
    }

    public void setWorking(boolean working) {
        md.setWorking(working);
    }

    public List<Point> getOgolem() {
        return md.getOgolem();
    }


    public List<Point> getWolne() {
        return md.getWolne();
    }

    public void addWolne(Point wolne) {
        md.addWolne(wolne);
    }

    public List<Point> getZajete() {
        return md.getZajete();
    }

    public void addZajete(Point zajete) {
        md.addZajete(zajete);
    }

    public Point getPoint() {
        return md.getPoint();
    }

    public void setPoint(Point point) {
        md.setPoint(point);
    }

    public boolean isSuperPoint() {
        return md.isSuperPoint();
    }

    public void setSuperPoint(boolean superPoint) {
        md.setSuperPoint(superPoint);
    }

    public boolean isNotWall() {
        return md.isNotWall();
    }

    public void setNotWall(boolean notWall) {
        md.setNotWall(notWall);
    }

    public int getCounter() {
        return md.getCounter();
    }

    public void setCounter(int counter) {
        md.setCounter(counter);
    }

    public int getSpeed() {
        return md.getSpeed();
    }

    public void setSpeed(int speed) {
        md.setSpeed(speed);
    }


}



