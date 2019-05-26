package it.polimi.isw2019.message.MoveMessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class UpdateMessage extends MoveMessage{

    private String gameBoard;
    private ArrayList<String> playerBoard;
    private ArrayList<String> weaponCard;
    private ArrayList<String> powerUpCard;
    private int temporaryScore;

    public UpdateMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }

    @Override
    public void visit(VisitorView visitorView) {

    }

    @Override
    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public void setNicknamePlayer(String nicknamePlayer) {
        this.nicknamePlayer = nicknamePlayer;
    }

    public String getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String gameBoard) {
        this.gameBoard = gameBoard;
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
}
