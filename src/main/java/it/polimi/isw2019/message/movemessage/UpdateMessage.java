package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class UpdateMessage extends MoveMessage{

    private GameBoard gameBoard;
    private ArrayList<Player> players;

    public UpdateMessage(String nicknamePlayer, GameBoard gameBoard, ArrayList<Player> players) {
        super(nicknamePlayer);
        this.gameBoard = gameBoard;
        this.players = players;
    }

    @Override
    public void accept(VisitorView visitorview) {

    }
    public void getPlayerBoard(){

    }
}
