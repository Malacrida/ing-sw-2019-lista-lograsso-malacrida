package it.polimi.isw2019.model;

public class Position {

    int x;
    int y;
    ColorRoom colorRoom;

    Position (int x, int y, ColorRoom colorRoom){
        this.x=x;
        this.y=y;
        this.colorRoom=colorRoom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ColorRoom getColorRoom() {
        return colorRoom;
    }
}
