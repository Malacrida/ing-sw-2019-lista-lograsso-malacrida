package it.polimi.isw2019.view.ViewGui;

import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

public class GuiHandler extends Observable<PlayerMove> implements Observer<MoveMessage> {

    private int[][] move;

    @Override
    public void update(MoveMessage message) {

    }

    public void setMove (int i, String pos){
        // converitre la stinga
        //move[i][0]=
    }
}
