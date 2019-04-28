package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.*;
import it.polimi.isw2019.Model.Model;

public interface VisitorModel{
    public abstract void visitModel(Model model,PlayerMove playerMove);

}
