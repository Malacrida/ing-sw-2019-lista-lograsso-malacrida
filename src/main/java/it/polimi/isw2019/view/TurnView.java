package it.polimi.isw2019.view;

import it.polimi.isw2019.message.MoveMessage.*;

public class TurnView implements VisitorView {
    @Override
    public void visitSetupView(MoveMessage messageMove) {

    }

    @Override
    public void visitActionView(MoveMessage moveMessage) {

    }

    @Override
    public void errorMessageView(MoveMessage moveMessage) {

    }

    @Override
    public void visitRun(RunMessage runMessage) {

    }

    @Override
    public void visitRunGrab(RunGrabMessage runGrabMessage) {

    }

    @Override
    public void visitReload(ReloadMessage reloadMessage) {

    }

    @Override
    public void visitUpdateView(UpdateMessage updateMessage) {

    }

    @Override
    public void visitTurnView(TurnMessage turnMessage) {
          //  if(turnMessage.getNicknamePlayer().compareTo(view.getNickname())
                    // yourTurnView
        //else
                //othersTurnsView
    }
}
