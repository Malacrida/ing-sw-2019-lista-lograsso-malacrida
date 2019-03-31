package it.polimi.isw2019.Model;

public interface WeaponCard {
    public String getName();

    public String getEffect();

    public int getID();

    public ColorCube getRechargecube();

    public ColorCube getColor();

    public boolean checkState();

    public boolean isAllowed();

}
