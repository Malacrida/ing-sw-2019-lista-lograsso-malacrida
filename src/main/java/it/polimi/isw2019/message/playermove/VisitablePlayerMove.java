package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.exception.EndAction;
import it.polimi.isw2019.model.exception.EndSingleAction;

public interface VisitablePlayerMove {

    public abstract void accept(VisitorController visitorController) throws EndAction, EndSingleAction;
}
