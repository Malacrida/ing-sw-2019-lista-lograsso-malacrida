package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

public class TerminatorMove extends PlayerMove {

    private int[] coordinates;
    private boolean shootPeople;
    private int colorSpawn;
    private int[] idPlayerToShoot;

    public TerminatorMove(String player, int[] coordinates, boolean shootPeople, int colorSpawn, int[] idPlayerToShoot) {
        super(player);
        this.coordinates = coordinates;
        this.shootPeople = shootPeople;
        this.colorSpawn = colorSpawn;
        this.idPlayerToShoot = idPlayerToShoot;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isShootPeople() {
        return shootPeople;
    }

    public void setShootPeople(boolean shootPeople) {
        this.shootPeople = shootPeople;
    }

    public int getColorSpawn() {
        return colorSpawn;
    }

    public void setColorSpawn(int colorSpawn) {
        this.colorSpawn = colorSpawn;
    }

    public int[] getIdPlayerToShoot() {
        return idPlayerToShoot;
    }

    public void setIdPlayerToShoot(int[] idPlayerToShoot) {
        this.idPlayerToShoot = idPlayerToShoot;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.terminatorAction(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {

    }
}
