package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

import java.util.ArrayList;

/**
 * player move send when player decide to use one power up card
 */

public class UsePowerUpCard extends PlayerMove {

    private int[][] coordinates;
    private int idPlayer;
    private boolean defend;
    private int positionPowerUp;

    public UsePowerUpCard(String player) {
        super(player);
    }

    public UsePowerUpCard(String player,int[][] coordinates, int idPlayer, boolean defend, int positionPowerUp) {
        super(player);
        this.coordinates = coordinates;
        this.idPlayer = idPlayer;
        this.defend = defend;
        this.positionPowerUp = positionPowerUp;

    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.usePowerUpCard(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendUsePowerUpCard(this);
    }

    /**
     * getter of coordinates
     * @return coordinates
     */

    public int[][] getCoordinates() {
        return coordinates;
    }

    /**
     * setter of coordinates
     * @param coordinates coordinates
     */

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isDefend() {
        return defend;
    }

    public void setDefend(boolean defend) {
        this.defend = defend;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    /**
     * getter of position of power up
     * @return position power uup
     */

    public int getPositionPowerUp() {
        return positionPowerUp;
    }

    /**
     * setter position of power up card
     * @param positionPowerUp power up card position
     */

    public void setPositionPowerUp(int positionPowerUp) {
        this.positionPowerUp = positionPowerUp;
    }
}
