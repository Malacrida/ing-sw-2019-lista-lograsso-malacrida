package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.*;
import it.polimi.isw2019.Model.Model;

public abstract class StrategyAction {
    private boolean notify;

    public abstract void visitModel(Model model, PlayerMove playerMove);

    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}