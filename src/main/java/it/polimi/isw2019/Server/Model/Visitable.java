package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Controller.StrategyAction;
import it.polimi.isw2019.Server.Message.PlayerMove.PlayerMove;

public interface Visitable{
    public abstract void accept(StrategyAction visitorAction, PlayerMove playerMove);
    }

