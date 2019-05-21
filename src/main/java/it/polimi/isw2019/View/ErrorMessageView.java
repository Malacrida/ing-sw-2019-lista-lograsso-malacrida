package it.polimi.isw2019.View;

import it.polimi.isw2019.Message.MoveMessage.*;

public class ErrorMessageView implements VisitorView{

    @Override
    public void visitSetupView(MoveMessage messageMove) {
    }

    @Override
    public void visitActionView(MoveMessage moveMessage) {

    }

    @Override
    public void errorMessageView(MoveMessage moveMessage) {
        System.out.println(((ErrorMessage)(moveMessage)).getErrorMessage());

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


}
