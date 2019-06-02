package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public interface VisitablePlayerMove {

    public abstract void accept(VisitorController visitorController);
}
