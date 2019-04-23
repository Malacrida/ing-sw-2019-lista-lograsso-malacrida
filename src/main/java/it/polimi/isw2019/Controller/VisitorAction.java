package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Model.Events.PlayerMove;
import it.polimi.isw2019.Model.Model;

public interface VisitorAction {
    public abstract void visitModel(Model model, PlayerMove playerMove);
}
