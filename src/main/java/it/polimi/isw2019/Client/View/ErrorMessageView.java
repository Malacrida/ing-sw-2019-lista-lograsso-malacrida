package it.polimi.isw2019.Client.View;

import it.polimi.isw2019.Server.Message.MoveMessage.ErrorMessage;
import it.polimi.isw2019.Server.Message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Server.Message.PlayerMove.SetUpMove;

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


}
