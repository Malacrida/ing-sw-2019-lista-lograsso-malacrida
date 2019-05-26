package it.polimi.isw2019.view;

import java.util.ArrayList;

public class StatusView{

    private String nicknamePlayer;
    private String actionHero;
    private String gameBoard;
    private ArrayList<String> playerBoard;
    private ArrayList<String> weaponCard;
    private ArrayList<String> powerUpCard;
    private int temporaryScore;




    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public void setNicknamePlayer(String nicknamePlayer) {
        this.nicknamePlayer = nicknamePlayer;
    }

    public String getActionHero() {
        return actionHero;
    }

    public ArrayList<String> getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(ArrayList<String> playerBoard) {
        this.playerBoard = playerBoard;
    }

    public ArrayList<String> getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(ArrayList<String> weaponCard) {
        this.weaponCard = weaponCard;
    }

    public ArrayList<String> getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(ArrayList<String> powerUpCard) {
        this.powerUpCard = powerUpCard;
    }

    public int getTemporaryScore() {
        return temporaryScore;
    }

    public void setTemporaryScore(int temporaryScore) {
        this.temporaryScore = temporaryScore;
    }

    public String getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setActionHero(String actionHero) {
        this.actionHero = actionHero;
    }
}
