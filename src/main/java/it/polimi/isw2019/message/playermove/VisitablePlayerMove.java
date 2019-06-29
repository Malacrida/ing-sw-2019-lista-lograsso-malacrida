package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

public interface VisitablePlayerMove {

    public abstract void accept(VisitorController visitorController);

    public abstract void accept(NetworkHandlerVisitorInterface networkHandler);
}
