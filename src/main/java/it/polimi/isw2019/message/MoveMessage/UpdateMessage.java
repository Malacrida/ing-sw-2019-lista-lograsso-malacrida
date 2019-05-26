package it.polimi.isw2019.message.MoveMessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class UpdateMessage extends MoveMessage{
    private String[][] gameBoard;


    private String[][] playerBoard;
    private int temporaryScore;

    public UpdateMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }

    @Override
    public void visit(VisitorView visitorView) {
            //visitorView.v
    }

    @Override
    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public void setNicknamePlayer(String nicknamePlayer) {
        this.nicknamePlayer = nicknamePlayer;
    }

    public String[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(String[][] playerBoard) {
        this.playerBoard = playerBoard;
    }

    public int getTemporaryScore() {
        return temporaryScore;
    }

    public void setTemporaryScore(int temporaryScore) {
        this.temporaryScore = temporaryScore;
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}