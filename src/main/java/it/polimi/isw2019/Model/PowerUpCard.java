package it.polimi.isw2019.Model;

public interface PowerUpCard {

    public String getName();

    public String getEffect();

    public int getId();

    public ColorCube getColor();

    public boolean checkState();

    public boolean isAllowed();
}
