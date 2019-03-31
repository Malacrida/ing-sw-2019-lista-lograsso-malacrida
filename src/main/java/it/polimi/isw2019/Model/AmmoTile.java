package it.polimi.isw2019.Model;

public interface AmmoTile {

    public int getId();

    public Cube getCube();

    public boolean checkState();

    public boolean checkCard();

    public boolean isAllowed();
}
