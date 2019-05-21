package it.polimi.isw2019.Client.View;

import it.polimi.isw2019.Server.Message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Server.Message.MoveMessage.ReloadMessage;
import it.polimi.isw2019.Server.Message.MoveMessage.RunGrabMessage;
import it.polimi.isw2019.Server.Message.MoveMessage.RunMessage;
import it.polimi.isw2019.Server.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Server.Message.PlayerMove.SetUpMove;
import it.polimi.isw2019.Server.Utilities.Observable;

public interface VisitorView{

    public abstract void visitSetupView(MoveMessage messageMove);
    public abstract void visitActionView(MoveMessage moveMessage);
    public abstract  void errorMessageView(MoveMessage moveMessage);
    public abstract void visitRun(RunMessage runMessage);
    public abstract void visitRunGrab(RunGrabMessage runGrabMessage);
    public abstract void visitReload(ReloadMessage reloadMessage);
}
