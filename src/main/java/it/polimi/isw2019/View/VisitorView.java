package it.polimi.isw2019.View;

import it.polimi.isw2019.Message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Message.MoveMessage.ReloadMessage;
import it.polimi.isw2019.Message.MoveMessage.RunGrabMessage;
import it.polimi.isw2019.Message.MoveMessage.RunMessage;

public interface VisitorView{

    public abstract void visitSetupView(MoveMessage messageMove);
    public abstract void visitActionView(MoveMessage moveMessage);
    public abstract  void errorMessageView(MoveMessage moveMessage);
    public abstract void visitRun(RunMessage runMessage);
    public abstract void visitRunGrab(RunGrabMessage runGrabMessage);
    public abstract void visitReload(ReloadMessage reloadMessage);
}
