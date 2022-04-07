package Components;

public class Point {

    private int X;
    private int Y;

    public Point(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean checkCoordinates(int X, int Y){
        if(X == this.X && Y == this.Y){
            return true;
        }else
            return false;
    }
}
