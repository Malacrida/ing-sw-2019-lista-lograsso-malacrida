package it.polimi.isw2019.Utilities;

public interface Observer {
    public void update();
    public <C> void update(C change);
}
