package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

import java.util.ArrayList;

public class UsePowerUpCard extends PlayerMove {

    private int[] cubes;
    private int[][] coordinates;
    private boolean defend;
    //private PlayerInterface playerToAttack;


    public UsePowerUpCard(String player) {
        super(player);
    }

    @Override
    public void accept(VisitorController visitorController) {

    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendUsePowerUpCard(this);
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isDefend() {
        return defend;
    }

    public void setDefend(boolean defend) {
        this.defend = defend;
    }

    /*public PlayerInterface getPlayerToAttack() {
        return playerToAttack;
    }*/

    /*public void setPlayerToAttack(PlayerInterface playerToAttack) {
        this.playerToAttack = playerToAttack;
    }*/


    public int[] getCubes() {
        return cubes;
    }

    public void setCubes(int[] cubes) {
        this.cubes = cubes;
    }
}
