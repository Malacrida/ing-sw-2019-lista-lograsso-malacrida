package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.GameBoardInterface;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class UpdateMessage extends MoveMessage {

    private GameBoardInterface gameBoard;
    private ArrayList<PlayerInterface> players;
    private String model;

    public UpdateMessage(String nicknamePlayer, GameBoardInterface gameBoard, ArrayList<PlayerInterface> players,boolean notifyAll) {
        super(nicknamePlayer,notifyAll);
        this.gameBoard = gameBoard;
        this.players = players;
    }

    public UpdateMessage(String nicknamePlayer, String model, GameBoardInterface gameBoard, ArrayList<PlayerInterface> players) {
        super(nicknamePlayer);
        this.model = model;
        this.gameBoard = gameBoard;
        this.players = players;
    }

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.visitUpdateView(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendUpdateView(this);
    }

    public GameBoardInterface getGameBoard() {
        return gameBoard;
    }

    public ArrayList<PlayerInterface> getPlayers() {
        return players;
    }

    @Override
    public int[] getFeaturesAvailable() {
        return new int[0];
    }

    @Override
    public void setFeaturesAvailable(int[] featuresAvailable) {

    }

    @Override
    public boolean isNotifyAll() {
        return super.isNotifyAll();
    }
}

