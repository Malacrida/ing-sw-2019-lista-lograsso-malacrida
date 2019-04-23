package it.polimi.isw2019.Model;

import it.polimi.isw2019.Controller.VisitorAction;
import it.polimi.isw2019.Model.Events.PlayerMove;

public interface Visitable{
    public abstract void accept(VisitorAction visitorAction, PlayerMove playerMove);
    }

