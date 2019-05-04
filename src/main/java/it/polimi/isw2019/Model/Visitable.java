package it.polimi.isw2019.Model;

import it.polimi.isw2019.Controller.StrategyAction;
import it.polimi.isw2019.Message.PlayerMove.PlayerMove;

public interface Visitable{
    public abstract void accept(StrategyAction visitorAction, PlayerMove playerMove);
    }

