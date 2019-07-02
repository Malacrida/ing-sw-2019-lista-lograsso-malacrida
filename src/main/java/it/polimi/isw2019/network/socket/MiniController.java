package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.movemessage.RunMessage;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.message.playermove.RunMove;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

public class MiniController extends Observable<MoveMessage> implements Observer<PlayerMove> {
    @Override
    public void update(PlayerMove message) {
        message.accept(this);
    }

    public void readRun (RunMove runMove){
        System.out.println("evviva");
        System.out.println("player: " + runMove.getPlayer());
        for (int i=0; i<3; i ++){
            for (int j=0; j<2;j++){
                System.out.println(runMove.getMovement()[i][j]);
            }

        }
        System.out.println("creazione della move message");
        RunMessage runMessage = new RunMessage(runMove.getPlayer(), null, 2);
        notifyObservers(runMessage);
    }
}
