package it.polimi.isw2019.view;

import it.polimi.isw2019.message.MoveMessage.*;


public interface VisitorView{

    public abstract void visitSetupView(MoveMessage messageMove);
    public abstract void visitActionView(MoveMessage moveMessage);
    public abstract  void errorMessageView(MoveMessage moveMessage);
    public abstract void visitRun(RunMessage runMessage);
    public abstract void visitRunGrab(RunGrabMessage runGrabMessage);
    public abstract void visitReload(ReloadMessage reloadMessage);
    public abstract void visitTurnView(TurnMessage turnMessage);
}
