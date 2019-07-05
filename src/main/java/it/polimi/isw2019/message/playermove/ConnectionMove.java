package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

/**
 * player move of connection
 */

public class ConnectionMove extends PlayerMove {

    int connection;

    public ConnectionMove(String nickname, int connection){
        super(nickname);
        this.connection= connection;
    }

    /**
     * getter of connection
     * @return connection
     */

    public int getConnection() {
        return connection;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.connectionPlayer(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendConnectionClient(this);
    }


}
