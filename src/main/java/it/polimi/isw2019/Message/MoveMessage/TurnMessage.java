package it.polimi.isw2019.Message.MoveMessage;

import it.polimi.isw2019.View.VisitorView;

import java.util.ArrayList;

public class TurnMessage extends MoveMessage {
    private boolean firstTurn;
    private boolean startGame;
    private ArrayList<Integer> powerUpCard;

    public TurnMessage(String idMoveMessage, int idPlayer, boolean startGame){
        super(idMoveMessage,idPlayer);
        this.startGame = startGame;
    }



    public TurnMessage(String idMoveMessage, int idPlayer, boolean firstTurn, ArrayList<Integer> powerUpCard){
        super(idMoveMessage,idPlayer);
        this.firstTurn = firstTurn;
        this.powerUpCard = powerUpCard;
    }



    public boolean isFirstTurn(){
        return this.firstTurn;
    }
    public boolean isStartGame(){
        return this.startGame;
    }

    public ArrayList<Integer> getPowerUpCard(){
        return this.powerUpCard;
    }

    @Override
    public void visit(VisitorView visitorView) {

    }
}
