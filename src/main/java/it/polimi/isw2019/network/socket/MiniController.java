package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.message.playermove.RunMove;
import it.polimi.isw2019.utilities.Observer;

public class MiniController implements Observer<PlayerMove> {
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
    }
}
