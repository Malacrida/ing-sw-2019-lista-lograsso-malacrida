package it.polimi.isw2019.Model;

import it.polimi.isw2019.Controller.VisitorModel;
import it.polimi.isw2019.Message.PlayerMove.PlayerMove;

public interface Visitable{
    public abstract void accept(VisitorModel visitorAction, PlayerMove playerMove);
    }

