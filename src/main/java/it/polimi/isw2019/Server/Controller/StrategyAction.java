package it.polimi.isw2019.Server.Controller;

import it.polimi.isw2019.Server.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Server.Model.Model;

public abstract class StrategyAction {
    private boolean notify;

    public abstract void visitModel(Model model, PlayerMove playerMove);

    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}
