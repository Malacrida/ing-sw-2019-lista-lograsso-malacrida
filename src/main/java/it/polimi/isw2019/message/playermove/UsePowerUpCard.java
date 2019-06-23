package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;

import java.util.ArrayList;

public class UsePowerUpCard extends PlayerMove {

    private InterfacePowerUpCard powerUpCardInterface;
    private ArrayList<String> cubes;
    private ArrayList<InterfacePowerUpCard> powerUpCardInterfaces;
    private int[][] coordinates;
    private boolean defend;
    private PlayerInterface playerToAttack;


    public UsePowerUpCard(String player, InterfacePowerUpCard powerUpCardInterface) {
        super(player);
        this.powerUpCardInterface = powerUpCardInterface;
    }

    @Override
    public void accept(VisitorController visitorController) {

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

    public PlayerInterface getPlayerToAttack() {
        return playerToAttack;
    }

    public void setPlayerToAttack(PlayerInterface playerToAttack) {
        this.playerToAttack = playerToAttack;
    }

    public InterfacePowerUpCard getPowerUpCardInterface() {
        return powerUpCardInterface;
    }

    public void setPowerUpCardInterface(InterfacePowerUpCard powerUpCardInterface) {
        this.powerUpCardInterface = powerUpCardInterface;
    }

    public ArrayList<String> getCubes() {
        return cubes;
    }

    public void setCubes(ArrayList<String> cubes) {
        this.cubes = cubes;
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCardInterfaces() {
        return powerUpCardInterfaces;
    }

    public void setPowerUpCardInterfaces(ArrayList<InterfacePowerUpCard> powerUpCardInterfaces) {
        this.powerUpCardInterfaces = powerUpCardInterfaces;
    }
}
